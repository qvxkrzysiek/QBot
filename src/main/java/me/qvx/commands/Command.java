package me.qvx.commands;

import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public interface Command<T> {

    String getCommandName();
    String getCommandDescription();
    SlashCommandData getSlashCommandData();
    void execute(T context);

}
