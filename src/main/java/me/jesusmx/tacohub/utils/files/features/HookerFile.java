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

public class HookerFile extends YamlConfiguration {

    private File file;
    private static HookerFile config;

    public HookerFile() {
        file = new File(TacoHub.getInstance().getDataFolder(), "features/hooker.yml");
        if(!file.exists()) TacoHub.getInstance().saveResource("features/hooker.yml", false);
        this.reload();
    }

    public static HookerFile getConfig() {
        if(config == null) {
            config = new HookerFile();
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
