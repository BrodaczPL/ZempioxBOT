package AutoRoles;

import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.util.Objects;
import Config.Config;

public class GuildJoinRole extends ListenerAdapter {
    private String mb;
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        if (!event.getUser().isBot()) {
            mb = event.getUser().getId();
            event.getGuild().addRoleToMember(mb, Objects.requireNonNull(event.getGuild().getRoleById(Config.userRole))).complete();
        }
        if (event.getUser().isBot()) {
            mb = event.getUser().getId();
            event.getGuild().addRoleToMember(mb, Objects.requireNonNull(event.getGuild().getRoleById(Config.botRole))).complete();
        }
    }}