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
            if (statement.executeUpdate("CREATE TABLE IF NOT EXISTS `spigotlobby` ( `id` INT NOT NULL AUTO_INCREMENT, `uuid` VARCHAR(36) NOT NULL, `test` VARCHAR(16) NOT NULL, PRIMARY KEY (`id`), UNIQUE (`id`), UNIQUE (`uuid`));") != 0) {
                SpigotLobby.getPlugin().getLogger().severe("Error creating the MySQL table!");
                SpigotLobby.getPlugin().getCommonUtilities().storage = new LocalFile();
            } else {
                SpigotLobby.getPlugin().getCommonUtilities().storage = this;
            }
            statement.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            SpigotLobby.getPlugin().getCommonUtilities().storage = new LocalFile();
        }
    }

    @Override
    public Boolean createPlayerInfo(LobbyPlayer player) {
        if (!existsPlayerInfo(player)) {
            try {
                Statement statement = openConnection().createStatement();
                if (statement.executeUpdate("INSERT INTO `spigotlobby` (`uuid`, `test`) VALUES ('" + player.getPlayer().getUniqueId().toString() + "', 'Hi!');") != 1) {
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
            player.setTest(res.get("test").toString());
            return true;
        }
        return false;
    }

    @Override
    public Boolean existsPlayerInfo(LobbyPlayer player) {
        HashMap<String, Object> res = getPlayerInfo(player);
        return res != null && !res.isEmpty();
        /*Boolean doReturn = false;
        try {
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM `spigotlobby` WHERE uuid = '" + player.getPlayer().getUniqueId().toString() + "';");
            if (res.next()) {
                doReturn = true;
            }
            res.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doReturn;*/
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
