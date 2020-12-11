import Config.Config;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.Async;

import java.sql.Time;
import java.util.Collections;
import java.util.Timer;

public class RadioChannel extends ListenerAdapter {
public void onGuildMessageReceived(GuildMessageReceivedEvent event){
    if(!event.getChannel().equals(event.getGuild().getTextChannelById(Config.musicChannel))){
            if(event.getMessage().getContentRaw().startsWith(">p")
            || event.getMessage().getContentRaw().startsWith("!p"))
    {
String msgid = event.getMessage().getId();
event.getChannel().deleteMessageById(msgid).complete();
event.getChannel().sendMessage("Aby odtworzyć muzykę z BOTa, należy użyć kanału `radioteka`!").queue();



    }

    }}

}
