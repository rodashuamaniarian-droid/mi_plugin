package tu.paquete.arenas;

import tu.paquete.BedWarsPlugin;
import org.bukkit.entity.Player;
import java.util.*;

public class ArenaManager {

    private final BedWarsPlugin plugin;
    private final Map<String, Arena> arenas = new HashMap<>();

    public ArenaManager(BedWarsPlugin plugin) {
        this.plugin = plugin;
        // load arenas from config if present (not implemented here)
    }

    public Arena createArena(String name) {
        Arena a = new Arena(name);
        arenas.put(name.toLowerCase(), a);
        return a;
    }

    public Arena getArena(String name) {
        if (name == null) return null;
        return arenas.get(name.toLowerCase());
    }

    public Collection<Arena> getArenas() { return arenas.values(); }

    public Arena getArenaByPlayer(Player p) {
        for (Arena a : arenas.values()) {
            for (TeamColor tc : TeamColor.values()) {
                if (a.getTeam(tc).getPlayers().contains(p)) return a;
            }
        }
        return null;
    }

    public void joinArena(Player p, String arenaName) {
        Arena a = getArena(arenaName);
        if (a == null) return;
        a.join(p, TeamColor.RED); // default for simplicity
    }

    public void leaveAllArenas(Player p) {
        Arena a = getArenaByPlayer(p);
        if (a != null) a.removePlayer(p);
    }

    public void saveArenas() {
        // saving logic (to config or files) should be implemented
    }
}
