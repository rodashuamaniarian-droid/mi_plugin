package tu.paquete.arenas;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.block.Block;
import java.util.*;

public class Arena {

    private final String name;
    private final Map<TeamColor, Team> teams = new EnumMap<>(TeamColor.class);
    private ArenaState state = ArenaState.WAITING;
    private boolean enabled = true;
    private final Set<Block> placedBlocks = new HashSet<>();

    public Arena(String name) {
        this.name = name;
        for (TeamColor c : TeamColor.values()) teams.put(c, new Team(c));
    }

    public String getName() { return name; }
    public Team getTeam(TeamColor color) { return teams.get(color); }
    public boolean isWaiting(){ return state == ArenaState.WAITING; }
    public boolean isStarting(){ return state == ArenaState.STARTING; }
    public void setEnabled(boolean b){ this.enabled = b; }
    public boolean isEnabled(){ return enabled; }

    public void join(Player p, TeamColor color) {
        teams.get(color).addPlayer(p);
        // teleport to spawn if set
        if (teams.get(color).getSpawn() != null) p.teleport(teams.get(color).getSpawn());
    }

    public void removePlayer(Player p) {
        for (Team t : teams.values()) t.removePlayer(p);
    }

    public boolean isBed(Block b) {
        for (Team t : teams.values()) {
            if (t.getBedLocation() != null && t.getBedLocation().getBlock().equals(b)) return true;
        }
        return false;
    }

    public void breakBed(Player breaker, Block bedBlock) {
        // find team and mark bed broken
        Bukkit.broadcastMessage("§cLa cama ha sido destruida!");
    }

    public void addPlacedBlock(Block b) { placedBlocks.add(b); }
    public boolean isPlaceBlock(Block b) { return placedBlocks.contains(b); }

    public void start() {
        this.state = ArenaState.RUNNING;
        Bukkit.broadcastMessage("§aLa partida en " + name + " ha comenzado!");
    }

    public void end(String winner) {
        this.state = ArenaState.ENDED;
        Bukkit.broadcastMessage("§6La partida en " + name + " terminó. Ganador: " + (winner==null?"Nadie":winner));
    }

    public void handlePlayerDeath(Player p) {
        // respawn or spectate
        p.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
    }

    public void handleVoidFall(Player p) {
        handlePlayerDeath(p);
    }
}
