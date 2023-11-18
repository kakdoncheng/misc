package kakdoncheng.plugin.refinedpufferfish.events;


import kakdoncheng.plugin.refinedpufferfish.items.PufferfishItems;

import org.bukkit.block.Furnace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;

public class FurnaceEventListener implements Listener{
	
	@EventHandler
    private void furnaceCanceller(FurnaceSmeltEvent event)
    {
        if(event.getSource() != null)
            if(event.getSource().getType() == PufferfishItems.rawpufferfishmash.getType())
                if(!event.getSource().isSimilar(PufferfishItems.rawpufferfishmash))
                    event.setCancelled(true);
    }
   
    @EventHandler
    private void furnaceCanceller(FurnaceBurnEvent event)
    {
        Furnace furnace = (Furnace) event.getBlock().getState();
        if(furnace != null)
            if(furnace.getInventory() != null)
                if(furnace.getInventory().getSmelting() != null)
                    if(furnace.getInventory().getSmelting().getType() == PufferfishItems.rawpufferfishmash.getType())
                        if(!furnace.getInventory().getSmelting().isSimilar(PufferfishItems.rawpufferfishmash))
                            event.setCancelled(true);
    }

}
