package info.u_team.useful_backpacks.proxy;

import info.u_team.useful_backpacks.UsefulBackPacksMod;
import info.u_team.useful_backpacks.gui.GuiBackPack;
import info.u_team.useful_backpacks.inventory.InventoryBackPack;
import info.u_team.useful_backpacks.item.ItemBackPack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.*;
import net.minecraftforge.fml.*;

@OnlyIn(Dist.CLIENT)
public class ClientProxy {
	
	public static void setup() {
		ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.GUIFACTORY, () -> {
			return (openContainer) -> {
				ResourceLocation location = openContainer.getId();
				if (location.toString().equalsIgnoreCase(UsefulBackPacksMod.modid + ":backpack")) {
					EntityPlayerSP player = Minecraft.getInstance().player;
					ItemStack stack = player.getHeldItemMainhand();
					Item item = stack.getItem();
					if (!(item instanceof ItemBackPack)) {
						return null;
					}
					ItemBackPack backpack = (ItemBackPack) item;
					return new GuiBackPack(player.inventory, new InventoryBackPack(stack, backpack.getType()));
				}
				return null;
			};
		});
	}
	
}
