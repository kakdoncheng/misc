package kakdoncheng.plugin.refinedpufferfish.commands;

import kakdoncheng.plugin.refinedpufferfish.items.PufferfishItems;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SimpleCommandExecutor implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)){
			sender.sendMessage("You must be a player to use this command!");
			return true;
		}
		if(cmd.getName().equals("getfuckedup")){
			Player player=(Player)sender;
			player.getInventory().addItem(PufferfishItems.rawpufferfishmash);
			player.getInventory().addItem(PufferfishItems.pufferfishhooch);
			player.sendMessage("Please drink responsibly.");
		}
		return true;
	}

}
