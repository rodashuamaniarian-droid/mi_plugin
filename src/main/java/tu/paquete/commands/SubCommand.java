package tu.paquete.commands;

import org.bukkit.command.CommandSender;

public interface SubCommand {
    String name();
    String usage();
    String permission(); // puede devolver null
    boolean execute(CommandSender sender, String[] args);
}
