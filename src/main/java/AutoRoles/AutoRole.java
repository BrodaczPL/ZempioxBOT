package AutoRoles;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import Config.Config;

public class AutoRole extends ListenerAdapter {
    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event) {
        if (event.getChannel().getId().equals(Config.autoRoleChannel) & !event.getUser().isBot()) {
//autoYearsOld

        }


    }
}
