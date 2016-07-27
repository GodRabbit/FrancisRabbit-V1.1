package com.godrabbit.francisrabbit.tools;

import java.util.Set;

import com.godrabbit.francisrabbit.armor.FaithEmblemItem;
import com.godrabbit.francisrabbit.items.ModItems;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class GlowingFlintScytheItem extends ItemTool{
	static ToolMaterial glowing = EnumHelper.addToolMaterial("glowing", 4, 199, 15.0f, 1.5f, 1);

	public GlowingFlintScytheItem() {
		super(1.0f, 1.0f, glowing, ImmutableSet.of(Blocks.CACTUS, Blocks.CARROTS, Blocks.VINE,
				Blocks.BEETROOTS, Blocks.WHEAT, Blocks.POTATOES, Blocks.PUMPKIN, Blocks.MELON_BLOCK,
				Blocks.REEDS, Blocks.DIRT, Blocks.GRASS, Blocks.FARMLAND));
		this.setUnlocalizedName("glowing_flint_scythe");
		this.setCreativeTab(ModItems.ultrarabbitTab);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if(world.getWorldTime() % 2400 == 0 && entity instanceof EntityPlayer && 
				((EntityPlayer)entity).getItemStackFromSlot(EntityEquipmentSlot.CHEST) != null &&
				((EntityPlayer)entity).getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof
				FaithEmblemItem)
		{
			ItemStack chest = ((EntityPlayer)entity).getItemStackFromSlot(EntityEquipmentSlot.CHEST);
			if(chest.getTagCompound() != null && chest.getTagCompound().hasKey("faith"))
			{
				int faith = chest.getTagCompound().getInteger("faith");
				if(faith >= 30 && faith < 100 && stack.getItemDamage() > 0)
				{
					stack.setItemDamage(stack.getItemDamage()-1);
				}
				if(faith >= 500)
				{
					stack.setItemDamage(0);
				}
			}
		}
		super.onUpdate(stack, world, entity, itemSlot, isSelected);
	}
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		for(int i=-1;i<=1;i++)
		{
			for(int j=-1;j<=1;j++)
			{
				Vec3i p = new Vec3i(i,0,j); //loc vec
				
				IBlockState bos = world.getBlockState(pos.add(p)); //the current block state
				Block b = bos.getBlock(); //curr block
				
				if(b instanceof BlockCrops && bos.getPropertyNames().contains(BlockCrops.AGE)) //is it crops?
				{
					if(((Integer)bos.getValue(BlockCrops.AGE)).intValue() >= ((BlockCrops)b).getMaxAge()) //is it fully grown?
					{
						for(ItemStack d : b.getDrops(world, pos.add(p), bos, 1)) //get drops
						{
							//bos.withProperty(BlockCrops.AGE, 0);
							world.setBlockState(pos.add(p), bos.withProperty(BlockCrops.AGE, 2));
							player.dropItem(d, false, false); //drop items to player
						}
					}
					
					
				}
			}
		}
		return super.onItemUse(stack, player, world, pos, hand, facing, hitX, hitY, hitZ);
	}

}
