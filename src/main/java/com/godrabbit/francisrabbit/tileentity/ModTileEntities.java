package com.godrabbit.francisrabbit.tileentity;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTileEntities {

	public static void init()
	{
		GameRegistry.registerTileEntity(CharityChestTileEntity.class, 
				"charity_chest_tile_entity");
		GameRegistry.registerTileEntity(SoilFertiliserTileEntity.class, 
				"soil_fertiliser_tile_entity");
	}
}
