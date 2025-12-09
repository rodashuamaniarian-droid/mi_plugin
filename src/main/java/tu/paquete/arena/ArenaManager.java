package tu.paquete.arena;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import tu.paquete.BedWarsPlugin;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Maneja múltiples arenas usando la configuración del plugin (config.yml).
 * Guarda en "arenas.<name>.*"
 */
public class ArenaManager {

    private final BedWarsPlugin plugin;
    private final Map<String, Arena> arenas = new HashMap<>();

    public ArenaManager(BedWarsPlugin plugin){
        this.plugin = plugin;
        loadArenasFromConfig();
    }

    /* ====== CREAR / HABILITAR / DESHABILITAR ====== */

    public boolean createArena(String name){
        name = name.toLowerCase();
        if(arenas.containsKey(name)) return false;
        Arena a = new Arena(name);
        arenas.put(name, a);
        saveArenaToConfig(a);
        return true;
    }

    public boolean enableArena(String name){
        Arena a = arenas.get(name.toLowerCase());
        if(a == null) return false;
        a.enabled = true;
        saveArenaToConfig(a);
        return true;
    }

    public boolean disableArena(String name){
        Arena a = arenas.get(name.toLowerCase());
        if(a == null) return false;
        a.enabled = false;
        saveArenaToConfig(a);
        return true;
    }

    public boolean removeArena(String name){
        name = name.toLowerCase();
        if(!arenas.containsKey(name)) return false;
        arenas.remove(name);
        plugin.getConfig().set("arenas." + name, null);
        plugin.saveConfig();
        return true;
    }

    /* ====== GETTERS / LIST ====== */

    public Arena getArena(String name){
        return arenas.get(name.toLowerCase());
    }

    public List<String> listArenas(){
        return new ArrayList<>(arenas.keySet());
    }

    public List<Arena> listArenaObjects(){
        return new ArrayList<>(arenas.values());
    }

    /* ====== CONFIG IO ====== */

    private void loadArenasFromConfig(){
        FileConfiguration cfg = plugin.getConfig();
        if(!cfg.isConfigurationSection("arenas")) return;
        for(String key : cfg.getConfigurationSection("arenas").getKeys(false)){
            try {
                String base = "arenas." + key + ".";
                boolean enabled = cfg.getBoolean(base + "enabled", false);
                Arena a = new Arena(key);
                a.enabled = enabled;

                // load arena region pos1/pos2
                if(cfg.contains(base + "pos1")) a.pos1 = stringToLoc(cfg.getString(base + "pos1"));
                if(cfg.contains(base + "pos2")) a.pos2 = stringToLoc(cfg.getString(base + "pos2"));

                // load teams
                if(cfg.isConfigurationSection(base + "teams")){
                    for(String teamName : cfg.getConfigurationSection(base + "teams").getKeys(false)){
                        String tbase = base + "teams." + teamName + ".";
                        if(cfg.contains(tbase + "spawn")) a.teamSpawns.put(teamName.toLowerCase(), stringToLoc(cfg.getString(tbase + "spawn")));
                        if(cfg.contains(tbase + "bed")) a.teamBeds.put(teamName.toLowerCase(), stringToLoc(cfg.getString(tbase + "bed")));
                        if(cfg.contains(tbase + "shop")) a.teamShop.put(teamName.toLowerCase(), stringToLoc(cfg.getString(tbase + "shop")));
                    }
                }

                arenas.put(key.toLowerCase(), a);
            } catch (Exception ex){
                plugin.getLogger().warning("Error cargando arena " + key + ": " + ex.getMessage());
            }
        }
    }

    private void saveArenaToConfig(Arena a){
        String base = "arenas." + a.name + ".";
        plugin.getConfig().set(base + "enabled", a.enabled);
        if(a.pos1 != null) plugin.getConfig().set(base + "pos1", locToString(a.pos1));
        if(a.pos2 != null) plugin.getConfig().set(base + "pos2", locToString(a.pos2));

        // teams
        for(String team : a.teamSpawns.keySet()){
            String tbase = base + "teams." + team + ".";
            plugin.getConfig().set(tbase + "spawn", locToString(a.teamSpawns.get(team)));
        }
        for(String team : a.teamBeds.keySet()){
            String tbase = base + "teams." + team + ".";
            plugin.getConfig().set(tbase + "bed", locToString(a.teamBeds.get(team)));
        }
        for(String team : a.teamShop.keySet()){
            String tbase = base + "teams." + team + ".";
            plugin.getConfig().set(tbase + "shop", locToString(a.teamShop.get(team)));
        }
        plugin.saveConfig();
    }

    public void saveAllToConfig(){
        for(Arena a : arenas.values()) saveArenaToConfig(a);
    }

    /* ====== HELPERS PARA SETEO ====== */

    public void setArenaRegion(String arenaName, Location pos1, Location pos2){
        Arena a = getArena(arenaName);
        if(a == null) throw new IllegalArgumentException("Arena no existe: " + arenaName);
        a.pos1 = pos1; a.pos2 = pos2;
        saveArenaToConfig(a);
    }

    public void setTeamSpawn(String arenaName, String team, Location spawn){
        Arena a = getArena(arenaName);
        if(a == null) throw new IllegalArgumentException("Arena no existe: " + arenaName);
        a.teamSpawns.put(team.toLowerCase(), spawn);
        saveArenaToConfig(a);
    }

    public void setTeamBed(String arenaName, String team, Location bed){
        Arena a = getArena(arenaName);
        if(a == null) throw new IllegalArgumentException("Arena no existe: " + arenaName);
        a.teamBeds.put(team.toLowerCase(), bed);
        saveArenaToConfig(a);
    }

    public void setTeamShop(String arenaName, String team, Location shop){
        Arena a = getArena(arenaName);
        if(a == null) throw new IllegalArgumentException("Arena no existe: " + arenaName);
        a.teamShop.put(team.toLowerCase(), shop);
        saveArenaToConfig(a);
    }

    /* ====== UTILS Location <-> String ====== */

    private String locToString(Location loc){
        if(loc == null) return "";
        return loc.getWorld().getName() + "," + loc.getX() + "," + loc.getY() + "," + loc.getZ()
                + "," + loc.getYaw() + "," + loc.getPitch();
    }

    private Location stringToLoc(String s){
        if(s == null || s.isEmpty()) return null;
        try{
            String[] p = s.split(",");
            World w = Bukkit.getWorld(p[0]);
            double x = Double.parseDouble(p[1]);
            double y = Double.parseDouble(p[2]);
            double z = Double.parseDouble(p[3]);
            float yaw = Float.parseFloat(p[4]);
            float pitch = Float.parseFloat(p[5]);
            return new Location(w,x,y,z,yaw,pitch);
        }catch(Exception ex){
            return null;
        }
    }

    /* ====== INNER ARENA MODEL ====== */

    public static class Arena {
        public final String name;
        public boolean enabled = false;
        public Location pos1;
        public Location pos2;
        public final Map<String, Location> teamSpawns = new HashMap<>();
        public final Map<String, Location> teamBeds = new HashMap<>();
        public final Map<String, Location> teamShop = new HashMap<>();

        public Arena(String name){ this.name = name.toLowerCase(); }

        public boolean isInside(Location loc){
            if(pos1 == null || pos2 == null) return false;
            double x1 = Math.min(pos1.getX(), pos2.getX());
            double y1 = Math.min(pos1.getY(), pos2.getY());
            double z1 = Math.min(pos1.getZ(), pos2.getZ());
            double x2 = Math.max(pos1.getX(), pos2.getX());
            double y2 = Math.max(pos1.getY(), pos2.getY());
            double z2 = Math.max(pos1.getZ(), pos2.getZ());
            return loc.getX() >= x1 && loc.getX() <= x2
                    && loc.getY() >= y1 && loc.getY() <= y2
                    && loc.getZ() >= z1 && loc.getZ() <= z2;
        }
    }
}
