package UserCommands;


import Config.Config;
import Config.JSONconfig;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.json.JSONException;
import org.json.JSONObject;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class PremiumCommand extends ListenerAdapter {
    private final EmbedBuilder messageEmbed = new EmbedBuilder();
    private void reset(){
        messageEmbed.setTitle(null)
                .setDescription(null)
                .setColor(Color.YELLOW)
                .clear();

    }
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent ctx) {
        TextChannel channel = ctx.getChannel();
        List<String> history_names = new ArrayList<>();
        JSONObject json;
        String name1;
        List<JSONObject> json_list = null;
        StringBuilder stringBuilder = new StringBuilder();

        if (ctx.getMessage().getContentRaw().startsWith(Config.prefix + "premium")){
            name1 = ctx.getMessage().getContentRaw().replace(Config.prefix + "premium ", "");
            reset();
            try {
                json = JSONconfig.readJsonFromUrl("https://api.mojang.com/users/profiles/minecraft/" + name1);
            } catch (IOException e) {
                EmbedBuilder message = messageEmbed.setTitle("Gracz o nicku " + name1 + " nie istnieje")
                        .setColor(Color.RED);
                channel.sendMessage(message.build()).queue();
                return;
            }

            Object uuid = json.get("id");

            try {
                json_list = readJsonFromUrlToNames("https://api.mojang.com/user/profiles/" + uuid + "/names");
            } catch (IOException e) {
                e.printStackTrace();
            }

            String newfield = "";
            for(int i = 0; i< Objects.requireNonNull(json_list).size(); i++){
                history_names.add((String)json_list.get(i).get("name"));
            }
            stringBuilder.append(newfield);
            history_names.forEach(s->stringBuilder.append("- ").append(s).append("\n"));
            EmbedBuilder message = messageEmbed.setTitle("Profil gracza: " + name1)
                    .setColor(Color.YELLOW)
                    .setImage("https://minotar.net/avatar/" + uuid + "/100")
                    .addField("Nick: ",  name1, false)
                    .addField("UUID", uuid.toString(), false)
                    .addField("Historia nicków: ", String.valueOf(stringBuilder), false);
            channel.sendMessage(message.build()).queue();
            reset();

        }

    }




    private  List<JSONObject> readJsonFromUrlToNames(String url) throws IOException, JSONException {
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String[] jsonText = readAll(rd).replace("[", "").replace("]", "").split("},");
            for (int i = 0; i < jsonText.length - 1; i++) {
                jsonText[i] += "}";
            }
            List<JSONObject> json = new ArrayList<>();
            for (String s : jsonText) {
                json.add(new JSONObject(s));
            }
            return json;
        }
    }

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}