package me.raulsmail.spigotlobby.menus.types;

import me.raulsmail.spigotlobby.SpigotLobby;
import me.raulsmail.spigotlobby.menus.Menu;
import me.raulsmail.spigotlobby.utils.ItemBuilder;
import me.raulsmail.spigotlobby.utils.LobbyPlayer;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created by raulsmail.
 */
public class OptionsMenu extends Menu {

    public OptionsMenu() {
        super(45, SpigotLobby.getPlugin().getConfig().getString("menus.optionsMenu.title").replaceAll("&", "§"));
    }

    @Override
    public void putItems(LobbyPlayer player, Inventory inventory, Integer page) {
        inventory.setItem(10, new ItemBuilder(Material.BOOK_AND_QUILL).setDisplayName((player.hasChatEnabled() ? "§a" : "§c") + "Toggle Chat Visibility").build());//ItemUtils.createItem(Material.BOOK_AND_QUILL, (player.hasChatEnabled() ? "§a" : "§c") + "Toggle Chat Visibility"));
        inventory.setItem(19, new ItemBuilder(Material.STAINED_CLAY).setDurability(player.hasChatEnabled() ? (short) 5 : (short) 14).setDisplayName(player.hasChatEnabled() ? "§aEnabled" : "§cDisabled").build());//ItemUtils.createItem(Material.STAINED_CLAY, player.hasChatEnabled() ? (short) 5 : (short) 14, player.hasChatEnabled() ? "§aEnabled" : "§cDisabled"));
        inventory.setItem(12, new ItemBuilder(Material.EYE_OF_ENDER).setDisplayName((player.hasPlayersEnabled() ? "§a" : "§c") + "Toggle Player Visibility").build());//ItemUtils.createItem(Material.EYE_OF_ENDER, (player.hasPlayersEnabled() ? "§a" : "§c") + "Toggle Player Visibility"));
        inventory.setItem(21, new ItemBuilder(Material.STAINED_CLAY).setDurability(player.hasPlayersEnabled() ? (short) 5 : (short) 14).setDisplayName(player.hasPlayersEnabled() ? "§aEnabled" : "§cDisabled").build());//ItemUtils.createItem(Material.STAINED_CLAY, player.hasPlayersEnabled() ? (short) 5 : (short) 14, player.hasPlayersEnabled() ? "§aEnabled" : "§cDisabled"));
        inventory.setItem(14, new ItemBuilder(Material.BONE).setDisplayName((player.hasPetsEnabled() ? "§a" : "§c") + "Toggle Pet Visibility").build());//ItemUtils.createItem(Material.BONE, (player.hasPetsEnabled() ? "§a" : "§c") + "Toggle Pet Visibility"));
        inventory.setItem(23, new ItemBuilder(Material.STAINED_CLAY).setDurability(player.hasPetsEnabled() ? (short) 5 : (short) 14).setDisplayName(player.hasPetsEnabled() ? "§aEnabled" : "§cDisabled").build());//ItemUtils.createItem(Material.STAINED_CLAY, player.hasPetsEnabled() ? (short) 5 : (short) 14, player.hasPetsEnabled() ? "§aEnabled" : "§cDisabled"));
        inventory.setItem(16, new ItemBuilder(Material.JUKEBOX).setDisplayName((player.hasAlertsEnabled() ? "§a" : "§c") + "Toggle Chat Alerts").build());//ItemUtils.createItem(Material.JUKEBOX, (player.hasAlertsEnabled() ? "§a" : "§c") + "Toggle Chat Alerts"));
        inventory.setItem(25, new ItemBuilder(Material.STAINED_CLAY).setDurability(player.hasAlertsEnabled() ? (short) 5 : (short) 14).setDisplayName(player.hasAlertsEnabled() ? "§aEnabled" : "§cDisabled").build());//ItemUtils.createItem(Material.STAINED_CLAY, player.hasAlertsEnabled() ? (short) 5 : (short) 14, player.hasAlertsEnabled() ? "§aEnabled" : "§cDisabled"));
        inventory.setItem(40, new ItemBuilder(SpigotLobby.getPlugin().getCommonUtilities().getOptionsMaterial()).setDisplayName("§cClose").build());//ItemUtils.createItem(SpigotLobby.getPlugin().getCommonUtilities().getOptionsMaterial(), "§cClose"));
    }

    @Override
    public void clickItemAction(InventoryClickEvent event, ItemStack itemStack, LobbyPlayer player) {
        switch (itemStack.getType()) {
            case BOOK_AND_QUILL:
                player.setChatEnabled(!player.hasChatEnabled());
                openMainMenu(player);
                break;
            case EYE_OF_ENDER:
                player.setPlayersEnabled(!player.hasPlayersEnabled());
                openMainMenu(player);
                break;
            case BONE:
                player.setPetsEnabled(!player.hasPetsEnabled());
                openMainMenu(player);
                break;
            case JUKEBOX:
                player.setAlertsEnabled(!player.hasAlertsEnabled());
                openMainMenu(player);
                break;
            case STAINED_CLAY:
                if (event != null) {
                    ItemStack item = event.getInventory().getItem(event.getSlot() - 9);
                    if (item != null && item.getType() != null) {
                        clickItemAction(null, item, player);
                    }
                }
                break;
            default:
                if (itemStack.getType().equals(SpigotLobby.getPlugin().getCommonUtilities().getOptionsMaterial())) {
                    player.getPlayer().closeInventory();
                }
                break;
        }
    }
}
