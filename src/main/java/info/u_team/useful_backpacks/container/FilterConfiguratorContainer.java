package info.u_team.useful_backpacks.container;

import info.u_team.u_team_core.container.UContainer;
import info.u_team.useful_backpacks.init.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.IWorldPosCallable;

public class FilterConfiguratorContainer extends UContainer {
	
	private final IWorldPosCallable worldPos;
	
	// Client
	public FilterConfiguratorContainer(int id, PlayerInventory playerInventory) {
		this(id, playerInventory, IWorldPosCallable.DUMMY);
	}
	
	// Server
	public FilterConfiguratorContainer(int id, PlayerInventory playerInventory, IWorldPosCallable worldPos) {
		super(UsefulBackpacksContainerTypes.FILTER_CONFIGURATOR.get(), id);
		this.worldPos = worldPos;
	}
	
	@Override
	public boolean canInteractWith(PlayerEntity player) {
		return isWithinUsableDistance(worldPos, player, UsefulBackpacksBlocks.FILTER_CONFIGURATOR.get());
	}
}
