package tu.paquete;

import org.bukkit.plugin.java.JavaPlugin;

import tu.paquete.commands.*;
import tu.paquete.placeholder.BedWarsPlaceholder;
import tu.paquete.scoreboard.ScoreboardManager;
import tu.paquete.listeners.PlayerDataListener;

public class BedWarsPlugin extends JavaPlugin {

    private static BedWarsPlugin instance;

    @Override
    public void onEnable() {
        instance = this;

        getLogger().info("§aBedWarsPlugin cargando...");

        // Registrar comando principal /bw
        getCommand("bw").setExecutor(new BwCommand(this));
        getCommand("bw").setTabCompleter(new BwCommand(this));

        // Registrar listeners
        getServer().getPluginManager().registerEvents(new PlayerDataListener(), this);

        // Registrar PlaceholderAPI
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new BedWarsPlaceholder(this).register();
            getLogger().info("§aPlaceholderAPI hook exitoso.");
        } else {
            getLogger().warning("§cPlaceholderAPI no encontrado. Las placeholders no funcionarán.");
        }

        // Scoreboard Manager
        ScoreboardManager.initialize();

        getLogger().info("§aBedWarsPlugin activado correctamente.");
    }

    @Override
    public void onDisable() {
        getLogger().info("§cBedWarsPlugin desactivado.");
    }

    public static BedWarsPlugin getInstance() {
        return instance;
    }
}
