package kakdoncheng.plugin.semipermadeath.commands;

import kakdoncheng.plugin.semipermadeath.Flags;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ToggleCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equals("toggle")){
			if(args.length>0){
				if(args[0].equals("verbose")){
					Flags.setVerbose(!Flags.isVerbose());
					sender.getServer().broadcastMessage("§6Verbose is "+(Flags.isVerbose()?"§a§lon":"§4§loff")+"§r§6.");
				}else{
					sender.sendMessage("§cInvalid flag.");
				}
			}else{
				sender.sendMessage("§cYou must specify a flag to toggle.");
			}
		}
		return true;
	}

}
