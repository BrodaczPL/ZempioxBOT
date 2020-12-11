import Config.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import Config.Config;

public class Embed extends ListenerAdapter {
EmbedBuilder eb = new EmbedBuilder();
public void onGuildMessageReceived(GuildMessageReceivedEvent event){
    if(event.getMessage().getContentRaw().startsWith(Config.prefix + "embed")){

        String msg = event.getMessage().getContentRaw().replace(Config.prefix + "embed", "");
        String msg_color = StringUtils.substringBetween(msg, "{", "}");
        int msg_color_1 = Integer.valueOf( msg_color.substring( 1, 3 ), 16 );
        int msg_color_2 = Integer.valueOf( msg_color.substring( 3, 5 ), 16 );
        int msg_color_3 = Integer.valueOf( msg_color.substring( 5, 7 ), 16 );
        String msg_title = StringUtils.substringBetween(msg, "|", "|");
        String msg_text = StringUtils.substringBetween(msg, "<", ">");

            eb.setTitle(msg_title)
                    .setColor(new Color(msg_color_1, msg_color_2, msg_color_3))
                    .setFooter(event.getAuthor().getAsTag(), event.getAuthor().getAvatarUrl())
                    .setDescription(msg_text);

            event.getChannel().sendMessage(eb.build()).queue();






    }






}
}
