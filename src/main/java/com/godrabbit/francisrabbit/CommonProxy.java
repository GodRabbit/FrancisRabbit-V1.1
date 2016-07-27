package com.godrabbit.francisrabbit;

import com.godrabbit.francisrabbit.blocks.ModBlocks;
import com.godrabbit.francisrabbit.crafting.ModCrafting;
import com.godrabbit.francisrabbit.items.ModItems;
import com.godrabbit.francisrabbit.network.ModGUIHandler;
import com.godrabbit.francisrabbit.tileentity.ModTileEntities;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {
	public void preInit(FMLPreInitializationEvent e) {
		ModItems.createItems();
		ModBlocks.createBlocks();
		ModTileEntities.init();
    }

    public void init(FMLInitializationEvent e) {
    	ModCrafting.initCrafting();
    	NetworkRegistry.INSTANCE.registerGuiHandler(MainClass.instance, new ModGUIHandler());
    	
    }

    public void postInit(FMLPostInitializationEvent e) {

    }
}
