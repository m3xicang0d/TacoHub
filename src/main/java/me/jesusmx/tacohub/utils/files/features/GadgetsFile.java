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

public class GadgetsFile extends YamlConfiguration {

    private static GadgetsFile config;
    private File file;

    public GadgetsFile() {
        file = new File(TacoHub.getInstance().getDataFolder(), "cosmetics/gadgets.yml");
        if(!file.exists()) TacoHub.getInstance().saveResource("cosmetics/gadgets.yml", false);
        this.reload();
    }

    public static GadgetsFile getConfig() {
        if(config == null) {
            config = new GadgetsFile();
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
