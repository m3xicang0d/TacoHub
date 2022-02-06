package me.jesusmx.tacohub.selector.subselector.menu;

import lombok.*;
import me.jesusmx.tacohub.selector.subselector.button.BackButton;
import me.jesusmx.tacohub.selector.subselector.button.SubServerButton;
import me.jesusmx.tacohub.utils.CC;
import me.jesusmx.tacohub.utils.bukkit.ItemBuilder;
import me.jesusmx.tacohub.utils.bukkit.menu.Button;
import me.jesusmx.tacohub.utils.bukkit.menu.Menu;
import me.jesusmx.tacohub.utils.files.menus.SubSelectorFile;
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

@AllArgsConstructor
public class SubSelectorMenu extends Menu {

    private String server;
    private final FileConfiguration config = SubSelectorFile.getConfig();

    @Override
    public String getTitle(Player player) {
        return CC.translate(config.getString("SUB-SELECTOR.TITLE").replace("%server%", server));
    }

    @Override
    public int size() {
        return config.getInt("SUB-SELECTOR.ROWS") * 9;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        for(String s : config.getConfigurationSection("SUB-SELECTOR." + server).getKeys(false)) {
            buttons.put(config.getInt("SUB-SELECTOR." + server + "." + s + ".SLOT"), new SubServerButton(server, s));
        }
        if (config.getBoolean("SUB-SELECTOR.BACK_BUTTON.ENABLED"))
        buttons.put(size() - 1, new BackButton());
        return buttons;
    }

    @Override
    public boolean usePlaceholder() {
        return config.getBoolean("SUB-SELECTOR.REFILL-GLASS.ENABLED");
    }

    @Override
    public ItemStack getPlaceholderItem(Player player) {
        return new ItemBuilder(Material.STAINED_GLASS_PANE).name(" ").data(config.getInt("SUB-SELECTOR.REFILL-GLASS.DATA")).build();
    }
}
