package me.raulsmail.spigotlobby.menus.types;

import me.raulsmail.spigotlobby.SpigotLobby;
import me.raulsmail.spigotlobby.cosmetics.wardrobe.Clothes;
import me.raulsmail.spigotlobby.menus.Menu;
import me.raulsmail.spigotlobby.utils.GeneralUtils;
import me.raulsmail.spigotlobby.utils.ItemUtils;
import me.raulsmail.spigotlobby.utils.LobbyPlayer;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by raulsmail.
 */
public class WardrobeMenu extends Menu {
    private List<Integer> availableSlots;

    public WardrobeMenu() {
        super(45, SpigotLobby.getPlugin().getConfig().getString("menus.wardrobeMenu.title").replaceAll("&", "§"));
        availableSlots = new ArrayList<>(Arrays.asList(1, 10, 19, 28, 3, 12, 21, 30, 5, 14, 23, 32, 7, 16, 25, 34));
    }

    @Override
    public void putItems(LobbyPlayer player, Inventory inventory, Integer page) {
        Integer slot = getNextSlot(0);

        if (page < 1) {
            page = 1;
        }

        Integer totalSlots = availableSlots.size();
        Integer maxPerPageItem = page * totalSlots;
        Integer minPerPageItem = maxPerPageItem - totalSlots;
        Integer clothesAmount = Clothes.values().length;
        Integer lastPageItem = 0;

        for (int i = minPerPageItem; i < maxPerPageItem; i++) {
            if (i < clothesAmount) {
                lastPageItem = i + 1;
                Clothes clothes = Clothes.values()[i];
                inventory.setItem(slot, ItemUtils.createClothes(clothes.getMaterial(), clothes.getDurability(), clothes.getDisplayName(), clothes.getColor()));
                slot = getNextSlot(slot);
            }
        }

        if (page.equals(1)) {
            inventory.setItem(44, ItemUtils.createItem(Material.ARROW, "§aPage 2"));
        } else if (lastPageItem.equals(clothesAmount)) {
            inventory.setItem(36, ItemUtils.createItem(Material.ARROW, "§aPage " + (page - 1)));
        } else {
            inventory.setItem(36, ItemUtils.createItem(Material.ARROW, "§aPage " + (page - 1)));
            inventory.setItem(44, ItemUtils.createItem(Material.ARROW, "§aPage " + (page + 1)));
        }

        inventory.setItem(40, ItemUtils.createItem(SpigotLobby.getPlugin().getCommonUtilities().getCosmeticsMaterial(), "§cBack"));
    }

    @Override
    public void clickItemAction(InventoryClickEvent event, ItemStack itemStack, LobbyPlayer player) {
        switch (itemStack.getType()) {
            case ARROW:
                if (itemStack.getItemMeta() != null && itemStack.getItemMeta().getDisplayName() != null) {
                    if (itemStack.getItemMeta().getDisplayName().contains("Page")) {
                        SpigotLobby.getPlugin().getCommonUtilities().getMenus().getWardrobeMenu().openMainMenu(player, GeneralUtils.getInteger(itemStack.getItemMeta().getDisplayName().split(" ")[1]));
                    }
                }
                break;
            default:
                if (itemStack.getType().equals(SpigotLobby.getPlugin().getCommonUtilities().getCosmeticsMaterial())) {
                    SpigotLobby.getPlugin().getCommonUtilities().getMenus().getCosmeticsMenu().openMainMenu(player);
                }
                break;
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        doDefaultClick(event);
    }

    private Integer getNextSlot(Integer previous) {
        if (previous != 0) {
            for (Integer current : availableSlots) {
                if (previous.equals(current)) {
                    Integer nextId = availableSlots.indexOf(current) + 1;
                    if (availableSlots.size() > nextId) {
                        return availableSlots.get(nextId);
                    }
                }
            }
        }
        return availableSlots.get(0);
    }
}
