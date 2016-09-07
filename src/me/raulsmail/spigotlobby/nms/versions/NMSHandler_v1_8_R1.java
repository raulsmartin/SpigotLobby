package me.raulsmail.spigotlobby.nms.versions;

import me.raulsmail.spigotlobby.nms.NMSHandler;
import net.minecraft.server.v1_8_R1.*;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

/**
 * Created by raulsmail.
 */
public class NMSHandler_v1_8_R1 implements NMSHandler {

    @Override
    public void sendTitle(Player player, String title) {
        IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + title + "\"}");
        PacketPlayOutTitle titleBar = new PacketPlayOutTitle(EnumTitleAction.TITLE, icbc);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(titleBar);
    }

    @Override
    public void sendSubTitle(Player player, String subTitle) {
        IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + subTitle + "\"}");
        PacketPlayOutTitle titleBar = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, icbc);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(titleBar);
    }

    @Override
    public void sendActionBar(Player player, String actionBar) {
        IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + actionBar + "\"}");
        PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte) 2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(bar);
    }

    @Override
    public void sendTabHeaderAndFooter(Player player, String header, String footer) {
        IChatBaseComponent tabTitle = ChatSerializer.a("{\"text\": \"" + header + "\"}");
        IChatBaseComponent tabFoot = ChatSerializer.a("{\"text\": \"" + footer + "\"}");
        PacketPlayOutPlayerListHeaderFooter headerFooter = new PacketPlayOutPlayerListHeaderFooter(tabTitle);
        try {
            Field footerField = headerFooter.getClass().getDeclaredField("b");
            footerField.setAccessible(true);
            footerField.set(headerFooter, tabFoot);
            footerField.setAccessible(!footerField.isAccessible());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(headerFooter);
    }
}
