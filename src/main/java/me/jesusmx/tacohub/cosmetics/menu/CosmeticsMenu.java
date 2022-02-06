package me.jesusmx.tacohub.cosmetics.menu;

import me.jesusmx.tacohub.cosmetics.button.hat.HatMenuButton;
import me.jesusmx.tacohub.utils.CC;
import me.jesusmx.tacohub.utils.bukkit.ItemBuilder;
import me.jesusmx.tacohub.utils.bukkit.menu.Button;
import me.jesusmx.tacohub.utils.bukkit.menu.Menu;
import me.jesusmx.tacohub.utils.files.features.CosmeticsFile;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class CosmeticsMenu extends Menu {

    private final FileConfiguration config = CosmeticsFile.getConfig();

    @Override
    public String getTitle(Player player) {
        return CC.translate(config.getString("COSMETICS.TITLE"));
    }

    @Override
    public int size() {
        return config.getInt("COSMETICS.ROWS") * 9;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        buttons.put(config.getInt("COSMETICS.HAT.SLOT"), new HatMenuButton());
        return buttons;
    }

    @Override
    public boolean usePlaceholder() {
        return config.getBoolean("COSMETICS.REFILL-GLASS.ENABLED");
    }

    @Override
    public ItemStack getPlaceholderItem(Player player) {
        return new ItemBuilder(Material.STAINED_GLASS_PANE).name(" ").data(config.getInt("COSMETICS.REFILL-GLASS.DATA")).build();
    }
}
