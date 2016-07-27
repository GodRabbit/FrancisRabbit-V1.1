package com.godrabbit.francisrabbit.blocks;

import javax.annotation.Nullable;

import com.godrabbit.francisrabbit.items.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

public class FaithedSoilBlock extends Block{

	protected static final AxisAlignedBB FARMLAND_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.9375D, 1.0D);
	
	public FaithedSoilBlock() {
		super(Material.GROUND);
		this.setUnlocalizedName("faithed_soil_block");
		this.setHardness(2.0f);
        this.setResistance(6.0f);
        this.setHarvestLevel("shoval", 2);
		this.setCreativeTab(ModItems.ultrarabbitTab);
	}
	
	@Override
	public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction,
			IPlantable plantable) {
		
		return true;
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player,
			EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		//right click on soil with empty hand will harvest and re-plant crops;
		if(heldItem == null)
		{
			IBlockState bos = world.getBlockState(pos.add(0, 1, 0)); //one block above the soil;
			Block b = bos.getBlock();
			
			if(b instanceof BlockCrops && bos.getPropertyNames().contains(BlockCrops.AGE)) //is it crops?
			{
				if(((Integer)bos.getValue(BlockCrops.AGE)).intValue() >= ((BlockCrops)b).getMaxAge()) //is it fully grown?
				{
					for(ItemStack d : b.getDrops(world, pos.add(0, 1, 0), bos, 1)) //get drops
					{
						//bos.withProperty(BlockCrops.AGE, 0);
						world.setBlockState(pos.add(0, 1, 0), bos.withProperty(BlockCrops.AGE, 1));
						player.dropItem(d, false, true); //drop items to player
					}
				}
				
				
			}
		}
		return super.onBlockActivated(world, pos, state, player, hand, heldItem, side, hitX, hitY, hitZ);
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return FARMLAND_AABB;
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos)
    {
        return FULL_BLOCK_AABB;
    }

    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

}
