package com.godrabbit.francisrabbit.tools;

import java.util.List;

import com.godrabbit.francisrabbit.armor.FaithEmblemItem;
import com.godrabbit.francisrabbit.items.ModItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class GlowingWoodenAxeItem extends ItemAxe{

	static ToolMaterial glowing = EnumHelper.addToolMaterial("glowing", 4, 199, 15.0f, 1.5f, 1);
	public static final String un_name = "glowing_wooden_axe" ;

	public GlowingWoodenAxeItem() {
		super(glowing, 7.0f, -1.5f);
		this.setUnlocalizedName(un_name);
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
				if(faith >= 100)
				{
					stack.setItemDamage(0);
				}
			}
		}
		super.onUpdate(stack, world, entity, itemSlot, isSelected);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		if(player.getItemStackFromSlot(EntityEquipmentSlot.CHEST) != null &&
			player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof FaithEmblemItem)
		{
			ItemStack chest = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
			if(chest.getTagCompound() != null && chest.getTagCompound().hasKey("faith"))
			{
				int faith = chest.getTagCompound().getInteger("faith");
				if(faith >= 30)
				{
					player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 160, 0, false, false));
					player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 160, 0, false, false));
				}
				if(faith >= 100)
				{
					player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 160, 1, false, false));
				}
				if(faith >= 200)
				{
					player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 160, 1, false, false));
				}
				if(faith >= 300)
				{
					player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 160, 2, false, false));
				}
				if(faith >= 600)
				{
					player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 800, 2, false, false));
				}
				if(faith >= 1000)
				{
					player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 800, 2, false, false));
				}
				if(faith >= 1500)
				{
					player.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, 800, 3, false, false));
				}
				if(faith < 0)
				{
					player.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 240, 5, false, true));
				}
			}
		}
		else
		{
			player.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 500, 2, false, true));
			player.addPotionEffect(new PotionEffect(MobEffects.POISON, 200, 1, false, true));
		}
		return super.onLeftClickEntity(stack, player, entity);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		tooltip.add("Can be used as a weapon");
		super.addInformation(stack, playerIn, tooltip, advanced);
	}

}
