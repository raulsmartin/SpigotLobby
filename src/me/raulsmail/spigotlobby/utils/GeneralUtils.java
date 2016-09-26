package me.raulsmail.spigotlobby.utils;

/**
 * Created by raulsmail.
 */
public class GeneralUtils {

    public static Integer getInteger(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException ignored) {
        }
        return 0;
    }
}
