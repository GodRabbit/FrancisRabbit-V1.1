package com.godrabbit.francisrabbit.items;

import com.godrabbit.francisrabbit.armor.FaithEmblemItem;
import com.godrabbit.francisrabbit.tools.FaithedShearsItem;
import com.godrabbit.francisrabbit.tools.FaithedStonePickaxeItem;
import com.godrabbit.francisrabbit.tools.FaithedWoodenSwordItem;
import com.godrabbit.francisrabbit.tools.GlowingFlintScytheItem;
import com.godrabbit.francisrabbit.tools.GlowingWoodenAxeItem;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {
	//creative tab:
	public static final CreativeTabs ultrarabbitTab = new CreativeTabs("ultrarabbitTab") {
	    @Override public Item getTabIconItem() {
	        return Item.getItemFromBlock(Blocks.HAY_BLOCK);
	    }
	};
	
	public static Item weed_leaf_item;
	public static Item oil_item;
	public static Item fluffy_cotton_item;
	public static Item pink_cotton_item;
	public static Item redstone_emblem_item;
	
	//seeds
	public static Item faithed_wheat_seeds;
	public static Item fluffy_cotton_seeds;
	
	//tools:
	public static Item faith_emblem_item;
	public static Item faithed_wooden_sword;
	public static Item glowing_wooden_axe;
	public static Item faithed_stone_pickaxe;
	public static Item glowing_flint_scythe;
	public static Item faithed_shears_item;
	
	public static void createItems() {
		//GameRegistry.registerItem(weedLeafItem = new WeedLeafItem("weed_leaf_item"), "weed_leaf_item");
		/*weed_leaf_item = new WeedLeafItem("weed_leaf_item");
		weed_leaf_item.setRegistryName("weed_leaf_item");
		GameRegistry.register(weed_leaf_item);*/
		
		oil_item = new OilItem();
		oil_item.setRegistryName("oil_item");
		GameRegistry.register(oil_item);
		//oil_item.setContainerItem(oil_item);
		
		fluffy_cotton_item = new FluffyCottonItem();
		fluffy_cotton_item.setRegistryName("fluffy_cotton_item");
		GameRegistry.register(fluffy_cotton_item);
		
		pink_cotton_item = new PinkCottonItem();
		pink_cotton_item.setRegistryName("pink_cotton_item");
		GameRegistry.register(pink_cotton_item);
		
		redstone_emblem_item = new RedstoneEmblemItem();
		redstone_emblem_item.setRegistryName("redstone_emblem_item");
		GameRegistry.registerItem(redstone_emblem_item);
		
		//seeds:
		faithed_wheat_seeds = new FaithedWheatSeeds();
		faithed_wheat_seeds.setRegistryName("faithed_wheat_seeds");
		GameRegistry.register(faithed_wheat_seeds);
		
		fluffy_cotton_seeds = new FluffyCottonSeeds();
		fluffy_cotton_seeds.setRegistryName("fluffy_cotton_seeds");
		GameRegistry.register(fluffy_cotton_seeds);
		
		//tools:
		faith_emblem_item = new FaithEmblemItem();
		faith_emblem_item.setRegistryName("faith_emblem_item");
		GameRegistry.register(faith_emblem_item);
		
		faithed_wooden_sword = new FaithedWoodenSwordItem();
		faithed_wooden_sword.setRegistryName("faithed_wooden_sword");
		GameRegistry.register(faithed_wooden_sword);
		
		glowing_wooden_axe = new GlowingWoodenAxeItem();
		glowing_wooden_axe.setRegistryName("glowing_wooden_axe");
		GameRegistry.register(glowing_wooden_axe);
		
		faithed_stone_pickaxe = new FaithedStonePickaxeItem();
		faithed_stone_pickaxe.setRegistryName("faithed_stone_pickaxe");
		GameRegistry.register(faithed_stone_pickaxe);
		
		glowing_flint_scythe = new GlowingFlintScytheItem();
		glowing_flint_scythe.setRegistryName("glowing_flint_scythe");
		GameRegistry.register(glowing_flint_scythe);
		
		faithed_shears_item = new FaithedShearsItem();
		faithed_shears_item.setRegistryName("faithed_shears_item");
		GameRegistry.register(faithed_shears_item);
    }
}
