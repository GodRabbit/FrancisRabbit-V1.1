package com.godrabbit.francisrabbit.items;

import com.godrabbit.francisrabbit.blocks.ModBlocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class FluffyCottonSeeds extends Item implements IPlantable{

	public FluffyCottonSeeds() {
		this.setUnlocalizedName("fluffy_cotton_seeds");
		this.setCreativeTab(ModItems.ultrarabbitTab);
	}
	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return EnumPlantType.Crop;
	}

	@Override
	public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
		return ModBlocks.fluffy_cotton_crops.getDefaultState();
	}
	
	/*@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player,
			World world, BlockPos pos, EnumFacing side, float hitX,
			float hitY, float hitZ) {
		if(side != EnumFacing.UP)
		{
			return false;
		}
		else if(!player.canPlayerEdit(pos.offset(side), side, stack))
		{
			return false;
		}
		else if (world.getBlockState(pos).getBlock().canSustainPlant(world, pos, EnumFacing.UP, this) && world.isAirBlock(pos.up()))
        {
            world.setBlockState(pos.up(), ModBlocks.fluffy_cotton_crops.getDefaultState());
            --stack.stackSize;
            return true;
        }
        else
        {
            return false;
        }
	}*/
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos,
			EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		
		if(side != EnumFacing.UP)
		{
			return EnumActionResult.FAIL;
		}
		else if(!player.canPlayerEdit(pos.offset(side), side, stack))
		{
			return EnumActionResult.FAIL;
		}
		else if (world.getBlockState(pos).getBlock().canSustainPlant(world.getBlockState(pos), 
				(IBlockAccess)world, pos,  EnumFacing.UP, 
				(IPlantable)this) && world.isAirBlock(pos.up()))
			
        {
            world.setBlockState(pos.up(), ModBlocks.fluffy_cotton_crops.getDefaultState());
            --stack.stackSize;
            return EnumActionResult.SUCCESS;
        }
        else
        {
            return EnumActionResult.FAIL;
        }
	}

}
