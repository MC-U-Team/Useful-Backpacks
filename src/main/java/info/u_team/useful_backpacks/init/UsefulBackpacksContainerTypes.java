package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.containertype.UContainerType;
import info.u_team.u_team_core.util.registry.CommonDeferredRegister;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.container.*;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class UsefulBackpacksContainerTypes {
	
	public static final CommonDeferredRegister<ContainerType<?>> CONTAINER_TYPES = CommonDeferredRegister.create(ForgeRegistries.CONTAINERS, UsefulBackpacksMod.MODID);
	
	public static final RegistryObject<ContainerType<BackpackContainer>> BACKPACK = CONTAINER_TYPES.register("backpack", () -> new UContainerType<>(BackpackContainer::createClientContainer));
	public static final RegistryObject<ContainerType<EnderChestBackpackContainer>> ENDERCHEST_BACKPACK = CONTAINER_TYPES.register("backpack_enderchest", () -> new UContainerType<>(EnderChestBackpackContainer::createEnderChestContainer));
	
	public static final RegistryObject<ContainerType<FilterConfiguratorContainer>> FILTER_CONFIGURATOR = CONTAINER_TYPES.register("filter_configurator", () -> new UContainerType<>(FilterConfiguratorContainer::new));
	
	public static void registerMod(IEventBus bus) {
		CONTAINER_TYPES.register(bus);
	}
}
