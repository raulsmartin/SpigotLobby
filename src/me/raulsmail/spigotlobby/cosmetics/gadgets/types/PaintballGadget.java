package me.raulsmail.spigotlobby.cosmetics.gadgets.types;

import me.raulsmail.spigotlobby.cosmetics.gadgets.Gadget;
import me.raulsmail.spigotlobby.cosmetics.gadgets.Gadgets;
import me.raulsmail.spigotlobby.utils.LobbyPlayer;
import org.bukkit.entity.EnderPearl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by raulsmail.
 */
public class PaintballGadget extends Gadget {

    private Map<UUID, ArrayList<EnderPearl>> enderPearls = new HashMap<>();

    public PaintballGadget(LobbyPlayer player) {
        super(player, Gadgets.PAINTBALL);
        equip();
    }

    @Override
    public void onClear() {
        for (ArrayList<EnderPearl> pearls : enderPearls.values()) {
            for (EnderPearl enderPearl : pearls) {
                enderPearl.remove();
            }
        }
    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void onLeftClick() {

    }
}
