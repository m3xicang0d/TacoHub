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

public class ArmorsFile extends YamlConfiguration {

    private File file;
    private static ArmorsFile config;

    public ArmorsFile() {
        file = new File(TacoHub.getInstance().getDataFolder(), "cosmetics/armors.yml");
        if(!file.exists()) TacoHub.getInstance().saveResource("cosmetics/armors.yml", false);
        this.reload();
    }

    public static ArmorsFile getConfig() {
        if(config == null) {
            config = new ArmorsFile();
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
