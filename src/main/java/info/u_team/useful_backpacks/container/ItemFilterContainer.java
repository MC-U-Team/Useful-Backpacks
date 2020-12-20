package info.u_team.useful_backpacks.container;

import info.u_team.u_team_core.container.UContainer;
import info.u_team.useful_backpacks.init.UsefulBackpacksContainerTypes;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

public class ItemFilterContainer extends UContainer {
	
	public ItemFilterContainer(int id, PlayerInventory playerInventory, PacketBuffer buffer) {
		this(id, playerInventory, null, buffer.readVarInt());
	}
	
	public ItemFilterContainer(int id, PlayerInventory inventory, ItemStack stack, int selectedSlot) {
		super(UsefulBackpacksContainerTypes.ITEM_FILTER.get(), id);
	}
	
}
