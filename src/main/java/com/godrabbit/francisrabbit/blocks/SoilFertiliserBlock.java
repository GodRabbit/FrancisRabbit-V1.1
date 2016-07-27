package com.godrabbit.francisrabbit.blocks;

import com.godrabbit.francisrabbit.MainClass;
import com.godrabbit.francisrabbit.items.ModItems;
import com.godrabbit.francisrabbit.network.ModGUIHandler;
import com.godrabbit.francisrabbit.tileentity.SoilFertiliserTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SoilFertiliserBlock extends Block implements ITileEntityProvider{

	public SoilFertiliserBlock() {
		super(Material.PISTON);
		this.setUnlocalizedName("soil_fertiliser_block");
		this.setHardness(2.0f);
        this.setResistance(2.0f);
        this.setHarvestLevel("pickaxe", 2);
		this.setCreativeTab(ModItems.ultrarabbitTab);
		this.isBlockContainer = true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new SoilFertiliserTileEntity();
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState blockstate) {
	    SoilFertiliserTileEntity te = (SoilFertiliserTileEntity) world.getTileEntity(pos);
	    InventoryHelper.dropInventoryItems(world, pos, te);
	    super.breakBlock(world, pos, blockstate);
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
	    if (stack.hasDisplayName()) {
	        ((SoilFertiliserTileEntity) worldIn.getTileEntity(pos)).setCustomName(stack.getDisplayName());
	    }
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player,
			EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(!world.isRemote)
		{
			player.openGui(MainClass.instance, ModGUIHandler.SOIL_FERTILISER_TILE_ENTITY_GUI_ID, 
					world, pos.getX(), pos.getY(), pos.getZ());
		}
		return super.onBlockActivated(world, pos, state, player, hand, heldItem, side, hitX, hitY, hitZ);
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
