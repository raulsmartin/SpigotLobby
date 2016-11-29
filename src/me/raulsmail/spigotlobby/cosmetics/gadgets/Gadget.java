package me.raulsmail.spigotlobby.cosmetics.gadgets;

import me.raulsmail.spigotlobby.SpigotLobby;
import me.raulsmail.spigotlobby.utils.ItemBuilder;
import me.raulsmail.spigotlobby.utils.LobbyPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by raulsmail.
 */
public abstract class Gadget implements Listener {

    private LobbyPlayer player;
    private Gadgets type;
    private Boolean equiped;
    private ItemStack item;

    public Gadget(LobbyPlayer player, Gadgets type) {
        this.player = player;
        this.type = type;

        if (player == null || player.getPlayer() == null || SpigotLobby.getPlugin().getServer().getPlayer(player.getPlayer().getUniqueId()) == null) {
            throw new IllegalArgumentException("The player can't be null (it must be online).");
        }

        SpigotLobby.getPlugin().getServer().getPluginManager().registerEvents(this, SpigotLobby.getPlugin());
    }

    public void equip() {
        equiped = true;

        if (player.hasEquippedGadget()) {
            player.removeGadget();
        }

        player.getPlayer().getInventory().setItem(SpigotLobby.getPlugin().getCommonUtilities().getGadgetSlot(), item = new ItemBuilder(type.getMaterial()).setDurability(type.getDurability()).setDisplayName(type.getName()).setLore(type.getLore()).build());//ItemUtils.createItem(type.getMaterial(), type.getDurability(), type.getName(), type.getLore()));
        player.setEquippedGadget(this);
    }

    public void clear() {
        HandlerList.unregisterAll(this);

        player.getPlayer().getInventory().setItem(SpigotLobby.getPlugin().getCommonUtilities().getGadgetSlot(), item = null);

        onClear();
    }

    public abstract void onClear();

    public abstract void onRightClick();

    public abstract void onLeftClick();

    public LobbyPlayer getPlayer() {
        return player;
    }

    public Gadgets getType() {
        return type;
    }

    public Boolean isEquiped() {
        return equiped;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getPlayer().getUniqueId().equals(player.getPlayer().getUniqueId())) {
            if (event.getItem() != null) {
                if (event.getItem().getType() != null && event.getItem().getItemMeta() != null && event.getItem().getItemMeta().getDisplayName() != null) {
                    if (event.getItem().getType().equals(type.getMaterial()) && event.getItem().getItemMeta().getDisplayName().equals(type.getName())) {
                        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
                            onRightClick();
                        } else if (event.getAction().equals(Action.LEFT_CLICK_BLOCK) || event.getAction().equals(Action.LEFT_CLICK_AIR)) {
                            onLeftClick();
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player p = (Player) event.getWhoClicked();
            for (ItemStack itemStack : event.getNewItems().values()) {
                if (itemStack != null && p.equals(player.getPlayer()) && itemStack.equals(item)) {
                    event.setCancelled(true);
                    p.updateInventory();
                }
            }
        }
    }
}
