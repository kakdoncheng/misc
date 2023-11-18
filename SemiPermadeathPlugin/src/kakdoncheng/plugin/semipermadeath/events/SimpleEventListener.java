package kakdoncheng.plugin.semipermadeath.events;

import kakdoncheng.plugin.semipermadeath.items.GenericItems;

import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SimpleEventListener implements Listener{
	
	@EventHandler
	public static void onRightClick(PlayerInteractEvent event){
		if(event.getAction()==Action.LEFT_CLICK_BLOCK||event.getAction()==Action.LEFT_CLICK_AIR){
			if(event.getItem()!=null&&event.getItem().getItemMeta().equals(GenericItems.boomstick.getItemMeta())){
				Player player=event.getPlayer();
				Block block=player.getTargetBlockExact(6*16);
				if(block!=null){
					player.getServer().broadcastMessage("§6"+player.getName()+" has used a B.O.O.M. stick!");
					for(int i=0;i<3;i++){
						player.getWorld().strikeLightningEffect(block.getLocation());
						player.getWorld().createExplosion(block.getLocation(), 4*(i+1), true, true);
						player.damage(player.getHealth()*(i+1),player);
					}
					if(player.getGameMode()!=GameMode.CREATIVE){
						player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,1200,0));
						if(player.getInventory().getItemInMainHand().getAmount()>1){
							player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount()-1);
						}else{
							player.getInventory().setItemInMainHand(null);
						}
					}
				}
			}
			if(event.getItem()!=null&&event.getItem().getItemMeta().equals(GenericItems.lesserboomstick.getItemMeta())){
				Player player=event.getPlayer();
				Block block=player.getTargetBlockExact(6*16);
				if(block!=null){
					player.getWorld().createExplosion(block.getLocation(), 1, true, false);
					player.getWorld().createExplosion(block.getLocation(), 3, false, false);
					player.getWorld().strikeLightning(block.getLocation());
					player.damage(player.getHealth()/2>1?player.getHealth()/2:1,player);
					if(player.getGameMode()!=GameMode.CREATIVE){
						if(player.getInventory().getItemInMainHand().getAmount()>1){
							player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount()-1);
						}else{
							player.getInventory().setItemInMainHand(null);
						}
					}
				}
			}
		}
		
	}

}
