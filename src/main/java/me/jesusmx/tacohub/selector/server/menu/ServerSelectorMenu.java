package me.jesusmx.tacohub.selector.server.menu;

import me.jesusmx.tacohub.selector.server.button.ServerButton;
import me.jesusmx.tacohub.utils.CC;
import me.jesusmx.tacohub.utils.bukkit.ItemBuilder;
import me.jesusmx.tacohub.utils.bukkit.menu.Button;
import me.jesusmx.tacohub.utils.bukkit.menu.Menu;
import me.jesusmx.tacohub.utils.files.menus.ServerSelectorFile;
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

public class ServerSelectorMenu extends Menu {

    private final FileConfiguration config = ServerSelectorFile.getConfig();

    @Override
    public String getTitle(Player player) {
        return CC.translate(ServerSelectorFile.getConfig().getString("SERVER-SELECTOR.TITLE"));
    }

    @Override
    public int size() {
        return ServerSelectorFile.getConfig().getInt("SERVER-SELECTOR.ROWS") * 9;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        for(String s : config.getConfigurationSection("SERVER-SELECTOR.ITEMS").getKeys(false)) {
            buttons.put(config.getInt("SERVER-SELECTOR.ITEMS." + s + ".SLOT"), new ServerButton(s));
        }
        return buttons;
    }

    @Override
    public boolean usePlaceholder() {
        return config.getBoolean("SERVER-SELECTOR.REFILL-GLASS.ENABLED");
    }

    @Override
    public ItemStack getPlaceholderItem(Player player) {
        return new ItemBuilder(Material.STAINED_GLASS_PANE).name(" ").data(config.getInt("SERVER-SELECTOR.REFILL-GLASS.DATA")).build();
    }
}
