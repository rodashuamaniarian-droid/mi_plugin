package tu.paquete;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private final String name;
    private final String color;
    private final List<Player> players = new ArrayList<>();
    private Location spawn;
    private boolean bedAlive = true;

    public Team(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() { return name; }
    public String getColor() { return color; }

    public List<Player> getPlayers() { return players; }

    public Location getSpawn() { return spawn; }
    public void setSpawn(Location spawn) { this.spawn = spawn; }

    public boolean isBedAlive() { return bedAlive; }
    public void setBedAlive(boolean alive) { bedAlive = alive; }

    public void restoreBed() { bedAlive = true; }
}
