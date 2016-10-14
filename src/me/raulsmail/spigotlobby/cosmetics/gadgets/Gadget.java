package me.raulsmail.spigotlobby.cosmetics.gadgets;

import me.raulsmail.spigotlobby.SpigotLobby;
import me.raulsmail.spigotlobby.utils.LobbyPlayer;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

/**
 * Created by raulsmail.
 */
public abstract class Gadget implements Listener {
    private LobbyPlayer player;
    private Gadgets type;

    public Gadget(LobbyPlayer player, Gadgets type) {
        this.player = player;
        this.type = type;
        SpigotLobby.getPlugin().getServer().getPluginManager().registerEvents(this, SpigotLobby.getPlugin());
    }

    public void clearGadget() {
        HandlerList.unregisterAll(this);
    }

    public LobbyPlayer getPlayer() {
        return player;
    }

    public Gadgets getType() {
        return type;
    }
}
