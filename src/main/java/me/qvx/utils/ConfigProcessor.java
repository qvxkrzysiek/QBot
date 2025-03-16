package me.qvx.utils;

import lombok.Getter;
import me.qvx.dto.Settings;

import java.net.HttpURLConnection;
import java.net.URL;

public class ConfigProcessor {

    @Getter
    private String discordBotToken;

    private boolean isInDebugMode = false;

    public ConfigProcessor(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-t") && i + 1 < args.length) {
                discordBotToken = args[i + 1];
            }
            if (args[i].equals("-d")) {
                isInDebugMode = true;
            }
        }
    }

    public boolean validateConfigValues(){
        if(!validateDiscordDeveloperToken()) return false;
        return true;
    }

    public Settings getSettings() {
        return new Settings(isInDebugMode);
    }

    private boolean validateDiscordDeveloperToken() {
        if (discordBotToken == null || discordBotToken.isEmpty()) {
            Logger.error("No token provided. Please provide a valid token.");
            return false;
        }
        HttpURLConnection connection = null;
        try {
            URL url = new URL("https://discord.com/api/v10/users/@me");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bot " + discordBotToken);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                Logger.info("Discord bot token loaded successfully.");
                return true;
            } else if (responseCode == 401) {
                Logger.error("Invalid Discord bot token (Unauthorized).");
            } else {
                Logger.error("Failed to validate Discord bot token. HTTP response code: " + responseCode);
            }
        } catch (Exception e) {
            if (isInDebugMode) Logger.debug(e.getMessage());
            Logger.error("Error during token validation.");
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return false;
    }

}
