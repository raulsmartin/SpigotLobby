package me.raulsmail.spigotlobby.storage;

import me.raulsmail.spigotlobby.SpigotLobby;
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
            location.mkdirs();
        }
        if (!existsPlayerInfo(player)) {
            try {
                if (playerFileLoc.createNewFile()) {
                    data = YamlConfiguration.loadConfiguration(playerFileLoc);
                    data.set("test", "Hi!");
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

        player.setTest(data.getString("test"));
        configurations.put(player, data);
        return true;
    }

    @Override
    public Boolean existsPlayerInfo(LobbyPlayer player) {
        return new File(location, player.getPlayer().getUniqueId().toString() + ".yml").exists();
    }
}
