package me.raulsmail.spigotlobby.storage;

import me.raulsmail.spigotlobby.SpigotLobby;
import me.raulsmail.spigotlobby.utils.LobbyPlayer;

import java.sql.*;
import java.util.HashMap;

/**
 * Created by raulsmail.
 */
public class MySQL implements Storage {

    private Connection connection;
    private String hostname, port, database, user, password;

    public MySQL(String hostname, String port, String database, String user, String password) {
        this.connection = null;
        this.hostname = hostname;
        this.port = port;
        this.database = database;
        this.user = user;
        this.password = password;

        try {
            Statement statement = openConnection().createStatement();
            if (statement.executeUpdate("CREATE TABLE IF NOT EXISTS `spigotlobby` ( `id` INT NOT NULL AUTO_INCREMENT, `uuid` VARCHAR(36) NOT NULL, `chatEnabled` TINYINT(1) NOT NULL, `playersEnabled` TINYINT(1) NOT NULL, `petsEnabled` TINYINT(1) NOT NULL, `alertsEnabled` TINYINT(1) NOT NULL, PRIMARY KEY (`id`), UNIQUE (`id`), UNIQUE (`uuid`));") != 0) {
                SpigotLobby.getPlugin().getLogger().severe("Error creating the MySQL table!");
                SpigotLobby.getPlugin().getCommonUtilities().setStorage(new LocalFile());
            } else {
                SpigotLobby.getPlugin().getCommonUtilities().setStorage(this);
            }
            statement.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            SpigotLobby.getPlugin().getCommonUtilities().setStorage(new LocalFile());
        }
    }

    @Override
    public Boolean createPlayerInfo(LobbyPlayer player) {
        if (!existsPlayerInfo(player)) {
            try {
                Statement statement = openConnection().createStatement();
                if (statement.executeUpdate("INSERT INTO `spigotlobby` (`uuid`, `chatEnabled`, `playersEnabled`, `petsEnabled`, `alertsEnabled`) VALUES ('" + player.getPlayer().getUniqueId().toString() + "', 1, 1, 1, 1);") != 1) {
                    SpigotLobby.getPlugin().getLogger().severe("Error creating the " + player.getPlayer().getName() + "'s information to the MySQL table!");
                    return false;
                }
                statement.close();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                return false;
            }
        }
        HashMap<String, Object> res = getPlayerInfo(player);
        if (res != null && !res.isEmpty()) {
            if (res.get("chatEnabled") instanceof Boolean) {
                player.setChatEnabled((Boolean) res.get("chatEnabled"));
            }
            if (res.get("playersEnabled") instanceof Boolean) {
                player.setPlayersEnabled((Boolean) res.get("playersEnabled"));
            }
            if (res.get("petsEnabled") instanceof Boolean) {
                player.setPetsEnabled((Boolean) res.get("petsEnabled"));
            }
            if (res.get("alertsEnabled") instanceof Boolean) {
                player.setAlertsEnabled((Boolean) res.get("alertsEnabled"));
            }
            return true;
        }
        return false;
    }

    @Override
    public Boolean existsPlayerInfo(LobbyPlayer player) {
        HashMap<String, Object> res = getPlayerInfo(player);
        return res != null && !res.isEmpty();
    }

    @Override
    public Boolean savePlayerInfo(LobbyPlayer player) {
        try {
            Statement statement = openConnection().createStatement();
            if (statement.executeUpdate("UPDATE `spigotlobby` SET `chatEnabled` = '" + (player.hasChatEnabled() ? 1 : 0) + "', `playersEnabled` = '" + (player.hasPlayersEnabled() ? 1 : 0) + "', `petsEnabled` = '" + (player.hasPetsEnabled() ? 1 : 0) + "', `alertsEnabled` = '" + (player.hasAlertsEnabled() ? 1 : 0) + "' WHERE uuid = '" + player.getPlayer().getUniqueId().toString() + "';") != 1) {
                SpigotLobby.getPlugin().getLogger().severe("Error updating the " + player.getPlayer().getName() + "'s information to the MySQL table!");
                return false;
            }
            statement.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private HashMap<String, Object> getPlayerInfo(LobbyPlayer player) {
        HashMap<String, Object> objects = new HashMap<>();
        ResultSet res;
        try {
            Statement statement = connection.createStatement();
            res = statement.executeQuery("SELECT * FROM `spigotlobby` WHERE uuid = '" + player.getPlayer().getUniqueId().toString() + "';");
            if (res.next()) {
                objects = new HashMap<>();
                for (int i = 1; i <= res.getMetaData().getColumnCount(); ++i) {
                    objects.put(res.getMetaData().getColumnName(i), res.getObject(i));
                }
            }
            res.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objects;
    }

    private Connection openConnection() throws SQLException, ClassNotFoundException {
        if (checkConnection()) {
            return connection;
        }
        String connectionURL = "jdbc:mysql://" + hostname + ":" + port;
        if (database != null) {
            connectionURL += "/" + database;
        }
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(connectionURL, user, password);
        return connection;
    }

    private boolean checkConnection() throws SQLException {
        return connection != null && !connection.isClosed();
    }

    public boolean closeConnection() throws SQLException {
        if (connection == null) {
            return false;
        }
        connection.close();
        return true;
    }
}
