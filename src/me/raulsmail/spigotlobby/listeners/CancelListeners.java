package me.raulsmail.spigotlobby.listeners;

import me.raulsmail.spigotlobby.SpigotLobby;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

/**
 * Created by raulsmail.
 */
public class CancelListeners implements Listener {

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (event.getEntity() != null && event.getEntity() instanceof Player) {
            if (SpigotLobby.getPlugin().getConfig().getBoolean("events.cancelEvents.foodChange")) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() != null && event.getEntity() instanceof Player) {
            if (SpigotLobby.getPlugin().getConfig().getBoolean("events.cancelEvents.damagePlayer")) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getPlayer() != null) {
            if (SpigotLobby.getPlugin().getConfig().getBoolean("events.cancelEvents.breakBlock")) {
                if (SpigotLobby.getPlugin().getConfig().getBoolean("permissions.admin")) {
                    if (event.getPlayer().hasPermission("lobby.admin")) {
                        return;
                    }
                }
                if (SpigotLobby.getPlugin().getConfig().getBoolean("permissions.events.breakBlock")) {
                    if (event.getPlayer().hasPermission("lobby.breakBlock")) {
                        return;
                    }
                }
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlaceBreak(BlockPlaceEvent event) {
        if (event.getPlayer() != null) {
            if (SpigotLobby.getPlugin().getConfig().getBoolean("events.cancelEvents.placeBlock")) {
                if (SpigotLobby.getPlugin().getConfig().getBoolean("permissions.admin")) {
                    if (event.getPlayer().hasPermission("lobby.admin")) {
                        return;
                    }
                }
                if (SpigotLobby.getPlugin().getConfig().getBoolean("permissions.events.placeBlock")) {
                    if (event.getPlayer().hasPermission("lobby.placeBlock")) {
                        return;
                    }
                }
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {
        if (event.getPlayer() != null) {
            if (SpigotLobby.getPlugin().getConfig().getBoolean("events.cancelEvents.pickupItem")) {
                if (SpigotLobby.getPlugin().getConfig().getBoolean("permissions.admin")) {
                    if (event.getPlayer().hasPermission("lobby.admin")) {
                        return;
                    }
                }
                if (SpigotLobby.getPlugin().getConfig().getBoolean("permissions.events.pickupItem")) {
                    if (event.getPlayer().hasPermission("lobby.pickupItem")) {
                        return;
                    }
                }
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        if (event.getPlayer() != null) {
            if (SpigotLobby.getPlugin().getConfig().getBoolean("events.cancelEvents.dropItem")) {
                if (SpigotLobby.getPlugin().getConfig().getBoolean("permissions.admin")) {
                    if (event.getPlayer().hasPermission("lobby.admin")) {
                        return;
                    }
                }
                if (SpigotLobby.getPlugin().getConfig().getBoolean("permissions.events.dropItem")) {
                    if (event.getPlayer().hasPermission("lobby.dropItem")) {
                        return;
                    }
                }
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getPlayer() != null) {
            if (SpigotLobby.getPlugin().getConfig().getBoolean("events.cancelEvents.interact")) {
                if (SpigotLobby.getPlugin().getConfig().getBoolean("permissions.admin")) {
                    if (event.getPlayer().hasPermission("lobby.admin")) {
                        return;
                    }
                }
                if (SpigotLobby.getPlugin().getConfig().getBoolean("permissions.events.interact")) {
                    if (event.getPlayer().hasPermission("lobby.interact")) {
                        return;
                    }
                }
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        if (event.toWeatherState()) {
            if (SpigotLobby.getPlugin().getConfig().getBoolean("events.cancelEvents.weatherChange")) {
                event.setCancelled(true);
            }
        }
    }
}
