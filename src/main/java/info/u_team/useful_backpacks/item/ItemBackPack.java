package info.u_team.useful_backpacks.item;

import info.u_team.u_team_core.item.UItem;
import info.u_team.useful_backpacks.container.InteractionObjectBackPack;
import info.u_team.useful_backpacks.enums.EnumBackPacks;
import info.u_team.useful_backpacks.init.UsefulBackPacksItemGroups;
import net.minecraft.entity.player.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class ItemBackPack extends UItem {
	
	private final EnumBackPacks type;
	
	public ItemBackPack(EnumBackPacks type) {
		super("backpack_" + type.getName(), UsefulBackPacksItemGroups.group, new Properties().rarity(type.getRarity()));
		this.type = type;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if (!world.isRemote && player instanceof EntityPlayerMP) {
			EntityPlayerMP playermp = (EntityPlayerMP) player;
			NetworkHooks.openGui(playermp, new InteractionObjectBackPack(stack, type), null);
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
	}
	
	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return !ItemStack.areItemsEqual(oldStack, newStack);
	}
	
	// Color
	
	public boolean hasColor(ItemStack stack) {
		NBTTagCompound nbttagcompound = stack.getChildTag("display");
		return nbttagcompound != null && nbttagcompound.contains("color", 99);
	}
	
	public int getColor(ItemStack stack) {
		NBTTagCompound nbttagcompound = stack.getChildTag("display");
		return nbttagcompound != null && nbttagcompound.contains("color", 99) ? nbttagcompound.getInt("color") : 10511680;
	}
	
	public void removeColor(ItemStack stack) {
		NBTTagCompound nbttagcompound = stack.getChildTag("display");
		if (nbttagcompound != null && nbttagcompound.hasKey("color")) {
			nbttagcompound.removeTag("color");
		}
	}
	
	public void setColor(ItemStack stack, int color) {
		stack.getOrCreateChildTag("display").setInt("color", color);
	}
	
	public EnumBackPacks getType() {
		return type;
	}
	
}
