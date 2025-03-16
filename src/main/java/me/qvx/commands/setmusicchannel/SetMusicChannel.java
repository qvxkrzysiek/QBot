package me.qvx.commands.setmusicchannel;

import me.qvx.commands.Command;
import me.qvx.music.PlayerManager;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import org.jetbrains.annotations.NotNull;

public class SetMusicChannel extends ListenerAdapter implements Command<Object> {

    @Override
    public String getCommandName() {
        return "setmusicchannel";
    }

    @Override
    public String getCommandDescription() {
        return "Sets the music channel";
    }

    @Override
    public SlashCommandData getSlashCommandData() {
        return Commands.slash(getCommandName(), getCommandDescription())
                .addOption(OptionType.CHANNEL, "channel", "The channel to set", true);
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (!event.isFromGuild()) return;
        if (!event.getName().equals(getCommandName())) return;

        var channel = event.getOption("channel").getAsChannel();

        if (channel instanceof TextChannel textChannel) {
            PlayerManager.getInstance().getGuildMusicManager(event.getGuild()).getGuildMusicChatHandler().setMusicChannel(textChannel);
            event.reply("Kanał tekstowy ustawiony na: " + textChannel.getName()).queue();
        } else {
            event.reply("Proszę wybrać kanał tekstowy!").queue();
        }
    }

    @Override
    public void execute(Object context) {

    }
}
