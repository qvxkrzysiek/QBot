package me.qvx.MusicPlayer.Events;

import me.qvx.MusicPlayer.Handlers.MusicChatHandler;
import me.qvx.MusicPlayer.PlayerManager;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;

public class MusicReactionClick extends ListenerAdapter {

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent e) {
        if(e.getUser().isBot()) return;
        if(e.getMessageId().equals(MusicChatHandler.getEmbedMessage().getId())){
            if(e.getReaction().getEmoji().asUnicode().getAsCodepoints().equals("U+25b6")){
                PlayerManager.getINSTANCE().getMusicManager(Objects.requireNonNull(e.getGuild())).scheduler.pauseOrResume();
                e.getReaction().removeReaction(e.getUser()).queue();
            }
            if(e.getReaction().getEmoji().asUnicode().getAsCodepoints().equals("U+23e9")){
                PlayerManager.getINSTANCE().getMusicManager(Objects.requireNonNull(e.getGuild())).scheduler.nextTrack();
                e.getReaction().removeReaction(e.getUser()).queue();
            }
            if(e.getReaction().getEmoji().asUnicode().getAsCodepoints().equals("U+1f501")){
                PlayerManager.getINSTANCE().getMusicManager(Objects.requireNonNull(e.getGuild())).scheduler.cycleRepeat();
                e.getReaction().removeReaction(e.getUser()).queue();
            }
            if(e.getReaction().getEmoji().asUnicode().getAsCodepoints().equals("U+1f500")){
                PlayerManager.getINSTANCE().getMusicManager(Objects.requireNonNull(e.getGuild())).scheduler.shuffle();
                e.getReaction().removeReaction(e.getUser()).queue();
            }
            if(e.getReaction().getEmoji().asUnicode().getAsCodepoints().equals("U+23f9")){
                e.getGuild().getAudioManager().closeAudioConnection();
                MusicChatHandler.reinit();
                PlayerManager.destroyINSTANCE();
                e.getReaction().removeReaction(e.getUser()).queue();
            }
        }
    }
}
