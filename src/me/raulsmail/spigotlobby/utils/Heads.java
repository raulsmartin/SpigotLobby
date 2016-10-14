package me.raulsmail.spigotlobby.utils;

/**
 * Created by raulsmail.
 */
public enum Heads {
    QUESTION_MARK("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTE2M2RhZmFjMWQ5MWE4YzkxZGI1NzZjYWFjNzg0MzM2NzkxYTZlMThkOGY3ZjYyNzc4ZmM0N2JmMTQ2YjYifX19");

    String texture;

    Heads(String texture) {
        this.texture = texture;
    }

    public String getTexture() {
        return texture;
    }
}
