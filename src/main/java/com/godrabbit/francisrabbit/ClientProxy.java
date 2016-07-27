package com.godrabbit.francisrabbit;

import com.godrabbit.francisrabbit.client.render.blocks.BlockRenderRegister;
import com.godrabbit.francisrabbit.client.render.items.ItemRenderRegister;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy  extends CommonProxy{
	@Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
        ItemRenderRegister.preInit();
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
        
        ItemRenderRegister.registerItemRenderer();
        BlockRenderRegister.registerBlockRenderer();
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }
}
