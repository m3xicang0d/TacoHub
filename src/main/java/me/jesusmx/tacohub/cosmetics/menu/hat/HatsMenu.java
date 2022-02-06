package me.jesusmx.tacohub.cosmetics.menu.hat;

import me.jesusmx.tacohub.cosmetics.button.hat.HatButton;
import me.jesusmx.tacohub.cosmetics.button.hat.HatRemoveButton;
import me.jesusmx.tacohub.utils.CC;
import me.jesusmx.tacohub.utils.bukkit.ItemBuilder;
import me.jesusmx.tacohub.utils.bukkit.menu.Button;
import me.jesusmx.tacohub.utils.bukkit.menu.Menu;
import me.jesusmx.tacohub.utils.files.cosmetics.HatsFile;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author JesusMX
 * @Project TacoHub
 **/

public class HatsMenu extends Menu {

    private final FileConfiguration config = HatsFile.getConfig();

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
        for(String s : config.getConfigurationSection("MENU.HATS").getKeys(false)) {
            buttons.put(config.getInt("MENU.HATS." + s + ".SLOT"), new HatButton(s));
        }
        buttons.put(config.getInt("MENU.REMOVE_HAT.SLOT"), new HatRemoveButton());
        return buttons;
    }

    @Override
    public boolean usePlaceholder() {
        return config.getBoolean("MENU.REFILL-GLASS.ENABLED");
    }

    @Override
    public ItemStack getPlaceholderItem(Player player) {
        return new ItemBuilder(Material.STAINED_GLASS_PANE)
                .name(" ")
                .data(config.getInt("MENU.REFILL-GLASS.DATA"))
                .build();
    }
}
