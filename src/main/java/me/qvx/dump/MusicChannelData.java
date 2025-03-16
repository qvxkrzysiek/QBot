package me.qvx.dump;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public record MusicChannelData(
    TextChannel textChannel,
    Message playerMessage,
    Message queueMessage
) {

    public MusicChannelDataRaw getRaw(){
        return new MusicChannelDataRaw(
                textChannel.getId(),
                playerMessage.getId(),
                queueMessage.getId());
    }

}
