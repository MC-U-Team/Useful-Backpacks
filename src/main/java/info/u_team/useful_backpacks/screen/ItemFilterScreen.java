package info.u_team.useful_backpacks.screen;

import info.u_team.u_team_core.gui.elements.CheckboxButton;
import info.u_team.u_team_core.screen.UBasicContainerScreen;
import info.u_team.u_team_core.util.RGBA;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.container.ItemFilterContainer;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class ItemFilterScreen extends UBasicContainerScreen<ItemFilterContainer> {
	
	private static final ResourceLocation BACKGROUND = new ResourceLocation(UsefulBackpacksMod.MODID, "textures/gui/item_filter.png");
	
	private final ITextComponent strictTextComponent;
	private final ITextComponent strictTooltipTextComponent;
	
	public ItemFilterScreen(ItemFilterContainer container, PlayerInventory playerInventory, ITextComponent title) {
		super(container, playerInventory, title, BACKGROUND, 176, 130);
		
		final String langKey = "container.usefulbackpacks.item_filter.";
		
		strictTextComponent = new TranslationTextComponent(langKey + "strict");
		strictTooltipTextComponent = new TranslationTextComponent(langKey + "strict.tooltip");
	}
	
	@Override
	protected void init() {
		super.init();
		
		final CheckboxButton isStrictCheckbox = addButton(new CheckboxButton(guiLeft + xSize - (17 + 16), guiTop + 17, 16, 16, strictTextComponent, container.isStrict(), true));
		isStrictCheckbox.setTextColor(new RGBA(0x404040FF));
		isStrictCheckbox.setLeftSideText(true);
		isStrictCheckbox.setPressable(() -> {
			container.getStrictMessage().triggerMessage(() -> new PacketBuffer(Unpooled.copyBoolean(isStrictCheckbox.isChecked())));
			container.setStrict(isStrictCheckbox.isChecked());
		});
		isStrictCheckbox.setTooltip((button, matrixStack, mouseX, mouseY) -> {
			if (button.isHovered()) {
				renderTooltip(matrixStack, strictTooltipTextComponent, mouseX, mouseY);
			}
		});
	}
}
