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

import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Created by raulsmail.
 */
public class ItemBuilder {
    private ItemStack itemStack;
    private ItemMeta itemMeta;

    public ItemBuilder(Material material) {
        itemStack = new ItemStack(material);
        itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder setAmount(Integer amount) {
        itemStack.setAmount(amount);
        return this;
    }

    public ItemBuilder setDurability(Short durability) {
        itemStack.setDurability(durability);
        return this;
    }

    public ItemBuilder setDisplayName(String displayName) {
        itemMeta.setDisplayName(displayName);
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        if (lore == null) {
            lore = Collections.emptyList();
        }
        itemMeta.setLore(lore);
        return this;
    }

    public ItemBuilder setColor(Color color) {
        if (color != null) {
            if (itemMeta instanceof LeatherArmorMeta) {
                ((LeatherArmorMeta) itemMeta).setColor(color);
            }
        }
        return this;
    }

    public ItemBuilder setSkullOwner(String skullOwner) {
        if (itemMeta instanceof SkullMeta) {
            ((SkullMeta) itemMeta).setOwner(skullOwner);
        }
        return this;
    }

    public ItemBuilder setSkullTextureUrl(String textureUrl) {
        if (itemMeta instanceof SkullMeta) {
            GameProfile profile = new GameProfile(UUID.randomUUID(), null);
            profile.getProperties().put("textures", new Property("textures", new String(Base64.encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", textureUrl).getBytes()))));
            GeneralUtils.setSkullProfile(itemMeta, profile);
        }
        return this;
    }

    public ItemBuilder setSkullTextureBase64(String textureBase64) {
        if (itemMeta instanceof SkullMeta) {
            GameProfile profile = new GameProfile(UUID.randomUUID(), null);
            profile.getProperties().put("textures", new Property("textures", textureBase64));
            GeneralUtils.setSkullProfile(itemMeta, profile);
        }
        return this;
    }

    public ItemStack build() {
        itemMeta.addItemFlags(ItemFlag.values());
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
