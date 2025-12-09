package tu.paquete.arena;

import org.bukkit.Location;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Team {

    private final TeamColor color;
    private Location spawn;
    private Location bedLocation;
    private boolean bedAlive = true;
    private final Set<UUID> players = new HashSet<>();

    public Team(TeamColor color) {
        this.color = color;
    }

    public TeamColor getColor() {
        return color;
    }

    public Location getSpawn() {
        return spawn;
    }

    public void setSpawn(Location spawn) {
        this.spawn = spawn;
    }

    public Location getBedLocation() {
        return bedLocation;
    }

    public void setBedLocation(Location bedLocation) {
        this.bedLocation = bedLocation;
    }

    public boolean isBedAlive() {
        return bedAlive;
    }

    public void setBedAlive(boolean bedAlive) {
        this.bedAlive = bedAlive;
    }

    public Set<UUID> getPlayers() {
        return players;
    }

    public void addPlayer(UUID uuid) {
        players.add(uuid);
    }

    public void removePlayer(UUID uuid) {
        players.remove(uuid);
    }

    public boolean isTeamDead() {
        return !bedAlive && players.isEmpty();
    }
}
 