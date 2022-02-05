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


public class ServerSelectorFile extends YamlConfiguration {

    private File file;
    private static ServerSelectorFile config;

    public ServerSelectorFile() {
        file = new File(TacoHub.getInstance().getDataFolder(), "menus/server-selector.yml");
        if(!file.exists()) TacoHub.getInstance().saveResource("menus/server-selector.yml", false);
        this.reload();
    }

    public static ServerSelectorFile getConfig() {
        if(config == null) {
            config = new ServerSelectorFile();
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
