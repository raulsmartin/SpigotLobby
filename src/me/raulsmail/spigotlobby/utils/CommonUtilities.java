package me.raulsmail.spigotlobby.utils;

import me.raulsmail.spigotlobby.SpigotLobby;
import me.raulsmail.spigotlobby.menus.Menus;
import me.raulsmail.spigotlobby.storage.LocalFile;
import me.raulsmail.spigotlobby.storage.MySQL;
import me.raulsmail.spigotlobby.storage.Storage;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Created by raulsmail.
 */
public class CommonUtilities extends CommonVariables {

    public void setupCommonVariables() {
        menus = new Menus();
        cosmeticsMaterial = getMaterial("events.join.cosmeticsItem.id");
        optionsMaterial = getMaterial("events.join.optionsItem.id");
        players = new HashMap<>();
        oldVersion = SpigotLobby.getPlugin().getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3].contains("v1_8_R");
        gadgetSlot = SpigotLobby.getPlugin().getConfig().getInt("gadgets.slot");
        if (SpigotLobby.getPlugin().getConfig().getBoolean("mysql.enabled")) {
            String hostname = SpigotLobby.getPlugin().getConfig().getString("mysql.hostname", "localhost");
            Integer port = SpigotLobby.getPlugin().getConfig().getInt("mysql.port", 3306);
            String database = SpigotLobby.getPlugin().getConfig().getString("mysql.database", "spigotlobby");
            String username = SpigotLobby.getPlugin().getConfig().getString("mysql.username", "root");
            String password = SpigotLobby.getPlugin().getConfig().getString("mysql.password", "root");
            if (!hostname.equals("") && !database.equals("") && !username.equals("") && !password.equals("")) {
                if (port.toString().equals("")) {
                    port = 3306;
                }
            }
            new MySQL(hostname, port.toString(), database, username, password);
        } else {
            storage = new LocalFile();
        }
        for (Player player : SpigotLobby.getPlugin().getServer().getOnlinePlayers()) {
            new LobbyPlayer(player);
        }
    }

    public Material getCosmeticsMaterial() {
        return cosmeticsMaterial;
    }

    public Material getOptionsMaterial() {
        return optionsMaterial;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public Menus getMenus() {
        return menus;
    }

    public Boolean isOldVersion() {
        return oldVersion;
    }

    public Integer getGadgetSlot() {
        return gadgetSlot;
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
        player.sendMessage("§e------------ §6[§9SpigotLobby Help§6]§e ------------");
        player.sendMessage("");
        switch (page) {
            case 1:
                player.sendMessage("   §e/fly §6- §9Toggles Fly Mode");
                player.sendMessage("   §e/sl menu [name] §6- §9Show The Specified Menu");
                player.sendMessage("   §e/sl help [page] §6- §9Show The Help Page");
                break;
        }
        player.sendMessage("");
        player.sendMessage("   §9Page: §e" + page + "§6/§e1");
        player.sendMessage("§e-----------------------------------------");
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
        givePlayerItemFormConfig(player, cosmeticsMaterial, "cosmeticsItem");
        givePlayerItemFormConfig(player, optionsMaterial, "optionsItem");
    }

    private void givePlayerItemFormConfig(Player player, Material material, String itemConfig) {
        player.getInventory().setItem(SpigotLobby.getPlugin().getConfig().getInt("events.join." + itemConfig + ".slot"), new ItemBuilder(material).setDurability((short) SpigotLobby.getPlugin().getConfig().getInt("events.join." + itemConfig + ".durability")).setDisplayName(SpigotLobby.getPlugin().getConfig().getString("events.join." + itemConfig + ".name").replaceAll("&", "§")).build());
    }
}
