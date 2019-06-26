package info.u_team.useful_backpacks.gui;

import com.mojang.blaze3d.platform.GlStateManager;

import info.u_team.u_team_core.gui.UContainerScreen;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.container.BackpackContainer;
import info.u_team.useful_backpacks.type.Backpack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.*;

@OnlyIn(Dist.CLIENT)
public class BackpackScreen extends UContainerScreen<BackpackContainer> {
	
	public BackpackScreen(BackpackContainer container, PlayerInventory playerInventory, ITextComponent title) {
		super(container, playerInventory, title, new ResourceLocation(UsefulBackpacksMod.MODID, "textures/gui/backpack/" + container.getBackpack().getName() + ".png"));
		final Backpack backpack = container.getBackpack();
		xSize = backpack.getTextureSizeX();
		ySize = backpack.getTextureSizeY();
	}
	
	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		renderBackground();
		super.render(mouseX, mouseY, partialTicks);
		renderHoveredToolTip(mouseX, mouseY);
	}
	
	@Override
	public void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		font.drawString(title.getFormattedText(), 8, 9, 4210752);
	}
	
	@Override
	public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		minecraft.getTextureManager().bindTexture(background);
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		blit(xStart, yStart, 0, 0, xSize, ySize, 512, 512);
	}
}
