package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.util.registry.BlockDeferredRegister;
import info.u_team.u_team_core.util.registry.BlockRegistryObject;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.block.FilterConfiguratorBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraftforge.eventbus.api.IEventBus;

public class UsefulBackpacksBlocks {
	
	public static final BlockDeferredRegister BLOCKS = BlockDeferredRegister.create(UsefulBackpacksMod.MODID);
	
	public static BlockRegistryObject<FilterConfiguratorBlock, BlockItem> FILTER_CONFIGURATOR = BLOCKS.register("filter_configurator", FilterConfiguratorBlock::new);
	
	public static void registerMod(IEventBus bus) {
		BLOCKS.register(bus);
	}
	
}
