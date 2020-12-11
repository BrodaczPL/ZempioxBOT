package UserCommands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import Config.Config;

import java.io.BufferedReader;
import java.io.FileReader;

public class HelpCommand extends ListenerAdapter {
    EmbedBuilder eb = new EmbedBuilder();

public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
    if (event.getMessage().getContentRaw().equalsIgnoreCase(Config.prefix + "help") & !event.getAuthor().isBot()) {

        try {
            BufferedReader read = new BufferedReader(new FileReader(Config.helpsrc));
            String nextline;
            StringBuilder help = new StringBuilder();
            while ((nextline = read.readLine()) != null) {
                help.append(nextline).append("\n");
            }
            eb.setTitle("Lista komend")
                    .setDescription(help.toString()).build();
            event.getChannel().sendMessage(eb.build()).queue();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}}
