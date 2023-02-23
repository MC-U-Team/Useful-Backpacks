package info.u_team.useful_backpacks.menu;

import info.u_team.useful_backpacks.init.UsefulBackpacksMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class EnderChestBackpackMenu extends ChestMenu {
	
	private final int selectedSlot;
	
	// Client
	public static EnderChestBackpackMenu createEnderChestContainer(int id, Inventory playerInventory, FriendlyByteBuf buffer) {
		final int selectedSlot = buffer.readVarInt();
		return createEnderChestContainer(id, playerInventory, new SimpleContainer(9 * 3), selectedSlot);
	}
	
	// Server
	public static EnderChestBackpackMenu createEnderChestContainer(int id, Inventory playerInventory, Container inventory, int selectedSlot) {
		return new EnderChestBackpackMenu(UsefulBackpacksMenuTypes.ENDERCHEST_BACKPACK.get(), id, playerInventory, inventory, 3, selectedSlot);
	}
	
	public EnderChestBackpackMenu(MenuType<?> type, int id, Inventory playerInventory, Container inventory, int rows, int selectedSlot) {
		super(type, id, playerInventory, inventory, rows);
		this.selectedSlot = selectedSlot;
	}
	
	@Override
	public void clicked(int slotId, int dragType, ClickType clickType, Player player) {
		Slot tmpSlot;
		if (slotId >= 0 && slotId < slots.size()) {
			tmpSlot = slots.get(slotId);
		} else {
			tmpSlot = null;
		}
		if (tmpSlot != null) {
			if (tmpSlot.container == player.getInventory() && tmpSlot.getSlotIndex() == selectedSlot) {
				return;
			}
		}
		if (clickType == ClickType.SWAP) {
			final ItemStack stack = player.getInventory().getItem(dragType);
			final ItemStack currentItem = Inventory.isHotbarSlot(selectedSlot) ? player.getInventory().items.get(selectedSlot) : selectedSlot == -1 ? player.getInventory().offhand.get(0) : ItemStack.EMPTY;
			
			if (!currentItem.isEmpty() && stack == currentItem) {
				return;
			}
		}
		super.clicked(slotId, dragType, clickType, player);
	}
	
}
