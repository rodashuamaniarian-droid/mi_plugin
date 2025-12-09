package tu.paquete.player;

import java.util.UUID;

public class BedWarsPlayer {
    private UUID uuid;
    private int kills, deaths, wins, losses, level;

    public BedWarsPlayer(UUID u){ uuid=u; }

    public void addKill(){ kills++; }
    public void addDeath(){ deaths++; }
    public void addWin(){ wins++; }
    public void addLoss(){ losses++; }

    public int getKills(){ return kills; }
    public int getDeaths(){ return deaths; }
    public int getWins(){ return wins; }
    public int getLosses(){ return losses; }
    public int getLevel(){ return level; }
}
