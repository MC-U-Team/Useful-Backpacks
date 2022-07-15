package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.menutype.UMenuType;
import info.u_team.u_team_core.util.registry.CommonDeferredRegister;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.menu.BackpackMenu;
import info.u_team.useful_backpacks.menu.EnderChestBackpackMenu;
import info.u_team.useful_backpacks.menu.FilterConfiguratorMenu;
import info.u_team.useful_backpacks.menu.ItemFilterMenu;
import info.u_team.useful_backpacks.menu.TagFilterMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class UsefulBackpacksMenuTypes {
	
	public static final CommonDeferredRegister<MenuType<?>> MENU_TYPES = CommonDeferredRegister.create(ForgeRegistries.MENU_TYPES, UsefulBackpacksMod.MODID);
	
	public static final RegistryObject<MenuType<BackpackMenu>> BACKPACK = MENU_TYPES.register("backpack", () -> new UMenuType<>(BackpackMenu::createClientContainer));
	public static final RegistryObject<MenuType<EnderChestBackpackMenu>> ENDERCHEST_BACKPACK = MENU_TYPES.register("backpack_enderchest", () -> new UMenuType<>(EnderChestBackpackMenu::createEnderChestContainer));
	
	public static final RegistryObject<MenuType<FilterConfiguratorMenu>> FILTER_CONFIGURATOR = MENU_TYPES.register("filter_configurator", () -> new UMenuType<>((id, playerInventory) -> new FilterConfiguratorMenu(id, playerInventory)));
	
	public static final RegistryObject<MenuType<ItemFilterMenu>> ITEM_FILTER = MENU_TYPES.register("item_filter", () -> new UMenuType<>(ItemFilterMenu::new));
	public static final RegistryObject<MenuType<TagFilterMenu>> TAG_FILTER = MENU_TYPES.register("tag_filter", () -> new UMenuType<>(TagFilterMenu::new));
	
	public static void registerMod(IEventBus bus) {
		MENU_TYPES.register(bus);
	}
}
