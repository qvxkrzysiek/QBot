package me.qvx.commands.play;

import me.qvx.commands.Command;
import me.qvx.musicplayer.PlayerManager;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.managers.AudioManager;
import org.jetbrains.annotations.NotNull;

import java.net.URL;

public class PlayCommand extends ListenerAdapter implements Command<PlayCommandContext> {
    @Override
    public String getCommandName() {
        return "play";
    }

    @Override
    public String getCommandDescription() {
        return "Plays the music";
    }

    @Override
    public SlashCommandData getSlashCommandData() {
        return Commands.slash(getCommandName(), getCommandDescription())
                .addOption(OptionType.STRING,"url","Link url",true);
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (!event.isFromGuild()) return;
        if (!event.getName().equals(getCommandName())) return;

        if(!event.getMember().getVoiceState().inAudioChannel()){
            event.getChannel().sendMessage("Musisz byc na kanale glosowym!").queue();
        }
        if(!event.getGuild().getAudioManager().isConnected()){
            final AudioManager audioManager = event.getGuild().getAudioManager();
            final VoiceChannel memberChannel = (VoiceChannel) event.getMember().getVoiceState().getChannel();

            audioManager.openAudioConnection(memberChannel);
        }
        String link = event.getOption("url").getAsString();

        if(!isValidURL(link)){
            link = "ytsearch:" + link + " audio";
        }

        PlayerManager.getInstance().loadAndPlay(event.getGuild(),link);
    }

    @Override
    public void execute(PlayCommandContext context) {

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
