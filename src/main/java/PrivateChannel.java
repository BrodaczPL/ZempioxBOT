import Config.Config;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;
import Config.Config;

public class PrivateChannel extends ListenerAdapter {
        @Override
        public void onGuildVoiceJoin(@Nonnull GuildVoiceJoinEvent event) {
            //create channel
            String userName = event.getMember().getUser().getName();

            List<String> channels = Objects.requireNonNull(event.getGuild().getCategoryById(Config.privateChannelCat)).getVoiceChannels().stream().map(GuildChannel::getName).collect(Collectors.toList());
            if(event.getChannelJoined()==event.getGuild().getVoiceChannelById(Config.privateChannelCreate)){
                if(!channels.contains(userName))
                    new Timer().schedule(new TimerTask() {
                                             @Override
                                             public void run() {
                                                 VoiceChannel new_voice_channel = Objects.requireNonNull(event.getGuild().getCategoryById(Config.privateChannelCat)).createVoiceChannel(userName).complete();
                                                 event.getGuild().moveVoiceMember(event.getMember(),new_voice_channel).complete();
                                             }
                                         },
                            1000);
                else
                    new Timer().schedule(new TimerTask() {
                                             @Override
                                             public void run() {
                                                 event.getGuild().kickVoiceMember(event.getMember()).complete();
                                             }
                                         },
                            1000);
            }
        }

        @Override
        public void onGuildVoiceLeave(@Nonnull GuildVoiceLeaveEvent event) {
            List<VoiceChannel> voiceChannels = Objects.requireNonNull(event.getGuild().getCategoryById(Config.privateChannelCat)).getVoiceChannels();
            if (voiceChannels.contains(event.getChannelLeft())
                    && event.getChannelLeft().getMembers().isEmpty()
                    && event.getChannelLeft()!=event.getGuild().getVoiceChannelById(Config.privateChannelCreate))
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        event.getChannelLeft().delete().complete();
                    }
                }, 3 * 1000);

        }

        @Override
        public void onGuildVoiceMove(@Nonnull GuildVoiceMoveEvent event) {
            String creator = event.getMember().getUser().getId();
            String userName = event.getMember().getUser().getName();
            List<VoiceChannel> voiceChannels = Objects.requireNonNull(event.getGuild().getCategoryById(Config.privateChannelCat)).getVoiceChannels();
            //delete channel
           if(voiceChannels.contains(event.getChannelLeft())
                    && event.getChannelLeft().getMembers().isEmpty()
                    && event.getChannelLeft()!=event.getGuild().getVoiceChannelById(Config.privateChannelCreate))
               new Timer().schedule(new TimerTask() {
                                        @Override
                                        public void run() {
                                            event.getChannelLeft().delete().complete();
                                        }
                                    }
                                    ,3000);

            //create channel
            List<String> channels = Objects.requireNonNull(event.getGuild().getCategoryById(Config.privateChannelCat)).getVoiceChannels().stream().map(GuildChannel::getName).collect(Collectors.toList());
            if(event.getChannelJoined()==event.getGuild().getVoiceChannelById(Config.privateChannelCreate)){
                if(!channels.contains(userName))
                    new Timer().schedule(new TimerTask() {
                                             @Override
                                             public void run() {
                                                 VoiceChannel new_voice_channel = Objects.requireNonNull(event.getGuild().getCategoryById(Config.privateChannelCat)).createVoiceChannel(userName).complete();
                                                 event.getGuild().moveVoiceMember(event.getMember(),new_voice_channel).complete();
                                             }
                                         },
                            1000);
                else {
                    new Timer().schedule(new TimerTask() {
                                             @Override
                                             public void run() { 
                                                 event.getChannelLeft().delete().complete();

                                             }
                                         },
                            3000);
                    if (!channels.contains(userName) && event.getChannelJoined() == event.getGuild().getVoiceChannelById(Config.privateChannelCreate))
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                VoiceChannel new_voice_channel = Objects.requireNonNull(event.getGuild().getCategoryById(Config.privateChannelCat)).createVoiceChannel(userName).complete();
                                event.getGuild().moveVoiceMember(event.getMember(), new_voice_channel).complete();
                            }
                        }, 4000);
                }
            }
        }
    }
