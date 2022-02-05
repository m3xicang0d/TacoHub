package me.jesusmx.tacohub.listerners;

import me.jesusmx.tacohub.commands.impl.builder.BuilderMode;
import me.jesusmx.tacohub.utils.CC;
import me.jesusmx.tacohub.utils.files.normal.ConfigFile;
import io.github.fxmxgragfx.api.listener.PluginListener;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

/**
 * Developed by JesusMX
 * Project: TacoHub
 **/

@PluginListener
public class WorldListener implements Listener {

    @EventHandler
    private void onCreatureSpawn(CreatureSpawnEvent event) {
        if (ConfigFile.getConfig().getBoolean("WORLD.NO-MOBS-SPAWN")) event.setCancelled(true);
    }
    @EventHandler
    private void onWeather(WeatherChangeEvent event) {
        event.setCancelled(true);
    }
    @EventHandler
    private void onFood(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }
    @EventHandler
    private void onDamage(EntityDamageEvent event) {
        event.setCancelled(true);
    }
    @EventHandler
    private void onEntitySpawn(EntitySpawnEvent event) {
        event.setCancelled(true);
    }
    @EventHandler
    private void onClick(InventoryClickEvent event) {
        if (!event.getWhoClicked().getGameMode().equals(GameMode.CREATIVE)) event.setCancelled(true);
    }
    @EventHandler
    private void onPickup(PlayerPickupItemEvent event) {
        if (!BuilderMode.getBuildMode(event.getPlayer())) event.setCancelled(true);
    }
    @EventHandler
    private void onDrop(PlayerDropItemEvent event) {
        if (!BuilderMode.getBuildMode(event.getPlayer())) event.setCancelled(true);
    }
    @EventHandler
    private void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (ConfigFile.getConfig().getBoolean("WORLD.NO-PLACE-BLOCKS")) {
            if (!player.hasPermission("hubcore.command.place") || !event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
                event.setCancelled(true);
            }
        }
    }
    @EventHandler
    private void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (ConfigFile.getConfig().getBoolean("WORLD.NO-BREAK-BLOCKS")) {
            if (!player.hasPermission("hubcore.command.break") || !event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
                event.setCancelled(true);
            }
        }
    }
    @EventHandler
    private void bucketEmpty(PlayerBucketFillEvent event) {
        if(!BuilderMode.getBuildMode(event.getPlayer())) event.setCancelled(true);
    }
    @EventHandler
    private void entityExplode(EntityExplodeEvent event){
        event.setCancelled(true);
    }
    @EventHandler
    private void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            event.setCancelled(true);
            if (ConfigFile.getConfig().getBoolean("WORLD.VOID-TELEPORT") && event.getCause() == EntityDamageEvent.DamageCause.VOID) {
                event.getEntity().teleport(event.getEntity().getWorld().getSpawnLocation());
            }
        }
    }


    @EventHandler
    private void disablePhysicsBlocks(BlockPhysicsEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    private void onPopDamager(EntityDamageByEntityEvent event) {
        Player player;
        Entity damager = event.getDamager();
        boolean b = event.getDamager() == null || event.getDamager() instanceof EnderPearl;
        boolean e = false;
        if(!ConfigFile.getConfig().getBoolean("WORLD.MINEHQ-HIDEPLAYERS-POP")) return;
        if (Bukkit.getVersion().contains("1.7")) {
            if(damager instanceof org.bukkit.craftbukkit.v1_7_R4.entity.CraftEnderPearl) {
                e = true;
            }
        } else if (Bukkit.getVersion().contains("1.8")) {
            if(damager instanceof org.bukkit.craftbukkit.v1_8_R3.entity.CraftEnderPearl) {
                e = true;
            }
        } else if (Bukkit.getVersion().contains("1.9")) {
            if(damager instanceof org.bukkit.craftbukkit.v1_9_R1.entity.CraftEnderPearl) {
                e = true;
            }
        } else if (Bukkit.getVersion().contains("1.10")) {
            if(damager instanceof org.bukkit.craftbukkit.v1_10_R1.entity.CraftEnderPearl) {
                e = true;
            }
        } else if (Bukkit.getVersion().contains("1.12")) {
            if(damager instanceof org.bukkit.craftbukkit.v1_12_R1.entity.CraftEnderPearl) {
                e = true;
            }
        } else if (Bukkit.getVersion().contains("1.14")) {
            if(damager instanceof org.bukkit.craftbukkit.v1_14_R1.entity.CraftEnderPearl) {
                e = true;
            }
        } else if (Bukkit.getVersion().contains("1.15")) {
            if(damager instanceof org.bukkit.craftbukkit.v1_15_R1.entity.CraftEnderPearl) {
                e = true;
            }
        }
        else if (Bukkit.getVersion().contains("1.16")) {
            if(damager instanceof org.bukkit.craftbukkit.v1_16_R3.entity.CraftEnderPearl) {
                e = true;
            }
        }
        if(b && e) {
            player = null;
        } else {
            player = (Player)event.getDamager();
        }
        Player damaged = (Player)event.getEntity();
        if (damaged != null && player != null &&
                event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
            player.hidePlayer(damaged);
            player.sendMessage(CC.translate(ConfigFile.getConfig().getString("WORLD.MINEHQ-HIDEPLAYERS.MESSAGE")));
            player.playSound(player.getLocation(), Sound.valueOf(ConfigFile.getConfig().getString("WORLD.MINEHQ-HIDEPLAYERS.SOUND")), 2.0f, 1.0f);
            player.playSound(player.getLocation(), Sound.BLAZE_HIT, 2F, 2F);
        }
        event.setCancelled(true);
    }
}
