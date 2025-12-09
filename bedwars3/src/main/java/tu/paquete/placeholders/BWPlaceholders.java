
package tu.paquete.placeholders;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class BWPlaceholders extends PlaceholderExpansion {

    @Override
    public String getIdentifier() {
        return "bw";
    }

    @Override
    public String getAuthor() {
        return "TuNombre";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String onPlaceholderRequest(Player p, String id) {
        if (p == null) return "";

        switch (id.toLowerCase()) {
            case "kills":
                return "0";
            case "deaths":
                return "0";
            case "wins":
                return "0";
            case "arena":
                return "none";
            case "team":
                return "none";
        }
        return null;
    }
}
