package me.jesusmx.tacohub.cosmetics.menu.armor;

import me.jesusmx.tacohub.utils.CC;
import me.jesusmx.tacohub.utils.bukkit.menu.Button;
import me.jesusmx.tacohub.utils.bukkit.menu.Menu;
import me.jesusmx.tacohub.utils.files.cosmetics.ArmorsFile;
import me.jesusmx.tacohub.utils.files.features.CosmeticsFile;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author UKry
 * @Project TacoHub
 **/

public class ArmorsMenu extends Menu {

    private final FileConfiguration config = ArmorsFile.getConfig();

    @Override
    public String getTitle(Player player) {
        return CC.translate(config.getString("MENU.TITLE"));
    }

    @Override
    public int size() {
        return config.getInt("MENU.ROWS") * 9;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        return buttons;
    }
}
