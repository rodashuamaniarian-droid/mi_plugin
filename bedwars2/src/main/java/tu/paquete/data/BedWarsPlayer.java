
package tu.paquete.data;

import org.bukkit.entity.Player;
import java.util.UUID;

public class BedWarsPlayer {
    private final UUID uuid;
    private int kills, deaths, wins, losses, level;

    public BedWarsPlayer(UUID uuid) { this.uuid = uuid; }

    public UUID getUuid(){ return uuid; }
    public int getKills(){ return kills; }
    public int getDeaths(){ return deaths; }
    public int getWins(){ return wins; }
    public int getLosses(){ return losses; }
    public int getLevel(){ return level; }

    public void setKills(int k){ this.kills = k; }
    public void setDeaths(int d){ this.deaths = d; }
    public void setWins(int w){ this.wins = w; }
    public void setLosses(int l){ this.losses = l; }
    public void setLevel(int lvl){ this.level = lvl; }

    public void addKill(){ kills++; }
    public void addDeath(){ deaths++; }
    public void addWin(){ wins++; }
    public void addLoss(){ losses++; }

    public void load() {
        // fallback/no-op for file-based loading
    }

    public void save() {
        // fallback/no-op for file-based saving
    }
}
