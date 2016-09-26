package me.raulsmail.spigotlobby.menus;

import me.raulsmail.spigotlobby.SpigotLobby;
import me.raulsmail.spigotlobby.utils.LobbyPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created by raulsmail.
 */
public abstract class Menu implements Listener {
    private Integer slots;
    private String title;

    public Menu(Integer slots, String title) {
        this.slots = slots;
        this.title = title;
        SpigotLobby.getPlugin().getServer().getPluginManager().registerEvents(this, SpigotLobby.getPlugin());
    }

    public void openMainMenu(LobbyPlayer player) {
        openMainMenu(player, 1);
    }

    public void openMainMenu(LobbyPlayer player, Integer page) {
        Inventory inventory = SpigotLobby.getPlugin().getServer().createInventory(null, slots, title);
        putItems(player, inventory, page);
        player.getPlayer().openInventory(inventory);
    }

    public abstract void putItems(LobbyPlayer player, Inventory inventory, Integer page);

    public abstract void clickItemAction(InventoryClickEvent event, ItemStack itemStack, LobbyPlayer player);

    protected void doDefaultClick(InventoryClickEvent event) {
        if (event.getWhoClicked() != null && event.getWhoClicked() instanceof Player && isInventory(event.getInventory())) {
            event.setCancelled(true);
            if (event.getCurrentItem() != null && event.getCurrentItem().getType() != null && !event.getCurrentItem().getType().equals(Material.AIR)) {
                LobbyPlayer player = SpigotLobby.getPlugin().getCommonUtilities().getLobbyPlayer((Player) event.getWhoClicked());
                clickItemAction(event, event.getCurrentItem(), player);
            }
        }
    }

    private Boolean isInventory(Inventory inventory) {
        if (inventory != null && inventory.getTitle() != null) {
            if (inventory.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }
}
