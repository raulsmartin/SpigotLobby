package me.raulsmail.spigotlobby.cosmetics.gadgets;

import me.raulsmail.spigotlobby.cosmetics.gadgets.types.PaintballGadget;
import me.raulsmail.spigotlobby.utils.LobbyPlayer;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by raulsmail.
 */
public enum Gadgets {
    PAINTBALL(PaintballGadget.class);

    private Class<?> gadgetClass;

    Gadgets(Class<?> gadgetClass) {
        this.gadgetClass = gadgetClass;
    }

    public static Gadget getGadget(LobbyPlayer player, Integer id) {
        try {
            if (id > 0) {
                Gadgets type = values()[id - 1];
                if (type != null) {
                    return (Gadget) type.getGadgetClass().getConstructor(LobbyPlayer.class).newInstance(player);
                }
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Class<?> getGadgetClass() {
        return gadgetClass;
    }
}
