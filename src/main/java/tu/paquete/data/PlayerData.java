package tu.paquete.data;

import java.util.UUID;

public class PlayerData {

    private UUID uuid;
    private int kills;
    private int deaths;
    private int wins;
    private int losses;
    private int bedsBroken;
    private int level;
    private int xp;

    public PlayerData(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() { return uuid; }

    public int getKills() { return kills; }
    public int getDeaths() { return deaths; }
    public int getWins() { return wins; }
    public int getLosses() { return losses; }
    public int getBedsBroken() { return bedsBroken; }
    public int getLevel() { return level; }
    public int getXp() { return xp; }

    public void addKill() { kills++; }
    public void addDeath() { deaths++; }
    public void addWin() { wins++; }
    public void addLoss() { losses++; }
    public void addBedBroken() { bedsBroken++; }

    public void addXP(int amount) {
        xp += amount;
        if (xp >= level * 100 + 100) {
            xp -= (level * 100 + 100);
            level++;
        }
    }

    public void setKills(int kills) { this.kills = kills; }
    public void setDeaths(int deaths) { this.deaths = deaths; }
    public void setWins(int wins) { this.wins = wins; }
    public void setLosses(int losses) { this.losses = losses; }
    public void setBedsBroken(int bedsBroken) { this.bedsBroken = bedsBroken; }
    public void setLevel(int level) { this.level = level; }
    public void setXP(int xp) { this.xp = xp; }
}
