package me.qvx.music;

import me.qvx.dump.GuildData;
import me.qvx.dump.GuildDataManager;
import me.qvx.dump.MusicChannelData;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

public class GuildMusicChatHandler {

    private MusicChannelData musicChannelData;
    private GuildData guildData;

    public GuildMusicChatHandler(Guild guild){
        guildData = GuildDataManager.getInstance().getGuildData(guild.getId());

        if(guildData != null){
            musicChannelData = guildData.getMusicChannelDataRaw().retrieveMusicChannelData(guild);
        } else {
            guildData = new GuildData();
            GuildDataManager.getInstance().setGuildData(guild.getId(), guildData);
        }
    }

    public void setMusicChannel(TextChannel channel){
        resetMusicChannel();

        Button pauseButton = Button.primary("pause", "⏸️ Pause/Resume");
        Button shuffleButton = Button.primary("shuffle", "🔀 Shuffle");
        Button nextButton = Button.primary("next", "⏭️ Next");
        Button repeatButton = Button.primary("repeat", "🔁 Repeat");
        Button stopButton = Button.danger("stop", "🛑 Stop");

        channel.sendMessage("🎶 **Music Player Controls** 🎶")
                .setActionRow(pauseButton, shuffleButton, nextButton, repeatButton, stopButton)
                .queue(message -> {
                    channel.sendMessage("📜 **Music Queue** 📜\nNo songs in queue.")
                            .queue(queueMessage -> {
                                musicChannelData = new MusicChannelData(
                                        channel,
                                        message,
                                        queueMessage
                                );
                                guildData.setMusicChannelDataRaw(musicChannelData.getRaw());
                                GuildDataManager.getInstance().save();
                            });
                });
    }

    private void resetMusicChannel() {
        if(musicChannelData == null) return;

        musicChannelData.playerMessage().delete().queue();
        musicChannelData.queueMessage().delete().queue();

        musicChannelData = null;
        guildData.setMusicChannelDataRaw(null);

        GuildDataManager.getInstance().save();
    }
}
