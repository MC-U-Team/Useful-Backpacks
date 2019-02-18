package info.u_team.useful_backpacks.container;

import info.u_team.useful_backpacks.enums.EnumBackPacks;
import info.u_team.useful_backpacks.inventory.InventoryBackPack;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IInteractionObject;

public class InteractionObjectBackPack implements IInteractionObject {
	
	private final ItemStack stack;
	private final EnumBackPacks type;
	
	public InteractionObjectBackPack(ItemStack stack, EnumBackPacks type) {
		this.stack = stack;
		this.type = type;
	}
	
	@Override
	public ITextComponent getName() {
		return null;
	}
	
	@Override
	public ITextComponent getCustomName() {
		return null;
	}
	
	@Override
	public boolean hasCustomName() {
		return false;
	}
	
	@Override
	public String getGuiID() {
		return "usefulbackpacks:backpack";
	}
	
	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer player) {
		return new ContainerBackPack(playerInventory, new InventoryBackPack(stack, type));
	}
	
}
