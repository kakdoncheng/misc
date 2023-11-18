package kakdoncheng.plugin.refinedpufferfish;

import kakdoncheng.plugin.refinedpufferfish.commands.SimpleCommandExecutor;
import kakdoncheng.plugin.refinedpufferfish.events.FurnaceEventListener;
import kakdoncheng.plugin.refinedpufferfish.items.PufferfishItems;

import org.bukkit.plugin.java.JavaPlugin;

public class RefinedPufferfishSpigotPlugin extends JavaPlugin{

	public RefinedPufferfishSpigotPlugin() {
		// TODO Auto-generated constructor stub
	}
	
	// Fired when plugin is first enabled
	@Override
	public void onEnable() {
		PufferfishItems.init();
		getServer().getPluginManager().registerEvents(new FurnaceEventListener(), this);
		getCommand("getfuckedup").setExecutor(new SimpleCommandExecutor());
	}
	
	// Fired when plugin is disabled
	@Override
	public void onDisable() {
		
	}

}
