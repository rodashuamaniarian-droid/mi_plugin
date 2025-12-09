package tu.paquete.arena;

import org.bukkit.ChatColor;

public enum TeamColor {

    RED(ChatColor.RED, "Rojo"),
    BLUE(ChatColor.BLUE, "Azul"),
    GREEN(ChatColor.GREEN, "Verde"),
    YELLOW(ChatColor.YELLOW, "Amarillo");

    private final ChatColor chat;
    private final String display;

    TeamColor(ChatColor chat, String display) {
        this.chat = chat;
        this.display = display;
    }

    public ChatColor getChatColor() {
        return chat;
    }

    public String getDisplayName() {
        return display;
    }
}
