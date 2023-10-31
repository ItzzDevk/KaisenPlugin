package me.kyren223_itzzdev.kaisensmp.commands;

import me.kyren223_itzzdev.kaisensmp.utils.CursedEnergy;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CursedEnergyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp()) return true;
        if (args.length != 2) return true;
        
        Player player = Bukkit.getPlayer(args[0]);
        if (player == null) return true;
        
        try {
            int amount = Integer.parseInt(args[1]);
            CursedEnergy.set(player, amount);
        } catch (NumberFormatException e) {
            return true;
        }
        
        return true;
    }
}
