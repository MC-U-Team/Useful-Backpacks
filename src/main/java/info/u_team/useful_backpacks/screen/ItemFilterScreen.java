package info.u_team.useful_backpacks.screen;

import info.u_team.u_team_core.gui.elements.CheckboxButton;
import info.u_team.u_team_core.screen.UContainerMenuScreen;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.menu.ItemFilterMenu;
import io.netty.buffer.Unpooled;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ItemFilterScreen extends UContainerMenuScreen<ItemFilterMenu> {
	
	private static final ResourceLocation BACKGROUND = new ResourceLocation(UsefulBackpacksMod.MODID, "textures/gui/item_filter.png");
	
	private final Component strictTextComponent;
	private final Component strictTooltipTextComponent;
	
	public ItemFilterScreen(ItemFilterMenu menu, Inventory playerInventory, Component title) {
		super(menu, playerInventory, title, BACKGROUND, 176, 130);
		
		final String langKey = "container.usefulbackpacks.item_filter.";
		
		strictTextComponent = Component.translatable(langKey + "strict");
		strictTooltipTextComponent = Component.translatable(langKey + "strict.tooltip");
	}
	
	@Override
	protected void init() {
		super.init();
		
		final CheckboxButton isStrictCheckbox = addRenderableWidget(new CheckboxButton(leftPos + imageWidth - (17 + 16), topPos + 17, 16, 16, strictTextComponent, menu.isStrict(), true));
		isStrictCheckbox.setTextColor(DEFAULT_TEXT_COLOR);
		isStrictCheckbox.setLeftSideText(true);
		isStrictCheckbox.setPressable(() -> {
			menu.getStrictMessage().triggerMessage(() -> new FriendlyByteBuf(Unpooled.copyBoolean(isStrictCheckbox.isChecked())));
			menu.setStrict(isStrictCheckbox.isChecked());
		});
		isStrictCheckbox.setTooltip(Tooltip.create(strictTooltipTextComponent));
	}
}
