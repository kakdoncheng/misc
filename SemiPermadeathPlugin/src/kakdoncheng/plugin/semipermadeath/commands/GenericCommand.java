package kakdoncheng.plugin.semipermadeath.commands;

import kakdoncheng.plugin.semipermadeath.items.GenericItems;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GenericCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equals("boomstick")){
			if(!(sender instanceof Player)){
				sender.sendMessage("§cYou must be a player to use this command!");
				return true;
			}
			((Player)sender).getInventory().addItem(GenericItems.boomstick);
		}
		if(cmd.getName().equals("smallboomstick")){
			if(!(sender instanceof Player)){
				sender.sendMessage("§cYou must be a player to use this command!");
				return true;
			}
			((Player)sender).getInventory().addItem(GenericItems.lesserboomstick);
		}
		return true;
	}

}
