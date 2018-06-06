package info.u_team.usefulbackpacks.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;

public class InventoryBackPack extends InventoryBasic {
	
	public InventoryBackPack(ItemStack itemstack, EntityPlayer player, int backpacksize) {
		super("backpack", false, backpacksize);
		if (itemstack.hasTagCompound()) {
			readNBT(itemstack.getTagCompound());
		}
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