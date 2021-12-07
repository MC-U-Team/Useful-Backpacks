package info.u_team.useful_backpacks.inventory;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;

public class FilterInventory extends SimpleContainer {
	
	private final ItemStack stack;
	
	public FilterInventory(ItemStack stack) {
		super(9);
		this.stack = stack;
		readItemStack();
	}
	
	public ItemStack getStack() {
		return stack;
	}
	
	public void readItemStack() {
		if (stack.getTag() != null && stack.getTag().contains("filter")) {
			readNBT(stack.getTagElement("filter"));
		}
	}
	
	public void writeItemStack() {
		if (isEmpty()) {
			stack.removeTagKey("filter");
		} else {
			writeNBT(stack.getOrCreateTagElement("filter"));
		}
	}
	
	private void readNBT(CompoundTag compound) {
		final NonNullList<ItemStack> list = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(compound, list);
		for (var index = 0; index < list.size(); index++) {
			setItem(index, list.get(index));
		}
	}
	
	private void writeNBT(CompoundTag compound) {
		final NonNullList<ItemStack> list = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
		for (var index = 0; index < list.size(); index++) {
			list.set(index, getItem(index));
		}
		ContainerHelper.saveAllItems(compound, list, false);
	}
}