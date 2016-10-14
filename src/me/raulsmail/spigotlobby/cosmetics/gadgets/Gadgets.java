package me.raulsmail.spigotlobby.cosmetics.gadgets;

import me.raulsmail.spigotlobby.cosmetics.gadgets.types.PaintballGadget;
import me.raulsmail.spigotlobby.utils.LobbyPlayer;
import org.bukkit.Material;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by raulsmail.
 */
public enum Gadgets {
    PAINTBALL(Material.DIAMOND_BARDING, "Paintball Gun", PaintballGadget.class);

    Material material;
    Short durability;
    String name;
    List<String> lore;
    Class<?> gadgetClass;

    Gadgets(Material material, String name, Class<?> gadgetClass) {
        this(material, (short) 0, name, null, gadgetClass);
    }

    Gadgets(Material material, String name, List<String> lore, Class<?> gadgetClass) {
        this(material, (short) 0, name, lore, gadgetClass);
    }

    Gadgets(Material material, Short durability, String name, List<String> lore, Class<?> gadgetClass) {
        this.material = material;
        this.durability = durability;
        this.name = name;
        this.lore = lore;
        this.gadgetClass = gadgetClass;
    }

    public static Gadget getGadget(LobbyPlayer player, Integer id) {
        try {
            if (id > 0) {
                Gadgets type = values()[id - 1];
                if (type != null) {
                    return (Gadget) type.getGadgetClass().getConstructor(LobbyPlayer.class).newInstance(player);
                }
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Material getMaterial() {
        return material;
    }

    public Short getDurability() {
        return durability;
    }

    public String getName() {
        return name;
    }

    public List<String> getLore() {
        return lore;
    }

    public Class<?> getGadgetClass() {
        return gadgetClass;
    }
}
