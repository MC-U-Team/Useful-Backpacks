package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.util.registry.BaseRegistryUtil;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.item.BackpackItem;
import info.u_team.useful_backpacks.type.Backpack;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = UsefulBackpacksMod.MODID, bus = Bus.MOD)
public class UsefulBackpacksItems {
	
	public static final BackpackItem SMALL_BACKPACK = new BackpackItem(Backpack.SMALL);
	public static final BackpackItem MEDIUM_BACKPACK = new BackpackItem(Backpack.MEDIUM);
	public static final BackpackItem LARGE_BACKPACK = new BackpackItem(Backpack.LARGE);
	
	@SubscribeEvent
	public static void register(Register<Item> event) {
		BaseRegistryUtil.getAllRegistryEntriesAndApplyNames(UsefulBackpacksMod.MODID, Item.class).forEach(event.getRegistry()::register);
	}
	
}
