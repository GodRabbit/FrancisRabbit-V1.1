package com.godrabbit.francisrabbit.blocks;

import com.godrabbit.francisrabbit.items.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class GlowingWoodBlock extends Block{

	public GlowingWoodBlock() {
		super(Material.WOOD);
		this.setUnlocalizedName("glowing_wood_block");
		this.setHardness(2.0f);
        this.setResistance(6.0f);
        this.setHarvestLevel("axe", 2);
        this.setLightLevel(1.0f);
		this.setCreativeTab(ModItems.ultrarabbitTab);
	}

}
