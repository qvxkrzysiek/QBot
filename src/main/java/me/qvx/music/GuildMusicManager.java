package me.qvx.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import lombok.Getter;
import net.dv8tion.jda.api.entities.Guild;

@Getter
public class GuildMusicManager {

    private final TrackScheduler scheduler;
    private final AudioPlayerSendHandler sendHandler;
    private final GuildMusicChatHandler guildMusicChatHandler;

    public GuildMusicManager(Guild guild, AudioPlayerManager audioPlayerManager) {
        AudioPlayer audioPlayer = audioPlayerManager.createPlayer();

        scheduler = new TrackScheduler(audioPlayer);
        audioPlayer.addListener(scheduler);
        sendHandler = new AudioPlayerSendHandler(audioPlayer);

        guildMusicChatHandler = new GuildMusicChatHandler(guild);
    }

}
