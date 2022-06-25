package me.jesusmx.tacohub.cosmetics.menu.gadgets;

import me.jesusmx.tacohub.cosmetics.button.gadgets.SnowBallButton;
import me.jesusmx.tacohub.cosmetics.button.gadgets.TeleportBowButton;
import me.jesusmx.tacohub.utils.CC;
import me.jesusmx.tacohub.utils.bukkit.ItemBuilder;
import me.jesusmx.tacohub.utils.bukkit.menu.Button;
import me.jesusmx.tacohub.utils.bukkit.menu.Menu;
import me.jesusmx.tacohub.utils.files.features.CosmeticsFile;
import me.jesusmx.tacohub.utils.files.features.GadgetsFile;
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

public class GadgetsMenu extends Menu {

    private final FileConfiguration config = GadgetsFile.getConfig();

    @Override
    public String getTitle(Player player) {
        return CC.translate(config.getString("MENU.TITLE"));
    }

    @Override
    public int size() {
        return 27;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        //11, 15
        Map<Integer, Button> buttons = new HashMap<>();
        buttons.put(config.getInt("MENU.BOW.SLOT"), new TeleportBowButton());
        buttons.put(config.getInt("MENU.SNOW.SLOT"), new SnowBallButton());
        return buttons;
    }

    @Override
    public boolean usePlaceholder() {
        return config.getBoolean("MENU.REFILL-GLASS.ENABLED");
    }

    @Override
    public ItemStack getPlaceholderItem(Player player) {
        return new ItemBuilder(Material.STAINED_GLASS_PANE).name(" ").data(config.getInt("MENU.REFILL-GLASS.DATA")).build();
    }
}