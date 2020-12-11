import  AdminCommand.ClearChat;
import AdminCommand.Mute;
import AdminCommand.Unmute;
import AutoRoles.*;
import Config.Config;
import Logs.DeleteMsg;
import Stats.JoinStatsChannel;
import Stats.ValueStatsChannel;
import UserCommands.HelpCommand;
import UserCommands.JoinDate;
import UserCommands.PremiumCommand;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

public class MainBOT
{

    public static void main(String[] args) throws Exception {
        new MainBOT();
    }

    public MainBOT() throws Exception {
        new JDABuilder(Config.token).setActivity(Activity.listening("Zempioxa eza jednego"))
                .addEventListeners(new GuildJoinRole())
                .addEventListeners(new ClearChat())
                .addEventListeners(new PrivateChannel())
                .addEventListeners(new ModRoleNickUpdate())
                .addEventListeners(new JoinDate())
                .addEventListeners(new Mute())
                .addEventListeners(new Unmute())
                .addEventListeners(new PremiumCommand())
                .addEventListeners(new RadioChannel())
                .addEventListeners(new HelperRoleNickUpdate())
                .addEventListeners(new TechRoleNickUpdate())
                .addEventListeners(new DeleteMsg())
                .addEventListeners(new AutoRole())
                .addEventListeners(new AdminRoleNickUpdate())
                .addEventListeners(new Embed())
                .addEventListeners(new JoinStatsChannel())
                .addEventListeners(new ValueStatsChannel())
                .addEventListeners(new HelpCommand()).build();

    }

}