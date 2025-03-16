package me.qvx.dump;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class GuildDataManager {

    private static GuildDataManager instance;

    private final JsonStorageHandler storageHandler;
    private final Map<String, GuildData> data;

    public static GuildDataManager getInstance() {
        if (instance == null) {
            instance = new GuildDataManager();
        }
        return instance;
    }

    private GuildDataManager() {
        this.storageHandler = new JsonStorageHandler("GuildsData.json");
        this.data = new HashMap<>(storageHandler.load());
    }

    public void setGuildData(String guildId, GuildData guildData) {
        data.put(guildId, guildData);
        save();
    }

    @Nullable
    public GuildData getGuildData(String guildId) {
        return data.getOrDefault(guildId,null);
    }

    public void save() {
        storageHandler.save(data);
    }

}
