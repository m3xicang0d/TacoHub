package me.jesusmx.tacohub.cosmetics.menu.armor;

import me.jesusmx.tacohub.cosmetics.button.armor.ArmorButton;
import me.jesusmx.tacohub.cosmetics.button.armor.ArmorRemoveButton;
import me.jesusmx.tacohub.utils.CC;
import me.jesusmx.tacohub.utils.bukkit.menu.Button;
import me.jesusmx.tacohub.utils.bukkit.menu.Menu;
import me.jesusmx.tacohub.utils.files.cosmetics.ArmorsFile;
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
        for(String s : config.getConfigurationSection("MENU.ARMORS").getKeys(false)) {
            buttons.put(config.getInt("MENU.ARMORS." + s + ".SLOT") - 1, new ArmorButton(s));
        }
        buttons.put(config.getInt("MENU.REMOVE_ARMOR.SLOT"), new ArmorRemoveButton());
        return buttons;
    }
}
