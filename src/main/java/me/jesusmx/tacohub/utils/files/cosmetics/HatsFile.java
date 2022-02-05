package me.jesusmx.tacohub.utils.files.cosmetics;

import me.jesusmx.tacohub.TacoHub;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Developed by JesusMX
 * Project: TacoHub
 **/

public class HatsFile extends YamlConfiguration {

    private File file;
    private static HatsFile config;

    public HatsFile() {
        file = new File(TacoHub.getInstance().getDataFolder(), "cosmetics/hats.yml");
        if(!file.exists()) TacoHub.getInstance().saveResource("cosmetics/hats.yml", false);
        this.reload();
    }

    public static HatsFile getConfig() {
        if(config == null) {
            config = new HatsFile();
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
