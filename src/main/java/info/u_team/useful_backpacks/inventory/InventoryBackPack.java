package info.u_team.useful_backpacks.inventory;

import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;

public class InventoryBackPack extends InventoryBasic {
	
	private final boolean client;
	private final ItemStack stack;
	
	public InventoryBackPack(boolean client, ItemStack stack, int backpacksize) {
		super("backpack", false, backpacksize);
		this.client = client;
		this.stack = stack;
		readItemStack();
	}
	
	public ItemStack getStack() {
		return stack;
	}
	
	public void readItemStack() {
		if (stack.hasTagCompound()) {
			readNBT(stack.getTagCompound());
		}
	}
	
	public void writeItemStack() {
		if (client) {
			return;
		}
		if (isEmpty()) {
			if (stack.hasTagCompound()) {
				stack.getTagCompound().removeTag("Items");
				if (stack.getTagCompound().isEmpty()) {
					stack.setTagCompound(null);
				}
			}
		} else {
			final NBTTagCompound compound;
			if (stack.hasTagCompound()) {
				compound = stack.getTagCompound();
			} else {
				compound = new NBTTagCompound();
				stack.setTagCompound(compound);
			}
			writeNBT(compound);
		}
	}
	
	public void readNBT(NBTTagCompound compound) {
		final NonNullList<ItemStack> list = NonNullList.<ItemStack> withSize(getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, list);
		for (int i = 0; i < list.size(); i++) {
			setInventorySlotContents(i, list.get(i));
		}
	}
	
	public void writeNBT(NBTTagCompound compound) {
		final NonNullList<ItemStack> list = NonNullList.<ItemStack> withSize(getSizeInventory(), ItemStack.EMPTY);
		for (int i = 0; i < list.size(); i++) {
			list.set(i, getStackInSlot(i));
		}
		ItemStackHelper.saveAllItems(compound, list);
	}
}