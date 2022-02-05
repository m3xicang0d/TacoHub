package me.jesusmx.tacohub.utils.files.normal;

import me.jesusmx.tacohub.TacoHub;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Developed by JesusMX
 * Project: TacoHub
 **/

public class MessagesFile extends YamlConfiguration {

    private File file;
    private static MessagesFile config;

    public MessagesFile() {
        file = new File(TacoHub.getInstance().getDataFolder(), "messages.yml");
        if(!file.exists()) TacoHub.getInstance().saveResource("messages.yml", false);
        this.reload();
    }

    public static MessagesFile getConfig() {
        if(config == null) {
            config = new MessagesFile();
        }
        return config;
    }

    public void save() {
        try {
            super.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        try {
            super.load(this.file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
