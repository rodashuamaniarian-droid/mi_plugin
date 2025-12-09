package tu.paquete;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.World;

public class ResourceSpawner extends BukkitRunnable {
    private Location loc;
    private Material mat;
    private int interval;
    public ResourceSpawner(Location loc, Material mat, int interval){
        this.loc = loc;
        this.mat = mat;
        this.interval = interval;
    }

    @Override
    public void run(){
        loc.getBlock().setType(mat);
    }
}