package me.qvx.commands;

import lombok.Getter;
import me.qvx.commands.play.PlayCommand;
import me.qvx.commands.setmusicchannel.SetMusicChannel;
import net.dv8tion.jda.api.hooks.EventListener;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CommandsManager {

    private static CommandsManager instance;
    private final List<EventListener> listenerAdapters = new ArrayList<>();
    private final List<Command<?>> commands = new ArrayList<>();

    private CommandsManager() {
        registerCommands();
    }

    public static CommandsManager getInstance() {
        if (instance == null) {
            instance = new CommandsManager();
        }
        return instance;
    }

    private void registerCommands() {
        PlayCommand playCommand  = new PlayCommand();
        SetMusicChannel setMusicChannel = new SetMusicChannel();

        listenerAdapters.add(playCommand);
        listenerAdapters.add(setMusicChannel);
        commands.add(playCommand);
        commands.add(setMusicChannel);
    }

    public <T> Command<T> getCommand(Class<? extends Command<T>> commandClass) {
        for (Command<?> command : commands) {
            if (commandClass.isInstance(command)) {
                return (Command<T>) command;
            }
        }
        return null;
    }

}
