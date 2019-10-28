package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.containertype.UContainerType;
import info.u_team.u_team_core.util.registry.BaseRegistryUtil;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.container.*;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = UsefulBackpacksMod.MODID, bus = Bus.MOD)
public class UsefulBackpacksContainerTypes {
	
	public static final ContainerType<BackpackContainer> BACKPACK = new UContainerType<>("backpack", BackpackContainer::createClientContainer);
	
	public static final ContainerType<EnderChestBackpackContainer> ENDERCHEST_BACKPACK = new UContainerType<>("backpack_enderchest", EnderChestBackpackContainer::createEnderChestContainer);
	
	@SubscribeEvent
	public static void register(Register<ContainerType<?>> event) {
		BaseRegistryUtil.getAllGenericRegistryEntriesAndApplyNames(UsefulBackpacksMod.MODID, ContainerType.class).forEach(event.getRegistry()::register);
	}
}
