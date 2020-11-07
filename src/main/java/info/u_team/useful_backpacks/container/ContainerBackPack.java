package info.u_team.useful_backpacks.container;

import java.util.*;

import info.u_team.useful_backpacks.enums.EnumBackPacks;
import info.u_team.useful_backpacks.inventory.InventoryBackPack;
import info.u_team.useful_backpacks.item.ItemBackPack;
import invtweaks.api.container.*;
import invtweaks.api.container.ChestContainer.RowSizeCallback;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Optional;

@ChestContainer
public class ContainerBackPack extends Container {
	
	private final IInventory inventory;
	private final EnumBackPacks type;
	private final boolean offhand;
	
	public ContainerBackPack(IInventory inventory, InventoryPlayer inventoryplayer, EnumBackPacks type, boolean offhand) {
		
		this.inventory = inventory;
		this.type = type;
		this.offhand = offhand;
		
		int x_backpackinv = 0;
		int y_backpackinv = 0;
		
		int x_playerinv = 0;
		int y_playerinv = 0;
		
		switch (type) {
		case SMALL:
			x_backpackinv = 44;
			y_backpackinv = 24;
			
			x_playerinv = 8;
			y_playerinv = 82;
			break;
		case MEDIUM:
			x_backpackinv = 8;
			y_backpackinv = 24;
			
			x_playerinv = 8;
			y_playerinv = 136;
			break;
		case LARGE:
			x_backpackinv = 8;
			y_backpackinv = 24;
			
			x_playerinv = 44;
			y_playerinv = 190;
			break;
		}
		
		drawBackPackInventory(inventory, x_backpackinv, y_backpackinv);
		drawPlayerInventory(inventoryplayer, x_playerinv, y_playerinv);
		
	}
	
	public void drawBackPackInventory(IInventory inventory, int x_offset, int y_offset) {
		for (int height = 0; height < type.getSizeY(); height++) {
			for (int width = 0; width < type.getSizeX(); width++) {
				addSlotToContainer(new SlotBackpack(inventory, width + height * type.getSizeX(), width * 18 + x_offset, height * 18 + y_offset));
			}
		}
	}
	
	public void drawPlayerInventory(InventoryPlayer inventory, int x_offset, int y_offset) {
		for (int height = 0; height < 4; height++) {
			for (int width = 0; width < 9; width++) {
				if (height == 3) {
					addSlotToContainer(new Slot(inventory, width, width * 18 + x_offset, height * 18 + 4 + y_offset));
					continue;
				}
				addSlotToContainer(new Slot(inventory, width + height * 9 + 9, width * 18 + x_offset, height * 18 + y_offset));
			}
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		final ItemStack currentItem = this.offhand ? player.getHeldItemOffhand() : player.getHeldItemMainhand();
		if (inventory instanceof InventoryBackPack) {
			final ItemStack stack = ((InventoryBackPack) inventory).getStack();
			return !stack.isEmpty() && stack.getItem() instanceof ItemBackPack && currentItem == stack;
		}
		return currentItem.getItem() instanceof ItemBackPack;
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		if (inventory instanceof InventoryBackPack) {
			((InventoryBackPack) inventory).writeItemStack();
		}
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		final Slot slot = this.inventorySlots.get(index);
		
		if (slot != null && slot.getHasStack()) {
			final ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			
			if (index < type.getCount()) {
				if (!this.mergeItemStack(itemstack1, type.getCount(), this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, type.getCount(), false)) {
				return ItemStack.EMPTY;
			}
			
			if (itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
		}
		return itemstack;
	}
	
	@Override
	public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {
		Slot tmpSlot;
		if (slotId >= 0 && slotId < inventorySlots.size()) {
			tmpSlot = inventorySlots.get(slotId);
		} else {
			tmpSlot = null;
		}
		if (tmpSlot != null) {
			if (tmpSlot.isHere(player.inventory, player.inventory.currentItem)) {
				return tmpSlot.getStack();
			}
		}
		if (clickTypeIn == ClickType.SWAP) {
			final ItemStack stack = player.inventory.getStackInSlot(dragType);
			final ItemStack currentItem = this.offhand ? player.getHeldItemOffhand() : player.getHeldItemMainhand();
			
			if (!currentItem.isEmpty() && stack == currentItem) {
				return ItemStack.EMPTY;
			}
		}
		return super.slotClick(slotId, dragType, clickTypeIn, player);
	}
	
	// Inventory Tweaks stuff
	
	@RowSizeCallback
	@Optional.Method(modid = "inventorytweaks")
	public int getRowSize() {
		return type.getSizeX();
	}
	
	@ContainerSectionCallback
	@Optional.Method(modid = "inventorytweaks")
	public Map<ContainerSection, List<Slot>> getContainerSections() {
		
		final Map<ContainerSection, List<Slot>> slots = new Object2ObjectOpenHashMap<>();
		
		slots.put(ContainerSection.CHEST, inventorySlots.subList(0, type.getCount()));
		
		slots.put(ContainerSection.INVENTORY, inventorySlots.subList(type.getCount(), inventorySlots.size()));
		slots.put(ContainerSection.INVENTORY_NOT_HOTBAR, inventorySlots.subList(type.getCount(), inventorySlots.size() - 9));
		slots.put(ContainerSection.INVENTORY_HOTBAR, inventorySlots.subList(inventorySlots.size() - 8, inventorySlots.size()));
		
		return slots;
	}
}
