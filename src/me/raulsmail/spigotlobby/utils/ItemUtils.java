package me.raulsmail.spigotlobby.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.sun.xml.internal.messaging.saaj.util.Base64;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by raulsmail.
 */
@Deprecated
public class ItemUtils {
    private static Map<String, ItemStack> heads = new HashMap<>();

    public static ItemStack createItem(Material material, Integer amount, Short durability, String name, List<String> lore) {
        ItemStack item = new ItemStack(material, amount, durability);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);
        if (lore != null && !lore.isEmpty()) {
            itemMeta.setLore(lore);
        }
        itemMeta.addItemFlags(ItemFlag.values());
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack createItem(Material material, String name) {
        return createItem(material, 1, (short) 0, name, null);
    }

    public static ItemStack createItem(Material material, Short durability, String name, List<String> lore) {
        return createItem(material, 1, durability, name, lore);
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
        itemMeta.addItemFlags(ItemFlag.values());
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack createHeadByTextureURL(String texture, String name) {
        return createHeadByTextureURL(texture, 1, name, null);
    }

    public static ItemStack createHeadByTextureURL(String texture, Integer amount, String name, List<String> lore) {
        ItemStack item = heads.containsKey(texture) ? heads.get(texture) : new ItemStack(Material.SKULL_ITEM, amount, (short) 3);
        SkullMeta itemMeta = (SkullMeta) item.getItemMeta();
        if (!heads.containsKey(texture)) {
            GameProfile profile = new GameProfile(UUID.randomUUID(), null);
            byte[] encodedData = Base64.encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", texture).getBytes());
            profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
            GeneralUtils.setSkullProfile(itemMeta, profile);
            item.setItemMeta(itemMeta);
            heads.put(texture, item);
        }
        itemMeta.setDisplayName(name);
        if (lore != null && !lore.isEmpty()) {
            itemMeta.setLore(lore);
        }
        itemMeta.addItemFlags(ItemFlag.values());
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack createHeadByByte64(Heads head, String name) {
        return createHeadByByte64(head, 1, name, null);
    }

    public static ItemStack createHeadByByte64(Heads head, Integer amount, String name, List<String> lore) {
        ItemStack item = heads.containsKey(head.getTexture()) ? heads.get(head.getTexture()) : new ItemStack(Material.SKULL_ITEM, amount, (short) 3);
        SkullMeta itemMeta = (SkullMeta) item.getItemMeta();
        if (!heads.containsKey(head.getTexture())) {
            GameProfile profile = new GameProfile(UUID.randomUUID(), null);
            profile.getProperties().put("textures", new Property("textures", head.getTexture()));
            GeneralUtils.setSkullProfile(itemMeta, profile);
            item.setItemMeta(itemMeta);
            heads.put(head.getTexture(), item);
        }
        itemMeta.setDisplayName(name);
        if (lore != null && !lore.isEmpty()) {
            itemMeta.setLore(lore);
        }
        itemMeta.addItemFlags(ItemFlag.values());
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack createClothes(Material material, Short durability, String displayName, Color color) {
        return createClothes(material, 1, durability, displayName, null, color);
    }

    public static ItemStack createClothes(Material material, Integer amount, Short durability, String displayName, List<String> lore, Color color) {
        ItemStack item = new ItemStack(material, amount, durability);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(displayName);
        if (color != null) {
            ((LeatherArmorMeta) itemMeta).setColor(color);
        }
        if (lore != null && !lore.isEmpty()) {
            itemMeta.setLore(lore);
        }
        itemMeta.addItemFlags(ItemFlag.values());
        item.setItemMeta(itemMeta);
        return item;
    }
}
