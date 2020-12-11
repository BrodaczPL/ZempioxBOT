package AutoRoles;

import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import Config.Config;

public class TechRoleNickUpdate extends ListenerAdapter {

    public void onGuildMemberRoleAdd(GuildMemberRoleAddEvent event){
        if(event.getRoles().contains(event.getGuild().getRoleById(Config.techRole))) {
            String nick = event.getUser().getName();
            event.getMember().modifyNickname("[TECH] " + nick).complete();}
    }
    public void onGuildMemberRoleRemove(GuildMemberRoleRemoveEvent event){
        if(event.getRoles().contains(event.getGuild().getRoleById(Config.techRole))) {
            String nick = event.getUser().getName().replace("[TECH] ", "");
            event.getMember().modifyNickname(nick).complete();}


    }
}
