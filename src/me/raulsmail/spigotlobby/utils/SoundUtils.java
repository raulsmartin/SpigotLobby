package me.raulsmail.spigotlobby.utils;

import me.raulsmail.spigotlobby.SpigotLobby;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

/**
 * Created by raulsmail.
 */
public class SoundUtils {

    public static void playSound(Player player, Sounds sound) {
        playSound(player, sound, 1.0F, 1.0F);
    }

    public static void playSound(Player player, Sounds sound, Float volume, Float pitch) {
        player.playSound(player.getLocation(), Sound.valueOf(SpigotLobby.getPlugin().getCommonUtilities().isOldVersion() ? sound.oldVersion : sound.newerVersion), volume, pitch);
    }

    public static void playSound(Location location, Sounds sound) {
        playSound(location, sound, 1.0F, 1.0F);
    }

    public static void playSound(Location location, Sounds sound, Float volume, Float pitch) {
        location.getWorld().playSound(location, Sound.valueOf(SpigotLobby.getPlugin().getCommonUtilities().isOldVersion() ? sound.oldVersion : sound.newerVersion), volume, pitch);
    }

    public enum Sounds {
        ENDERMAN_TELEPORT("ENDERMAN_TELEPORT", "ENTITY_ENDERMEN_TELEPORT"),
        STEP_STONE("STEP_STONE", "BLOCK_STONE_STEP"),
        ORB_PICKUP("ORB_PICKUP", "ENTITY_EXPERIENCE_ORB_PICKUP");

        String oldVersion, newerVersion;

        Sounds(String oldVersion, String newerVersion) {
            this.oldVersion = oldVersion;
            this.newerVersion = newerVersion;
        }
    }
}
