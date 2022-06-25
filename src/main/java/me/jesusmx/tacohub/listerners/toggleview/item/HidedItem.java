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

public class HidedItem extends Item {

    private final FileConfiguration config = HotbarFile.getConfig();

    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.valueOf(config.getString("PLAYER_HIDER.HIDED.MATERIAL")))
                .name(config.getString("PLAYER_HIDER.HIDED.NAME"))
                .lore(config.getStringList("PLAYER_HIDER.HIDED.LORE"))
                .data(config.getInt("PLAYER_HIDER.HIDED.ID"))
                .build();
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Player player = event.getPlayer();
                TacoHub.getInstance().getOnlinePlayers().forEach(player::showPlayer);
                player.removeMetadata("PLAYERS_HIDED", TacoHub.getInstance());
                player.setMetadata("PLAYERS_SHOWEDS", new FixedMetadataValue(TacoHub.getInstance(), true));
                player.sendMessage(CC.translate(HotbarFile.getConfig().getString("PLAYER_HIDER.HIDED.MESSAGE")));
                new ShowedItem().set(player, config.getInt("PLAYER_HIDER.SLOT"));
            }
        }.runTaskLater(TacoHub.getInstance(), 5);
    }
}
