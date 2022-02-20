package me.jesusmx.tacohub.pvpmode.config;

import me.jesusmx.tacohub.TacoHub;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Developed by JesusMX
 * Project: TacoHub
 **/

public class PvPModeFile extends YamlConfiguration {

    private File file;
    private static PvPModeFile config;

    public PvPModeFile() {
        file = new File(TacoHub.getInstance().getDataFolder(), "features/pvp.yml");
        if(!file.exists()) TacoHub.getInstance().saveResource("features/pvp.yml", false);
        this.reload();
    }

    public static PvPModeFile getConfig() {
        if(config == null) {
            config = new PvPModeFile();
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
