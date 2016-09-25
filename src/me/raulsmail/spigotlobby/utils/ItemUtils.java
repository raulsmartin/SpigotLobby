package me.raulsmail.spigotlobby.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by raulsmail.
 */
public class ItemUtils {
    private static Map<String, ItemStack> heads = new HashMap<>();

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

    public static ItemStack createHeadByName(String owner, String name) {
        return createHeadByName(owner, 1, name, null);
    }

    public static ItemStack createHeadByName(String owner, Integer amount, String name, List<String> lore) {
        ItemStack item = heads.containsKey(owner) ? heads.get(owner) : new ItemStack(Material.SKULL_ITEM, amount, (short) 3);
        SkullMeta itemMeta = (SkullMeta) item.getItemMeta();
        if (!heads.containsKey(owner)) {
            itemMeta.setOwner(owner);
            item.setItemMeta(itemMeta);
            heads.put(owner, item);
        }
        itemMeta.setDisplayName(name);
        if (lore != null && !lore.isEmpty()) {
            itemMeta.setLore(lore);
        }
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        return item;
    }

    //THIS NEEDS JAVA 1.8!!!
    /*public static ItemStack createHeadByTextureURL(String texture, String name) {
        return createHeadByTextureURL(texture, 1, name, null);
    }

    public static ItemStack createHeadByTextureURL(String texture, Integer amount, String name, List<String> lore) {
        ItemStack item = heads.containsKey(texture) ? heads.get(texture) : new ItemStack(Material.SKULL_ITEM, amount, (short) 3);
        SkullMeta itemMeta = (SkullMeta) item.getItemMeta();
        if (!heads.containsKey(texture)) {
            GameProfile profile = new GameProfile(UUID.randomUUID(), null);
            byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", texture).getBytes());
            profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
            try {
                Field profileField = itemMeta.getClass().getDeclaredField("profile");
                profileField.setAccessible(true);
                profileField.set(itemMeta, profile);
                profileField.setAccessible(!profileField.isAccessible());
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
            item.setItemMeta(itemMeta);
            heads.put(texture, item);
        }
        itemMeta.setDisplayName(name);
        if (lore != null && !lore.isEmpty()) {
            itemMeta.setLore(lore);
        }
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        return item;
    }*/

    public static ItemStack createHeadByByte64(String texture, String name) {
        return createHeadByByte64(texture, 1, name, null);
    }

    public static ItemStack createHeadByByte64(String texture, Integer amount, String name, List<String> lore) {
        ItemStack item = heads.containsKey(texture) ? heads.get(texture) : new ItemStack(Material.SKULL_ITEM, amount, (short) 3);
        SkullMeta itemMeta = (SkullMeta) item.getItemMeta();
        if (!heads.containsKey(texture)) {
            GameProfile profile = new GameProfile(UUID.randomUUID(), null);
            profile.getProperties().put("textures", new Property("textures", texture));
            try {
                Field profileField = itemMeta.getClass().getDeclaredField("profile");
                profileField.setAccessible(true);
                profileField.set(itemMeta, profile);
                profileField.setAccessible(!profileField.isAccessible());
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
            item.setItemMeta(itemMeta);
            heads.put(texture, item);
        }
        itemMeta.setDisplayName(name);
        if (lore != null && !lore.isEmpty()) {
            itemMeta.setLore(lore);
        }
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        return item;
    }
}
