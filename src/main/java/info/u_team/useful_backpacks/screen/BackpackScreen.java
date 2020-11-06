package info.u_team.useful_backpacks.screen;

import info.u_team.u_team_core.screen.UBasicContainerScreen;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.container.BackpackContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class BackpackScreen extends UBasicContainerScreen<BackpackContainer> {
	
	public BackpackScreen(BackpackContainer container, PlayerInventory playerInventory, ITextComponent title) {
		super(container, playerInventory, title, new ResourceLocation(UsefulBackpacksMod.MODID, "textures/gui/backpack/" + container.getBackpack().getName() + ".png"), container.getBackpack().getTextureSizeX(), container.getBackpack().getTextureSizeY());
		backgroundWidth = backgroundHeight = 512;
		drawInventoryText = false;
		setTextLocation(8, 9, 0, 0);
	}
}
