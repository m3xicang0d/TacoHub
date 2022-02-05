package me.jesusmx.tacohub.utils.files.menus;

import me.jesusmx.tacohub.TacoHub;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Developed by JesusMX
 * Project: TacoHub
 **/

public class HubSelectorFile extends YamlConfiguration {

    private File file;
    private static HubSelectorFile config;

    public HubSelectorFile() {
        file = new File(TacoHub.getInstance().getDataFolder(), "menus/hub-selector.yml");
        if(!file.exists()) TacoHub.getInstance().saveResource("menus/hub-selector.yml", false);
        this.reload();
    }

    public static HubSelectorFile getConfig() {
        if(config == null) {
            config = new HubSelectorFile();
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


