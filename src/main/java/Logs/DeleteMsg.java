package Logs;

import Config.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageDeleteEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DeleteMsg extends ListenerAdapter {
    EmbedBuilder eb = new EmbedBuilder();

    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String id_send = event.getMessage().getId();
        String text_send = event.getMessage().getContentRaw();

    }

    public void onGuildMessageDelete(GuildMessageDeleteEvent event){
        String id_deleted = event.getMessageId();
        String tekst_deleted = event.getChannel().getHistory().getMessageById(id_deleted).getContentRaw();
        eb.setDescription("Wiadomość została usunięta!")
                .addField("Tekst", tekst_deleted, false)
                .addField("ID", id_deleted, false);
        event.getGuild().getTextChannelById(Config.debugChannel).sendMessage(eb.build()).queue();
}


}
