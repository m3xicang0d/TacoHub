package io.github.nosequel.tab.shared;

import me.jesusmx.tacohub.TacoHub;
import org.bukkit.entity.Player;

public class TabRunnable extends Thread {

    private TabHandler handler;

    /**
     * Constructor to make a new TabThread
     *
     * @param handler the handler to register it to
     */
    public TabRunnable(TabHandler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        for (Player player : TacoHub.getInstance().getOnlinePlayers()) {
            this.handler.sendUpdate(player);
        }
    }
}