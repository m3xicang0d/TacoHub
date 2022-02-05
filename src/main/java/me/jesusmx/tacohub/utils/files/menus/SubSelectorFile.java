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
public class SubSelectorFile extends YamlConfiguration {

    private File file;
    private static SubSelectorFile config;

    public SubSelectorFile() {
        file = new File(TacoHub.getInstance().getDataFolder(), "menus/sub-selector.yml");
        if(!file.exists()) TacoHub.getInstance().saveResource("menus/sub-selector.yml", false);
        this.reload();
    }

    public static SubSelectorFile getConfig() {
        if(config == null) {
            config = new SubSelectorFile();
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


