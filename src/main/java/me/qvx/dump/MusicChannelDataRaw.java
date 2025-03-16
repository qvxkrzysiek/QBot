package me.qvx.dump;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public record MusicChannelDataRaw(
        String musicChannelId,
        String musicPlayerMessageId,
        String musicQueueMessageId
) {

    public MusicChannelData retrieveMusicChannelData(Guild guild) {
        TextChannel channel = guild.getTextChannelById(musicChannelId);
        if (channel == null) return null;

        Message playerMessage;
        Message queueMessage;

        try {
            playerMessage = channel.retrieveMessageById(musicPlayerMessageId).complete();
            queueMessage = channel.retrieveMessageById(musicQueueMessageId).complete();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return new MusicChannelData(channel, playerMessage, queueMessage);
    }
}
