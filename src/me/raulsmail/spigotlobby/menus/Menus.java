package me.raulsmail.spigotlobby.menus;

/**
 * Created by raulsmail.
 */
public class Menus {
    private final CosmeticsMenu cosmeticsMenu;
    private final OptionsMenu optionsMenu;

    public Menus() {
        cosmeticsMenu = new CosmeticsMenu();
        optionsMenu = new OptionsMenu();
    }

    public CosmeticsMenu getCosmeticsMenu() {
        return cosmeticsMenu;
    }

    public OptionsMenu getOptionsMenu() {
        return optionsMenu;
    }
}
