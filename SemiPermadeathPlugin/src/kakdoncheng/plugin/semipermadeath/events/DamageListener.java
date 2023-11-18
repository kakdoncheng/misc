package kakdoncheng.plugin.semipermadeath.events;

import kakdoncheng.plugin.semipermadeath.Flags;

import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DamageListener implements Listener{
	
	private static final String[] DEATH_ADJ={
			"killed","devastated","demolished","obliterated",
			"annihilated","assassinated","destroyed","murdered",
			"disintegrated","massacred","executed","eradicated"
	};
	
	//can also try EntityDamageByEntityEvent
	@EventHandler
	public static void onEntityDamaged(EntityDamageEvent event){
		Entity e=event.getEntity();
		double dmg=(double)Math.round(event.getFinalDamage()*5)/10;
		double dmgp=((event.getDamage()-event.getFinalDamage())/event.getDamage())*100;
		double dmgr=(double)Math.round((dmgp)*10)/10;
		if(Flags.isVerbose()&&e instanceof LivingEntity){
			if(e instanceof Player){
				e.getServer().broadcastMessage("§e"+e.getName()+" -"+dmg+"§4♥§e ("+(dmgr)+"%).");
			}else{
				e.getServer().broadcastMessage("§7"+e.getName()+" -"+dmg+"§4♥§7 ("+(dmgr)+"%).");
			}
		}
		
	}
	
	@EventHandler
	public static void onPlayerDeath(PlayerDeathEvent event){
		Player player=event.getEntity();
		if(player.getKiller()!=null){
			event.setDeathMessage(player.getName()+" was "+DEATH_ADJ[(int)(Math.random()*DEATH_ADJ.length)]+".");
			if(Flags.isSemipermadeathOn()&&player.getLastDamage()>=20){
				player.setGameMode(GameMode.SPECTATOR);
				event.setDeathMessage("§4§l"+player.getName()+" was permanently slain.");
			}else if(Flags.isPermadeathOn()&&player.getLastDamage()>=0){
				player.setGameMode(GameMode.SPECTATOR);
				event.setDeathMessage("§4§l"+player.getName()+" was permanently slain.");
			}
		}
		
	}

}
