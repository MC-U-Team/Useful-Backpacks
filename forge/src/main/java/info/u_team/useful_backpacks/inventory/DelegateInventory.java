package info.u_team.useful_backpacks.inventory;

import java.util.Set;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class DelegateInventory implements Container {
	
	private final Container fallback;
	private Container inventory;
	
	public DelegateInventory(Container fallback) {
		this.fallback = fallback;
		inventory = fallback;
	}
	
	public void setInventory(Container inventory) {
		this.inventory = inventory;
		if (this.inventory == null) {
			this.inventory = fallback;
		}
	}
	
	@Override
	public void clearContent() {
		inventory.clearContent();
	}
	
	@Override
	public int getContainerSize() {
		return inventory.getContainerSize();
	}
	
	@Override
	public boolean isEmpty() {
		return inventory.isEmpty();
	}
	
	@Override
	public ItemStack getItem(int index) {
		return inventory.getItem(index);
	}
	
	@Override
	public ItemStack removeItem(int index, int count) {
		return inventory.removeItem(index, count);
	}
	
	@Override
	public ItemStack removeItemNoUpdate(int index) {
		return inventory.removeItemNoUpdate(index);
	}
	
	@Override
	public void setItem(int index, ItemStack stack) {
		inventory.setItem(index, stack);
	}
	
	@Override
	public int getMaxStackSize() {
		return inventory.getMaxStackSize();
	}
	
	@Override
	public void setChanged() {
		inventory.setChanged();
	}
	
	@Override
	public boolean stillValid(Player player) {
		return inventory.stillValid(player);
	}
	
	@Override
	public void startOpen(Player player) {
		inventory.startOpen(player);
	}
	
	@Override
	public void stopOpen(Player player) {
		inventory.stopOpen(player);
	}
	
	@Override
	public boolean canPlaceItem(int index, ItemStack stack) {
		return inventory.canPlaceItem(index, stack);
	}
	
	@Override
	public int countItem(Item itemIn) {
		return inventory.countItem(itemIn);
	}
	
	@Override
	public boolean hasAnyOf(Set<Item> set) {
		return inventory.hasAnyOf(set);
	}
}
