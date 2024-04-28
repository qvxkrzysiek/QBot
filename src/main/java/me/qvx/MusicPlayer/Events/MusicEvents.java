package me.qvx.MusicPlayer.Events;

import me.qvx.MusicPlayer.Handlers.MusicChatHandler;
import me.qvx.MusicPlayer.PlayerManager;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

import java.net.URL;

public class MusicEvents extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e){
        if (!e.getChannel().getId().equals(MusicChatHandler.getMusicTextChannel().getId())) {
            return;
        }
        if(e.getAuthor().isBot()) {
            return;
        }
        if(!e.getMember().getVoiceState().inAudioChannel()){
            e.getChannel().sendMessage("Musisz byc na kanale glosowym!").queue();
        }
        if(!e.getGuild().getAudioManager().isConnected()){
            final AudioManager audioManager = e.getGuild().getAudioManager();
            final VoiceChannel memberChannel = (VoiceChannel) e.getMember().getVoiceState().getChannel();

            audioManager.openAudioConnection(memberChannel);
        }

        String link = e.getMessage().getContentRaw();

        if(!isValidURL(link)){
            link = "ytsearch:" + link + " audio";
        }

        PlayerManager.getINSTANCE().loadAndPlay(e,link);
        e.getMessage().delete().queue();
    }

    private boolean isValidURL(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
