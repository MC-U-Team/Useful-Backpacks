package info.u_team.useful_backpacks.item;

import info.u_team.u_team_core.item.UItem;
import info.u_team.u_team_core.util.CustomResourceLocation;
import info.u_team.useful_backpacks.*;
import info.u_team.useful_backpacks.enums.EnumBackPacks;
import info.u_team.useful_backpacks.init.UsefulBackPacksCreativeTabs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.*;

public class ItemBackPack extends UItem {
	
	public ItemBackPack(String name) {
		super(name, UsefulBackPacksCreativeTabs.tab);
		setMaxStackSize(1);
		hasSubtypes = true;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerModel() {
		for (int i = 0; i < EnumBackPacks.values().length; i++) {
			setModel(this, i, new CustomResourceLocation(getRegistryName(), "_" + EnumBackPacks.byMetadata(i).getName()));
		}
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if (player == null || hand == null) {
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, player.getHeldItem(hand));
		}
		if (!world.isRemote) {
			player.openGui(UsefulBackpacksMod.getInstance(), hand == EnumHand.MAIN_HAND ? 0 : 1, world, 0, 0, 0);
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
	}
	
	@Override
	public int getMetadata(int damage) {
		return damage;
	}
	
	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return !ItemStack.areItemsEqual(oldStack, newStack);
	}
	
	public int getColor(ItemStack stack) {
		NBTTagCompound tag = stack.getTagCompound();
		if (tag != null) {
			NBTTagCompound displaytag = tag.getCompoundTag("display");
			if (displaytag != null && displaytag.hasKey("color", 3)) {
				return displaytag.getInteger("color");
			}
		}
		return 0x816040;
	}
	
	public void setColor(ItemStack stack, int color) {
		NBTTagCompound tag = stack.getTagCompound();
		
		if (tag == null) {
			tag = new NBTTagCompound();
		}
		
		if (!tag.hasKey("display", 10)) {
			tag.setTag("display", new NBTTagCompound());
		}
		NBTTagCompound displaytag = tag.getCompoundTag("display");
		
		displaytag.setInteger("color", color);
		
		stack.setTagCompound(tag);
	}
	
	public void removeColor(ItemStack stack) {
		NBTTagCompound tag = stack.getTagCompound();
		if (tag == null) {
			return;
		}
		
		if (!tag.hasKey("display", 10)) {
			return;
		}
		NBTTagCompound displaytag = tag.getCompoundTag("display");
		displaytag.removeTag("color");
	}
	
	public boolean hasColor(ItemStack stack) {
		NBTTagCompound tag = stack.getTagCompound();
		return tag != null && tag.hasKey("display", 10) ? tag.getCompoundTag("display").hasKey("color", 3) : false;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (!isInCreativeTab(tab)) {
			return;
		}
		for (int i = 0; i < EnumBackPacks.values().length; i++) {
			ItemStack normalstack = new ItemStack(this, 1, i);
			items.add(normalstack);
		}
		for (EnumDyeColor color : EnumDyeColor.values()) {
			for (int i = 0; i < EnumBackPacks.values().length; i++) {
				ItemStack dyedstack = new ItemStack(this, 1, i);
				setColor(dyedstack, color.getColorValue());
				items.add(dyedstack);
			}
		}
	}
	
	@Override
	public String getTranslationKey(ItemStack stack) {
		EnumBackPacks type = EnumBackPacks.byMetadata(stack.getMetadata());
		return UsefulBackPacksConstants.MODID + ":item.backpack." + type.getName();
	}
	
	// Fix #22 (cause items to appear a bit later but the bug is fixed)
	
	@Override
	public NBTTagCompound getNBTShareTag(ItemStack stack) {
		if (!stack.hasTagCompound()) {
			return null;
		}
		final NBTTagCompound compound = stack.getTagCompound().copy();
		compound.removeTag("Items");
		if (compound.isEmpty()) {
			return null;
		}
		return compound;
	}
	
}
