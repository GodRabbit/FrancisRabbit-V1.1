package com.godrabbit.francisrabbit.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class OilItem extends Item{
	
	public final String unlocalizedName = "oil_item";
	
	public OilItem() {
		super();
		this.setUnlocalizedName(unlocalizedName);
		this.setHasSubtypes(true);
		this.setCreativeTab(ModItems.ultrarabbitTab);
		//this.setContainerItem(ModItems.oil_item);
	}

	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		
		switch(stack.getItemDamage())
		{
		case 0:
			return "item."+unlocalizedName+"_"+"base";
		case 1:
			return "item."+unlocalizedName+"_"+"bark";
		case 2:
			return "item."+unlocalizedName+"_"+"seeds";
		case 3:
			return "item."+unlocalizedName+"_"+"pumpkin";
		case 4:
			return "item."+unlocalizedName+"_"+"coal";
		case 5:
			return "item."+unlocalizedName+"_"+"circuit";
		}
		return super.getUnlocalizedName();
	}
	
	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
		subItems.add(new ItemStack(itemIn, 1, 0));
	    subItems.add(new ItemStack(itemIn, 1, 1));
	    subItems.add(new ItemStack(itemIn, 1, 2));
	    subItems.add(new ItemStack(itemIn, 1, 3));
	    subItems.add(new ItemStack(itemIn, 1, 4));
	    subItems.add(new ItemStack(itemIn, 1, 5));
	}
	

}
