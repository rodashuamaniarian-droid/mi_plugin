package tu.paquete.placeholder;

import org.bukkit.entity.Player;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import tu.paquete.BedWarsPlugin;
import tu.paquete.player.BedWarsPlayer;
import tu.paquete.player.PlayerDataManager;

public class BedWarsPlaceholder extends PlaceholderExpansion {

    private final BedWarsPlugin plugin;

    public BedWarsPlaceholder(BedWarsPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getIdentifier() {
        return "bedwars";
    }

    @Override
    public String getAuthor() {
        return "TuNombre";
    }

    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public boolean persist() {
        return true; // Mantener activa incluso al recargar
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player p, String id) {

        if (p == null) return "";

        BedWarsPlayer bw = PlayerDataManager.getPlayer(p.getUniqueId());

        if (bw == null) return "0";

        switch (id.toLowerCase()) {

            case "kills":
                return String.valueOf(bw.getKills());

            case "deaths":
                return String.valueOf(bw.getDeaths());

            case "wins":
                return String.valueOf(bw.getWins());

            case "losses":
                return String.valueOf(bw.getLosses());

            case "level":
                return String.valueOf(bw.getLevel());

            case "coins":
                return String.valueOf(bw.getCoins());

            case "kdr":
                if (bw.getDeaths() == 0) return String.valueOf(bw.getKills());
                double kdr = (double) bw.getKills() / bw.getDeaths();
                return String.format("%.2f", kdr);

            default:
                return null;
        }
    }
}
