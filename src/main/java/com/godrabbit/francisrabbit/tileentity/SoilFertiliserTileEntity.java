package com.godrabbit.francisrabbit.tileentity;

import com.godrabbit.francisrabbit.items.ModItems;

import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class SoilFertiliserTileEntity extends TileEntity implements IInventory, ITickable{

	public static final int MAX_BURN_TIME = 200;

	private ItemStack fuelSlot;
	private String customName;

	//specific veriables:
	int burnTime;

	public SoilFertiliserTileEntity() {
		fuelSlot = null;
	}

	public String getCustomName() {
		return this.customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}


	@Override
	public String getName() {
		return this.hasCustomName() ? this.customName : 
			"container.soil_fertiliser_tile_entity";
	}

	@Override
	public boolean hasCustomName() {
		return this.customName != null && !this.customName.equals("");
	}

	@Override
	public ITextComponent getDisplayName() {
		return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
	}

	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		if(index != 0)
			return null;
		return this.fuelSlot;
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		if (this.getStackInSlot(index) != null) {
			ItemStack itemstack;

			if (this.getStackInSlot(index).stackSize <= count) {
				itemstack = this.getStackInSlot(index);
				this.setInventorySlotContents(index, null);
				this.markDirty();
				return itemstack;
			} else {
				itemstack = this.getStackInSlot(index).splitStack(count);

				if (this.getStackInSlot(index).stackSize <= 0) {
					this.setInventorySlotContents(index, null);
				} else {
					//Just to show that changes happened
					this.setInventorySlotContents(index, this.getStackInSlot(index));
				}

				this.markDirty();
				return itemstack;
			}
		} else {
			return null;
		}
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		ItemStack stack = this.getStackInSlot(index);
		this.setInventorySlotContents(index, null);
		return stack;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if (index < 0 || index >= this.getSizeInventory())
			return;

		if (stack != null && stack.stackSize > this.getInventoryStackLimit())
			stack.stackSize = this.getInventoryStackLimit();

		if (stack != null && stack.stackSize == 0)
			stack = null;

		this.fuelSlot = stack;
		this.markDirty();

	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.worldObj.getTileEntity(this.getPos()) == this && player.getDistanceSq(this.pos.add(0.5, 0.5, 0.5)) <= 64;
	}

	@Override
	public void openInventory(EntityPlayer player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeInventory(EntityPlayer player) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		// TODO to be changed to coal oil!
		return true;
	}

	@Override
	public int getField(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		this.fuelSlot = null;

	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);

		NBTTagList list = new NBTTagList();
		for (int i = 0; i < this.getSizeInventory(); ++i) {
			if (this.getStackInSlot(i) != null) {
				NBTTagCompound stackTag = new NBTTagCompound();
				stackTag.setByte("Slot", (byte) i);
				this.getStackInSlot(i).writeToNBT(stackTag);
				list.appendTag(stackTag);
			}
		}
		nbt.setTag("Items", list);

		if (this.hasCustomName()) {
			nbt.setString("CustomName", this.getCustomName());
		}

		nbt.setInteger("burnTime", this.burnTime);

		return nbt;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);

		NBTTagList list = nbt.getTagList("Items", 10);
		for (int i = 0; i < list.tagCount(); ++i) {
			NBTTagCompound stackTag = list.getCompoundTagAt(i);
			int slot = stackTag.getByte("Slot") & 255;
			this.setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(stackTag));
		}

		if (nbt.hasKey("CustomName", 8)) {
			this.setCustomName(nbt.getString("CustomName"));
		}

		if(nbt.hasKey("burnTime"))
		{
			this.burnTime = nbt.getInteger("burnTime");
		}
		else
			this.burnTime = 0;
	}

	public int getBurnTime()
	{
		return this.burnTime;
	}

	@Override
	public void update() {
		//burn fuel, and fertilise soil around you

		//if the soil fertiliser is already burning
		if(this.burnTime > 0 && !this.getWorld().isRemote)
		{
			this.burnTime --;
			for(int i = -2; i < 3; i++)
			{
				for(int j = -2; j < 3; j++)
				{
					if(this.getWorld().rand.nextInt(500) == 0)
					{
						int x = this.getPos().getX() + i;
						int y = this.getPos().getY() + 1;
						int z = this.getPos().getZ() + j;
						BlockPos var = new BlockPos(x, y, z);
						if(this.getWorld().getBlockState(var).getBlock() instanceof IGrowable)
						{

							((IGrowable)this.getWorld().getBlockState(var).getBlock())
							.grow(this.getWorld(), this.getWorld().rand, var, this.getWorld().getBlockState(var));
							this.markDirty();

						}
					}
				}
			}
		}
		else if(!this.getWorld().isRemote) // if burn time is over try to burn another fuel
		{
			//there is fuel in the slot!
			if(this.fuelSlot != null && this.fuelSlot.getItem() == ModItems.oil_item 
					&& this.fuelSlot.getItemDamage() == 4)
			{
				this.burnTime = MAX_BURN_TIME; //start burning!
				//decrease fuel stack by one
				this.decrStackSize(0, 1);
				this.markDirty();
			}

		}

	}

}
