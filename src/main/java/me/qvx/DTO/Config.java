package me.qvx.DTO;

public class Config {
    private final String api_discord_token;
    private final AudioPlayer audio_player;

    public Config(String api_discord_token, AudioPlayer audio_player) {
        this.api_discord_token = api_discord_token;
        this.audio_player = audio_player;
    }

    public String getApiDiscordToken() {
        return api_discord_token;
    }
    public AudioPlayer getAudioPlayer() {
        return audio_player;
    }
}

