package com.godrabbit.francisrabbit;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MainClass.MODID, name= MainClass.MODNAME, version = MainClass.VERSION)
public class MainClass {
	
	@SidedProxy(clientSide="com.godrabbit.francisrabbit.ClientProxy", serverSide="com.godrabbit.francisrabbit.ServerProxy")
	public static CommonProxy proxy;
	
	public static final String MODID = "francisrabbit";
    public static final String MODNAME = "Francis Rabbit";
    public static final String VERSION = "1.1.6";

    @Instance
    public static MainClass instance = new MainClass();

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
    	this.proxy.preInit(e);
    }

    @EventHandler
    public void init(FMLInitializationEvent e) {
    	this.proxy.init(e);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
    	this.proxy.postInit(e);
    }
}
