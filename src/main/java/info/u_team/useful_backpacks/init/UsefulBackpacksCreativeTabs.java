package info.u_team.useful_backpacks.init;

import java.util.List;

import info.u_team.u_team_core.api.dye.DyeableItem;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.item.BackpackItem;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class UsefulBackpacksCreativeTabs {
	
	public static CreativeModeTab TAB;
	
	private static void register(CreativeModeTabEvent.Register event) {
		TAB = event.registerCreativeModeTab(new ResourceLocation(UsefulBackpacksMod.MODID, "tab"), builder -> {
			builder.icon(() -> new ItemStack(UsefulBackpacksItems.SMALL_BACKPACK.get()));
			builder.title(Component.translatable("creativetabs.usefulbackpacks.tab"));
			builder.displayItems((enabledFeatures, output, displayOperatorCreativeTab) -> {
				UsefulBackpacksBlocks.BLOCKS.itemIterable().forEach(item -> {
					output.accept(item);
				});
				UsefulBackpacksItems.ITEMS.forEach(registryObject -> {
					final Item item = registryObject.get();
					output.accept(item);
					if (item instanceof BackpackItem) {
						for (final DyeColor color : DyeColor.values()) {
							output.accept(DyeableItem.colorStack(new ItemStack(item), List.of(color)));
						}
					}
				});
			});
		});
	}
	
	public static void registerMod(IEventBus bus) {
		bus.addListener(UsefulBackpacksCreativeTabs::register);
	}
	
}
