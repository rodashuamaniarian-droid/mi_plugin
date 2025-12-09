package tu.paquete.player;

import java.util.HashMap;
import java.util.UUID;

public class PlayerManager {
    private static HashMap<UUID, BedWarsPlayer> players = new HashMap<>();

    public static BedWarsPlayer get(UUID u){
        return players.computeIfAbsent(u, BedWarsPlayer::new);
    }
}
