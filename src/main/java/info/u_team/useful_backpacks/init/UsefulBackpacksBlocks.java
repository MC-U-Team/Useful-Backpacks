package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.util.registry.BlockDeferredRegister;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import net.minecraftforge.eventbus.api.IEventBus;

public class UsefulBackpacksBlocks {
	
	public static final BlockDeferredRegister BLOCKS = BlockDeferredRegister.create(UsefulBackpacksMod.MODID);
	
	public static void registerMod(IEventBus bus) {
		BLOCKS.register(bus);
	}
	
}
