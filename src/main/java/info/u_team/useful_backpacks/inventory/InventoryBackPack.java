package info.u_team.useful_backpacks.inventory;

import info.u_team.useful_backpacks.enums.EnumBackPacks;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextComponentString;

public class InventoryBackPack extends InventoryBasic {
	
	private ItemStack stack;
	private EnumBackPacks type;
	
	public InventoryBackPack(ItemStack stack, EnumBackPacks type) {
		super(new TextComponentString("backpack"), type.getCount());
		this.stack = stack;
		this.type = type;
		readItemStack(stack);
	}
	
	public ItemStack getStack() {
		return stack;
	}
	
	public EnumBackPacks getType() {
		return type;
	}
	
	public void readItemStack(ItemStack stack) {
		if (stack.getTag() != null) {
			readNBT(stack.getTag());
		}
	}
	
	public void writeItemStack(ItemStack stack) {
		writeNBT(stack.getOrCreateTag());
	}
	
	public void readNBT(NBTTagCompound compound) {
		NonNullList<ItemStack> list = NonNullList.<ItemStack> withSize(getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, list);
		for (int i = 0; i < list.size(); i++) {
			setInventorySlotContents(i, list.get(i));
		}
	}
	
	public void writeNBT(NBTTagCompound compound) {
		NonNullList<ItemStack> list = NonNullList.<ItemStack> withSize(getSizeInventory(), ItemStack.EMPTY);
		for (int i = 0; i < list.size(); i++) {
			list.set(i, getStackInSlot(i));
		}
		ItemStackHelper.saveAllItems(compound, list);
	}
}