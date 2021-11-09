package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.menutype.UMenuType;
import info.u_team.u_team_core.util.registry.CommonDeferredRegister;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.container.BackpackContainer;
import info.u_team.useful_backpacks.container.EnderChestBackpackContainer;
import info.u_team.useful_backpacks.container.FilterConfiguratorContainer;
import info.u_team.useful_backpacks.container.ItemFilterContainer;
import info.u_team.useful_backpacks.container.TagFilterContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class UsefulBackpacksMenuTypes {
	
	public static final CommonDeferredRegister<MenuType<?>> MENU_TYPES = CommonDeferredRegister.create(ForgeRegistries.CONTAINERS, UsefulBackpacksMod.MODID);
	
	public static final RegistryObject<MenuType<BackpackContainer>> BACKPACK = MENU_TYPES.register("backpack", () -> new UMenuType<>(BackpackContainer::createClientContainer));
	public static final RegistryObject<MenuType<EnderChestBackpackContainer>> ENDERCHEST_BACKPACK = MENU_TYPES.register("backpack_enderchest", () -> new UMenuType<>(EnderChestBackpackContainer::createEnderChestContainer));
	
	public static final RegistryObject<MenuType<FilterConfiguratorContainer>> FILTER_CONFIGURATOR = MENU_TYPES.register("filter_configurator", () -> new UMenuType<>((id, playerInventory) -> new FilterConfiguratorContainer(id, playerInventory)));
	
	public static final RegistryObject<MenuType<ItemFilterContainer>> ITEM_FILTER = MENU_TYPES.register("item_filter", () -> new UMenuType<>(ItemFilterContainer::new));
	public static final RegistryObject<MenuType<TagFilterContainer>> TAG_FILTER = MENU_TYPES.register("tag_filter", () -> new UMenuType<>(TagFilterContainer::new));
	
	public static void registerMod(IEventBus bus) {
		MENU_TYPES.register(bus);
	}
}
