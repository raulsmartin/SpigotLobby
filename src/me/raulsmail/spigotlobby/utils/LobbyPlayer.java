package me.raulsmail.spigotlobby.utils;

import me.raulsmail.spigotlobby.SpigotLobby;
import me.raulsmail.spigotlobby.cosmetics.gadgets.Gadget;
import org.bukkit.entity.Player;

import java.util.Collections;

/**
 * Created by raulsmail.
 */
public class LobbyPlayer {
    private Player player;
    private Boolean chatEnabled, playersEnabled, petsEnabled, alertsEnabled;
    private Gadget currentGadget;

    public LobbyPlayer(Player player) {
        this.player = player;
        SpigotLobby.getPlugin().getCommonUtilities().addLobbyPlayer(this);
        SpigotLobby.getPlugin().getCommonUtilities().getStorage().createPlayerInfo(this);
    }

    public Player getPlayer() {
        return player;
    }

    public Boolean hasChatEnabled() {
        return chatEnabled;
    }

    public void setChatEnabled(Boolean chatEnabled) {
        this.chatEnabled = chatEnabled;
    }

    public Boolean hasPlayersEnabled() {
        return playersEnabled;
    }

    public void setPlayersEnabled(Boolean playersEnabled) {
        if (this.playersEnabled != null) {
            ParticleEffect.FIREWORKS_SPARK.display(0, 0, 0, 1, 10, player.getLocation(), Collections.singletonList(player));
            SoundUtils.playSound(player, SoundUtils.Sounds.ENDERMAN_TELEPORT, 1F, 10F);
        }
        this.playersEnabled = playersEnabled;
        if (!this.playersEnabled) {
            for (Player player : SpigotLobby.getPlugin().getServer().getOnlinePlayers()) {
                if (!this.player.getUniqueId().equals(player.getUniqueId())) {
                    this.player.hidePlayer(player);
                }
            }
        } else {
            for (Player player : SpigotLobby.getPlugin().getServer().getOnlinePlayers()) {
                if (!this.player.getUniqueId().equals(player.getUniqueId())) {
                    this.player.showPlayer(player);
                }
            }
        }
    }

    public Boolean hasPetsEnabled() {
        return petsEnabled;
    }

    public void setPetsEnabled(Boolean petsEnabled) {
        this.petsEnabled = petsEnabled;
    }

    public Boolean hasAlertsEnabled() {
        return alertsEnabled;
    }

    public void setAlertsEnabled(Boolean alertsEnabled) {
        this.alertsEnabled = alertsEnabled;
    }

    public Gadget getCurrentGadget() {
        return currentGadget;
    }

    public void setCurrentGadget(Gadget currentGadget) {
        this.currentGadget = currentGadget;
    }

    public Boolean hasActiveGadget() {
        return currentGadget != null;
    }

    public void clearGadget() {
        //TODO: Put things here.
        currentGadget.clearGadget();
        currentGadget = null;
    }

    public void alertPlayer() {
        SoundUtils.playSound(player, SoundUtils.Sounds.ORB_PICKUP, 1F, 10F);
    }
}
