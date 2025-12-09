
package tu.paquete.data;

import java.sql.*;
import java.util.logging.Level;
import tu.paquete.BedWarsPlugin;

public class MySQLManager {

    private final BedWarsPlugin plugin;
    private Connection connection;
    private String host, database, username, password;
    private int port;

    public MySQLManager(BedWarsPlugin plugin, String host, int port, String database, String username, String password) {
        this.plugin = plugin;
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    public boolean connect() {
        try {
            synchronized (this) {
                if (connection != null && !connection.isClosed()) return true;
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false&serverTimezone=UTC";
                connection = DriverManager.getConnection(url, username, password);
                plugin.getLogger().info("MySQL conectado.");
                createTablesIfNeeded();
                return true;
            }
        } catch (Exception e) {
            plugin.getLogger().log(Level.SEVERE, "Error conectando a MySQL: " + e.getMessage(), e);
            return false;
        }
    }

    public Connection getConnection() { return connection; }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) connection.close();
        } catch (SQLException ex) {
            plugin.getLogger().severe("Error cerrando MySQL: " + ex.getMessage());
        }
    }

    private void createTablesIfNeeded() {
        try (Statement st = connection.createStatement()) {
            st.executeUpdate("CREATE TABLE IF NOT EXISTS bw_players (uuid VARCHAR(36) PRIMARY KEY, kills INT DEFAULT 0, deaths INT DEFAULT 0, wins INT DEFAULT 0, losses INT DEFAULT 0, level INT DEFAULT 1)");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS bw_upgrades (uuid VARCHAR(36), upgrade VARCHAR(64), level INT, PRIMARY KEY (uuid, upgrade))");
        } catch (SQLException e) {
            plugin.getLogger().severe("Error creando tablas MySQL: " + e.getMessage());
        }
    }
}
