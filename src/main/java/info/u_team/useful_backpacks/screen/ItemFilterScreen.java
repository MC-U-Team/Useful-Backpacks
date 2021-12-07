package info.u_team.useful_backpacks.screen;

import info.u_team.u_team_core.gui.elements.CheckboxButton;
import info.u_team.u_team_core.screen.UContainerMenuScreen;
import info.u_team.u_team_core.util.WidgetUtil;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.container.ItemFilterContainer;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ItemFilterScreen extends UContainerMenuScreen<ItemFilterContainer> {
	
	private static final ResourceLocation BACKGROUND = new ResourceLocation(UsefulBackpacksMod.MODID, "textures/gui/item_filter.png");
	
	private final Component strictTextComponent;
	private final Component strictTooltipTextComponent;
	
	public ItemFilterScreen(ItemFilterContainer container, Inventory playerInventory, Component title) {
		super(container, playerInventory, title, BACKGROUND, 176, 130);
		
		final var langKey = "container.usefulbackpacks.item_filter.";
		
		strictTextComponent = new TranslatableComponent(langKey + "strict");
		strictTooltipTextComponent = new TranslatableComponent(langKey + "strict.tooltip");
	}
	
	@Override
	protected void init() {
		super.init();
		
		final var isStrictCheckbox = addRenderableWidget(new CheckboxButton(leftPos + imageWidth - (17 + 16), topPos + 17, 16, 16, strictTextComponent, menu.isStrict(), true));
		isStrictCheckbox.setTextColor(DEFAULT_TEXT_COLOR);
		isStrictCheckbox.setLeftSideText(true);
		isStrictCheckbox.setPressable(() -> {
			menu.getStrictMessage().triggerMessage(() -> new FriendlyByteBuf(Unpooled.copyBoolean(isStrictCheckbox.isChecked())));
			menu.setStrict(isStrictCheckbox.isChecked());
		});
		isStrictCheckbox.setTooltip((button, poseStack, mouseX, mouseY) -> {
			if (WidgetUtil.isHovered(button)) {
				renderTooltip(poseStack, strictTooltipTextComponent, mouseX, mouseY);
			}
		});
	}
}
