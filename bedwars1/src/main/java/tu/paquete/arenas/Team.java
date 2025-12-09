package tu.paquete.arenas;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import java.util.HashSet;
import java.util.Set;

public class Team {
    private final TeamColor color;
    private Location spawn;
    private Location bedLocation;
    private final Set<Player> players = new HashSet<>();

    public Team(TeamColor color) { this.color = color; }

    public TeamColor getColor() { return color; }
    public void setSpawn(Location loc) { this.spawn = loc; }
    public Location getSpawn() { return spawn; }
    public void setBedLocation(Location loc) { this.bedLocation = loc; }
    public Location getBedLocation() { return bedLocation; }

    public void addPlayer(Player p) { players.add(p); }
    public void removePlayer(Player p) { players.remove(p); }
    public Set<Player> getPlayers() { return players; }
}
