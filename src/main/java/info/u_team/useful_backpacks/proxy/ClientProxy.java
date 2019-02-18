package info.u_team.useful_backpacks.proxy;

import info.u_team.useful_backpacks.UsefulBackPacksMod;
import info.u_team.useful_backpacks.gui.GuiBackPack;
import info.u_team.useful_backpacks.init.UsefulBackPacksItems;
import info.u_team.useful_backpacks.inventory.InventoryBackPack;
import info.u_team.useful_backpacks.item.ItemBackPack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.*;
import net.minecraftforge.fml.*;

@OnlyIn(Dist.CLIENT)
public class ClientProxy extends CommonProxy implements IModProxy {
	
	@Override
	public void construct() {
		super.construct();
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
	
	@Override
	public void setup() {
		super.setup();
	}
	
	@Override
	public void complete() {
		super.complete();
		ItemColors colors = Minecraft.getInstance().getItemColors();
		colors.register((itemstack, tintIndex) -> {
			if (itemstack.getItem() instanceof ItemBackPack) {
				return ((ItemBackPack) itemstack.getItem()).getColor(itemstack);
			}
			return 0;
		}, UsefulBackPacksItems.small, UsefulBackPacksItems.medium, UsefulBackPacksItems.large);
	}
}
