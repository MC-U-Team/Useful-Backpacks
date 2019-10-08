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
				return new ContainerBackPack(new InventoryBackPack(false, stack, type.getCount()), player.inventory, type);
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
				return new GuiBackPack(new InventoryBasic("backpack", false, type.getCount()), player.inventory, type);
			}
		}
		return null;
	}
	
}
