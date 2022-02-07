package me.jesusmx.tacohub.cosmetics.button.armor;

import io.github.fxmxgragfx.api.item.ArmorBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import me.jesusmx.tacohub.utils.CC;
import me.jesusmx.tacohub.utils.bukkit.ItemBuilder;
import me.jesusmx.tacohub.utils.bukkit.menu.Button;
import me.jesusmx.tacohub.utils.files.cosmetics.ArmorsFile;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

/**
 * @Author UKry
 * @Project TacoHub
 **/

@Getter
public class ArmorButton extends Button {

    private final String armor;
    private final FileConfiguration config = ArmorsFile.getConfig();
    private final String path;

    public ArmorButton(String armor) {
        this.armor = armor;
        this.path = "MENU.ARMORS." + armor + ".";
    }

    @Override
    public void click(Player player, int slot, ClickType clickType, int hotbarButton) {
        if(!player.hasPermission(config.getString(path + "PERMISSION"))) {
            player.sendMessage(CC.RED + "You no have permissions!");
            return;
        }
        player.getInventory().setHelmet(
                new ArmorBuilder(this, "HELMET")
                        .build()
        );
        player.getInventory().setChestplate(
                new ArmorBuilder(this, "CHESTPLATE")
                        .build()
        );
        player.getInventory().setLeggings(
                new ArmorBuilder(this, "LEGGINGS")
                        .build()
        );
        player.getInventory().setBoots(
                new ArmorBuilder(this, "BOOTS")
                        .build()
        );
        player.updateInventory();
    }

    @Override
    public ItemStack getItem(Player player) {
        ItemBuilder builder = new ItemBuilder(Material.LEATHER_HELMET)
                .data(config.getInt(path + "ICON.DATA"))
                .name(config.getString(path + "ICON.NAME"));
        if(player.hasPermission(config.getString(path + "PERMISSION"))) {
            builder.lore(config.getStringList(path + "ICON.LORE.WITH_PERMISSIONS"));
        } else {
            builder.lore(config.getStringList(path + "ICON.LORE.WITHOUT_PERMISSIONS"));
        }
        ItemStack stack = builder.build();
        LeatherArmorMeta meta = (LeatherArmorMeta) stack.getItemMeta();
        meta.setColor(Color.fromBGR(
                config.getInt(path + "ICON.B"),
                config.getInt(path + "ICON.G"),
                config.getInt(path + "ICON.R")
        ));
        stack.setItemMeta(meta);
        return stack;
    }

    private boolean isPresent(String iPath) {
        String s = path + "." + iPath + ".";
        return config.getString(s + "R") != null &&
                config.getString(s + "G") != null &&
                config.getString(s + "B") != null;
    }
}
