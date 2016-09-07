package me.raulsmail.spigotlobby.utils;

import me.raulsmail.spigotlobby.SpigotLobby;
import me.raulsmail.spigotlobby.menus.Menus;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

/**
 * Created by raulsmail.
 */
public class CommonUtilities extends CommonVariables {

    public void setupCommonVariables() {
        menus = new Menus();
        optionsMaterial = getMaterial("events.join.optionsItem.id");
        players = new HashMap<>();
        for (Player player : SpigotLobby.getPlugin().getServer().getOnlinePlayers()) {
            new LobbyPlayer(player);
        }
    }

    public Menus getMenus() {
        return menus;
    }

    public LobbyPlayer getLobbyPlayer(Player player) {
        return players.get(player);
    }

    public void addLobbyPlayer(LobbyPlayer player) {
        players.put(player.getPlayer(), player);
    }

    public void removeLobbyPlayer(Player player) {
        players.remove(player);
    }

    public Boolean hasPlayerPermission(Player player, String permission) {
        Boolean hasPermission = false;
        if (!SpigotLobby.getPlugin().getConfig().getBoolean("permissions.admin") && !SpigotLobby.getPlugin().getConfig().getBoolean("permissions.commands." + permission)) {
            hasPermission = true;
        } else {
            if (SpigotLobby.getPlugin().getConfig().getBoolean("permissions.admin")) {
                if (player.hasPermission("lobby.admin")) {
                    hasPermission = true;
                }
            }
            if (SpigotLobby.getPlugin().getConfig().getBoolean("permissions.commands." + permission)) {
                if (player.hasPermission("lobby." + permission)) {
                    hasPermission = true;
                }
            }
        }
        return hasPermission;
    }

    public void showHelp(Player player, Integer page) {
        if (page <= 0) {
            page = 1;
        }
        player.sendMessage("§e----------- §6[§9SpigotLobby Help§6]§e -----------");
        switch (page) {
            case 1:
                player.sendMessage("   §e/fly                  §6- §9Toggles Fly Mode");
                player.sendMessage("   §e/sl help [number] §6- §9Show That Help Page");
                break;
        }
        player.sendMessage("");
        player.sendMessage("   §9Page: §e" + page + "§6/§e1");
        player.sendMessage("§e---------------------------------------");
    }

    public Integer getInteger(Player player, String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException ignored) {
            if (player != null) {
                player.sendMessage(SpigotLobby.getPlugin().getMessages().getString("general.notNumber").replaceAll("&", "§").replaceAll("%number%", number));
            }
        }
        return -1;
    }

    private Material getMaterial(String config) {
        String materialString = SpigotLobby.getPlugin().getConfig().getString(config);
        Integer id = getInteger(null, materialString);
        return id != 1 ? Material.getMaterial(id) : Material.getMaterial(materialString.toUpperCase());
    }

    public void givePlayerJoinOptionsItem(Player player) {
        ItemStack item = new ItemStack(optionsMaterial, 1, (short) SpigotLobby.getPlugin().getConfig().getInt("events.join.optionsItem.durability"));
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(SpigotLobby.getPlugin().getConfig().getString("events.join.optionsItem.name").replaceAll("&", "§"));
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        player.getInventory().setItem(SpigotLobby.getPlugin().getConfig().getInt("events.join.optionsItem.slot"), item);
    }
}
