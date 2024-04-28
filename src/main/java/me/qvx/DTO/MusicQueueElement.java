package me.qvx.DTO;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class MusicQueueElement {

    private AudioTrack audioTrack;
    private MessageReceivedEvent messageEvent;

    private long trackDuration;
    private long trackPosition;

    private String title;
    private String audioAuthor;
    private String uri;
    private String identifier;

    private String messageAuthor;
    public MusicQueueElement(AudioTrack audioTrack, MessageReceivedEvent messageEvent) {
        this.audioTrack = audioTrack;
        this.messageEvent = messageEvent;
        this.trackDuration = audioTrack.getDuration();
        this.trackPosition = audioTrack.getPosition();
        this.title = audioTrack.getInfo().title;
        this.audioAuthor = audioTrack.getInfo().author;
        this.uri = audioTrack.getInfo().uri;
        this.identifier = audioTrack.getIdentifier();
        this.messageAuthor = messageEvent.getAuthor().getName();
    }

    public AudioTrack getAudioTrack() {
        return audioTrack;
    }

    public MessageReceivedEvent getMessageEvent() {
        return messageEvent;
    }

    public long getTrackDuration() {
        return trackDuration;
    }

    public long getTrackPosition() {
        return trackPosition;
    }

    public String getTitle() {
        return title;
    }

    public String getAudioAuthor() {
        return audioAuthor;
    }

    public String getUri() {
        return uri;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getMessageAuthor() {
        return messageAuthor;
    }
}
