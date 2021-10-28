package info.u_team.useful_backpacks.screen;

import com.mojang.blaze3d.vertex.PoseStack;

import info.u_team.u_team_core.gui.elements.ScalableTextField;
import info.u_team.u_team_core.screen.UBasicContainerScreen;
import info.u_team.u_team_core.util.WidgetUtil;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.container.TagFilterContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

public class TagFilterScreen extends UBasicContainerScreen<TagFilterContainer> {
	
	private static final ResourceLocation BACKGROUND = new ResourceLocation(UsefulBackpacksMod.MODID, "textures/gui/tag_filter.png");
	
	private final Component tagSearchTextComponent;
	
	private ScalableTextField tagTextField;
	
	private TagFilterTagList tagList;
	
	public TagFilterScreen(TagFilterContainer container, Inventory playerInventory, Component title) {
		super(container, playerInventory, title, BACKGROUND, 176, 190);
		
		tagSearchTextComponent = new TranslatableComponent("container.usefulbackpacks.tag_filter.search");
	}
	
	@Override
	protected void init() {
		super.init();
		
		minecraft.keyboardHandler.setSendRepeatsToGui(true);
		
		tagTextField = addButton(new ScalableTextField(font, leftPos + 8, topPos + 20, 160, 15, tagTextField, tagSearchTextComponent, 0.75F));
		tagTextField.setMaxLength(300);
		tagTextField.setTooltip((textField, matrixStack, mouseX, mouseY) -> {
			if (WidgetUtil.isHovered(textField)) {
				renderTooltip(matrixStack, tagSearchTextComponent, mouseX, mouseY);
			}
		});
		tagTextField.setResponder(search -> {
			tagList.updateSearch(search);
		});
		setInitialFocus(tagTextField);
		
		tagList = new TagFilterTagList(menu, leftPos + 7, topPos + 40, 161, 52, menu.getTag());
		children.add(tagList);
	}
	
	@Override
	public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		super.render(matrixStack, mouseX, mouseY, partialTicks);
		tagList.render(matrixStack, mouseX, mouseY, partialTicks);
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
		final boolean dragged = getFocused() != null && isDragging() && button == 0 ? getFocused().mouseDragged(mouseX, mouseY, button, dragX, dragY) : false;
		if (!dragged) {
			return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
		}
		return true;
	}
}
