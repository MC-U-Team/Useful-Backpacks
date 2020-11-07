package info.u_team.useful_backpacks.gui;

import info.u_team.useful_backpacks.UsefulBackPacksConstants;
import info.u_team.useful_backpacks.container.ContainerBackPack;
import info.u_team.useful_backpacks.enums.EnumBackPacks;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBackPack extends GuiContainer {
	
	private static final ResourceLocation texture_small = new ResourceLocation(UsefulBackPacksConstants.MODID, "textures/gui/backpack/small.png");
	private static final ResourceLocation texture_medium = new ResourceLocation(UsefulBackPacksConstants.MODID, "textures/gui/backpack/medium.png");
	private static final ResourceLocation texture_big = new ResourceLocation(UsefulBackPacksConstants.MODID, "textures/gui/backpack/large.png");
	
	private final EnumBackPacks type;
	
	public GuiBackPack(IInventory inventory, InventoryPlayer inventoryplayer, EnumBackPacks type, boolean offhand) {
		super(new ContainerBackPack(inventory, inventoryplayer, type, offhand));
		this.type = type;
		
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
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}
	
	@Override
	public void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		fontRenderer.drawString(I18n.format(UsefulBackPacksConstants.MODID + ":item.backpack." + type.getName() + ".name"), 8, 9, 4210752);
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
