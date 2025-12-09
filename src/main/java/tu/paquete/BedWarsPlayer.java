package tu.paquete.data;

import java.util.UUID;

/**
 * Modelo con todas las estadísticas del jugador.
 */
public class BedWarsPlayer {

    private final UUID uuid;
    private final String name;

    private int coins;
    private int kills;
    private int deaths;
    private int wins;
    private int losses;
    private int level;
    private int xp;
    private int finalKills;
    private int bedsBroken;
    private int killStreak;
    private int bestStreak;

    public BedWarsPlayer(UUID uuid, String name){
        this.uuid = uuid;
        this.name = name;
        this.coins = 0;
        this.kills = 0;
        this.deaths = 0;
        this.wins = 0;
        this.losses = 0;
        this.level = 1;
        this.xp = 0;
        this.finalKills = 0;
        this.bedsBroken = 0;
        this.killStreak = 0;
        this.bestStreak = 0;
    }

    /* ====== IDENTIDAD ====== */
    public UUID getUuid(){ return uuid; }
    public String getName(){ return name; }

    /* ====== COINS ====== */
    public int getCoins(){ return coins; }
    public void setCoins(int coins){ this.coins = coins; }
    public void addCoins(int a){ coins += a; }
    public void removeCoins(int a){ coins = Math.max(0, coins - a); }

    /* ====== KILLS/DEATHS/WINS/LOSSES ====== */
    public int getKills(){ return kills; }
    public void setKills(int kills){ this.kills = kills; }
    public void addKill(){ this.kills++; this.killStreak++; if(this.killStreak > this.bestStreak) this.bestStreak = this.killStreak; }

    public int getDeaths(){ return deaths; }
    public void setDeaths(int deaths){ this.deaths = deaths; this.killStreak = 0; }
    public void addDeath(){ this.deaths++; this.killStreak = 0; }

    public int getWins(){ return wins; }
    public void setWins(int wins){ this.wins = wins; }
    public void addWin(){ this.wins++; }

    public int getLosses(){ return losses; }
    public void setLosses(int losses){ this.losses = losses; }
    public void addLoss(){ this.losses++; }

    /* ====== LEVEL / XP ====== */
    public int getLevel(){ return level; }
    public void setLevel(int level){ this.level = level; }
    public void addLevel(int a){ this.level += a; }

    public int getXp(){ return xp; }
    public void setXp(int xp){ this.xp = xp; }
    public void addXp(int a){ this.xp += a; }

    /* ====== OTRAS STATS ====== */
    public int getFinalKills(){ return finalKills; }
    public void setFinalKills(int v){ finalKills = v; }
    public void addFinalKill(){ finalKills++; }

    public int getBedsBroken(){ return bedsBroken; }
    public void setBedsBroken(int v){ bedsBroken = v; }
    public void addBedBroken(){ bedsBroken++; }

    public int getKillStreak(){ return killStreak; }
    public int getBestStreak(){ return bestStreak; }
    public void setKillStreak(int v){ killStreak = v; }
    public void setBestStreak(int v){ bestStreak = v; }
}
