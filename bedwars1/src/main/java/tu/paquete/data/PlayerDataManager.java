package tu.paquete.data;

import tu.paquete.BedWarsPlugin;
import org.bukkit.entity.Player;
import java.util.*;

public class PlayerDataManager {

    private final BedWarsPlugin plugin;
    private final Map<UUID, BedWarsPlayer> players = new HashMap<>();

    public PlayerDataManager(BedWarsPlugin plugin) {
        this.plugin = plugin;
    }

    public BedWarsPlayer get(UUID id) {
        return players.get(id);
    }

    public BedWarsPlayer getOrCreate(Player p) {
        return players.computeIfAbsent(p.getUniqueId(), BedWarsPlayer::new);
    }

    public void saveAll() {
        for (BedWarsPlayer b : players.values()) b.save();
    }
}
