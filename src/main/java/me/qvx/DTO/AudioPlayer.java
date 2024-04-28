package me.qvx.DTO;

public class AudioPlayer {
    private final String chat_id;
    private final String chat_id_secret;

    public AudioPlayer(String chat_id, String chat_id_secret) {
        this.chat_id = chat_id;
        this.chat_id_secret = chat_id_secret;
    }

    public String getChatId() {return chat_id;}
    public String getChatIdSecret() {return chat_id_secret;}
}
