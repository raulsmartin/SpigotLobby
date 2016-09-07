package me.raulsmail.spigotlobby.utils;

import me.raulsmail.spigotlobby.SpigotLobby;
import org.bukkit.entity.Player;

/**
 * Created by raulsmail.
 */
public class LobbyPlayer {
    private Player player;

    public LobbyPlayer(Player player) {
        this.player = player;
        SpigotLobby.getPlugin().getCommonUtilities().addLobbyPlayer(this);
    }

    public Player getPlayer() {
        return player;
    }
}
