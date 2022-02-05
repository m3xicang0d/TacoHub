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

public class ConfigFile extends YamlConfiguration {

    private File file;
    private static ConfigFile config;

    public ConfigFile() {
        file = new File(TacoHub.getInstance().getDataFolder(), "config.yml");
        if(!file.exists()) TacoHub.getInstance().saveResource("config.yml", false);
        this.reload();
    }

    public static ConfigFile getConfig() {
        if(config == null) {
            config = new ConfigFile();
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
