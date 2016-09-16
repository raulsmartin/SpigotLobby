package me.raulsmail.spigotlobby.utils;

import me.raulsmail.spigotlobby.SpigotLobby;
import org.bukkit.entity.Player;

/**
 * Created by raulsmail.
 */
public class LobbyPlayer {
    private Player player;
    private String test;

    public LobbyPlayer(Player player) {
        this.player = player;
        SpigotLobby.getPlugin().getCommonUtilities().addLobbyPlayer(this);
        SpigotLobby.getPlugin().getCommonUtilities().storage.createPlayerInfo(this);
    }

    public Player getPlayer() {
        return player;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
