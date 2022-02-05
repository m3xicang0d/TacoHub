package io.github.nosequel.tab.shared;

import me.jesusmx.tacohub.TacoHub;
import org.bukkit.entity.Player;

public class TabThread extends Thread {

    private TabHandler handler;

    /**
     * Constructor to make a new TabThread
     *
     * @param handler the handler to register it to
     */
    public TabThread(TabHandler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        TacoHub.getInstance().getOnlinePlayers().forEach(this.handler::sendUpdate);
    }
}