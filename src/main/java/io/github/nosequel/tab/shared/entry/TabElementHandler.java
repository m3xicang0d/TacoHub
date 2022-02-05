package io.github.nosequel.tab.shared.entry;

import org.bukkit.entity.Player;

public interface TabElementHandler {

    /**
     * Get the tab element of a player
     *
     * @param player the player
     * @return the element
     */
    TabElement getElement(Player player);

}
