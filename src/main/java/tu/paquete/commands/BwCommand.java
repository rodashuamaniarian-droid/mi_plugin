package tu.paquete.commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import tu.paquete.BedWarsPlugin;

public class BwCommand implements CommandExecutor, TabCompleter {

    private final ArrayList<SubCommand> subCommands = new ArrayList<>();

    public BwCommand(BedWarsPlugin plugin) {

        // Registrar TODOS los subcomandos

        subCommands.add(new HelpCmd());
        subCommands.add(new ArenaCreateCmd());
        subCommands.add(new CreateTeamCmd());
        subCommands.add(new SetSpawnCmd());
        subCommands.add(new SetBedCmd());
        subCommands.add(new SetShopCmd());
        subCommands.add(new SetLobbyCmd());
        subCommands.add(new SetSpecCmd());
        subCommands.add(new SetupCmd());
        subCommands.add(new SetupGradesCmd());
        subCommands.add(new SaveCmd());
        subCommands.add(new EnableCmd());
        subCommands.add(new DisableCmd());
        subCommands.add(new JoinCmd());
        subCommands.add(new LeaveCmd());
        subCommands.add(new StatsCmd(plugin));
        subCommands.add(new ScoreboardToggleCmd(plugin));
        subCommands.add(new StartCmd());
        subCommands.add(new StopCmd());

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Solo jugadores pueden usar este comando.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage("§eUsa /bw help");
            return true;
        }

        for (SubCommand sub : subCommands) {
            if (sub.getName().equalsIgnoreCase(args[0])) {
                sub.perform(player, args);
                return true;
            }
        }

        player.sendMessage("§cComando desconocido. Usa /bw help.");
        return true;
    }

    @Override
    public java.util.List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {

        ArrayList<String> completions = new ArrayList<>();

        if (args.length == 1) {
            for (SubCommand sub : subCommands) {
                completions.add(sub.getName());
            }
        }

        return completions;
    }
}
