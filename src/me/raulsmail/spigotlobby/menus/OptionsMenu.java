package me.raulsmail.spigotlobby.menus;

import me.raulsmail.spigotlobby.SpigotLobby;
import me.raulsmail.spigotlobby.utils.ItemUtils;
import me.raulsmail.spigotlobby.utils.LobbyPlayer;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

/**
 * Created by raulsmail.
 */
public class OptionsMenu extends Menu {

    OptionsMenu() {
        setSlots(36);
        setTitle(SpigotLobby.getPlugin().getConfig().getString("menus.optionsMenu.title").replaceAll("&", "§"));
    }

    @Override
    public void putItems(LobbyPlayer player, Inventory inventory) {
        inventory.setItem(10, ItemUtils.createItem(Material.BOOK_AND_QUILL, "§eToggle Chat"));
        inventory.setItem(19, ItemUtils.createItem(Material.STAINED_CLAY, (short) 5, "§aEnabled"));
    }
}
