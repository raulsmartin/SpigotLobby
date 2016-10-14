package me.raulsmail.spigotlobby.utils;

import com.mojang.authlib.GameProfile;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Field;

/**
 * Created by raulsmail.
 */
public class GeneralUtils {

    public static Integer getInteger(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException ignored) {
        }
        return 0;
    }

    public static void setSkullProfile(ItemMeta itemMeta, GameProfile profile) {
        try {
            Field profileField = itemMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(itemMeta, profile);
            profileField.setAccessible(!profileField.isAccessible());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
