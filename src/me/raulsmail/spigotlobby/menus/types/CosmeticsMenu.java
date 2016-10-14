package me.raulsmail.spigotlobby.menus.types;

import me.raulsmail.spigotlobby.SpigotLobby;
import me.raulsmail.spigotlobby.menus.Menu;
import me.raulsmail.spigotlobby.utils.Heads;
import me.raulsmail.spigotlobby.utils.ItemBuilder;
import me.raulsmail.spigotlobby.utils.LobbyPlayer;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created by raulsmail.
 */
public class CosmeticsMenu extends Menu {

    public CosmeticsMenu() {
        super(45, SpigotLobby.getPlugin().getConfig().getString("menus.cosmeticsMenu.title").replaceAll("&", "§"));
    }

    @Override
    public void putItems(LobbyPlayer player, Inventory inventory, Integer page) {
        inventory.setItem(10, new ItemBuilder(Material.GOLD_CHESTPLATE).setDisplayName("§aWardrobe").build());//ItemUtils.createItem(Material.GOLD_CHESTPLATE, "§aWardrobe"));
        inventory.setItem(12, new ItemBuilder(Material.DIAMOND_HELMET).setDisplayName("§aHats").build());//ItemUtils.createItem(Material.DIAMOND_HELMET, "§aHats"));
        inventory.setItem(14, new ItemBuilder(Material.BONE).setDisplayName("§aPets").build());//ItemUtils.createItem(Material.BONE, "§aPets"));
        inventory.setItem(16, new ItemBuilder(Material.PISTON_BASE).setDisplayName("§aGadgets").build());//ItemUtils.createItem(Material.PISTON_BASE, "§aGadgets"));
        inventory.setItem(20, new ItemBuilder(Material.SADDLE).setDisplayName("§aMounts").build());//ItemUtils.createItem(Material.SADDLE, "§aMounts"));
        inventory.setItem(22, new ItemBuilder(Material.RED_ROSE).setDurability((short) 4).setDisplayName("§aTrails").build());//ItemUtils.createItem(Material.RED_ROSE, (short) 4, "§aTrails"));
        inventory.setItem(24, new ItemBuilder(Material.SKULL_ITEM).setDurability((short) 3).setDisplayName("§cComing Soon").setSkullTextureBase64(Heads.QUESTION_MARK.getTexture()).build());//ItemUtils.createHeadByByte64(ItemUtils.Heads.QUESTION_MARK, "§cComing Soon"));
        inventory.setItem(40, new ItemBuilder(SpigotLobby.getPlugin().getCommonUtilities().getCosmeticsMaterial()).setDisplayName("§cClose").build());//ItemUtils.createItem(SpigotLobby.getPlugin().getCommonUtilities().getCosmeticsMaterial(), "§cClose"));
    }

    @Override
    public void clickItemAction(InventoryClickEvent event, ItemStack itemStack, LobbyPlayer player) {
        switch (itemStack.getType()) {
            case GOLD_CHESTPLATE:
                SpigotLobby.getPlugin().getCommonUtilities().getMenus().getWardrobeMenu().openMainMenu(player);
                break;
            case DIAMOND_HELMET:
                //SpigotLobby.getPlugin().getCommonUtilities().getMenus().getHatsMenu().openMainMenu(player);
                break;
            case BONE:
                //SpigotLobby.getPlugin().getCommonUtilities().getMenus().getPetsMenu().openMainMenu(player);
                break;
            case PISTON_BASE:
                //SpigotLobby.getPlugin().getCommonUtilities().getMenus().getGadgetsMenu().openMainMenu(player);
                break;
            case SADDLE:
                //SpigotLobby.getPlugin().getCommonUtilities().getMenus().getMountsMenu().openMainMenu(player);
                break;
            case RED_ROSE:
                //SpigotLobby.getPlugin().getCommonUtilities().getMenus().getTrailsMenu().openMainMenu(player);
                break;
            case SKULL_ITEM:
                player.getPlayer().sendMessage(SpigotLobby.getPlugin().getMessages().getString("menus.comingSoon").replaceAll("&", "§"));
                break;
            default:
                if (itemStack.getType().equals(SpigotLobby.getPlugin().getCommonUtilities().getCosmeticsMaterial())) {
                    player.getPlayer().closeInventory();
                }
                break;
        }
    }
}
