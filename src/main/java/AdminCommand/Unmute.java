package AdminCommand;

import jdk.nashorn.internal.runtime.ListAdapter;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;
import Config.Config;

public class Unmute extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
    String user2 = event.getMessage().getContentRaw().replace(Config.prefix + "unmute ", "").replace("<@", "").replace(">", "").replace("!", "");
        if(event.getMessage().getContentRaw().startsWith(Config.prefix + "unmute") &
                Objects.requireNonNull(event.getGuild().getMemberById(user2)).getRoles().contains(event.getGuild().getRoleById(Config.muteRole))){
            String nick = Objects.requireNonNull(event.getGuild().getMemberById(user2)).getEffectiveName();
            Objects.requireNonNull(event.getGuild().getTextChannelById(Config.debugChannel)).sendMessage("`" + event.getAuthor().getName() + "` usunął wyciszenie użytkownika `" + nick + "`!").queue();
            event.getGuild().removeRoleFromMember(user2, Objects.requireNonNull(event.getGuild().getRoleById(Config.muteRole))).complete();
        }





}}
