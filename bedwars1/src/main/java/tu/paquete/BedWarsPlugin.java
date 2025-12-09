package tu.paquete;

import org.bukkit.plugin.java.JavaPlugin;
import tu.paquete.commands.CommandManager;
import tu.paquete.listeners.*;
import tu.paquete.arenas.ArenaManager;
import tu.paquete.data.PlayerDataManager;
import tu.paquete.scoreboard.ScoreboardManager;

public class BedWarsPlugin extends JavaPlugin {

    private ArenaManager arenaManager;
    private PlayerDataManager playerData;
    private CommandManager commandManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        this.arenaManager = new ArenaManager(this);
        this.playerData = new PlayerDataManager(this);
        this.commandManager = new CommandManager(this);

        // register listeners
        getServer().getPluginManager().registerEvents(new tu.paquete.listeners.PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new tu.paquete.listeners.PlayerQuitListener(this), this);
        getServer().getPluginManager().registerEvents(new tu.paquete.listeners.PlayerDeathListener(this), this);
        getServer().getPluginManager().registerEvents(new tu.paquete.listeners.BlockBreakListener(this), this);
        getServer().getPluginManager().registerEvents(new tu.paquete.listeners.BlockPlaceListener(this), this);
        getServer().getPluginManager().registerEvents(new tu.paquete.listeners.EntityDamageListener(this), this);
        getServer().getPluginManager().registerEvents(new tu.paquete.listeners.PlayerMoveListener(this), this);
        getServer().getPluginManager().registerEvents(new tu.paquete.listeners.InventoryClickListener(this), this);

        ScoreboardManager.initialize(this);

        getLogger().info("BedWarsPlugin enabled.");
    }

    @Override
    public void onDisable() {
        // save players and arenas
        playerData.saveAll();
        arenaManager.saveArenas();
        getLogger().info("BedWarsPlugin disabled.");
    }

    public ArenaManager getArenaManager() { return arenaManager; }
    public PlayerDataManager getPlayerData() { return playerData; }
}
