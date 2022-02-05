package me.jesusmx.tacohub.queue;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.jesusmx.tacohub.TacoHub;
import me.jesusmx.tacohub.queue.custom.QueueHandler;
import me.jesusmx.tacohub.utils.files.normal.ConfigFile;
import me.joeleoli.portal.shared.queue.Queue;
import me.signatured.ezqueueshared.QueueInfo;
import me.signatured.ezqueuespigot.EzQueueAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@SuppressWarnings("UnstableApiUsage")
public class QueueManager {
	
	public Queues getQueueSupport() {
		switch (ConfigFile.getConfig().getString("QUEUE-SUPPORT")) {
			case "EZQUEUE":
				return Queues.EZQUEUE;
			case "PORTAL":
				return Queues.PORTAL;
			case "CUSTOM":
				return Queues.CUSTOM;
			default:
				return Queues.NONE;
		}
	}
	
	public boolean inQueue(Player player) {
		switch (this.getQueueSupport()) {
			case EZQUEUE:
				return QueueInfo.getQueueInfo(EzQueueAPI.getQueue(player)) != null;
			case PORTAL:
				return Queue.getByPlayer(player.getUniqueId()) != null;
			case CUSTOM:
				return QueueHandler.getQueue(player) != null;
			default:
				return false;
		}
	}
	
	public void sendPlayer(Player player, String server) {
		switch (this.getQueueSupport()) {
			case EZQUEUE:
				EzQueueAPI.addToQueue(player, server);
				break;
			case PORTAL:
				Bukkit.getServer().dispatchCommand(player, "joinqueue " + server);
				break;
			case CUSTOM:
				Bukkit.getServer().dispatchCommand(player, "play " + server);
			default:
				ByteArrayDataOutput out = ByteStreams.newDataOutput();
				out.writeUTF("Connect");
				out.writeUTF(server);
				player.getPlayer().sendPluginMessage(TacoHub.getInstance(), "BungeeCord", out.toByteArray());
				break;
		}
	}
	
	public String getQueueIn(Player player) {
		switch (this.getQueueSupport()) {
			case EZQUEUE:
				return EzQueueAPI.getQueue(player);
			case PORTAL:
				return Queue.getByPlayer(player.getUniqueId()).getName();
			case CUSTOM:
				return QueueHandler.getQueue(player).getServer();
			default:
				return "NoInQueue";
		}
	}
	
	public int getPosition(Player player) {
		switch (this.getQueueSupport()) {
			case EZQUEUE:
				return EzQueueAPI.getPosition(player);
			case PORTAL:
				return Queue.getByPlayer(player.getUniqueId()).getPosition(player.getUniqueId());
			case CUSTOM:
				return QueueHandler.getQueue(player).getPosition(player);
			default:
				return 0;
		}
	}
	
	public int getInQueue(String queue) {
		switch (this.getQueueSupport()) {
			case EZQUEUE:
				return QueueInfo.getQueueInfo(queue).getPlayersInQueue().size();
			case PORTAL:
				return Queue.getByName(queue).getPlayers().size();
			case CUSTOM:
				return QueueHandler.getQueue(queue).getPlayers().size();
			default:
				return 0;
		}
	}
}
