package me.jesusmx.tacohub.selector.hub.menu;

import me.jesusmx.tacohub.selector.hub.button.HubButton;
import me.jesusmx.tacohub.utils.CC;
import me.jesusmx.tacohub.utils.bukkit.ItemBuilder;
import me.jesusmx.tacohub.utils.bukkit.menu.Button;
import me.jesusmx.tacohub.utils.bukkit.menu.Menu;
import me.jesusmx.tacohub.utils.files.menus.HubSelectorFile;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * Developed by JesusMX
 * Project: TacoHub
 **/

public class HubSelectorMenu extends Menu {

    private FileConfiguration config = HubSelectorFile.getConfig();

    @Override
    public String getTitle(Player player) {
        return CC.translate(HubSelectorFile.getConfig().getString("HUB-SELECTOR.TITLE"));
    }

    @Override
    public int size() {
        return HubSelectorFile.getConfig().getInt("HUB-SELECTOR.ROWS") * 9;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        for(String s : config.getConfigurationSection("HUB-SELECTOR.ITEMS").getKeys(false)) {
            buttons.put(config.getInt("HUB-SELECTOR.ITEMS." + s + ".SLOT"), new HubButton(s));
        }
        return buttons;
    }

    @Override
    public boolean usePlaceholder() {
        return config.getBoolean("HUB-SELECTOR.REFILL-GLASS.ENABLED");
    }

    @Override
    public ItemStack getPlaceholderItem(Player player) {
        return new ItemBuilder(Material.STAINED_GLASS_PANE).name(" ").data(config.getInt("HUB-SELECTOR.REFILL-GLASS.DATA")).build();
    }
}

