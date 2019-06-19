package info.u_team.useful_backpacks.item;

import java.util.List;

import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;

public interface IDyeableItem {
	
	default boolean hasColor(ItemStack stack) {
		CompoundNBT compound = stack.getChildTag("display");
		return compound != null && compound.contains("color", 99);
	}
	
	default int getColor(ItemStack stack) {
		CompoundNBT compound = stack.getChildTag("display");
		return compound != null && compound.contains("color", 99) ? compound.getInt("color") : getDefaultColor();
	}
	
	default void removeColor(ItemStack stack) {
		CompoundNBT compound = stack.getChildTag("display");
		if (compound != null && compound.contains("color")) {
			compound.remove("color");
		}
	}
	
	default void setColor(ItemStack stack, int color) {
		stack.getOrCreateChildTag("display").putInt("color", color);
	}
	
	default int getDefaultColor() {
		return 10511680;
	}
	
	public static ItemStack colorStack(ItemStack stack, List<DyeItem> dyeItemList) {
		ItemStack dyeableItem = ItemStack.EMPTY;
		
		int[] aint = new int[3];
		int i = 0;
		int j = 0;
		IDyeableItem idyeableItem = null;
		Item item = stack.getItem();
		if (item instanceof IDyeableItem) {
			idyeableItem = (IDyeableItem) item;
			dyeableItem = stack.copy();
			dyeableItem.setCount(1);
			if (idyeableItem.hasColor(stack)) {
				int k = idyeableItem.getColor(dyeableItem);
				float f = (float) (k >> 16 & 255) / 255.0F;
				float f1 = (float) (k >> 8 & 255) / 255.0F;
				float f2 = (float) (k & 255) / 255.0F;
				i = (int) ((float) i + Math.max(f, Math.max(f1, f2)) * 255.0F);
				aint[0] = (int) ((float) aint[0] + f * 255.0F);
				aint[1] = (int) ((float) aint[1] + f1 * 255.0F);
				aint[2] = (int) ((float) aint[2] + f2 * 255.0F);
				++j;
			}
			
			for (DyeItem dyeitem : dyeItemList) {
				float[] afloat = dyeitem.getDyeColor().getColorComponentValues();
				int i2 = (int) (afloat[0] * 255.0F);
				int l = (int) (afloat[1] * 255.0F);
				int i1 = (int) (afloat[2] * 255.0F);
				i += Math.max(i2, Math.max(l, i1));
				aint[0] += i2;
				aint[1] += l;
				aint[2] += i1;
				++j;
			}
		}
		
		if (idyeableItem == null) {
			return ItemStack.EMPTY;
		} else {
			int j1 = aint[0] / j;
			int k1 = aint[1] / j;
			int l1 = aint[2] / j;
			float f3 = (float) i / (float) j;
			float f4 = (float) Math.max(j1, Math.max(k1, l1));
			j1 = (int) ((float) j1 * f3 / f4);
			k1 = (int) ((float) k1 * f3 / f4);
			l1 = (int) ((float) l1 * f3 / f4);
			int j2 = (j1 << 8) + k1;
			j2 = (j2 << 8) + l1;
			idyeableItem.setColor(dyeableItem, j2);
			return dyeableItem;
		}
	}
}
