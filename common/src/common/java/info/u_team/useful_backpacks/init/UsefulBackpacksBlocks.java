package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.api.registry.BlockRegister;
import info.u_team.u_team_core.api.registry.BlockRegistryEntry;
import info.u_team.useful_backpacks.UsefulBackpacksReference;
import info.u_team.useful_backpacks.block.FilterConfiguratorBlock;
import net.minecraft.world.item.BlockItem;

public class UsefulBackpacksBlocks {
	
	public static final BlockRegister BLOCKS = BlockRegister.create(UsefulBackpacksReference.MODID);
	
	public static BlockRegistryEntry<FilterConfiguratorBlock, BlockItem> FILTER_CONFIGURATOR = BLOCKS.register("filter_configurator", FilterConfiguratorBlock::new);
	
	static void register() {
		BLOCKS.register();
	}
	
}
