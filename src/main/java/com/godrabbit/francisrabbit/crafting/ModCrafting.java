package com.godrabbit.francisrabbit.crafting;

import com.godrabbit.francisrabbit.blocks.ModBlocks;
import com.godrabbit.francisrabbit.items.ModItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModCrafting {
	
	public static void initCrafting()
	{
		GameRegistry.addRecipe(new ItemStack(ModItems.faith_emblem_item), 
				" A ",
				"APA",
				" A ", 'A', Items.LEATHER_CHESTPLATE, 'P', Blocks.RED_FLOWER);
		
		GameRegistry.addRecipe(new ItemStack(ModItems.faithed_wooden_sword),
				"WAW",
				"ASA",
				"WAW", 'W', Blocks.LOG, 'A', ModItems.faith_emblem_item, 'S', Items.WOODEN_SWORD);
		
		GameRegistry.addRecipe(new ItemStack(ModItems.glowing_wooden_axe),
				"WSW",
				"SAS",
				"WSW", 'W', ModBlocks.glowing_wood_block, 'S', ModItems.faithed_wooden_sword,
				'A', Items.WOODEN_AXE);
		
		GameRegistry.addRecipe(new ItemStack(ModBlocks.glowing_wood_block),
				"WWW",
				"WPW",
				"WWW", 'W', Blocks.LOG, 'P', Blocks.PLANKS);
		
		GameRegistry.addRecipe(new ItemStack(ModItems.faithed_stone_pickaxe),
				"WAW",
				"APA",
				"WAW", 'W', ModBlocks.glowing_wood_block, 'A', ModItems.faith_emblem_item,
				'P', Items.STONE_PICKAXE);
		
		GameRegistry.addRecipe(new ItemStack(ModItems.glowing_flint_scythe),
				"WAW",
				"APA",
				"WAW", 'W', ModBlocks.glowing_wood_block, 'A', ModItems.faithed_stone_pickaxe,
				'P', Items.STONE_HOE);
		
		GameRegistry.addRecipe(new ItemStack(ModBlocks.charity_chest_block), 
				"GCG",
				"CCC",
				"GCG", 'G', ModBlocks.glowing_wood_block, 'C', Blocks.CHEST);
		
		GameRegistry.addRecipe(new ItemStack(Items.LEATHER, 8),
				"WFW",
				"FLF",
				"WFW", 'W', Blocks.WOOL, 'F', Items.FEATHER, 'L', new ItemStack(ModItems.oil_item, 1, 2));
		
		GameRegistry.addRecipe(new ItemStack(ModItems.oil_item, 4, 0),
				" B ",
				"BEB",
				" B ", 'B', Items.BRICK, 'E', Items.EGG);
		
		GameRegistry.addRecipe(new ItemStack(ModItems.oil_item, 1, 1),
				"BBB",
				"BEB",
				"BBB", 'B', Blocks.SAPLING, 'E', new ItemStack(ModItems.oil_item, 1, 0));
		
		GameRegistry.addRecipe(new ItemStack(ModItems.oil_item, 1, 1),
				"BBB",
				"BEB",
				"BBB", 'B', ModBlocks.glowing_wood_block, 'E', new ItemStack(ModItems.oil_item, 1, 0));
		
		GameRegistry.addRecipe(new ItemStack(ModItems.oil_item, 1, 2),
				"BBB",
				"BEB",
				"BBB", 'B', Items.WHEAT_SEEDS, 'E', new ItemStack(ModItems.oil_item, 1, 1));
		
		GameRegistry.addRecipe(new ItemStack(ModItems.oil_item, 1, 3),
				"BBB",
				"BEB",
				"BBB", 'B', Blocks.PUMPKIN, 'E', new ItemStack(ModItems.oil_item, 1, 2));
		
		GameRegistry.addRecipe(new ItemStack(ModItems.oil_item, 1, 4),
				" B ",
				"BEB",
				" B ", 'B', Blocks.COAL_BLOCK, 'E', new ItemStack(ModItems.oil_item, 1, 2));
		
		GameRegistry.addRecipe(new ItemStack(ModItems.oil_item, 1, 5),
				"BBB",
				"BEB",
				"BBB", 'B', ModItems.redstone_emblem_item, 'E', new ItemStack(ModItems.oil_item, 1, 4));
		
		GameRegistry.addRecipe(new ItemStack(ModBlocks.faithed_soil_block, 8),
				"DDD",
				"DOD",
				"DDD", 'D', Blocks.DIRT, 'O', new ItemStack(ModItems.oil_item, 1, 1));
		
		GameRegistry.addRecipe(new ItemStack(ModItems.fluffy_cotton_seeds),
				"OOO",
				"OSO",
				"OOO", 'O', new ItemStack(ModItems.oil_item, 1, 3), 'S', Items.WHEAT_SEEDS);
		
		
		GameRegistry.addRecipe(new ItemStack(Items.DYE, 8, 15),
				"DDD",
				"DOD",
				"DDD", 'D', Items.WHEAT_SEEDS, 'O', new ItemStack(ModItems.oil_item, 1, 2));
		
		GameRegistry.addRecipe(new ItemStack(ModBlocks.glowing_farmer_block),
				"SBS",
				"BFB",
				"SBS", 'S', ModBlocks.faithed_soil_block, 'B', Blocks.BRICK_BLOCK, 'F', Items.FLINT_AND_STEEL);
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.FEATHER), ModItems.pink_cotton_item,
				ModItems.pink_cotton_item, ModItems.pink_cotton_item, ModItems.pink_cotton_item);
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.STRING), ModItems.pink_cotton_item,
				ModItems.pink_cotton_item, ModItems.pink_cotton_item);
		
		GameRegistry.addRecipe(new ItemStack(Blocks.WOOL, 1, 6),
				"DDD",
				"DDD",
				"DDD", 'D', ModItems.pink_cotton_item);
		
		GameRegistry.addRecipe(new ItemStack(ModItems.faithed_shears_item),
				" Q", 
				"Q ", 'Q', ModBlocks.glowing_wood_block);
		
		GameRegistry.addRecipe(new ItemStack(ModItems.redstone_emblem_item),
				"RTR",
				"TDT",
				"RTR", 'R', Items.REDSTONE, 'T', Blocks.REDSTONE_TORCH, 'D', Blocks.DAYLIGHT_DETECTOR);
		
		GameRegistry.addRecipe(new ItemStack(ModBlocks.soil_fertiliser_block),
				"SOS",
				"OFO",
				"SOS", 'S', ModBlocks.faithed_soil_block, 'O', new ItemStack(ModItems.oil_item, 1, 5),
				'F', ModBlocks.glowing_farmer_block);
		
	}
}
