package tu.paquete.data;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import tu.paquete.BedWarsPlugin;

public class PlayerDataManager {

    private BedWarsPlugin plugin;
    private HashMap<UUID, PlayerData> cache = new HashMap<>();

    public PlayerDataManager(BedWarsPlugin plugin) {
        this.plugin = plugin;
    }

    public PlayerData get(UUID uuid) {
        return cache.computeIfAbsent(uuid, this::load);
    }

    public PlayerData load(UUID uuid) {
        File file = new File(plugin.getDataFolder() + "/players", uuid.toString() + ".yml");

        if (!file.exists()) {
            return new PlayerData(uuid);
        }

        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        PlayerData data = new PlayerData(uuid);

        data.setKills(cfg.getInt("kills"));
        data.setDeaths(cfg.getInt("deaths"));
        data.setWins(cfg.getInt("wins"));
        data.setLosses(cfg.getInt("losses"));
        data.setBedsBroken(cfg.getInt("bedsBroken"));
        data.setLevel(cfg.getInt("level"));
        data.setXP(cfg.getInt("xp"));

        return data;
    }

    public void save(PlayerData data) {
        try {
            File folder = new File(plugin.getDataFolder() + "/players");
            if (!folder.exists()) folder.mkdirs();

            File file = new File(folder, data.getUuid().toString() + ".yml");
            FileConfiguration cfg = new YamlConfiguration();

            cfg.set("kills", data.getKills());
            cfg.set("deaths", data.getDeaths());
            cfg.set("wins", data.getWins());
            cfg.set("losses", data.getLosses());
            cfg.set("bedsBroken", data.getBedsBroken());
            cfg.set("level", data.getLevel());
            cfg.set("xp", data.getXp());

            cfg.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveAll() {
        for (PlayerData data : cache.values()) {
            save(data);
        }
    }
}
