package me.raulsmail.spigotlobby.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.sun.xml.internal.messaging.saaj.util.Base64;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
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

    public ItemBuilder setSkullOwner(String skullOwner) {
        if (itemMeta instanceof SkullMeta) {
            ((SkullMeta) itemMeta).setOwner(skullOwner);
        }
        return this;
    }

    public ItemBuilder setSkullTextureUrl(String textureUrl) {
        if (itemMeta instanceof SkullMeta) {
            GameProfile profile = new GameProfile(UUID.randomUUID(), null);
            byte[] encodedData = Base64.encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", textureUrl).getBytes());
            profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
            try {
                Field profileField = itemMeta.getClass().getDeclaredField("profile");
                profileField.setAccessible(true);
                profileField.set(itemMeta, profile);
                profileField.setAccessible(!profileField.isAccessible());
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    public ItemBuilder setSkullTextureBase64(String textureBase64) {
        if (itemMeta instanceof SkullMeta) {
            GameProfile profile = new GameProfile(UUID.randomUUID(), null);
            profile.getProperties().put("textures", new Property("textures", textureBase64));
            try {
                Field profileField = ((SkullMeta) itemMeta).getClass().getDeclaredField("profile");
                profileField.setAccessible(true);
                profileField.set(itemMeta, profile);
                profileField.setAccessible(!profileField.isAccessible());
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return this;
    }
}
