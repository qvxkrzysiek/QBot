package me.qvx;

import me.qvx.DTO.AudioPlayer;
import me.qvx.DTO.Config;
import me.qvx.Utils.Logger;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigLoader {

    private final Config config;

    public ConfigLoader() {
        Logger.info("Loading config...");

        File configFile = tryLoadConfig();
        config = loadConfig(configFile);
        ConfigChecker.checkConfig(config);

        Logger.info("Config loaded successfully!");
    }

    public Config getConfig() {
        return config;
    }

    private File tryLoadConfig() {
        String jarDir = System.getProperty("user.dir");
        Path configPath = Paths.get(jarDir, "config.properties");

        File configFile = new File(configPath.toString());

        if (!configFile.exists()) {
            Logger.warning("Config file does not exist, creating new one...");
            try {
                InputStream inputStream = ConfigLoader.class.getResourceAsStream("/config.properties");
                FileOutputStream outputStream = new FileOutputStream(configFile);
                byte[] buf = new byte[1024];
                int len;
                while (true) {
                    assert inputStream != null;
                    if (!((len = inputStream.read(buf)) > 0)) break;
                    outputStream.write(buf, 0, len);
                }

                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                Logger.error("Failed to load config file from resources!");
                Logger.error(e.getMessage());
            }
            Logger.info("Config file created successfully!");
            Logger.info("Please fill up created config file before running again");
            Logger.info("Exiting program...");
            System.exit(0);
        }

        return configFile;
    }

    private Config loadConfig(File file) {

        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(file)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String discordApiToken = properties.getProperty("api-discord-token", "");
        boolean audioPlayerEnabled = Boolean.parseBoolean(properties.getProperty("module-audioplayer-bool", "false"));
        String musicChatId = properties.getProperty("chat-id-music", "");
        String musicSecretChatId = null;//properties.getProperty("chat-id-music-secret", "");

        AudioPlayer audioPlayer = null;
        if(audioPlayerEnabled) {
            audioPlayer = new AudioPlayer(musicChatId, musicSecretChatId);
        }

        return new Config(discordApiToken, audioPlayer);
    }

}
