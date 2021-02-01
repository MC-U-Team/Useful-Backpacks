package info.u_team.useful_backpacks.screen;

import com.mojang.blaze3d.matrix.MatrixStack;

import info.u_team.u_team_core.gui.elements.ScalableTextField;
import info.u_team.u_team_core.screen.UBasicContainerScreen;
import info.u_team.u_team_core.util.WidgetUtil;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.container.TagFilterContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class TagFilterScreen extends UBasicContainerScreen<TagFilterContainer> {
	
	private static final ResourceLocation BACKGROUND = new ResourceLocation(UsefulBackpacksMod.MODID, "textures/gui/tag_filter.png");
	
	private ScalableTextField tagTextField;
	
	private TagFilterTagList tagList;
	
	public TagFilterScreen(TagFilterContainer container, PlayerInventory playerInventory, ITextComponent title) {
		super(container, playerInventory, title, BACKGROUND, 176, 190);
	}
	
	@Override
	protected void init() {
		super.init();
		
		tagTextField = addButton(new ScalableTextField(font, guiLeft + 8, guiTop + 20, 160, 15, tagTextField, ITextComponent.getTextComponentOrEmpty("Scalable Text Field"), 0.75F));
		tagTextField.setMaxStringLength(300);
		tagTextField.setTooltip((textField, matrixStack, mouseX, mouseY) -> {
			if (WidgetUtil.isHovered(textField)) {
				renderTooltip(matrixStack, ITextComponent.getTextComponentOrEmpty("Search for tags"), mouseX, mouseY);
			}
		});
		tagTextField.setResponder(search -> {
			tagList.updateSearch(search);
		});
		
		tagList = new TagFilterTagList(container, guiLeft + 7, guiTop + 40, 161, 52, container.getTag());
		children.add(tagList);
	}
	
	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		super.render(matrixStack, mouseX, mouseY, partialTicks);
		tagList.render(matrixStack, mouseX, mouseY, partialTicks);
	}
	
	@Override
	public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
		final boolean dragged = getListener() != null && isDragging() && button == 0 ? getListener().mouseDragged(mouseX, mouseY, button, dragX, dragY) : false;
		if (!dragged) {
			return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
		}
		return true;
	}
}
