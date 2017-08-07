package info.u_team.usefulbackpacks.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentString;

public abstract class InventoryBackPackBase implements IInventory {
	
	protected ItemStack[] inventory;
	protected int size;
	
	@Override
	public int getSizeInventory() {
		return this.inventory.length;
	}
	
	@Override
	public ItemStack getStackInSlot(int index) {
		return inventory[index];
	}
	
	@Override
	public ItemStack decrStackSize(int slot, int count) {
		if (inventory[slot] == null) {
			return null;
		}
		ItemStack returnStack;
		if (inventory[slot].stackSize > count) {
			returnStack = inventory[slot].splitStack(count);
		} else {
			returnStack = inventory[slot];
			inventory[slot] = null;
		}
		onInventoryChanged();
		return returnStack;
	}
	
	@Override
	public void setInventorySlotContents(int slot, ItemStack itemStack) {
		if (0 <= slot && slot < size) {
			inventory[slot] = itemStack;
		}
		
	}
	
	public void onInventoryChanged() {
		for (int i = 0; i < size; i++) {
			ItemStack tempStack = getStackInSlot(i);
			if (tempStack != null && tempStack.stackSize == 0) {
				setInventorySlotContents(i, null);
			}
		}
	}
	
	public void increaseSize(int i) {
		ItemStack[] newInventory = new ItemStack[size + i];
		System.arraycopy(inventory, 0, newInventory, 0, size);
		inventory = newInventory;
		size = size + i;
	}
	
	abstract public void readFromNBT(NBTTagCompound myCompound);
	
	abstract public void writeToNBT(NBTTagCompound myCompound);
	
	@Override
	public TextComponentString getDisplayName() {
		return new TextComponentString(getName());
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer myPlayer) {
		return true;
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	@Override
	public void openInventory(EntityPlayer player) {
	}
	
	@Override
	public void closeInventory(EntityPlayer player) {
	}
	
	@Override
	public boolean hasCustomName() {
		return false;
	}
	
	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}
	
	@Override
	public ItemStack removeStackFromSlot(int index) {
		ItemStack returnStack = getStackInSlot(index);
		setInventorySlotContents(index, null);
		return returnStack;
	}
	
	@Override
	public void markDirty() {
	}
	
	@Override
	public int getField(int id) {
		return 0;
	}
	
	@Override
	public void setField(int id, int value) {
	}
	
	@Override
	public int getFieldCount() {
		return 0;
	}
	
	@Override
	public void clear() {
	}
}
