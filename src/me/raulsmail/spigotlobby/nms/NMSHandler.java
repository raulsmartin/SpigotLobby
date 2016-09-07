package me.raulsmail.spigotlobby.nms;

import org.bukkit.entity.Player;

/**
 * Created by raulsmail.
 */
public interface NMSHandler {
    void sendTitle(Player player, String title);

    void sendSubTitle(Player player, String subTitle);

    void sendActionBar(Player player, String actionBar);

    void sendTabHeaderAndFooter(Player player, String header, String footer);

}
