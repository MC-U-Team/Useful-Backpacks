package info.u_team.useful_backpacks.item;

import java.util.List;

import info.u_team.u_team_core.api.dye.DyeableItem;
import info.u_team.u_team_core.item.UItem;
import info.u_team.u_team_core.util.MenuUtil;
import info.u_team.useful_backpacks.inventory.BackpackInventory;
import info.u_team.useful_backpacks.menu.BackpackMenu;
import info.u_team.useful_backpacks.type.BackpackType;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class BackpackItem extends UItem implements AutoPickupBackpack, DyeableItem {
	
	private final BackpackType backpack;
	
	public BackpackItem(BackpackType backpack) {
		super(new Properties().stacksTo(1).rarity(backpack.getRarity()));
		this.backpack = backpack;
		addColoredItem(this);
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		final ItemStack stack = player.getItemInHand(hand);
		if (!level.isClientSide && player instanceof ServerPlayer) {
			open((ServerPlayer) player, stack, hand == InteractionHand.MAIN_HAND ? player.getInventory().selected : -1);
		}
		return InteractionResultHolder.success(stack);
	}
	
	@Override
	public void open(ServerPlayer player, ItemStack backpackStack, int selectedSlot) {
		MenuUtil.openMenu(player, new SimpleMenuProvider((id, playerInventory, unused) -> {
			return new BackpackMenu(id, playerInventory, getInventory(player, backpackStack), backpack, selectedSlot);
		}, backpackStack.getHoverName()), buffer -> {
			buffer.writeEnum(backpack);
			buffer.writeVarInt(selectedSlot);
		}, false);
	}
	
	@Override
	public SimpleContainer getInventory(ServerPlayer player, ItemStack backpackStack) {
		return new BackpackInventory(backpackStack, backpack.getInventorySize());
	}
	
	@Override
	public void saveInventory(SimpleContainer inventory, ItemStack backpackStack) {
		if (inventory instanceof BackpackInventory backpackInventory) {
			backpackInventory.writeItemStack();
		}
	}
	
	@Override
	public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
		addTooltip(stack, level, tooltip, flag);
	}
	
	public BackpackType getBackpack() {
		return backpack;
	}
	
	// Default backpack color if not present
	
	@Override
	public int getDefaultColor() {
		return 0x816040;
	}
}
