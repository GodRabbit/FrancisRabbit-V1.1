package com.godrabbit.francisrabbit.network;

import com.godrabbit.francisrabbit.client.gui.GUISoilFertiliserTileEntity;
import com.godrabbit.francisrabbit.guicontainer.SoilFertiliserContainer;
import com.godrabbit.francisrabbit.tileentity.SoilFertiliserTileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class ModGUIHandler implements IGuiHandler{

	public static final int SOIL_FERTILISER_TILE_ENTITY_GUI_ID = 0;
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == SOIL_FERTILISER_TILE_ENTITY_GUI_ID)
			return new SoilFertiliserContainer(player.inventory, 
					(SoilFertiliserTileEntity)world.getTileEntity(new BlockPos(x, y, z)));
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == SOIL_FERTILISER_TILE_ENTITY_GUI_ID)
			return new GUISoilFertiliserTileEntity(player.inventory, 
					(SoilFertiliserTileEntity)world.getTileEntity(new BlockPos(x, y, z)));
		return null;
	}

}
