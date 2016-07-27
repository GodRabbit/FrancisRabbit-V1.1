package com.godrabbit.francisrabbit.items;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class FaithedWheatSeeds extends Item implements IPlantable{
	
	public FaithedWheatSeeds() {
		super();
		this.setUnlocalizedName("faithed_wheat_seeds");
		this.setCreativeTab(ModItems.ultrarabbitTab);
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return EnumPlantType.Crop;
	}

	@Override
	public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
		// TODO Auto-generated method stub
		return null;
	}

}
