package com.godrabbit.francisrabbit.client.render.items;

import com.godrabbit.francisrabbit.MainClass;
import com.godrabbit.francisrabbit.items.ModItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public final class ItemRenderRegister {
	
	public static void preInit()
	{
		ModelBakery.registerItemVariants(ModItems.oil_item, 
				new ResourceLocation(modid + ":" + "oil_item_base"),
				new ResourceLocation(modid + ":" + "oil_item_bark"),
				new ResourceLocation(modid + ":" + "oil_item_seed"), 
				new ResourceLocation(modid + ":" + "oil_item_pumpkin"),
				new ResourceLocation(modid + ":" + "oil_item_coal"),
				new ResourceLocation(modid + ":" + "oil_item_circuit"));
	}
	
	public static void registerItemRenderer() {
		//registerItem(ModItems.weed_leaf_item);
		registerItem(ModItems.faith_emblem_item);
		registerItem(ModItems.faithed_wooden_sword);
		registerItem(ModItems.glowing_wooden_axe);
		registerItem(ModItems.faithed_stone_pickaxe);
		registerItem(ModItems.faithed_wheat_seeds);
		registerItem(ModItems.glowing_flint_scythe);
		registerItem(ModItems.fluffy_cotton_seeds);
		registerItem(ModItems.fluffy_cotton_item);
		registerItem(ModItems.faithed_shears_item);
		registerItem(ModItems.pink_cotton_item);
		registerItem(ModItems.redstone_emblem_item);
		
		//register metadata items:
		registerItem(ModItems.oil_item, 0, "oil_item_base");
		registerItem(ModItems.oil_item, 1, "oil_item_bark");
		registerItem(ModItems.oil_item, 2, "oil_item_seed");
		registerItem(ModItems.oil_item, 3, "oil_item_pumpkin");
		registerItem(ModItems.oil_item, 4, "oil_item_coal");
		registerItem(ModItems.oil_item, 5, "oil_item_circuit");
    }
	
	public static String modid = MainClass.MODID;

	public static void registerItem(Item item) {
	    Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
	    .register(item, 0, new ModelResourceLocation(modid + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
	
	public static void registerItem(Item item, int meta, String file) {
	    Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation(modid + ":" + file, "inventory"));
	}
	
	
}
