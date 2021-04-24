package info.u_team.useful_backpacks.inventory;

import java.util.Set;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class DelegateInventory implements IInventory {
	
	private final IInventory fallback;
	private IInventory inventory;
	
	public DelegateInventory(IInventory fallback) {
		this.fallback = fallback;
		inventory = fallback;
	}
	
	public void setInventory(IInventory inventory) {
		this.inventory = inventory;
		if (this.inventory == null) {
			this.inventory = fallback;
		}
	}
	
	@Override
	public void clear() {
		inventory.clear();
	}
	
	@Override
	public int getSizeInventory() {
		return inventory.getSizeInventory();
	}
	
	@Override
	public boolean isEmpty() {
		return inventory.isEmpty();
	}
	
	@Override
	public ItemStack getStackInSlot(int index) {
		return inventory.getStackInSlot(index);
	}
	
	@Override
	public ItemStack decrStackSize(int index, int count) {
		return inventory.decrStackSize(index, count);
	}
	
	@Override
	public ItemStack removeStackFromSlot(int index) {
		return inventory.removeStackFromSlot(index);
	}
	
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		inventory.setInventorySlotContents(index, stack);
	}
	
	@Override
	public int getInventoryStackLimit() {
		return inventory.getInventoryStackLimit();
	}
	
	@Override
	public void markDirty() {
		inventory.markDirty();
	}
	
	@Override
	public boolean isUsableByPlayer(PlayerEntity player) {
		return inventory.isUsableByPlayer(player);
	}
	
	@Override
	public void openInventory(PlayerEntity player) {
		inventory.openInventory(player);
	}
	
	@Override
	public void closeInventory(PlayerEntity player) {
		inventory.closeInventory(player);
	}
	
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return inventory.isItemValidForSlot(index, stack);
	}
	
	@Override
	public int count(Item itemIn) {
		return inventory.count(itemIn);
	}
	
	@Override
	public boolean hasAny(Set<Item> set) {
		return inventory.hasAny(set);
	}
}
