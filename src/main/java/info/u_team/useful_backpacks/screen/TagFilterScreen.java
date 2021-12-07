package info.u_team.useful_backpacks.screen;

import info.u_team.u_team_core.gui.elements.ScalableEditBox;
import info.u_team.u_team_core.screen.UContainerMenuScreen;
import info.u_team.u_team_core.util.WidgetUtil;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.container.TagFilterContainer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class TagFilterScreen extends UContainerMenuScreen<TagFilterContainer> {
	
	private static final ResourceLocation BACKGROUND = new ResourceLocation(UsefulBackpacksMod.MODID, "textures/gui/tag_filter.png");
	
	private final Component tagSearchTextComponent;
	
	private ScalableEditBox tagTextField;
	private TagFilterTagList tagList;
	
	public TagFilterScreen(TagFilterContainer container, Inventory playerInventory, Component title) {
		super(container, playerInventory, title, BACKGROUND, 176, 190);
		
		tagSearchTextComponent = new TranslatableComponent("container.usefulbackpacks.tag_filter.search");
	}
	
	@Override
	protected void init() {
		super.init();
		
		minecraft.keyboardHandler.setSendRepeatsToGui(true);
		
		tagTextField = addRenderableWidget(new ScalableEditBox(font, leftPos + 8, topPos + 20, 160, 15, tagTextField, tagSearchTextComponent, 0.75F));
		tagTextField.setMaxLength(300);
		tagTextField.setTooltip((textField, poseStack, mouseX, mouseY) -> {
			if (WidgetUtil.isHovered(textField)) {
				renderTooltip(poseStack, tagSearchTextComponent, mouseX, mouseY);
			}
		});
		tagTextField.setResponder(search -> {
			tagList.updateSearch(search);
		});
		setInitialFocus(tagTextField);
		
		tagList = addRenderableWidget(new TagFilterTagList(menu, leftPos + 7, topPos + 40, 161, 52, menu.getTag()));
	}
	
	@Override
	public void removed() {
		minecraft.keyboardHandler.setSendRepeatsToGui(false);
	}
	
	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		if (keyCode == 256 && shouldCloseOnEsc()) {
			onClose();
			return true;
		}
		return !tagTextField.keyPressed(keyCode, scanCode, modifiers) && !tagTextField.canConsumeInput() ? super.keyPressed(keyCode, scanCode, modifiers) : true;
	}
	
	@Override
	public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
		final var dragged = getFocused() != null && isDragging() && button == 0 ? getFocused().mouseDragged(mouseX, mouseY, button, dragX, dragY) : false;
		if (!dragged) {
			return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
		}
		return true;
	}
}
