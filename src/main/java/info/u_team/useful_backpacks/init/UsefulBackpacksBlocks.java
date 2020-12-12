package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.util.registry.*;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.block.BackpackFilterConfiguratorBlock;
import net.minecraft.item.BlockItem;
import net.minecraftforge.eventbus.api.IEventBus;

public class UsefulBackpacksBlocks {
	
	public static final BlockDeferredRegister BLOCKS = BlockDeferredRegister.create(UsefulBackpacksMod.MODID);
	
	public static BlockRegistryObject<BackpackFilterConfiguratorBlock, BlockItem> BACKPACK_FILTER_CONFIGURATOR = BLOCKS.register("backpack_filter_configurator", BackpackFilterConfiguratorBlock::new);
	
	public static void registerMod(IEventBus bus) {
		BLOCKS.register(bus);
	}
	
}
