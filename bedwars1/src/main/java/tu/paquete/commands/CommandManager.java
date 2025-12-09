package tu.paquete.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tu.paquete.BedWarsPlugin;
import java.util.ArrayList;
import java.util.List;

public class CommandManager implements CommandExecutor {

    private final BedWarsPlugin plugin;
    private final List<SubCommand> subCommands = new ArrayList<>();

    public CommandManager(BedWarsPlugin plugin) {
        this.plugin = plugin;
        // register subcommands
        subCommands.add(new tu.paquete.commands.sub.JoinCmd(plugin));
        subCommands.add(new tu.paquete.commands.sub.LeaveCmd(plugin));
        subCommands.add(new tu.paquete.commands.sub.StartCmd(plugin));
        subCommands.add(new tu.paquete.commands.sub.StopCmd(plugin));
        subCommands.add(new tu.paquete.commands.sub.EnableCmd(plugin));
        subCommands.add(new tu.paquete.commands.sub.DisableCmd(plugin));
        subCommands.add(new tu.paquete.commands.sub.SaveCmd(plugin));
        subCommands.add(new tu.paquete.commands.sub.CreateCmd(plugin));
        subCommands.add(new tu.paquete.commands.sub.SetSpawnCmd(plugin));
        subCommands.add(new tu.paquete.commands.sub.SetBedCmd(plugin));
        subCommands.add(new tu.paquete.commands.sub.SetLobbyCmd(plugin));
        subCommands.add(new tu.paquete.commands.sub.ForceStartCmd(plugin));
        subCommands.add(new tu.paquete.commands.sub.ScoreboardToggleCmd(plugin));
        subCommands.add(new tu.paquete.commands.sub.StatsCmd(plugin));

        plugin.getCommand("bw").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }
        Player p = (Player) sender;
        if (args.length == 0) {
            p.sendMessage("§6--- BedWars Commands ---");
            for (SubCommand sc : subCommands) {
                p.sendMessage("§e/" + label + " " + sc.getSyntax() + " §7- " + sc.getDescription());
            }
            return true;
        }
        String sub = args[0].toLowerCase();
        for (SubCommand sc : subCommands) {
            if (sc.getName().equalsIgnoreCase(sub)) {
                sc.perform(p, args);
                return true;
            }
        }
        p.sendMessage("§cSubcomando no encontrado.");
        return true;
    }
}
