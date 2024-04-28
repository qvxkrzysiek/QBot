package me.qvx;

import me.qvx.DTO.Config;
import me.qvx.Utils.Logger;

public class ConfigChecker {
    public static void checkConfig(Config config){
        if(config.getApiDiscordToken() == null || config.getApiDiscordToken().isEmpty()){
            Logger.error("Config api-discord-token is empty, unable to start program!");
            System.exit(1);
        }
        if(config.getAudioPlayer() != null){
            if(config.getAudioPlayer().getChatId() == null || config.getAudioPlayer().getChatId().isEmpty()){
                Logger.error("Config chat-id-music is empty, unable to start program!");
                System.exit(1);
            }
            Logger.info("Audio player config has been loaded successfully!");
        }
    }
}
