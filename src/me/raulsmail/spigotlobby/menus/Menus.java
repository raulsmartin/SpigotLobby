package me.raulsmail.spigotlobby.menus;

import me.raulsmail.spigotlobby.menus.types.CosmeticsMenu;
import me.raulsmail.spigotlobby.menus.types.OptionsMenu;
import me.raulsmail.spigotlobby.menus.types.WardrobeMenu;

/**
 * Created by raulsmail.
 */
public class Menus {
    private final CosmeticsMenu cosmeticsMenu;
    private final OptionsMenu optionsMenu;
    private final WardrobeMenu wardrobeMenu;

    public Menus() {
        cosmeticsMenu = new CosmeticsMenu();
        optionsMenu = new OptionsMenu();
        wardrobeMenu = new WardrobeMenu();
    }

    public CosmeticsMenu getCosmeticsMenu() {
        return cosmeticsMenu;
    }

    public OptionsMenu getOptionsMenu() {
        return optionsMenu;
    }

    public WardrobeMenu getWardrobeMenu() {
        return wardrobeMenu;
    }
}
