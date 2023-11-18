package kakdoncheng.plugin.semipermadeath.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class GenericItems {
	
	public static ItemStack boomstick,lesserboomstick;
	
	public static void init(){
		createBoomstick();
		createLesserBoomstick();
	}

	private static void createBoomstick(){
		ItemStack item=new ItemStack(Material.BLAZE_ROD, 1);
		ItemMeta meta=item.getItemMeta();
		meta.setDisplayName("§6§kB.O.O.M. stick");
		meta.addEnchant(Enchantment.LUCK, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		boomstick=item;
	}
	private static void createLesserBoomstick(){
		ItemStack item=new ItemStack(Material.BLAZE_ROD, 1);
		ItemMeta meta=item.getItemMeta();
		meta.setDisplayName("§6Boomstick");
		meta.addEnchant(Enchantment.LUCK, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		lesserboomstick=item;
		
		ShapelessRecipe slr=new ShapelessRecipe(NamespacedKey.minecraft("lesser_boomstick"), lesserboomstick);
		slr.addIngredient(1,Material.BLAZE_ROD);
		slr.addIngredient(1,Material.IRON_NUGGET);
		slr.addIngredient(1,Material.REDSTONE);
		Bukkit.getServer().addRecipe(slr);
	}

}
