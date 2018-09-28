package info.u_team.useful_backpacks.handler;

import info.u_team.useful_backpacks.container.ContainerBackPack;
import info.u_team.useful_backpacks.enums.EnumBackPacks;
import info.u_team.useful_backpacks.gui.GuiBackPack;
import info.u_team.useful_backpacks.inventory.InventoryBackPack;
import info.u_team.useful_backpacks.item.ItemBackPack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class UsefulBackPacksGuiHandler implements IGuiHandler {
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == 0) {
			ItemStack stack = player.inventory.getCurrentItem();
			if (stack != null && stack.getItem() instanceof ItemBackPack) {
				EnumBackPacks type = EnumBackPacks.byMetadata(stack.getMetadata());
				InventoryBackPack inventory = new InventoryBackPack(stack, player, type.getCount());
				return new ContainerBackPack(inventory, player.inventory, type);
			}
		}
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == 0) {
			ItemStack stack = player.inventory.getCurrentItem();
			if (stack != null && stack.getItem() instanceof ItemBackPack) {
				EnumBackPacks type = EnumBackPacks.byMetadata(stack.getMetadata());
				InventoryBackPack inventory = new InventoryBackPack(stack, player, type.getCount());
				return new GuiBackPack(inventory, player.inventory, type);
			}
		}
		return null;
	}
	
}
