package com.godrabbit.francisrabbit.tools;

import com.godrabbit.francisrabbit.armor.FaithEmblemItem;
import com.godrabbit.francisrabbit.items.ModItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class FaithedShearsItem extends ItemShears{

	public FaithedShearsItem() {
		this.setUnlocalizedName("faithed_shears_item");
		this.setCreativeTab(ModItems.ultrarabbitTab);
		this.setMaxDamage(150);
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
				if(faith >= 100)
				{
					stack.setItemDamage(0);
				}
			}
		}
		super.onUpdate(stack, world, entity, itemSlot, isSelected);
	}

}
