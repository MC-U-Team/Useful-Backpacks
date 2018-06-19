package info.u_team.usefulbackpacks.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;

public class InventoryBackPack extends InventoryBackPackBase {
	
	public InventoryBackPack(ItemStack itemStack, EntityPlayer player, int backpacksize) {
		size = backpacksize;
		inventory = new ItemStack[size];
		NBTTagCompound tag = itemStack.getTagCompound();
		if (tag != null) {
			readFromNBT(tag);
		}
	}
	
	@Override
	public String getName() {
		return "backpack";
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		NBTTagList list = tag.getTagList("backpack", 10);
		for (int i = 0; i < list.tagCount() && i < inventory.length; i++) {
			NBTTagCompound currenttag = (NBTTagCompound) list.get(i);
			int slot = currenttag.getInteger("slot");
			try {
				inventory[slot] = ItemStack.loadItemStackFromNBT(currenttag);
			} catch (NullPointerException ex) {
				inventory[slot] = null;
			}
		}
		
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		NBTTagList list = new NBTTagList();
		for (int i = 0; i < this.inventory.length; i++) {
			if (inventory[i] != null && inventory[i].stackSize > 0) {
				NBTTagCompound currenttag = new NBTTagCompound();
				list.appendTag(currenttag);
				currenttag.setInteger("slot", i);
				inventory[i].writeToNBT(currenttag);
			}
		}
		if (!list.hasNoTags()) {
			tag.setTag("backpack", list);
		} else {
			tag.removeTag("backpack");
		}
	}
	
}