package info.u_team.useful_backpacks.inventory;

import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;

public class BackpackInventory extends Inventory {
	
	private final ItemStack stack;
	
	public BackpackInventory(ItemStack stack, int count) {
		super(count);
		this.stack = stack;
		readItemStack();
	}
	
	public ItemStack getStack() {
		return stack;
	}
	
	public void readItemStack() {
		if (stack.getTag() != null) {
			readNBT(stack.getTag());
		}
	}
	
	public void writeItemStack() {
		if (isEmpty()) {
			stack.removeChildTag("Items");
		} else {
			writeNBT(stack.getOrCreateTag());
		}
	}
	
	private void readNBT(CompoundNBT compound) {
		final NonNullList<ItemStack> list = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, list);
		for (int index = 0; index < list.size(); index++) {
			setInventorySlotContents(index, list.get(index));
		}
	}
	
	private void writeNBT(CompoundNBT compound) {
		final NonNullList<ItemStack> list = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
		for (int index = 0; index < list.size(); index++) {
			list.set(index, getStackInSlot(index));
		}
		ItemStackHelper.saveAllItems(compound, list, false);
	}
}