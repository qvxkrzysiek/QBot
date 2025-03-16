package me.qvx.commands.play;

import net.dv8tion.jda.api.entities.Member;

public record PlayCommandContext(String title, Member requester) {
}
