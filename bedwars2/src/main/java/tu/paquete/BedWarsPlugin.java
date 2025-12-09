
package tu.paquete;

import org.bukkit.plugin.java.JavaPlugin;
import tu.paquete.commands.CommandManager;
import tu.paquete.listeners.*;
import tu.paquete.arenas.ArenaManager;
import tu.paquete.data.PlayerDataManager;
import tu.paquete.data.MySQLManager;
import tu.paquete.scoreboard.ScoreboardManager;
import tu.paquete.shop.UpgradeManager;
import tu.paquete.shop.ShopGUI;

public class BedWarsPlugin extends JavaPlugin {

    private ArenaManager arenaManager;
    private PlayerDataManager playerData;
    private CommandManager commandManager;
    private MySQLManager mysql;
    private UpgradeManager upgradeManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        // initialize mysql from config (if provided)
        String host = getConfig().getString("mysql.host","localhost");
        int port = getConfig().getInt("mysql.port",3306);
        String db = getConfig().getString("mysql.database","bedwars");
        String user = getConfig().getString("mysql.user","root");
        String pass = getConfig().getString("mysql.password","");

        mysql = new MySQLManager(this, host, port, db, user, pass);
        mysql.connect();

        this.arenaManager = new ArenaManager(this);
        this.playerData = new PlayerDataManager(this, mysql);
        this.commandManager = new CommandManager(this);

        this.upgradeManager = new UpgradeManager(this, mysql);
        ShopGUI.initialize(this, upgradeManager);

        // register listeners
        getServer().getPluginManager().registerEvents(new tu.paquete.listeners.PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new tu.paquete.listeners.PlayerQuitListener(this), this);
        getServer().getPluginManager().registerEvents(new tu.paquete.listeners.PlayerDeathListener(this), this);
        getServer().getPluginManager().registerEvents(new tu.paquete.listeners.BlockBreakListener(this), this);
        getServer().getPluginManager().registerEvents(new tu.paquete.listeners.BlockPlaceListener(this), this);
        getServer().getPluginManager().registerEvents(new tu.paquete.listeners.EntityDamageListener(this), this);
        getServer().getPluginManager().registerEvents(new tu.paquete.listeners.PlayerMoveListener(this), this);
        getServer().getPluginManager().registerEvents(new tu.paquete.listeners.InventoryClickListener(this), this);

        ScoreboardManager.initialize(this, mysql);

        getLogger().info("BedWarsPlugin enabled.");
    }

    @Override
    public void onDisable() {
        // save players and arenas
        playerData.saveAll();
        arenaManager.saveArenas();
        if (mysql != null) mysql.close();
        getLogger().info("BedWarsPlugin disabled.");
    }

    public ArenaManager getArenaManager() { return arenaManager; }
    public PlayerDataManager getPlayerData() { return playerData; }
    public MySQLManager getMySQL(){ return mysql; }
    public UpgradeManager getUpgradeManager(){ return upgradeManager; }
}
