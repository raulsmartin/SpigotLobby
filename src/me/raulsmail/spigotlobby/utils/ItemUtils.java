package me.raulsmail.spigotlobby.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * Created by raulsmail.
 */
public class ItemUtils {

    public static ItemStack createItem(Material material, Integer amount, Short durability, String name, List<String> lore) {
        ItemStack item = new ItemStack(material, amount, durability);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);
        if (lore != null && !lore.isEmpty()) {
            itemMeta.setLore(lore);
        }
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack createItem(Material material, String name) {
        return createItem(material, 1, (short) 0, name, null);
    }

    public static ItemStack createItem(Material material, Short durability, String name) {
        return createItem(material, 1, durability, name, null);
    }
}
