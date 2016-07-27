package com.godrabbit.francisrabbit.blocks;

import com.godrabbit.francisrabbit.armor.FaithEmblemItem;
import com.godrabbit.francisrabbit.items.ModItems;
import com.godrabbit.francisrabbit.tileentity.CharityChestTileEntity;
import com.godrabbit.francisrabbit.utility.RabbitInventoryHelper;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class CharityChestBlock extends Block implements ITileEntityProvider {

	
	public CharityChestBlock() {
		super(Material.ROCK);
		this.setUnlocalizedName("charity_chest_block");
		this.setHardness(2.0f);
        this.setResistance(6.0f);
        this.setHarvestLevel("axe", 1);
        this.setCreativeTab(ModItems.ultrarabbitTab);
        this.isBlockContainer = true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new CharityChestTileEntity();
	}
	
	//this te don't drop its contents on break!!
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		//CharityChestTileEntity te = (CharityChestTileEntity)world.getTileEntity(pos);
		//InventoryHelper.dropInventoryItems(world, pos, te);
	    super.breakBlock(world, pos, state);
	}
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		if(stack.hasDisplayName())
		{
			((CharityChestTileEntity)world.getTileEntity(pos)).setCustomName(stack.getDisplayName());
		}
		super.onBlockPlacedBy(world, pos, state, placer, stack);
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player,
			EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(!world.isRemote && player.getItemStackFromSlot(EntityEquipmentSlot.CHEST) != null &&
				heldItem != null &&
				player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof
				FaithEmblemItem && heldItem.getItem() instanceof ItemFood &&
				((CharityChestTileEntity)world.getTileEntity(pos)).getCooldown() == 0)
		{
			ItemStack leftover = 
					RabbitInventoryHelper.putStackInInventoryAllSlots((CharityChestTileEntity)world.getTileEntity(pos), heldItem, side);
			player.setHeldItem(hand, leftover);
			ItemStack chest = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST); //chestplate stack
			if(chest.getTagCompound() != null && chest.getTagCompound().hasKey("faith"))
			{
				int faith = chest.getTagCompound().getInteger("faith");
				int val = getFaithValue(heldItem);
				chest.getTagCompound().setInteger("faith", faith+val);
				player.addChatMessage(new TextComponentString("Player Got +" + val + " Faith points!"));
				player.setItemStackToSlot(EntityEquipmentSlot.CHEST, chest);
			}
			((CharityChestTileEntity)world.getTileEntity(pos)).resetCooldown();
			return super.onBlockActivated(world, pos, state, player, hand, heldItem, side, hitX, hitY, hitZ);
		}
		else if(!world.isRemote && heldItem == null && 
				((CharityChestTileEntity)world.getTileEntity(pos)).getCooldown() == 0
				&& player.isSneaking() && (player.getItemStackFromSlot(EntityEquipmentSlot.CHEST) == null || !(player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem()
				instanceof FaithEmblemItem)) )
		{
			CharityChestTileEntity te = ((CharityChestTileEntity)world.getTileEntity(pos));
			for(int i = 0; i < te.getSizeInventory(); i++)
			{
				if(te.getStackInSlot(i) != null)
				{
					RabbitInventoryHelper.pullItemFromSlotForPlayer(player, 
							((CharityChestTileEntity)world.getTileEntity(pos)), i, side);
					break;
				}
			}
			((CharityChestTileEntity)world.getTileEntity(pos)).resetCooldown();
		}
		return super.onBlockActivated(world, pos, state, player, hand, heldItem, side, hitX, hitY, hitZ);
	}
	
	public static int getFaithValue(ItemStack stack)
	{
		double val = 0.0;
		double stackMod2 = 0.0; //get +1.0 if at max stack size;
		if(stack.getItem() instanceof ItemFood)
		{
			double hunger = (double)((ItemFood)stack.getItem()).getHealAmount(stack);
			double sat = 2.0*hunger*(double)((ItemFood)stack.getItem()).getSaturationModifier(stack);
			//if(stack.getItem().)
			double stackModifier = getStackValue(stack.stackSize); //for 64 stack food, get the usual formula
			double qualityModifier = 0.1; //1.0 for "non-quality" food [sat/hung < 1] and 2.0 for rest;
			
			if(stack.getMaxStackSize() == 1)
			{
				stackModifier = 2.0; //singular stack food get more points
			}
			
			if(sat/hunger >= 1.0)
				qualityModifier = 0.4;
			
			
			if(stack.stackSize == stack.getItem().getItemStackLimit())
				stackMod2 = 1.0;
			/*System.out.println("sat/hung : "+sat/hunger);
			System.out.println("sat: "+sat);
			System.out.println("Hunger: "+hunger);*/
			val += qualityModifier*stackModifier*(sat/hunger)*(hunger + sat);
		}
		return (int) (Math.floor(val) + stackMod2);
	}
	
	public static double getStackValue(int size)
	{
		if(size >= 64)
			return 2.0;
		else if(size < 64 && size>= 48)
			return 1.5;
		else if(size < 48 && size >= 32)
			return 1.0;
		else if(size < 32 && size >= 16)
			return 0.5;
		else
			return 0.0;
	}
	

}
