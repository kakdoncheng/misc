package kakdoncheng.plugin.refinedpufferfish.items;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PufferfishItems {
	
	public static ItemStack rawpufferfishmash,pufferfishhooch;
	
	public static void init(){
		createPufferfishMash();
		createDistillate();
	}

	private static void createPufferfishMash(){
		ItemStack pot=new ItemStack(Material.POTION, 1);
		PotionMeta meta=(PotionMeta)pot.getItemMeta();
		meta.setDisplayName("§6Ungodly Distillate");
		meta.setColor(Color.MAROON);
		meta.addCustomEffect(new PotionEffect(PotionEffectType.BLINDNESS,100,0), true);
		meta.addCustomEffect(new PotionEffect(PotionEffectType.HUNGER,2400,4), true);
		meta.addCustomEffect(new PotionEffect(PotionEffectType.POISON,2400,4), true);
		meta.addCustomEffect(new PotionEffect(PotionEffectType.CONFUSION,2400,2), true);
		meta.addCustomEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,1200,1), true);
		meta.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION,1200,4), true);
		meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		pot.setItemMeta(meta);
		rawpufferfishmash=pot;
		
		ShapelessRecipe slr=new ShapelessRecipe(NamespacedKey.minecraft("ungodly_distillate"), pot);
		slr.addIngredient(1,Material.GLASS_BOTTLE);
		slr.addIngredient(1,Material.PUFFERFISH);
		slr.addIngredient(1,Material.RED_MUSHROOM);
		Bukkit.getServer().addRecipe(slr);
		
		ShapelessRecipe slr_alt=new ShapelessRecipe(NamespacedKey.minecraft("ungodly_distillate_alt"), pot);
		slr_alt.addIngredient(1,Material.GLASS_BOTTLE);
		slr_alt.addIngredient(1,Material.PUFFERFISH);
		slr_alt.addIngredient(1,Material.BROWN_MUSHROOM);
		Bukkit.getServer().addRecipe(slr_alt);
	}
	
	private static void createDistillate(){
		ItemStack pot=new ItemStack(Material.POTION, 1);
		PotionMeta meta=(PotionMeta)pot.getItemMeta();
		meta.setDisplayName("§6Pufferfish Moonshine");
		meta.setColor(Color.SILVER);
		meta.addCustomEffect(new PotionEffect(PotionEffectType.CONFUSION,1200,1), true);
		meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		pot.setItemMeta(meta);
		pufferfishhooch=pot;
		
		FurnaceRecipe fr=new FurnaceRecipe(NamespacedKey.minecraft("pufferfish_hooch_smelt"), pot, rawpufferfishmash.getType(), 0.2f, 200);
		Bukkit.getServer().addRecipe(fr);
	}

}
