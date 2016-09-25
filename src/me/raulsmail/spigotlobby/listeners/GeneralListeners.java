package me.raulsmail.spigotlobby.listeners;

import me.raulsmail.spigotlobby.SpigotLobby;
import me.raulsmail.spigotlobby.utils.LobbyPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raulsmail.
 */
public class GeneralListeners implements Listener {

    @EventHandler
    public void onServerListPing(ServerListPingEvent event) {
        if (SpigotLobby.getPlugin().getConfig().getBoolean("events.setMotd")) {
            event.setMotd(SpigotLobby.getPlugin().getMessages().getString("events.motd").replaceAll("&", "§").replace("%newLine%", "\n"));
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        LobbyPlayer lobbyPlayer = new LobbyPlayer(event.getPlayer());
        for (Player player : SpigotLobby.getPlugin().getServer().getOnlinePlayers()) {
            if (!event.getPlayer().getUniqueId().equals(player.getUniqueId())) {
                if (!lobbyPlayer.hasPlayersEnabled()) {
                    event.getPlayer().hidePlayer(player);
                }
                if (!SpigotLobby.getPlugin().getCommonUtilities().getLobbyPlayer(player).hasPlayersEnabled()) {
                    player.hidePlayer(event.getPlayer());
                }
            }
        }
        if (SpigotLobby.getPlugin().getConfig().getBoolean("events.join.sendPlayerChat")) {
            event.getPlayer().sendMessage(SpigotLobby.getPlugin().getMessages().getString("events.join.playerChat").replaceAll("&", "§").replaceAll("%player%", event.getPlayer().getName()));
        }
        if (SpigotLobby.getPlugin().getConfig().getBoolean("events.join.sendGlobalChat")) {
            for (Player player : SpigotLobby.getPlugin().getServer().getOnlinePlayers()) {
                if (!event.getPlayer().getUniqueId().equals(player.getUniqueId())) {
                    player.sendMessage(SpigotLobby.getPlugin().getMessages().getString("events.join.globalChat").replaceAll("&", "§").replaceAll("%player%", event.getPlayer().getName()));
                }
            }
        }
        if (SpigotLobby.getPlugin().getConfig().getBoolean("events.join.sendTitle")) {
            SpigotLobby.getPlugin().getNMSHandler().sendTitle(event.getPlayer(), SpigotLobby.getPlugin().getMessages().getString("events.join.title").replaceAll("&", "§").replaceAll("%player%", event.getPlayer().getName()));
        }
        if (SpigotLobby.getPlugin().getConfig().getBoolean("events.join.sendSubTitle")) {
            SpigotLobby.getPlugin().getNMSHandler().sendSubTitle(event.getPlayer(), SpigotLobby.getPlugin().getMessages().getString("events.join.subTitle").replaceAll("&", "§").replaceAll("%player%", event.getPlayer().getName()));
        }
        if (SpigotLobby.getPlugin().getConfig().getBoolean("events.join.sendActionBar")) {
            SpigotLobby.getPlugin().getNMSHandler().sendActionBar(event.getPlayer(), SpigotLobby.getPlugin().getMessages().getString("events.join.actionBar").replaceAll("&", "§").replaceAll("%player%", event.getPlayer().getName()));
        }
        if (SpigotLobby.getPlugin().getConfig().getBoolean("events.join.sendTabHeaderAndFooter")) {
            SpigotLobby.getPlugin().getNMSHandler().sendTabHeaderAndFooter(event.getPlayer(), SpigotLobby.getPlugin().getMessages().getString("events.join.tabHeader").replaceAll("&", "§").replaceAll("%player%", event.getPlayer().getName()), SpigotLobby.getPlugin().getMessages().getString("events.join.tabFooter").replaceAll("&", "§").replaceAll("%player%", event.getPlayer().getName()));
        }
        if (SpigotLobby.getPlugin().getConfig().getBoolean("events.join.optionsItem.enabled")) {
            SpigotLobby.getPlugin().getCommonUtilities().givePlayerJoinOptionsItem(event.getPlayer());
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
        LobbyPlayer lobbyPlayer = SpigotLobby.getPlugin().getCommonUtilities().getLobbyPlayer(event.getPlayer());
        SpigotLobby.getPlugin().getCommonUtilities().getStorage().savePlayerInfo(lobbyPlayer);
        SpigotLobby.getPlugin().getCommonUtilities().removeLobbyPlayer(event.getPlayer());
        if (SpigotLobby.getPlugin().getConfig().getBoolean("events.quit.sendGlobalChat")) {
            for (Player player : SpigotLobby.getPlugin().getServer().getOnlinePlayers()) {
                if (!event.getPlayer().getUniqueId().equals(player.getUniqueId())) {
                    player.sendMessage(SpigotLobby.getPlugin().getMessages().getString("events.quit.globalChat").replaceAll("&", "§").replaceAll("%player%", event.getPlayer().getName()));
                }
            }
        }
    }

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        if (!SpigotLobby.getPlugin().getCommonUtilities().getLobbyPlayer(event.getPlayer()).hasChatEnabled()) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(SpigotLobby.getPlugin().getMessages().getString("events.chat.disabledChat").replaceAll("&", "§"));
        }
        if (!event.isCancelled()) {
            List<Player> cancelledPlayers = new ArrayList<>();
            for (Player player : event.getRecipients()) {
                LobbyPlayer lobbyPlayer = SpigotLobby.getPlugin().getCommonUtilities().getLobbyPlayer(player);
                if (!lobbyPlayer.hasChatEnabled()) {
                    cancelledPlayers.add(player);
                }
                if (!event.getPlayer().getUniqueId().equals(player.getUniqueId()) && event.getMessage().toLowerCase().contains(player.getName().toLowerCase()) && lobbyPlayer.hasAlertsEnabled()) {
                    lobbyPlayer.alertPlayer();
                }
            }
            event.getRecipients().removeAll(cancelledPlayers);
        }
    }
}
