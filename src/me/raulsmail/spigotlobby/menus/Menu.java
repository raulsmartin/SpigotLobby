package me.raulsmail.spigotlobby.menus;

import me.raulsmail.spigotlobby.SpigotLobby;
import me.raulsmail.spigotlobby.utils.LobbyPlayer;
import org.bukkit.inventory.Inventory;

/**
 * Created by raulsmail.
 */
abstract class Menu {
    private Integer slots;
    private String title;

    public void openMainMenu(LobbyPlayer player) {
        Inventory inventory = SpigotLobby.getPlugin().getServer().createInventory(null, slots, title);
        putItems(player, inventory);
        player.getPlayer().openInventory(inventory);
    }

    public abstract void putItems(LobbyPlayer player, Inventory inventory);

    void setSlots(Integer slots) {
        this.slots = slots;
    }

    void setTitle(String title) {
        this.title = title;
    }
}
