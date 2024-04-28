package me.qvx;

import me.qvx.DTO.Config;
import me.qvx.MusicPlayer.Events.MusicEvents;
import me.qvx.MusicPlayer.Events.MusicReactionClick;
import me.qvx.MusicPlayer.Handlers.MusicChatHandler;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;


public class Main {

    public static void main(String[] args) throws Exception{

        ConfigLoader configLoader = new ConfigLoader();
        Config config = configLoader.getConfig();

        JDA jda = JDABuilder.createDefault(config.getApiDiscordToken())
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .enableCache(CacheFlag.VOICE_STATE)
                .build().awaitReady();

        if(config.getAudioPlayer() != null){
            TextChannel musicTextChannel = jda.getTextChannelById(config.getAudioPlayer().getChatId());
            MusicChatHandler.init(musicTextChannel);
            jda.addEventListener(new MusicEvents());
            jda.addEventListener(new MusicReactionClick());
        }

    }

}
