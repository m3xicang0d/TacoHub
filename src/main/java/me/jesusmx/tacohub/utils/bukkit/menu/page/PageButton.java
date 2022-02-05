package me.jesusmx.tacohub.utils.bukkit.menu.page;

import me.jesusmx.tacohub.utils.bukkit.ItemBuilder;
import me.jesusmx.tacohub.utils.bukkit.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class PageButton extends Button {

    private int mod;
    private PagedMenu menu;

    public PageButton(int mod, PagedMenu menu) {
        this.mod = mod;
        this.menu = menu;
    }


    @Override
    public ItemStack getItem(Player player) {
        if (this.hasNext(player)) {
            return new ItemBuilder(Material.ARROW)
                    .name("§a(§e" + (menu.getPage() + mod) + "/§e" + menu.getPages(player) + "§a)")
                    .build();
        } else {
            return new ItemBuilder(Material.ARROW)
                    .name(mod > 0 ? "§7Last page" : "§7First page")
                    .build();
        }
    }

    @Override
    public void click(Player player, int slot, ClickType clickType, int hotbarButton) {
        if (hasNext(player)) {
            this.menu.modPage(player, mod);
        }
    }

    private boolean hasNext(Player player) {
        int pg = this.menu.getPage() + this.mod;
        return pg > 0 && this.menu.getPages(player) >= pg;
    }

}
