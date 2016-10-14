package me.raulsmail.spigotlobby.storage;

import me.raulsmail.spigotlobby.SpigotLobby;
import me.raulsmail.spigotlobby.cosmetics.gadgets.Gadgets;
import me.raulsmail.spigotlobby.utils.LobbyPlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by raulsmail.
 */
public class LocalFile implements Storage {
    private File location = new File(SpigotLobby.getPlugin().getDataFolder() + File.separator + "players");
    private Map<LobbyPlayer, FileConfiguration> configurations = new HashMap<>();

    @Override
    public Boolean createPlayerInfo(LobbyPlayer player) {
        File playerFileLoc = new File(location, player.getPlayer().getUniqueId().toString() + ".yml");
        FileConfiguration data = null;
        if (!location.exists()) {
            if (!location.mkdirs()) {
                SpigotLobby.getPlugin().getLogger().warning(player.getPlayer().getName() + "'s file couldn't be created, please contact with the developer.");
            }
        }
        if (!existsPlayerInfo(player)) {
            try {
                if (playerFileLoc.createNewFile()) {
                    data = YamlConfiguration.loadConfiguration(playerFileLoc);
                    data.set("chatEnabled", true);
                    data.set("playersEnabled", true);
                    data.set("petsEnabled", true);
                    data.set("alertsEnabled", true);
                    data.set("currentGadget", 0);
                    data.save(playerFileLoc);
                } else {
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        if (data == null) {
            data = YamlConfiguration.loadConfiguration(playerFileLoc);
        }

        player.setChatEnabled(data.getBoolean("chatEnabled"));
        player.setPlayersEnabled(data.getBoolean("playersEnabled"));
        player.setPetsEnabled(data.getBoolean("petsEnabled"));
        player.setAlertsEnabled(data.getBoolean("alertsEnabled"));
        player.setCurrentGadget(Gadgets.getGadget(player, data.getInt("currentGadget")));
        configurations.put(player, data);
        return true;
    }

    @Override
    public Boolean existsPlayerInfo(LobbyPlayer player) {
        return new File(location, player.getPlayer().getUniqueId().toString() + ".yml").exists();
    }

    @Override
    public Boolean savePlayerInfo(LobbyPlayer player) {
        if (configurations.containsKey(player) && existsPlayerInfo(player)) {
            FileConfiguration data = configurations.get(player);
            if (data != null) {
                try {
                    data.set("chatEnabled", player.hasChatEnabled());
                    data.set("playersEnabled", player.hasPlayersEnabled());
                    data.set("petsEnabled", player.hasPetsEnabled());
                    data.set("alertsEnabled", player.hasAlertsEnabled());
                    data.set("currentGadget", player.hasActiveGadget() ? player.getCurrentGadget().getType().ordinal() + 1 : 0);
                    data.save(new File(location, player.getPlayer().getUniqueId().toString() + ".yml"));
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
