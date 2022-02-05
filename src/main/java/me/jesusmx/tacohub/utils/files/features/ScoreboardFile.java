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

public class ScoreboardFile extends YamlConfiguration {

    private File file;
    private static ScoreboardFile config;

    public ScoreboardFile() {
        file = new File(TacoHub.getInstance().getDataFolder(), "features/scoreboard.yml");
        if(!file.exists()) TacoHub.getInstance().saveResource("features/scoreboard.yml", false);
        this.reload();
    }

    public static ScoreboardFile getConfig() {
        if(config == null) {
            config = new ScoreboardFile();
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
