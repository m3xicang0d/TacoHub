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

public class ParticlesFile extends YamlConfiguration {

    private File file;
    private static ParticlesFile config;

    public ParticlesFile() {
        file = new File(TacoHub.getInstance().getDataFolder(), "cosmetics/hats.yml");
        if(!file.exists()) TacoHub.getInstance().saveResource("cosmetics/hats.yml", false);
        this.reload();
    }

    public static ParticlesFile getConfig() {
        if(config == null) {
            config = new ParticlesFile();
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
