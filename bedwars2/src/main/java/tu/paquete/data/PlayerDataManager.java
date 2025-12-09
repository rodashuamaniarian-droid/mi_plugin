
package tu.paquete.data;

import tu.paquete.BedWarsPlugin;
import org.bukkit.entity.Player;
import java.util.*;
import java.sql.*;

public class PlayerDataManager {

    private final BedWarsPlugin plugin;
    private final Map<UUID, BedWarsPlayer> players = new HashMap<>();
    private final MySQLManager mysql;

    public PlayerDataManager(BedWarsPlugin plugin, MySQLManager mysql) {
        this.plugin = plugin;
        this.mysql = mysql;
        if (mysql != null) mysql.connect();
    }

    public BedWarsPlayer get(UUID id) {
        return players.get(id);
    }

    public BedWarsPlayer getOrCreate(Player p) {
        return players.computeIfAbsent(p.getUniqueId(), uuid -> {
            BedWarsPlayer b = new BedWarsPlayer(uuid);
            loadFromStorage(b);
            return b;
        });
    }

    public void loadFromStorage(BedWarsPlayer b) {
        if (mysql == null || mysql.getConnection() == null) {
            b.load(); // fallback (local)
            return;
        }
        try (PreparedStatement ps = mysql.getConnection().prepareStatement("SELECT kills,deaths,wins,losses,level FROM bw_players WHERE uuid = ?")) {
            ps.setString(1, b.getUuid().toString());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    b.setKills(rs.getInt("kills"));
                    b.setDeaths(rs.getInt("deaths"));
                    b.setWins(rs.getInt("wins"));
                    b.setLosses(rs.getInt("losses"));
                    b.setLevel(rs.getInt("level"));
                } else {
                    // insert default row
                    try (PreparedStatement ins = mysql.getConnection().prepareStatement("INSERT INTO bw_players (uuid) VALUES (?)")) {
                        ins.setString(1, b.getUuid().toString());
                        ins.executeUpdate();
                    }
                }
            }
        } catch (SQLException ex) {
            plugin.getLogger().severe("Error cargando jugador MySQL: " + ex.getMessage());
        }
    }

    public void save(BedWarsPlayer b) {
        if (mysql == null || mysql.getConnection() == null) {
            b.save();
            return;
        }
        try (PreparedStatement ps = mysql.getConnection().prepareStatement(
                "REPLACE INTO bw_players (uuid,kills,deaths,wins,losses,level) VALUES (?,?,?,?,?,?)")) {
            ps.setString(1, b.getUuid().toString());
            ps.setInt(2, b.getKills());
            ps.setInt(3, b.getDeaths());
            ps.setInt(4, b.getWins());
            ps.setInt(5, b.getLosses());
            ps.setInt(6, b.getLevel());
            ps.executeUpdate();
        } catch (SQLException ex) {
            plugin.getLogger().severe("Error guardando jugador MySQL: " + ex.getMessage());
        }
    }

    public void saveAll() {
        for (BedWarsPlayer b : players.values()) save(b);
    }
}
