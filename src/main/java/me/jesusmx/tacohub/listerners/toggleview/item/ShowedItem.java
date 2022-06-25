package me.jesusmx.tacohub.listerners.toggleview.item;

import me.jesusmx.tacohub.TacoHub;
import me.jesusmx.tacohub.utils.CC;
import me.jesusmx.tacohub.utils.bukkit.ItemBuilder;
import me.jesusmx.tacohub.utils.files.features.HotbarFile;
import io.github.m3xicang0d.api.item.Item;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

public class ShowedItem extends Item {

    private final FileConfiguration config = HotbarFile.getConfig();

    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.valueOf(config.getString("PLAYER_HIDER.SHOWN.MATERIAL")))
                .name(config.getString("PLAYER_HIDER.SHOWN.NAME"))
                .lore(config.getStringList("PLAYER_HIDER.SHOWN.LORE"))
                .data(config.getInt("PLAYER_HIDER.SHOWN.ID"))
                .build();
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Player player = event.getPlayer();
                TacoHub.getInstance().getOnlinePlayers().forEach(player::hidePlayer);
                player.removeMetadata("PLAYERS_SHOWEDS", TacoHub.getInstance());
                player.setMetadata("PLAYERS_HIDED", new FixedMetadataValue(TacoHub.getInstance(), true));
                player.sendMessage(CC.translate(HotbarFile.getConfig().getString("PLAYER_HIDER.SHOWN.MESSAGE")));
                new HidedItem().set(player, config.getInt("PLAYER_HIDER.SLOT"));
            }
        }.runTaskLater(TacoHub.getInstance(), 5);
    }
}
