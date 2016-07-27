package com.godrabbit.francisrabbit.client.render.blocks;

import com.godrabbit.francisrabbit.MainClass;
import com.godrabbit.francisrabbit.blocks.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

public final class BlockRenderRegister {
	
	public static String modid = MainClass.MODID;
	
	public static void registerBlockRenderer() {
		registerBlock(ModBlocks.glowing_wood_block);
		registerBlock(ModBlocks.charity_chest_block);
		registerBlock(ModBlocks.faithed_soil_block);
		registerBlock(ModBlocks.glowing_farmer_block);
		registerBlock(ModBlocks.fluffy_cotton_crops);
		registerBlock(ModBlocks.soil_fertiliser_block);
    }
	
	public static void registerBlock(Block block)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
	    .register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(modid + ":" + block.getUnlocalizedName().substring(5), "inventory"));
	}

}
