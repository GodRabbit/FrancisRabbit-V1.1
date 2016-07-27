package com.godrabbit.francisrabbit.blocks;

import com.godrabbit.francisrabbit.crops.FluffyCottonCrops;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModBlocks {
	
	public static Block glowing_wood_block;
	public static Block faithed_soil_block;
	public static Block glowing_farmer_block;
	
	//machines\tile entities:
	public static Block charity_chest_block;
	public static Block soil_fertiliser_block;
	
	//crops:
	public static Block fluffy_cotton_crops;
	
	public static void createBlocks() {
		glowing_wood_block = new GlowingWoodBlock();
		glowing_wood_block.setRegistryName("glowing_wood_block");
		GameRegistry.registerBlock(glowing_wood_block);
		
		charity_chest_block = new CharityChestBlock();
		charity_chest_block.setRegistryName("charity_chest_block");
		GameRegistry.registerBlock(charity_chest_block);
		
		faithed_soil_block = new FaithedSoilBlock();
		faithed_soil_block.setRegistryName("faithed_soil_block");
		GameRegistry.registerBlock(faithed_soil_block);
		
		glowing_farmer_block = new GlowingFarmerBlock();
		glowing_farmer_block.setRegistryName("glowing_farmer_block");
		GameRegistry.registerBlock(glowing_farmer_block);
		
		soil_fertiliser_block = new SoilFertiliserBlock();
		soil_fertiliser_block.setRegistryName("soil_fertiliser_block");
		GameRegistry.registerBlock(soil_fertiliser_block);
		
		//crops:
		fluffy_cotton_crops = new FluffyCottonCrops();
		fluffy_cotton_crops.setRegistryName("fluffy_cotton_crops");
		GameRegistry.register(fluffy_cotton_crops);
    }
}
