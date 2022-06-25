package me.jesusmx.tacohub.listerners.rocket;

import me.jesusmx.tacohub.utils.bukkit.ItemBuilder;
import me.jesusmx.tacohub.utils.files.features.HotbarFile;
import io.github.m3xicang0d.api.item.Item;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class RocketItem extends Item {

    private final FileConfiguration config = HotbarFile.getConfig();

    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.valueOf(config.getString("ROCKET.ITEM")))
                .name(config.getString("ROCKET.NAME"))
                .lore(config.getStringList("ROCKET.LORE"))
                .build();
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event) {
        Action action = event.getAction();
        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            event.setCancelled(true);
            player.setVelocity(player.getLocation().getDirection().multiply(config.getDouble("ROCKET.MULTIPLY")).setY(1.5));
            player.getWorld().spigot().playEffect(player.getLocation(), Effect.valueOf(config.getString("ROCKET.EFFECT").toUpperCase()), 26, 0, 0.2F, 0.5F, 0.2F, 0.2F, 12, 387);
            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.valueOf(config.getString("ROCKET.SOUND")), 2.0f, 1.0f);
        }
    }
}
