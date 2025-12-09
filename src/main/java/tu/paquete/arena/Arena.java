package tu.paquete.arena;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import tu.paquete.BedWarsPlugin;

import java.util.*;

public class Arena {

    private final String name;
    private final Map<TeamColor, Team> teams = new EnumMap<>(TeamColor.class);

    private GameState state = GameState.WAITING;
    private Location lobbyLocation;
    private Location spectatorLocation;

    private final Set<UUID> players = new HashSet<>();

    private final BedWarsPlugin plugin;

    public Arena(BedWarsPlugin plugin, String name) {
        this.plugin = plugin;
        this.name = name;

        // Crear equipos por defecto
        for (TeamColor c : TeamColor.values()) teams.put(c, new Team(c));
    }

    public String getName() {
        return name;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public Location getLobbyLocation() {
        return lobbyLocation;
    }

    public void setLobbyLocation(Location lobbyLocation) {
        this.lobbyLocation = lobbyLocation;
    }

    public Location getSpectatorLocation() {
        return spectatorLocation;
    }

    public void setSpectatorLocation(Location spectatorLocation) {
        this.spectatorLocation = spectatorLocation;
    }

    public Collection<Team> getTeams() {
        return teams.values();
    }

    public Team getTeam(TeamColor color) {
        return teams.get(color);
    }

    public boolean isReady() {
        for (Team t : teams.values()) {
            if (t.getSpawn() == null || t.getBedLocation() == null)
                return false;
        }
        return lobbyLocation != null && spectatorLocation != null;
    }

    public void addPlayer(Player p) {
        players.add(p.getUniqueId());
        p.teleport(lobbyLocation);
    }

    public void removePlayer(Player p) {
        players.remove(p.getUniqueId());
    }

    public boolean isFull() {
        return players.size() >= (teams.size() * 1);  // 1 player per team
    }

    public void start() {
        state = GameState.RUNNING;

        Iterator<Player> it = getPlayers().iterator();
        for (Team t : teams.values()) {
            if (it.hasNext()) {
                Player p = it.next();
                t.addPlayer(p.getUniqueId());
                p.teleport(t.getSpawn());
            }
        }
    }

    public List<Player> getPlayers() {
        List<Player> list = new ArrayList<>();
        for (UUID uuid : players) {
            Player p = Bukkit.getPlayer(uuid);
            if (p != null) list.add(p);
        }
        return list;
    }

    public void end(Team winner) {
        state = GameState.ENDING;

        for (Player p : getPlayers()) {
            p.sendMessage("§aEl equipo §e" + winner.getColor().getDisplayName() + "§a ganó!");
            p.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
        }

        players.clear();
        for (Team t : teams.values()) {
            t.getPlayers().clear();
            t.setBedAlive(true);
        }

        state = GameState.WAITING;
    }
}
