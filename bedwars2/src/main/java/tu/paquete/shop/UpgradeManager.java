
package tu.paquete.shop;

import tu.paquete.BedWarsPlugin;
import tu.paquete.data.MySQLManager;
import java.sql.*;

public class UpgradeManager {

    private final BedWarsPlugin plugin;
    private final MySQLManager mysql;

    public UpgradeManager(BedWarsPlugin plugin, MySQLManager mysql) {
        this.plugin = plugin;
        this.mysql = mysql;
    }

    public int getLevel(java.util.UUID uuid, Upgrade up) {
        if (mysql == null || mysql.getConnection() == null) return 0;
        try (PreparedStatement ps = mysql.getConnection().prepareStatement("SELECT level FROM bw_upgrades WHERE uuid=? AND upgrade=?")) {
            ps.setString(1, uuid.toString());
            ps.setString(2, up.name());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("level");
            }
        } catch (SQLException ex) {
            plugin.getLogger().severe("Error leyendo upgrade: " + ex.getMessage());
        }
        return 0;
    }

    public void setLevel(java.util.UUID uuid, Upgrade up, int level) {
        if (mysql == null || mysql.getConnection() == null) return;
        try (PreparedStatement ps = mysql.getConnection().prepareStatement(
                "REPLACE INTO bw_upgrades (uuid, upgrade, level) VALUES (?,?,?)")) {
            ps.setString(1, uuid.toString());
            ps.setString(2, up.name());
            ps.setInt(3, level);
            ps.executeUpdate();
        } catch (SQLException ex) {
            plugin.getLogger().severe("Error guardando upgrade: " + ex.getMessage());
        }
    }

    public boolean canUpgrade(java.util.UUID uuid, Upgrade up) {
        int cur = getLevel(uuid, up);
        return cur < up.getMaxLevel();
    }
}
