package me.raulsmail.spigotlobby.nms.versions;

import me.raulsmail.spigotlobby.nms.NMSHandler;
import net.minecraft.server.v1_9_R1.IChatBaseComponent;
import net.minecraft.server.v1_9_R1.PacketPlayOutChat;
import net.minecraft.server.v1_9_R1.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_9_R1.PacketPlayOutTitle;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

/**
 * Created by raulsmail.
 */
public class NMSHandler_v1_9_R1 implements NMSHandler {

    @Override
    public void sendTitle(Player player, String title) {
        IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
        PacketPlayOutTitle titleBar = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, icbc);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(titleBar);
    }

    @Override
    public void sendSubTitle(Player player, String subTitle) {
        IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subTitle + "\"}");
        PacketPlayOutTitle titleBar = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, icbc);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(titleBar);
    }

    @Override
    public void sendActionBar(Player player, String actionBar) {
        IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + actionBar + "\"}");
        PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte) 2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(bar);
    }

    @Override
    public void sendTabHeaderAndFooter(Player player, String header, String footer) {
        IChatBaseComponent tabTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + header + "\"}");
        IChatBaseComponent tabFoot = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + footer + "\"}");
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
