package kakdoncheng.plugin.semipermadeath;

import kakdoncheng.plugin.semipermadeath.commands.GenericCommand;
import kakdoncheng.plugin.semipermadeath.commands.SetCommand;
import kakdoncheng.plugin.semipermadeath.commands.ToggleCommand;
import kakdoncheng.plugin.semipermadeath.events.DamageListener;
import kakdoncheng.plugin.semipermadeath.events.SimpleEventListener;
import kakdoncheng.plugin.semipermadeath.items.GenericItems;

import org.bukkit.plugin.java.JavaPlugin;

public class SemiPermadeathPlugin extends JavaPlugin{

	// Fired when plugin is first enabled
	@Override
	public void onEnable() {
		GenericItems.init();
		getServer().getPluginManager().registerEvents(new SimpleEventListener(), this);
		getServer().getPluginManager().registerEvents(new DamageListener(), this);
		getServer().getConsoleSender().sendMessage("§cMay thy flesh be consumed.");
		
		getCommand("boomstick").setExecutor(new GenericCommand());
		getCommand("smallboomstick").setExecutor(new GenericCommand());
		getCommand("toggle").setExecutor(new ToggleCommand());
		getCommand("set").setExecutor(new SetCommand());
	}
	
	// Fired when plugin is disabled
	@Override
	public void onDisable() {
		
	}

}
