package AdminCommand;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import sun.applet.Main;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import Config.Config;

public class ClearChat extends ListenerAdapter {
    EmbedBuilder eb = new EmbedBuilder();
public void onGuildMessageReceived(GuildMessageReceivedEvent event){
    if(event.getMessage().getContentRaw().startsWith(Config.prefix + "clear")){
        int repmsg = Integer.parseInt(event.getMessage().getContentRaw().replace(Config.prefix, "").replace("clear ", ""));
        if(repmsg >= 2 && 100 >= repmsg){
            List<Message> his = event.getChannel().getHistory().retrievePast(repmsg).complete();
        event.getChannel().deleteMessages(his).complete();
        event.getChannel().sendMessage(repmsg + " wiadomości zostało usuniętych!").queue();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = new Date();



            List<Message> his2 = event.getChannel().getHistory().retrievePast(2).complete();
        event.getChannel().deleteMessages(his2).complete();
        eb.setTitle("Usunięto wiadomości")
                .setColor(Color.BLUE)
                .setFooter(sd.format(date) + " || " + event.getAuthor().getAsTag())
                .addField("Ilość wiadomości", "Usunięto " + repmsg + " wiadomości", false)
                .addField("Kanał", event.getChannel().getName(), false);

        event.getGuild().getTextChannelById(Config.debugChannel).sendMessage(eb.build()).queue();}
        if(repmsg > 100){event.getChannel().sendMessage("Ilość wiadomości do usunięcia powinna być większa od `2` i mniejsza od `100`!").queue();}
        if(repmsg < 2){event.getChannel().sendMessage("Ilość wiadomości do usunięcia powinna być większa od `2` i mniejsza od `100`!").queue();}
    }
    if(event.getMessage().getContentRaw().equalsIgnoreCase(Config.prefix + "clear")){event.getChannel().sendMessage("Prawidłowe użycie: /clear <ilość wiadomości>!").queue();}


}

}
