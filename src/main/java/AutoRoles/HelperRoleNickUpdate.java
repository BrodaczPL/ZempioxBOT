package AutoRoles;

import jdk.nashorn.internal.runtime.ListAdapter;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import sun.awt.ConstrainableGraphics;

import java.util.List;
import Config.Config;

public class HelperRoleNickUpdate extends ListenerAdapter {

    public void onGuildMemberRoleAdd(GuildMemberRoleAddEvent event){
        if(event.getRoles().contains(event.getGuild().getRoleById(Config.helperRole))) {
            String nick = event.getUser().getName();
            event.getMember().modifyNickname("[POM] " + nick).complete();}
    }
    public void onGuildMemberRoleRemove(GuildMemberRoleRemoveEvent event){
        if(event.getRoles().contains(event.getGuild().getRoleById(Config.helperRole))) {
            String nick = event.getUser().getName().replace("[POM] ", "");
            event.getMember().modifyNickname(nick).complete();}


    }
}