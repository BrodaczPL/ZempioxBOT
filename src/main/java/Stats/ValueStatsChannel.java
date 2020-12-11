package Stats;

import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import Config.Config;

public class ValueStatsChannel extends ListenerAdapter {
public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event){
    if(!event.getUser().isBot()){
        int value = event.getGuild().getMemberCount();
        event.getGuild().getVoiceChannelById(Config.statsValueChannel).getManager().setName("Ilość osób: " + value).complete();
    }
}
    public void onGuildMemberLeave(@NotNull GuildMemberLeaveEvent event){
        if(!event.getUser().isBot()){
            int value = event.getGuild().getMemberCount();
            event.getGuild().getVoiceChannelById(Config.statsValueChannel).getManager().setName("Ilość osób: " + value).complete();
        }
    }
    public void onGuildMemberRemove(@NotNull GuildMemberRemoveEvent event){
        if(!event.getUser().isBot()){
            int value = event.getGuild().getMemberCount();
            event.getGuild().getVoiceChannelById(Config.statsValueChannel).getManager().setName("Ilość osób: " + value).complete();
        }
    }
}
