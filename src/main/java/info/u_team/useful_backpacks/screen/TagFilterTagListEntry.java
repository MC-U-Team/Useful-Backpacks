package info.u_team.useful_backpacks.screen;

import com.mojang.blaze3d.matrix.MatrixStack;

import info.u_team.u_team_core.gui.elements.ScrollableListEntry;
import net.minecraft.util.ResourceLocation;

public class TagFilterTagListEntry extends ScrollableListEntry<TagFilterTagListEntry> {
	
	private final ResourceLocation tag;
	
	public TagFilterTagListEntry(ResourceLocation tag) {
		this.tag = tag;
	}
	
	@Override
	public void render(MatrixStack matrixStack, int slotIndex, int entryY, int entryX, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float partialTicks) {
		matrixStack.push();
		matrixStack.scale(0.75F, 0.75F, 0.75F);
		minecraft.fontRenderer.drawString(matrixStack, tag.toString(), entryX * 1 / 0.75f, entryY * 1 / 0.75f, 0xFF0083FF);
		matrixStack.pop();
	}
	
	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		getList().setSelected(this);
		return super.mouseClicked(mouseX, mouseY, button);
	}
	
	public ResourceLocation getTag() {
		return tag;
	}
	
}
