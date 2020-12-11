package AdminCommand;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;
import Config.Config;

public class Mute extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String user = event.getMessage().getContentRaw().replace(Config.prefix + "mute ", "").replace("<@", "").replace(">", "").replace("!", "");

        if(event.getMessage().getContentRaw().startsWith(Config.prefix + "mute") &
                !Objects.requireNonNull(event.getGuild().getMemberById(user)).getRoles().contains(event.getGuild().getRoleById(Config.muteRole))){
           String nick = Objects.requireNonNull(event.getGuild().getMemberById(user)).getEffectiveName();
           Objects.requireNonNull(event.getGuild().getTextChannelById(Config.debugChannel)).sendMessage("`" + event.getAuthor().getName() + "` wyciszył użytkownika `" + nick + "`!").queue();
           event.getGuild().addRoleToMember(user, Objects.requireNonNull(event.getGuild().getRoleById(Config.muteRole))).complete();
        }







    }
}
