package me.jesusmx.tacohub.pvpmode.cache;

import lombok.Getter;
import me.jesusmx.tacohub.cosmetics.item.CosmeticsItem;
import me.jesusmx.tacohub.listerners.enderbutt.item.EnderButtItem;
import me.jesusmx.tacohub.listerners.rocket.RocketItem;
import me.jesusmx.tacohub.listerners.toggleview.item.ShowedItem;
import me.jesusmx.tacohub.parkour.item.ParkourItem;
import me.jesusmx.tacohub.pvpmode.config.PvPModeFile;
import me.jesusmx.tacohub.pvpmode.item.PvPModeItem;
import me.jesusmx.tacohub.selector.hub.item.HubSelectorItem;
import me.jesusmx.tacohub.selector.server.item.ServerSelectorItem;
import me.jesusmx.tacohub.utils.CC;
import me.jesusmx.tacohub.utils.files.features.HotbarFile;
import me.jesusmx.tacohub.utils.files.normal.ConfigFile;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author UKry
 * @Project TacoHub
 **/

public class PvPModeHandler {

    private static Map<UUID, Long> inPvPMode;
    @Getter private static Map<UUID, Integer> kills;

    public PvPModeHandler() {
        inPvPMode = new HashMap<>();
        kills = new HashMap<>();
    }

    public static long getTime(Player player) {
        return inPvPMode.get(player.getUniqueId());
    }

    public static boolean isOnPvPMode(Player player) {
        return inPvPMode.containsKey(player.getUniqueId());
    }

    public static void togglePvPMode(Player player) {
        if(PvPModeFile.getConfig().get("INVENTORY") == null || PvPModeFile.getConfig().get("ARMOR") == null) {
            player.sendMessage(CC.RED + "Erorr, PvPMode no is configured!");
            return;
        }
        ItemStack[] contents = ((List<ItemStack>)PvPModeFile.getConfig().get("INVENTORY")).stream().toArray(ItemStack[]::new) ;
        ItemStack[] armor = ((List<ItemStack>)PvPModeFile.getConfig().get("ARMOR")).stream().toArray(ItemStack[]::new) ;
        if(isOnPvPMode(player)) {
            inPvPMode.remove(player.getUniqueId());
            kills.remove(player.getUniqueId());
            FileConfiguration config = HotbarFile.getConfig();
            player.getInventory().clear();
            player.getInventory().setHelmet(new ItemStack(null, 1));
            player.getInventory().setChestplate(new ItemStack(null, 1));
            player.getInventory().setLeggings(new ItemStack(null, 1));
            player.getInventory().setBoots(new ItemStack(null, 1));
            // Server selector item
            if(config.getBoolean("SERVER-SELECTOR.ENABLED")) {
                new ServerSelectorItem().set(player, config.getInt("SERVER-SELECTOR.SLOT"));
            }
            // Hub selector item
            if(config.getBoolean("HUB-SELECTOR.ENABLED")) {
                new HubSelectorItem().set(player, config.getInt("HUB-SELECTOR.SLOT"));
            }
            // Parkour mode item
            if (config.getBoolean("PARKOUR.ENABLED")) {
                new ParkourItem().set(player, config.getInt("PARKOUR.SLOT"));
            }
            // Enderbutt item
            if(HotbarFile.getConfig().getBoolean("ENDERBUTT.ENABLED")) {
                new EnderButtItem().set(player, config.getInt("ENDERBUTT.SLOT"));
            }
            // Rocket item
            if(config.getBoolean("ROCKET.ENABLED")) {
                new RocketItem().set(player, config.getInt("ROCKET.SLOT"));
            }
            // Player hider/shower item
            if(config.getBoolean("PLAYER_HIDER.ENABLED")) {
                new ShowedItem().set(player, config.getInt("PLAYER_HIDER.SLOT"));
            }
            // Cosmetics Item
            if (config.getBoolean("COSMETICS.ENABLED")) {
                new CosmeticsItem().set(player, config.getInt("COSMETICS.SLOT"));
            }

            if (config.getBoolean("PVP_MODE")) {
                new PvPModeItem().set(player, config.getInt("PVP_MODE.SLOT"));
            }
            if (ConfigFile.getConfig().getBoolean("JOIN-SPEED.ENABLE")) {
                player.setWalkSpeed((float) ConfigFile.getConfig().getDouble("JOIN-SPEED.MULTIPLIER"));
            } else {
                player.setWalkSpeed(0.2F);
            }
            player.updateInventory();
            player.sendMessage("Successfully toggled pvpmode");
            FileConfiguration c = ConfigFile.getConfig();
            if (c.getBoolean("SPAWN.SET")) {
                Location location = new Location(
                        Bukkit.getWorld(c.getString("SPAWN.WORLD")),
                        c.getDouble("SPAWN.X"),
                        c.getDouble("SPAWN.Y"),
                        c.getDouble("SPAWN.Z"),
                        Float.parseFloat(c.getString("SPAWN.YAW")),
                        Float.parseFloat(c.getString("SPAWN.PITCH"))
                );
                player.teleport(location);
            } else {
                player.sendMessage(CC.translate("&cSpawn has not been set!"));
            }
        } else {
            inPvPMode.put(player.getUniqueId(), System.currentTimeMillis());
            kills.put(player.getUniqueId(), 0);
            player.setWalkSpeed(0.2F);
            player.getInventory().setContents(contents);
            player.getInventory().setArmorContents(armor);
            player.updateInventory();
            player.sendMessage("Successfully toggled pvpmode");
        }
    }
}