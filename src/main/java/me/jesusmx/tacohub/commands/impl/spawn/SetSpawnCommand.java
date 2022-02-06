package me.jesusmx.tacohub.commands.impl.spawn;

import me.jesusmx.tacohub.commands.impl.Permissions;
import me.jesusmx.tacohub.utils.CC;
import me.jesusmx.tacohub.utils.bukkit.command.Command;
import me.jesusmx.tacohub.utils.files.normal.ConfigFile;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SetSpawnCommand extends Command {

   @Override
   public boolean execute(CommandSender sender, String label, String[] args) {
      if(!(sender instanceof Player)) {
         sender.sendMessage(CC.NO_CONSOLE);
         return false;
      }
      Player player = (Player)sender;
      if (!Permissions.SET_SPAWN_COMMAND.has(player)) {
         player.sendMessage(CC.translate("&cYou dont have permission to use this command."));
      } else {
         World world = player.getWorld();
         Location loc = player.getLocation();
         world.setSpawnLocation(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ());
         FileConfiguration config = ConfigFile.getConfig();
         config.set("SPAWN.WORLD", loc.getWorld().getName());
         config.set("SPAWN.X", loc.getX());
         config.set("SPAWN.Y", loc.getY());
         config.set("SPAWN.Z", loc.getZ());
         config.set("SPAWN.YAW", loc.getYaw());
         config.set("SPAWN.PITCH", loc.getPitch());
         config.set("SPAWN.SET", true);
         ConfigFile.getConfig().save();
         ConfigFile.getConfig().reload();
         player.sendMessage(CC.translate("&aSuccessfully set the spawn point."));
      }
      return false;
   }
}