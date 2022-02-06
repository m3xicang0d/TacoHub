package me.jesusmx.tacohub.cosmetics.button.hat;

import lombok.AllArgsConstructor;
import me.jesusmx.tacohub.TacoHub;
import me.jesusmx.tacohub.utils.CC;
import me.jesusmx.tacohub.utils.bukkit.ItemBuilder;
import me.jesusmx.tacohub.utils.bukkit.menu.Button;
import me.jesusmx.tacohub.utils.files.cosmetics.HatsFile;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.metadata.FixedMetadataValue;

/**
 * @Author JesusMX
 * @Project TacoHub
 **/

@AllArgsConstructor
public class HatButton extends Button {

    private String s;
    private final FileConfiguration config = HatsFile.getConfig();

    @Override
    public void click(Player player, int slot, ClickType clickType, int hotbarButton) {
        String path = "MENU.HATS." + s + ".";
        ItemStack item;
        if(config.getBoolean(path + "SKULL.ENABLED")) {
            item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
            SkullMeta skull = (SkullMeta) item.getItemMeta();
            skull.setOwner(config.getString(path + "SKULL.OWNER"));
            skull.setDisplayName(CC.translate(config.getString(path + "NAME")));
            item.setItemMeta(skull);
        } else {
            item = getItem(player);
            ItemMeta meta = item.getItemMeta();
            item.setItemMeta(meta);
        }
        player.getInventory().setHelmet(item);
        player.updateInventory();
        player.setMetadata("HAT", new FixedMetadataValue(TacoHub.getInstance(), s));
    }

    @Override
    public ItemStack getItem(Player player) {
        String path = "MENU.HATS." + s + ".";
        //System.out.println(path);
        if(config.getBoolean(path + "SKULL.ENABLED")) {
            ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
            SkullMeta skull = (SkullMeta) item.getItemMeta();
            skull.setOwner(config.getString(path + "SKULL.OWNER"));
            skull.setDisplayName(CC.translate(config.getString(path + "NAME")));
            skull.setLore(config.getStringList(path + "LORE"));
            item.setItemMeta(skull);
            return item;
        } else {
            return new ItemBuilder(Material.valueOf(config.getString(path + "ITEM")))
                    .name(config.getString(path + "NAME"))
                    .lore(config.getStringList(path + "LORE"))
                    .data(config.getInt(path + "DATA"))
                    .build();
        }
    }
}
