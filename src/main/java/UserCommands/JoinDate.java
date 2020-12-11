package UserCommands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.awt.*;
import java.text.SimpleDateFormat;
import Config.Config;

public class JoinDate extends ListenerAdapter{
        private final EmbedBuilder eb = new EmbedBuilder();
        public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event){
            if(event.getMessage().getContentRaw().startsWith(Config.prefix + "info")){
                String playerid = event.getMessage().getContentRaw().replace(Config.prefix + "info <@", "").replace(">", "").replace("!", "");
                String date1 = event.getGuild().getMemberById(playerid).getTimeJoined().toString().replace("T", " ").substring(0, 19);
                String playername = event.getGuild().getMemberById(playerid).getUser().getName();
                long x = (Long.parseLong(playerid) >> 22) + 1420070400000L;
                String y = new SimpleDateFormat("yyyy-MM-mm HH:mm:mm").format(x);
                String msg1 = ("Utworzenie konta na discordzie: " + "``" + y + "``");
                String msg2 = ("Dołączenie do kanału: " + "``" + date1 + "``");
                OnlineStatus online = event.getGuild().getMemberById(playerid).getOnlineStatus();
                String msg3 = ("Status: " + "``" + online + "``");
                eb.setTitle("Informacje o graczu " + playername + ":")
                        .addField("Na Discordzie", msg1, false)
                        .addField("Na serwerze", msg2, false)
                        .addField("Status", msg3, false)
                        .setColor(Color.lightGray);
                event.getChannel().sendMessage(eb.build()).queue();
            }
            eb.clear();
        }
    }


