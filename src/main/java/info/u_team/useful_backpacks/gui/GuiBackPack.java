package info.u_team.useful_backpacks.gui;

import info.u_team.useful_backpacks.UsefulBackPacksMod;
import info.u_team.useful_backpacks.container.ContainerBackPack;
import info.u_team.useful_backpacks.enums.EnumBackPacks;
import info.u_team.useful_backpacks.inventory.InventoryBackPack;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.*;

@OnlyIn(Dist.CLIENT)
public class GuiBackPack extends GuiContainer {
	
	private ResourceLocation texture_small = new ResourceLocation(UsefulBackPacksMod.modid, "textures/gui/backpack/small.png");
	private ResourceLocation texture_medium = new ResourceLocation(UsefulBackPacksMod.modid, "textures/gui/backpack/medium.png");
	private ResourceLocation texture_big = new ResourceLocation(UsefulBackPacksMod.modid, "textures/gui/backpack/large.png");
	
	private EnumBackPacks type;
	
	public GuiBackPack(InventoryPlayer playerInventory, InventoryBackPack backpackInventory) {
		super(new ContainerBackPack(playerInventory, backpackInventory));
		
		this.type = backpackInventory.getType();
		
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
		switch (type) {
		case SMALL:
			mc.getTextureManager().bindTexture(texture_small);
			break;
		case MEDIUM:
			mc.getTextureManager().bindTexture(texture_medium);
			break;
		case LARGE:
			mc.getTextureManager().bindTexture(texture_big);
			break;
		}
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		drawModalRectWithCustomSizedTexture(k, l, 0, 0, this.xSize, this.ySize, 512, 512);
	}
	
}
