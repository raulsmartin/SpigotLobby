package me.raulsmail.spigotlobby.storage;

import me.raulsmail.spigotlobby.utils.LobbyPlayer;

/**
 * Created by raulsmail.
 */
public interface Storage {

    Boolean createPlayerInfo(LobbyPlayer player);

    Boolean existsPlayerInfo(LobbyPlayer player);
}
