package me.jesusmx.tacohub.utils.files.features;

import me.jesusmx.tacohub.TacoHub;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Developed by JesusMX
 * Project: TacoHub
 **/

public class HotbarFile extends YamlConfiguration {

    private File file;
    private static HotbarFile config;

    public HotbarFile() {
        file = new File(TacoHub.getInstance().getDataFolder(), "features/hotbar.yml");
        if(!file.exists()) TacoHub.getInstance().saveResource("features/hotbar.yml", false);
        this.reload();
    }

    public static HotbarFile getConfig() {
        if(config == null) {
            config = new HotbarFile();
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
