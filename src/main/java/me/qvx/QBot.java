package me.qvx;

import lombok.Getter;
import me.qvx.dto.Settings;
import me.qvx.utils.ConfigProcessor;
import me.qvx.commands.Command;
import me.qvx.commands.CommandsManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class QBot {

    @Getter
    private static Settings settings;

    public static void main(String[] args) throws Exception  {

        ConfigProcessor configProcessor = new ConfigProcessor(args);
        if (!configProcessor.validateConfigValues()) return;

        settings = configProcessor.getSettings();

        CommandsManager commandsManager = CommandsManager.getInstance();

        JDA jda = JDABuilder.createDefault(configProcessor.getDiscordBotToken())
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .enableCache(CacheFlag.VOICE_STATE)
                .build().awaitReady();

        for(Object listenerAdapter : commandsManager.getListenerAdapters()){
            jda.addEventListener(listenerAdapter);
        }

        for (Command<?> command : commandsManager.getCommands()) {
            jda.upsertCommand(command.getSlashCommandData()).queue();
        }

    }

}

