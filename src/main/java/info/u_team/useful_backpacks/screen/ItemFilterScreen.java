package info.u_team.useful_backpacks.screen;

import info.u_team.u_team_core.gui.elements.CheckboxButton;
import info.u_team.u_team_core.screen.UBasicContainerScreen;
import info.u_team.u_team_core.util.RGBA;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.container.ItemFilterContainer;
import io.netty.buffer.Unpooled;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

public class ItemFilterScreen extends UBasicContainerScreen<ItemFilterContainer> {
	
	private static final ResourceLocation BACKGROUND = new ResourceLocation(UsefulBackpacksMod.MODID, "textures/gui/item_filter.png");
	
	private final Component strictTextComponent;
	private final Component strictTooltipTextComponent;
	
	public ItemFilterScreen(ItemFilterContainer container, Inventory playerInventory, Component title) {
		super(container, playerInventory, title, BACKGROUND, 176, 130);
		
		final String langKey = "container.usefulbackpacks.item_filter.";
		
		strictTextComponent = new TranslatableComponent(langKey + "strict");
		strictTooltipTextComponent = new TranslatableComponent(langKey + "strict.tooltip");
	}
	
	@Override
	protected void init() {
		super.init();
		
		final CheckboxButton isStrictCheckbox = addButton(new CheckboxButton(leftPos + imageWidth - (17 + 16), topPos + 17, 16, 16, strictTextComponent, menu.isStrict(), true));
		isStrictCheckbox.setTextColor(new RGBA(0x404040FF));
		isStrictCheckbox.setLeftSideText(true);
		isStrictCheckbox.setPressable(() -> {
			menu.getStrictMessage().triggerMessage(() -> new FriendlyByteBuf(Unpooled.copyBoolean(isStrictCheckbox.isChecked())));
			menu.setStrict(isStrictCheckbox.isChecked());
		});
		isStrictCheckbox.setTooltip((button, matrixStack, mouseX, mouseY) -> {
			if (button.isHovered()) {
				renderTooltip(matrixStack, strictTooltipTextComponent, mouseX, mouseY);
			}
		});
	}
}
