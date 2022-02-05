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

public class CosmeticsFile extends YamlConfiguration {

    private File file;
    private static CosmeticsFile config;

    public CosmeticsFile() {
        file = new File(TacoHub.getInstance().getDataFolder(), "features/cosmetics.yml");
        if(!file.exists()) TacoHub.getInstance().saveResource("features/cosmetics.yml", false);
        this.reload();
    }

    public static CosmeticsFile getConfig() {
        if(config == null) {
            config = new CosmeticsFile();
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
