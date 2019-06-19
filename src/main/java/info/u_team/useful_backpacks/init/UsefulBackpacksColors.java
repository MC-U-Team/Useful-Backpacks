package info.u_team.useful_backpacks.init;

import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.item.IDyeableItem;
import net.minecraft.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = UsefulBackpacksMod.modid, value = Dist.CLIENT)
public class UsefulBackpacksColors {
	
	@SubscribeEvent
	public static void register(ColorHandlerEvent.Item event) {
		event.getItemColors().register((itemstack, index) -> {
			final Item item = itemstack.getItem();
			if (item instanceof IDyeableItem) {
				return ((IDyeableItem) item).getColor(itemstack);
			}
			return 0;
		}, UsefulBackpacksItems.small, UsefulBackpacksItems.medium, UsefulBackpacksItems.large);
	}
}
