package me.raulsmail.spigotlobby.cosmetics.wardrobe;

import org.bukkit.Color;
import org.bukkit.Material;

/**
 * Created by raulsmail.
 */
public enum Clothes {
    RED_HELMET(Material.LEATHER_HELMET, (short) 0, "§4Red Helmet", Color.RED),
    RED_CHESTPLATE(Material.LEATHER_CHESTPLATE, (short) 0, "§4Red Chestplate", Color.RED),
    RED_LEGGINGS(Material.LEATHER_LEGGINGS, (short) 0, "§4Red Leggings", Color.RED),
    RED_BOOTS(Material.LEATHER_BOOTS, (short) 0, "§4Red Boots", Color.RED),
    GREEN_HELMET(Material.LEATHER_HELMET, (short) 0, "§2Green Helmet", Color.GREEN),
    GREEN_CHESTPLATE(Material.LEATHER_CHESTPLATE, (short) 0, "§2Green Chestplate", Color.GREEN),
    GREEN_LEGGINGS(Material.LEATHER_LEGGINGS, (short) 0, "§2Green Leggings", Color.GREEN),
    GREEN_BOOTS(Material.LEATHER_BOOTS, (short) 0, "§2Green Boots", Color.GREEN),
    BLUE_HELMET(Material.LEATHER_HELMET, (short) 0, "§9Blue Helmet", Color.BLUE),
    BLUE_CHESTPLATE(Material.LEATHER_CHESTPLATE, (short) 0, "§9Blue Chestplate", Color.BLUE),
    BLUE_LEGGINGS(Material.LEATHER_LEGGINGS, (short) 0, "§9Blue Leggings", Color.BLUE),
    BLUE_BOOTS(Material.LEATHER_BOOTS, (short) 0, "§9Blue Boots", Color.BLUE),
    ORANGE_HELMET(Material.LEATHER_HELMET, (short) 0, "§6Orange Helmet", Color.ORANGE),
    ORANGE_CHESTPLATE(Material.LEATHER_CHESTPLATE, (short) 0, "§6Orange Chestplate", Color.ORANGE),
    ORANGE_LEGGINGS(Material.LEATHER_LEGGINGS, (short) 0, "§6Orange Leggings", Color.ORANGE),
    ORANGE_BOOTS(Material.LEATHER_BOOTS, (short) 0, "§6Orange Boots", Color.ORANGE),
    LIME_HELMET(Material.LEATHER_HELMET, (short) 0, "§aLime Helmet", Color.LIME),
    LIME_CHESTPLATE(Material.LEATHER_CHESTPLATE, (short) 0, "§aLime Chestplate", Color.LIME),
    LIME_LEGGINGS(Material.LEATHER_LEGGINGS, (short) 0, "§aLime Leggings", Color.LIME),
    LIME_BOOTS(Material.LEATHER_BOOTS, (short) 0, "§aLime Boots", Color.LIME),
    PURPLE_HELMET(Material.LEATHER_HELMET, (short) 0, "§5Purple Helmet", Color.PURPLE),
    PURPLE_CHESTPLATE(Material.LEATHER_CHESTPLATE, (short) 0, "§5Purple Chestplate", Color.PURPLE),
    PURPLE_LEGGINGS(Material.LEATHER_LEGGINGS, (short) 0, "§5Purple Leggings", Color.PURPLE),
    PURPLE_BOOTS(Material.LEATHER_BOOTS, (short) 0, "§5Purple Boots", Color.PURPLE),
    YELLOW_HELMET(Material.LEATHER_HELMET, (short) 0, "§eYellow Helmet", Color.YELLOW),
    YELLOW_CHESTPLATE(Material.LEATHER_CHESTPLATE, (short) 0, "§eYellow Chestplate", Color.YELLOW),
    YELLOW_LEGGINGS(Material.LEATHER_LEGGINGS, (short) 0, "§eYellow Leggings", Color.YELLOW),
    YELLOW_BOOTS(Material.LEATHER_BOOTS, (short) 0, "§eYellow Boots", Color.YELLOW),
    TEAL_HELMET(Material.LEATHER_HELMET, (short) 0, "§3Teal Helmet", Color.TEAL),
    TEAL_CHESTPLATE(Material.LEATHER_CHESTPLATE, (short) 0, "§3Teal Chestplate", Color.TEAL),
    TEAL_LEGGINGS(Material.LEATHER_LEGGINGS, (short) 0, "§3Teal Leggings", Color.TEAL),
    TEAL_BOOTS(Material.LEATHER_BOOTS, (short) 0, "§3Teal Boots", Color.TEAL),
    SILVER_HELMET(Material.LEATHER_HELMET, (short) 0, "§7Silver Helmet", Color.SILVER),
    SILVER_CHESTPLATE(Material.LEATHER_CHESTPLATE, (short) 0, "§7Silver Chestplate", Color.SILVER),
    SILVER_LEGGINGS(Material.LEATHER_LEGGINGS, (short) 0, "§7Silver Leggings", Color.SILVER),
    SILVER_BOOTS(Material.LEATHER_BOOTS, (short) 0, "§7Silver Boots", Color.SILVER);

    private Material material;
    private Short durability;
    private String displayName;
    private Color color;

    Clothes(Material material, Short durability, String displayName, Color color) {
        this.material = material;
        this.durability = durability;
        this.displayName = displayName;
        this.color = color;
    }

    public Material getMaterial() {
        return material;
    }

    public Short getDurability() {
        return durability;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Color getColor() {
        return color;
    }
}
