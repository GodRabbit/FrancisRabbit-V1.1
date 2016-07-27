package com.godrabbit.francisrabbit.utility;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.IHopper;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.EnumFacing;

public class RabbitInventoryHelper {

	/**
	 * Attempts to place the passed stack in the inventory, using as many slots as required. Returns leftover items.
	 * 
	 * unlike the TileEntityHopper method, this one completely ignores ISidedInventory restraints. 
	 * Useful in these cases:
	 *  	1. You want to pull\put items in the inventory in some way, but want hoppers\nodes\transfer pipes 
	 *  to ignore your TE and wont pull\put items in.
	 */
	public static ItemStack putStackInInventoryAllSlots(IInventory inventoryIn, ItemStack stack, @Nullable EnumFacing side)
	{
		//sided inventory being considered here only due to which has which slots aviable
		//otherwise, you could insert items to wrong slots.
		if (inventoryIn instanceof ISidedInventory && side != null)
		{
			ISidedInventory isidedinventory = (ISidedInventory)inventoryIn;
			int[] aint = isidedinventory.getSlotsForFace(side);

			for (int k = 0; k < aint.length && stack != null && stack.stackSize > 0; ++k)
			{
				stack = insertStack(inventoryIn, stack, aint[k], side);
			}
		}
		else
		{
			int i = inventoryIn.getSizeInventory();

			for (int j = 0; j < i && stack != null && stack.stackSize > 0; ++j)
			{
				stack = insertStack(inventoryIn, stack, j, side);
			}
		}

		if (stack != null && stack.stackSize == 0)
		{
			stack = null;
		}

		return stack;
	}

	/**
	 * Insert the specified stack to the specified inventory and return any leftover items
	 * 
	 * unlike the TileEntityHopper method, this one completely ignores ISidedInventory restraints.
	 */
	private static ItemStack insertStack(IInventory inventoryIn, ItemStack stack, int index, EnumFacing side)
	{
		ItemStack itemstack = inventoryIn.getStackInSlot(index);

		if (canInsertItemInSlot(inventoryIn, stack, index, side))
		{
			boolean flag = false;

			if (itemstack == null)
			{
				//Forge: BUGFIX: Again, make things respect max stack sizes.
				int max = Math.min(stack.getMaxStackSize(), inventoryIn.getInventoryStackLimit());
				if (max >= stack.stackSize)
				{
					inventoryIn.setInventorySlotContents(index, stack);
					stack = null;
				}
				else
				{
					inventoryIn.setInventorySlotContents(index, stack.splitStack(max));
				}
				flag = true;
			}
			else if (canCombine(itemstack, stack))
			{
				//Forge: BUGFIX: Again, make things respect max stack sizes.
				int max = Math.min(stack.getMaxStackSize(), inventoryIn.getInventoryStackLimit());
				if (max > itemstack.stackSize)
				{
					int i = max - itemstack.stackSize;
					int j = Math.min(stack.stackSize, i);
					stack.stackSize -= j;
					itemstack.stackSize += j;
					flag = j > 0;
				}
			}

			if (flag)
			{
				if (inventoryIn instanceof TileEntityHopper)
				{
					TileEntityHopper tileentityhopper = (TileEntityHopper)inventoryIn;

					if (tileentityhopper.mayTransfer())
					{
						tileentityhopper.setTransferCooldown(8);
					}

					inventoryIn.markDirty();
				}

				inventoryIn.markDirty();
			}
		}

		return stack;
	}

	/**
	 * Can this hopper insert the specified item from the specified slot on the specified side?
	 * 
	 * unlike the TileEntityHopper method, this one completely ignores ISidedInventory restraints
	 */
	public static boolean canInsertItemInSlot(IInventory inventoryIn, ItemStack stack, int index, EnumFacing side)
	{
		return !inventoryIn.isItemValidForSlot(index, stack) ? false : true;
	}

	/**
	 * Copy of the method from TileEntityHopper due to the method being private;
	 */
	public static boolean canCombine(ItemStack stack1, ItemStack stack2)
	{
		return stack1.getItem() != stack2.getItem() ? false : (stack1.getMetadata() != stack2.getMetadata() ? false : (stack1.stackSize > stack1.getMaxStackSize() ? false : ItemStack.areItemStackTagsEqual(stack1, stack2)));
	}

	/**
	 * Pulls from the specified slot in the inventory and spawn it in the world
	 *  as ItemEntity;
	 * . Returns true if
	 * the entire stack was moved.
	 * 
	 * Ignore ISidedInventory restraints. Used to pull items out to player.
	 */
	public static void pullItemFromSlotForPlayer(EntityPlayer player, IInventory inventoryIn, int index, EnumFacing direction)
	{
		if(!player.worldObj.isRemote)
		{
			//TODO: need testing. Extensive testing 
			ItemStack itemstack = inventoryIn.getStackInSlot(index);

			//&& canExtractItemFromSlot(inventoryIn, itemstack, index, direction) \\ was removed
			if (itemstack != null )
			{
				//ItemStack itemstack1 = itemstack.copy();
				ItemStack itemstack2 = inventoryIn.decrStackSize(index, itemstack.stackSize);
				player.dropItem(itemstack2, false, false);

				if (itemstack2 == null || itemstack2.stackSize == 0)
				{
					inventoryIn.markDirty();
					
				}

				inventoryIn.setInventorySlotContents(index, null);
			}

			
		}
		
	}


}
