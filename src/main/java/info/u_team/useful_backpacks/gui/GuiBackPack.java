package info.u_team.useful_backpacks.gui;

import info.u_team.u_team_core.gui.UGuiContainer;
import info.u_team.useful_backpacks.UsefulBackPacksMod;
import info.u_team.useful_backpacks.container.ContainerBackPack;
import info.u_team.useful_backpacks.enums.EnumBackPacks;
import info.u_team.useful_backpacks.inventory.InventoryBackPack;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.*;

@OnlyIn(Dist.CLIENT)
public class GuiBackPack extends UGuiContainer {
	
	private final EnumBackPacks type;
	
	public GuiBackPack(InventoryPlayer playerInventory, InventoryBackPack backpackInventory) {
		super(new ContainerBackPack(playerInventory, backpackInventory), new ResourceLocation(UsefulBackPacksMod.modid, "textures/gui/backpack/" + backpackInventory.getType().getName() + ".png"));
		
		type = backpackInventory.getType();
		
		switch (type) {
		case SMALL:
			xSize = 176;
			ySize = 164;
			break;
		case MEDIUM:
			xSize = 176;
			ySize = 218;
			break;
		case LARGE:
			xSize = 248;
			ySize = 272;
			break;
		}
	}
	
	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		super.render(mouseX, mouseY, partialTicks);
		renderHoveredToolTip(mouseX, mouseY);
	}
	
	@Override
	public void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		fontRenderer.drawString(I18n.format("item." + UsefulBackPacksMod.modid + ".backpack_" + type.getName()), 8, 9, 4210752);
	}
	
	@Override
	public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(background);
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		drawModalRectWithCustomSizedTexture(xStart, yStart, 0, 0, xSize, ySize, 512, 512);
	}
}
