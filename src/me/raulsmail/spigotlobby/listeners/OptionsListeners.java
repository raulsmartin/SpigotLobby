package me.raulsmail.spigotlobby.listeners;

import me.raulsmail.spigotlobby.SpigotLobby;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by raulsmail.
 */
public class OptionsListeners implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (event.getItem() != null && event.getItem().getType() != null && event.getItem().getItemMeta() != null && event.getItem().getItemMeta().getDisplayName() != null) {
                if (event.getItem().getType().equals(SpigotLobby.getPlugin().getCommonUtilities().optionsMaterial)) {
                    SpigotLobby.getPlugin().getCommonUtilities().getMenus().getOptionsMenu().openMainMenu(SpigotLobby.getPlugin().getCommonUtilities().getLobbyPlayer(event.getPlayer()));
                    event.setCancelled(true);
                }
            }
        }
    }
}
