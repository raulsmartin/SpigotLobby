package me.raulsmail.spigotlobby.utils;

import me.raulsmail.spigotlobby.menus.Menus;
import me.raulsmail.spigotlobby.storage.Storage;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Map;

/**
 * Created by raulsmail.
 */
class CommonVariables {
    Material cosmeticsMaterial, optionsMaterial;
    Storage storage;
    Map<Player, LobbyPlayer> players;
    Menus menus;
    Boolean oldVersion;
    Integer gadgetSlot;
}
