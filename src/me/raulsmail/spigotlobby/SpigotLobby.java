package me.raulsmail.spigotlobby;

import com.google.common.base.Charsets;
import me.raulsmail.spigotlobby.commands.FlyCommand;
import me.raulsmail.spigotlobby.commands.SpigotLobbyCommand;
import me.raulsmail.spigotlobby.listeners.CancelListeners;
import me.raulsmail.spigotlobby.listeners.GeneralListeners;
import me.raulsmail.spigotlobby.listeners.OptionsListeners;
import me.raulsmail.spigotlobby.nms.NMSHandler;
import me.raulsmail.spigotlobby.nms.versions.*;
import me.raulsmail.spigotlobby.storage.MySQL;
import me.raulsmail.spigotlobby.utils.CommonUtilities;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;

/**
 * Created by raulsmail.
 */
public class SpigotLobby extends JavaPlugin {
    private static SpigotLobby plugin;
    private final File configFile = new File(getDataFolder(), "config.yml"), messagesFile = new File(getDataFolder(), "messages.yml");
    private FileConfiguration config, messages;
    private NMSHandler nmsHandler;
    private CommonUtilities commonUtilities;

    public static SpigotLobby getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        if (setupNMSHandler()) {
            commonUtilities = new CommonUtilities();
            saveDefaultConfigs();
            getServer().getPluginManager().registerEvents(new CancelListeners(), plugin);
            getServer().getPluginManager().registerEvents(new GeneralListeners(), plugin);
            getServer().getPluginManager().registerEvents(new OptionsListeners(), plugin);
            getServer().getPluginCommand("fly").setExecutor(new FlyCommand());
            getServer().getPluginCommand("spigotLobby").setExecutor(new SpigotLobbyCommand());

            commonUtilities.setupCommonVariables();
        } else {
            getLogger().severe("Failed while setting up NMS classes!");
            getLogger().severe("Your server version is not compatible with this plugin!");
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        for (Player player : getServer().getOnlinePlayers()) {
            commonUtilities.getStorage().savePlayerInfo(commonUtilities.getLobbyPlayer(player));
        }
        if (commonUtilities.getStorage() instanceof MySQL) {
            try {
                ((MySQL) commonUtilities.getStorage()).closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        getServer().getScheduler().cancelTasks(plugin);
    }

    @Override
    public FileConfiguration getConfig() {
        if (config == null) {
            reloadConfigs();
        }

        return config;
    }

    public FileConfiguration getMessages() {
        if (messages == null) {
            reloadConfigs();
        }

        return messages;
    }

    public NMSHandler getNMSHandler() {
        return nmsHandler;
    }

    public CommonUtilities getCommonUtilities() {
        return commonUtilities;
    }

    private boolean setupNMSHandler() {
        try {
            String version = getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
            getLogger().info("Your server is running version " + version);
            switch (version) {
                case "v1_8_R1":
                    nmsHandler = new NMSHandler_v1_8_R1();
                    break;
                case "v1_8_R2":
                    nmsHandler = new NMSHandler_v1_8_R2();
                    break;
                case "v1_8_R3":
                    nmsHandler = new NMSHandler_v1_8_R3();
                    break;
                case "v1_9_R1":
                    nmsHandler = new NMSHandler_v1_9_R1();
                    break;
                case "v1_9_R2":
                    nmsHandler = new NMSHandler_v1_9_R2();
                    break;
                case "v1_10_R1":
                    nmsHandler = new NMSHandler_v1_10_R1();
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        return nmsHandler != null;
    }

    private void reloadConfigs() {
        config = YamlConfiguration.loadConfiguration(configFile);
        InputStream defConfigStream = getResource("config.yml");
        messages = YamlConfiguration.loadConfiguration(messagesFile);
        InputStream defMessagesStream = getResource("messages.yml");
        if (defConfigStream != null) {
            config.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream, Charsets.UTF_8)));
        }
        if (defMessagesStream != null) {
            messages.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(defMessagesStream, Charsets.UTF_8)));
        }
    }

    private void saveDefaultConfigs() {
        if (!configFile.exists()) {
            saveResource("config.yml", false);
        }
        if (!messagesFile.exists()) {
            saveResource("messages.yml", false);
        }
    }
}
