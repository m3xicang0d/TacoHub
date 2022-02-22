package me.jesusmx.tacohub.cosmetics.button.armor;

import me.jesusmx.tacohub.utils.CC;
import me.jesusmx.tacohub.utils.bukkit.ItemBuilder;
import me.jesusmx.tacohub.utils.bukkit.menu.Button;
import me.jesusmx.tacohub.utils.files.cosmetics.HatsFile;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

/**
 * @Author JesusMX
 * @Project TacoHub
 **/

public class ArmorRemoveButton extends Button {

    private final FileConfiguration config = HatsFile.getConfig();

    @Override
    public void click(Player player, int slot, ClickType clickType, int hotbarButton) {
        player.getInventory().setHelmet(new ItemStack(Material.AIR));
        player.getInventory().setChestplate(new ItemStack(Material.AIR));
        player.getInventory().setLeggings(new ItemStack(Material.AIR));
        player.getInventory().setBoots(new ItemStack(Material.AIR));
        player.closeInventory();
        player.sendMessage(CC.GREEN + "Successfully removed you actual armor!");
    }

    @Override
    public ItemStack getItem(Player player) {
       /* if(player.hasMetadata("HAT")) {
            //String hat = player.getMetadata("HAT").get(0).asString();
        */
        return new ItemBuilder(Material.valueOf(config.getString("MENU.REMOVE_HAT.ITEM")))
                .name(config.getString("MENU.REMOVE_HAT.NAME"))
                .lore(config.getStringList("MENU.REMOVE_HAT.LORE"))
                .data(config.getInt("MENU.REMOVE_HAT.DATA"))
                .build();
    }
}
