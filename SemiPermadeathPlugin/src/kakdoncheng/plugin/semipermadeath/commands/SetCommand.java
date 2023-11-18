package kakdoncheng.plugin.semipermadeath.commands;

import kakdoncheng.plugin.semipermadeath.Flags;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SetCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equals("set")){
			if(args.length>0){
				if(args[0].equals("verbose")&&args.length>1){
					if(args[1].equals("off")){
						Flags.setVerbose(false);
						sender.getServer().broadcastMessage("§6Verbose is "+(Flags.isVerbose()?"§a§lon":"§4§loff")+"§r§6.");
					}else if(args[1].equals("on")){
						Flags.setVerbose(true);
						sender.getServer().broadcastMessage("§6Verbose is "+(Flags.isVerbose()?"§a§lon":"§4§loff")+"§r§6.");
					}else{
						sender.sendMessage("§cVerbose can only be set to on or off.");
					}
				}else if(args[0].equals("permadeath")&&args.length>1){
					if(args[1].equals("none")){
						Flags.setPermadeathOn(false);
						Flags.setSemipermadeathOn(false);
						sender.getServer().broadcastMessage("§6Semipermadeath is §4§loff§r§6.");
					}else if(args[1].equals("semi")){
						Flags.setPermadeathOn(false);
						Flags.setSemipermadeathOn(true);
						sender.getServer().broadcastMessage("§6Semipermadeath is §a§lon§r§6.");
					}else if(args[1].equals("full")){
						Flags.setPermadeathOn(true);
						Flags.setSemipermadeathOn(false);
						sender.getServer().broadcastMessage("§6Permadeath is §a§lon§r§6..");
					}else{
						sender.sendMessage("§cPermadeath type can only be set to none, semi, or full.");
					}
				}else{
					sender.sendMessage("§cInvalid flag.");
				}
			}else{
				sender.sendMessage("§cYou must specify a flag to set.");
			}
		}
		return true;
	}

}
