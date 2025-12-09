package tu.paquete;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class UserDataManager {

    private final JavaPlugin plugin;
    private final File folder;

    public UserDataManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.folder = new File(plugin.getDataFolder(), "userdata");
    }

    public void ensureFolder(){
        if(!folder.exists()) folder.mkdirs();
    }

    public File getFile(UUID uuid){
        return new File(folder, uuid.toString() + ".yml");
    }

    public BedWarsPlayer loadOrCreate(UUID uuid, String playerName){
        File f = getFile(uuid);
        if(!f.exists()){
            BedWarsPlayer bw = new BedWarsPlayer(uuid, playerName);
            save(bw);
            return bw;
        }
        return load(uuid);
    }

    public BedWarsPlayer load(UUID uuid){
        File f = getFile(uuid);
        if(!f.exists()) return new BedWarsPlayer(uuid, "unknown");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);

        BedWarsPlayer bw = new BedWarsPlayer(uuid, cfg.getString("name", "unknown"));
        bw.setCoins(cfg.getInt("coins", 0));
        bw.setKills(cfg.getInt("kills",0));
        bw.setDeaths(cfg.getInt("deaths",0));
        bw.setWins(cfg.getInt("wins",0));
        bw.setLosses(cfg.getInt("losses",0));
        bw.setLevel(cfg.getInt("level",1));
        bw.setXp(cfg.getInt("xp",0));
        bw.setFinalKills(cfg.getInt("finalKills",0));
        bw.setBedsBroken(cfg.getInt("bedsBroken",0));
        bw.setKillStreak(cfg.getInt("killStreak",0));
        bw.setBestStreak(cfg.getInt("bestStreak",0));
        // upgrades could be loaded later
        return bw;
    }

    public void save(BedWarsPlayer bw){
        try{
            File f = getFile(bw.getUuid());
            YamlConfiguration cfg = new YamlConfiguration();
            cfg.set("uuid", bw.getUuid().toString());
            cfg.set("name", bw.getName());
            cfg.set("coins", bw.getCoins());
            cfg.set("kills", bw.getKills());
            cfg.set("deaths", bw.getDeaths());
            cfg.set("wins", bw.getWins());
            cfg.set("losses", bw.getLosses());
            cfg.set("level", bw.getLevel());
            cfg.set("xp", bw.getXp());
            cfg.set("finalKills", bw.getFinalKills());
            cfg.set("bedsBroken", bw.getBedsBroken());
            cfg.set("killStreak", bw.getKillStreak());
            cfg.set("bestStreak", bw.getBestStreak());
            cfg.save(f);
        } catch(Exception ex){
            plugin.getLogger().severe("Error saving userdata for " + bw.getUuid() + ": " + ex.getMessage());
        }
    }

    public void saveAll(Collection<BedWarsPlayer> list){
        for(BedWarsPlayer bw : list) save(bw);
    }

    /** returns a simple snapshot of top players by reading all files without fully instantiating objects */
    public java.util.List<SimpleStat> loadTop(String stat, int limit){
        java.util.List<SimpleStat> result = new ArrayList<>();
        File[] files = folder.listFiles((d, name)-> name.endsWith(".yml"));
        if(files == null) return result;
        for(File f : files){
            try{
                YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
                String name = cfg.getString("name", f.getName().replace(".yml",""));
                int value = cfg.getInt(stat, 0);
                result.add(new SimpleStat(name, value));
            } catch(Exception ignored){}
        }
        result.sort((a,b)-> Integer.compare(b.value, a.value));
        if(result.size() > limit) return result.subList(0, limit);
        return result;
    }

    public static class SimpleStat {
        public final String name;
        public final int value;
        public SimpleStat(String name, int value){ this.name = name; this.value = value; }
    }
}
