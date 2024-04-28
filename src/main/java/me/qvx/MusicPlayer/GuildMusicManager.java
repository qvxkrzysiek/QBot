package me.qvx.MusicPlayer;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import me.qvx.MusicPlayer.Handlers.AudioPlayerSendHandler;
import me.qvx.MusicPlayer.Handlers.MusicChatHandler;

public class GuildMusicManager {

    public final AudioPlayer audioPlayer;
    public final TrackScheduler scheduler;
    public final AudioPlayerSendHandler sendHandler;
    public final MusicChatHandler musicChatHandler;

    public GuildMusicManager(AudioPlayerManager manager) {
        this.audioPlayer = manager.createPlayer();
        this.musicChatHandler = new MusicChatHandler();
        this.scheduler = new TrackScheduler(this.audioPlayer, this.musicChatHandler);
        this.audioPlayer.addListener(this.scheduler);
        this.sendHandler = new AudioPlayerSendHandler(this.audioPlayer);
    }

    public AudioPlayerSendHandler getSendHandler(){
        return this.sendHandler;
    }
}
