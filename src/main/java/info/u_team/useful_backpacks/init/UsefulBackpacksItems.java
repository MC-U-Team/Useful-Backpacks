package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.util.registry.CommonDeferredRegister;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.item.*;
import info.u_team.useful_backpacks.type.Backpack;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class UsefulBackpacksItems {
	
	public static final CommonDeferredRegister<Item> ITEMS = CommonDeferredRegister.create(ForgeRegistries.ITEMS, UsefulBackpacksMod.MODID);
	
	public static final RegistryObject<BackpackItem> SMALL_BACKPACK = ITEMS.register("backpack_" + Backpack.SMALL.getName(), () -> new BackpackItem(Backpack.SMALL));
	public static final RegistryObject<BackpackItem> MEDIUM_BACKPACK = ITEMS.register("backpack_" + Backpack.MEDIUM.getName(), () -> new BackpackItem(Backpack.MEDIUM));
	public static final RegistryObject<BackpackItem> LARGE_BACKPACK = ITEMS.register("backpack_" + Backpack.LARGE.getName(), () -> new BackpackItem(Backpack.LARGE));
	
	public static final RegistryObject<EnderChestBackpackItem> ENDERCHEST_BACKPACK = ITEMS.register("backpack_enderchest", EnderChestBackpackItem::new);
	
	public static void register(IEventBus bus) {
		ITEMS.register(bus);
	}
	
}
