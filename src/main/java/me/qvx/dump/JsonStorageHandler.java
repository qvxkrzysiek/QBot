package me.qvx.dump;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonStorageHandler {

    private final File file;
    private final ObjectMapper objectMapper;

    public JsonStorageHandler(String fileName) {
        this.file = new File(fileName);
        this.objectMapper = new ObjectMapper();
    }

    public Map<String, GuildData> load() {
        if (file.exists()) {
            try {
                return objectMapper.readValue(file, new TypeReference<Map<String, GuildData>>() {});
            } catch (IOException e) {
                System.err.println("Błąd podczas wczytywania danych z pliku: " + e.getMessage());
            }
        }
        return new HashMap<>(); // Zwróć pustą mapę jeśli plik nie istnieje lub błąd
    }

    public void save(Map<String, GuildData> data) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, data);
        } catch (IOException e) {
            System.err.println("Błąd podczas zapisu danych do pliku: " + e.getMessage());
        }
    }
}
