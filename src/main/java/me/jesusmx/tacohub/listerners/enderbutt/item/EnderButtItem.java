package me.jesusmx.tacohub.listerners.enderbutt.item;

import me.jesusmx.tacohub.utils.bukkit.ItemBuilder;
import me.jesusmx.tacohub.utils.files.features.HotbarFile;
import io.github.m3xicang0d.api.item.Item;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class EnderButtItem extends Item {

    private final FileConfiguration config = HotbarFile.getConfig();

    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.valueOf(config.getString("ENDERBUTT.MATERIAL")), config.getInt("ENDERBUTT.AMOUNT"))
                .name(config.getString("ENDERBUTT.NAME"))
                .lore(config.getStringList("ENDERBUTT.LORE"))
                .data(config.getInt("ENDERBUTT.DATA"))
                .build();
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            event.setCancelled(true);
            if (player.isInsideVehicle()) {
                player.getVehicle().remove();
            }
            event.setUseItemInHand(org.bukkit.event.Event.Result.DENY);
            event.setUseInteractedBlock(org.bukkit.event.Event.Result.DENY);
            if(config.getBoolean("ENDERBUTT.RIDE-ENABLED")) {
                if(player.getItemInHand().getType() == Material.SNOW_BALL) {
                    Snowball snowball = player.launchProjectile(Snowball.class);
                    snowball.setPassenger(player);
                    snowball.setVelocity(player.getLocation().getDirection().normalize().multiply(3F));
                } else if(player.getItemInHand().getType() == Material.ENDER_PEARL) {
                    EnderPearl pearl = player.launchProjectile(EnderPearl.class);
                    pearl.setPassenger(player);
                    pearl.setVelocity(player.getLocation().getDirection().normalize().multiply(3F));
                } else {
                    player.setVelocity(player.getLocation().getDirection().multiply(2.5f));
                }
            } else {
                player.setVelocity(player.getLocation().getDirection().multiply(2.5f));
            }
            player.playSound(player.getLocation(), Sound.valueOf(config.getString("ENDERBUTT.SOUND")), 2.0f, 1.0f);
            player.spigot().setCollidesWithEntities(false);
            super.set(player, config.getInt("ENDERBUTT.SLOT"));
            player.updateInventory();
        }
    }
}
