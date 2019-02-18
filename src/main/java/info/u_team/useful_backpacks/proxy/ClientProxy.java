package info.u_team.useful_backpacks.proxy;

import java.lang.reflect.Field;

import info.u_team.useful_backpacks.UsefulBackPacksMod;
import info.u_team.useful_backpacks.gui.GuiBackPack;
import info.u_team.useful_backpacks.init.UsefulBackPacksItems;
import info.u_team.useful_backpacks.inventory.InventoryBackPack;
import info.u_team.useful_backpacks.item.ItemBackPack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.color.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
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
					System.out.println("YES");
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
		System.out.println("-.--------------------------------------------------------------------------");
		
		System.out.println(Item.getIdFromItem(UsefulBackPacksItems.small));
		
		ItemColors colors = Minecraft.getInstance().getItemColors();
		
		try {
			
			Class<?> clazz = ItemColors.class;
			Field field = clazz.getDeclaredField("colors");
			field.setAccessible(true);
			ObjectIntIdentityMap<IItemColor> list = (ObjectIntIdentityMap<IItemColor>) field.get(colors);
			
			System.out.println(list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		colors.register(new IItemColor() {
			
			@Override
			public int getColor(ItemStack itemstack, int tintIndex) {
				if (itemstack.getItem() instanceof ItemBackPack) {
					return ((ItemBackPack) itemstack.getItem()).getColor(itemstack);
				}
				return 0;
			}
		}, UsefulBackPacksItems.small, UsefulBackPacksItems.medium, UsefulBackPacksItems.large);
	}
}
