package com.godrabbit.francisrabbit.blocks;

import com.godrabbit.francisrabbit.items.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GlowingFarmerBlock extends Block{

	public GlowingFarmerBlock() {
		super(Material.ROCK);
		this.setUnlocalizedName("glowing_farmer_block");
		this.setHardness(2.0f);
        this.setResistance(6.0f);
        this.setHarvestLevel("pickaxe", 1);
        this.setLightLevel(1.0f);
		this.setCreativeTab(ModItems.ultrarabbitTab);
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
    
    @Override
    public void onLanded(World world, Entity entity) {
    	if(!world.isRemote && entity != null && entity instanceof EntityAnimal)
    	{
    		if(!((EntityAgeable)entity).isChild())
    		{
    			((EntityAnimal)entity).setFire(30);
    		}
    	}
    	super.onLanded(world, entity);
    }

}
