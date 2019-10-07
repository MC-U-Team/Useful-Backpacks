package info.u_team.useful_backpacks.handler;

import info.u_team.useful_backpacks.container.ContainerBackPack;
import info.u_team.useful_backpacks.enums.EnumBackPacks;
import info.u_team.useful_backpacks.gui.GuiBackPack;
import info.u_team.useful_backpacks.inventory.InventoryBackPack;
import info.u_team.useful_backpacks.item.ItemBackPack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class UsefulBackPacksGuiHandler implements IGuiHandler {
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == 0 || ID == 1) {
			final ItemStack stack = ID == 0 ? player.getHeldItemMainhand() : player.getHeldItemOffhand();
			if (stack != null && stack.getItem() instanceof ItemBackPack) {
				EnumBackPacks type = EnumBackPacks.byMetadata(stack.getMetadata());
				InventoryBackPack inventory = new InventoryBackPack(false, stack, type.getCount());
				return new ContainerBackPack(inventory, player.inventory, type);
			}
		}
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == 0 || ID == 1) {
			final ItemStack stack = ID == 0 ? player.getHeldItemMainhand() : player.getHeldItemOffhand();
			if (stack != null && stack.getItem() instanceof ItemBackPack) {
				EnumBackPacks type = EnumBackPacks.byMetadata(stack.getMetadata());
				// Issue #22 introduce this "bug" again because we won't send the item data to the client anymore.
				//
				// !!!! The text below is outdated !!!!
				//
				// We use here the inventory backpack instance because else the items are not
				// there when you open the backpack instantly. This is a bit of a design error
				// of this mod, but i don't have time to rewrite everything. It works but could
				// produce ghost items if you open the backpack very very quickly and remove
				// items from it in the same tick. This is fixed in 1.14.4 versions which is my
				// current goal to make better, cause 1.12 is kinda outdated soon.
				return new GuiBackPack(new InventoryBasic("backpack", false, type.getCount()), player.inventory, type);
			}
		}
		return null;
	}
	
}
