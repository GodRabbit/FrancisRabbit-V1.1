package com.godrabbit.francisrabbit.armor;

import java.util.ArrayList;
import java.util.List;

import com.godrabbit.francisrabbit.MainClass;
import com.godrabbit.francisrabbit.items.ModItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class FaithEmblemItem extends ItemArmor{
	public static ArmorMaterial FAITH_MATERIAL = EnumHelper.addArmorMaterial("FAITH_MATERIAL", 
			MainClass.MODID+":faith_emblem",15 ,new int[] {4, 4, 4, 4}, 30, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2.5f);

	ArrayList<ItemStack> _expensiveList;

	public FaithEmblemItem() {
		super(FAITH_MATERIAL, 1, EntityEquipmentSlot.CHEST);
		this.setUnlocalizedName("faith_emblem_item");
		this.setCreativeTab(ModItems.ultrarabbitTab);
		this.setMaxDamage(195);
		
		this.initExpensiveList();
	}
	
	public void initExpensiveList()
	{
		this._expensiveList = new ArrayList<ItemStack>();
		this._expensiveList.add(new ItemStack(Items.DIAMOND));
		this._expensiveList.add(new ItemStack(Items.GOLD_INGOT));
		this._expensiveList.add(new ItemStack(Items.DIAMOND_AXE));
		this._expensiveList.add(new ItemStack(Items.DIAMOND_HOE));
		this._expensiveList.add(new ItemStack(Items.DIAMOND_HORSE_ARMOR));
		this._expensiveList.add(new ItemStack(Items.DIAMOND_PICKAXE));
		this._expensiveList.add(new ItemStack(Items.DIAMOND_SHOVEL));
		this._expensiveList.add(new ItemStack(Items.DIAMOND_SWORD));
		this._expensiveList.add(new ItemStack(Items.DIAMOND_BOOTS));
		this._expensiveList.add(new ItemStack(Items.DIAMOND_CHESTPLATE));
		this._expensiveList.add(new ItemStack(Items.DIAMOND_HELMET));
		this._expensiveList.add(new ItemStack(Items.DIAMOND_LEGGINGS));
		this._expensiveList.add(new ItemStack(Items.GOLDEN_APPLE));
		this._expensiveList.add(new ItemStack(Items.GOLDEN_AXE));
		this._expensiveList.add(new ItemStack(Items.GOLDEN_CARROT));
		this._expensiveList.add(new ItemStack(Items.GOLDEN_HOE));
		this._expensiveList.add(new ItemStack(Items.GOLD_NUGGET));
		this._expensiveList.add(new ItemStack(Items.GOLDEN_HORSE_ARMOR));
		this._expensiveList.add(new ItemStack(Items.GOLDEN_PICKAXE));
		this._expensiveList.add(new ItemStack(Items.GOLDEN_SHOVEL));
		this._expensiveList.add(new ItemStack(Items.GOLDEN_SWORD));
		this._expensiveList.add(new ItemStack(Items.GOLDEN_BOOTS));
		this._expensiveList.add(new ItemStack(Items.GOLDEN_CHESTPLATE));
		this._expensiveList.add(new ItemStack(Items.GOLDEN_HELMET));
		this._expensiveList.add(new ItemStack(Items.GOLDEN_LEGGINGS));
		this._expensiveList.add(new ItemStack(Items.CAKE));
		this._expensiveList.add(new ItemStack(Items.EMERALD));
		this._expensiveList.add(new ItemStack(Items.ENDER_PEARL));
		this._expensiveList.add(new ItemStack(Items.GHAST_TEAR));
		this._expensiveList.add(new ItemStack(Items.GLOWSTONE_DUST));
		this._expensiveList.add(new ItemStack(Blocks.GLOWSTONE));
		this._expensiveList.add(new ItemStack(Blocks.DIAMOND_BLOCK));
		this._expensiveList.add(new ItemStack(Blocks.DIAMOND_ORE));
		this._expensiveList.add(new ItemStack(Blocks.BEACON));
		this._expensiveList.add(new ItemStack(Blocks.GOLD_BLOCK));
		this._expensiveList.add(new ItemStack(Blocks.GOLD_ORE));
		this._expensiveList.add(new ItemStack(Blocks.EMERALD_BLOCK));
		this._expensiveList.add(new ItemStack(Blocks.EMERALD_ORE));
		this._expensiveList.add(new ItemStack(Blocks.DRAGON_EGG));
		this._expensiveList.add(new ItemStack(Blocks.LAPIS_BLOCK));
		this._expensiveList.add(new ItemStack(Items.NETHER_STAR));
	}
	
	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		if(stack.getTagCompound() == null)
			stack.setTagCompound(new NBTTagCompound());
		if(!stack.getTagCompound().hasKey("faith"))
			stack.getTagCompound().setInteger("faith", 0);
		super.onCreated(stack, worldIn, playerIn);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		if(stack.getTagCompound() == null)
			stack.setTagCompound(new NBTTagCompound());
		if(!stack.getTagCompound().hasKey("faith"))
			stack.getTagCompound().setInteger("faith", 0);
		tooltip.add("Faith: "+stack.getTagCompound().getInteger("faith"));
		super.addInformation(stack, playerIn, tooltip, advanced);
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		/*every minute on which you don't have expensive item\tool\block on you, you'll earn 1 
		 * Faith Point [FP]. If, on the other hand, you have a expensive item on you,
		 * you'll lose 30 FP [negative values are possible].
		 */
		if(world.getWorldTime() % 1200 == 0 && !world.isRemote && stack != null) //change to 1200
		{
			boolean _found_expensive = false;
			if(stack.getTagCompound() == null)
				stack.setTagCompound(new NBTTagCompound());
			if(!stack.getTagCompound().hasKey("faith"))
				stack.getTagCompound().setInteger("faith", 0);
			//System.out.println("HELLLLLOOOOOOO"); //debug
			int faith = stack.getTagCompound().getInteger("faith")+1;
			
			for(ItemStack s : player.inventory.mainInventory)
			{
				if(isExpensive(s))
				{
					_found_expensive = true;
					break;
				}
			}
			for(ItemStack s : player.inventory.armorInventory)
			{
				if(isExpensive(s))
				{
					_found_expensive = true;
					break;
				}
			}
			for(ItemStack s : player.inventory.offHandInventory)
			{
				if(isExpensive(s))
				{
					_found_expensive = true;
					break;
				}
			}
			
			if(_found_expensive)
			{
				faith -= 32;
			}
			else if(faith >= 100)
			{
				stack.setItemDamage(0); //repair itself every minute if faith greater than 100
			}
			stack.getTagCompound().setInteger("faith", faith);
		}
		super.onArmorTick(world, player, stack);
	}

	//check if an itemstack is expensive
	public boolean isExpensive(ItemStack stack)
	{
		if(stack == null || stack.getItem() == ModItems.faith_emblem_item)
			return false;
		if(stack.stackSize == 1 && stack.getMaxDamage() > 200)
			return true;
		for(ItemStack s : this._expensiveList)
		{
			if(s != null && checkEqual(s, stack))
				return true;
		}

		return false;
	}

	//checks if 2 item stacks are the same just with different stack size
	public static boolean checkEqual(ItemStack s1, ItemStack s2)
	{
		return (s1.getItem() == s2.getItem()) && (s1.getItemDamage() == s2.getItemDamage());
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		//if the armor is NOT on your chestpiece, the FP will be reset to 0;
		if(!world.isRemote && world.getWorldTime() % 600 == 0 && itemSlot != 2)
		{
			if(stack.getTagCompound() != null && stack.getTagCompound().hasKey("faith"))
			{
				stack.getTagCompound().setInteger("faith", 0);
			}
		}
		super.onUpdate(stack, world, entity, itemSlot, isSelected);
	}
	
	@Override
	public boolean onDroppedByPlayer(ItemStack stack, EntityPlayer player) {
		if(!player.worldObj.isRemote && stack.getItem() instanceof FaithEmblemItem && stack.getTagCompound() != null
				&& stack.getTagCompound().hasKey("faith"))
		{
			stack.getTagCompound().setInteger("faith", 0);
		}
		return super.onDroppedByPlayer(stack, player);
	}

}
