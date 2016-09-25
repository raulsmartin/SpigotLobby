package me.raulsmail.spigotlobby.menus;

import me.raulsmail.spigotlobby.SpigotLobby;
import me.raulsmail.spigotlobby.utils.ItemUtils;
import me.raulsmail.spigotlobby.utils.LobbyPlayer;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created by raulsmail.
 */
public class CosmeticsMenu extends Menu {

    CosmeticsMenu() {
        super(45, SpigotLobby.getPlugin().getConfig().getString("menus.cosmeticsMenu.title").replaceAll("&", "§"));
    }

    @Override
    public void putItems(LobbyPlayer player, Inventory inventory) {
        inventory.setItem(10, ItemUtils.createItem(Material.GOLD_CHESTPLATE, "§aWardrobe"));
        inventory.setItem(12, ItemUtils.createItem(Material.DIAMOND_HELMET, "§aHats"));
        inventory.setItem(14, ItemUtils.createItem(Material.BONE, "§aPets"));
        inventory.setItem(16, ItemUtils.createItem(Material.PISTON_BASE, "§aGadgets"));
        inventory.setItem(20, ItemUtils.createItem(Material.SADDLE, "§aMounts"));
        inventory.setItem(22, ItemUtils.createItem(Material.RED_ROSE, (short) 4, "§aTrail"));
        inventory.setItem(24, ItemUtils.createHeadByByte64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTE2M2RhZmFjMWQ5MWE4YzkxZGI1NzZjYWFjNzg0MzM2NzkxYTZlMThkOGY3ZjYyNzc4ZmM0N2JmMTQ2YjYifX19", "§cComing Soon"));
        inventory.setItem(40, ItemUtils.createItem(Material.ARROW, "§aBack"));
    }

    @Override
    public void clickItemAction(InventoryClickEvent event, ItemStack itemStack, LobbyPlayer player) {
        switch (itemStack.getType()) {
            case ARROW:
                player.getPlayer().closeInventory();
                break;
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        doDefaultClick(event);
    }
}
