package io.github.fxmxgragfx.api.item;

import me.jesusmx.tacohub.cosmetics.button.armor.ArmorButton;
import me.jesusmx.tacohub.utils.CC;
import me.jesusmx.tacohub.utils.files.cosmetics.ArmorsFile;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.Collections;

/**
 * @Author UKry
 * @Project TacoHub
 **/

public class ArmorBuilder {

    private final ItemStack stack;
    private final LeatherArmorMeta meta;
    private final String part;
    private final FileConfiguration config = ArmorsFile.getConfig();
    private final ArmorButton button;

    public ArmorBuilder(ArmorButton button, String part) {
        this.button = button;
        this.part = part;
        this.stack = new ItemStack(Material.valueOf("LEATHER_" + part));
        this.meta = (LeatherArmorMeta) this.stack.getItemMeta();
        if(isPresent(part)) {
            meta.setColor(getColor());
        } else {
            meta.setColor(Color.fromBGR(
                    config.getInt(this.button.getPath() + "COLOR.B"),
                    config.getInt(this.button.getPath() + "COLOR.G"),
                    config.getInt(this.button.getPath() + "COLOR.R")
                    ));
        }
        this.stack.setItemMeta(this.meta);
    }

    public ItemStack build() {
        String s = this.button.getPath() + "." + this.part + ".";
        if(config.getString(s + "NAME") != null) {
            this.meta.setDisplayName(CC.translate(config.getString(s + "NAME")));
        } else {
            this.meta.setDisplayName(CC.GREEN + this.button.getArmor() + " Armor");
        }
        if(config.getStringList(s + "LORE") != null) {
            this.meta.setLore(CC.translate(config.getStringList(s + "LORE")));
        } else {
            this.meta.setLore(Collections.singletonList(CC.GREEN + this.button.getArmor() + " Armor"));
        }
        this.stack.setItemMeta(this.meta);
        return this.stack;
    }

    private boolean isPresent(String iPath) {
        String s = this.button.getPath() + "." + iPath + ".";
        return config.getString(s + "R") != null &&
                config.getString(s + "G") != null &&
                config.getString(s + "B") != null;
    }

    private Color getColor() {
        String s = this.button.getPath() + "." + this.part + ".";
        return Color.fromBGR(
                config.getInt(s+ "B"),
                config.getInt(s + "G"),
                config.getInt(s + "R")
                );
    }
}
