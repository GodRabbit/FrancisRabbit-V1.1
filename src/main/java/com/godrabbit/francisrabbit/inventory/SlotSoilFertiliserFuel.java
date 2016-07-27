package com.godrabbit.francisrabbit.inventory;

import com.godrabbit.francisrabbit.items.ModItems;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

@Deprecated
public class SlotSoilFertiliserFuel extends Slot{

	public SlotSoilFertiliserFuel(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return (stack.getItem() == ModItems.oil_item) && stack.getItemDamage() == 4;
	}

}
