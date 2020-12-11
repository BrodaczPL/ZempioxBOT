package Stats;

import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import Config.Config;

public class JoinStatsChannel extends ListenerAdapter {
public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event){
    if(!event.getUser().isBot()){
        String value = event.getUser().getName();
        event.getGuild().getVoiceChannelById(Config.statsJoinChannel).getManager().setName("Ostatni: " + value).complete();
    }
}}
