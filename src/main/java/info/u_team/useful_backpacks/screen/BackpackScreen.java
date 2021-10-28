package info.u_team.useful_backpacks.screen;

import info.u_team.u_team_core.screen.UBasicContainerScreen;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.container.BackpackContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

public class BackpackScreen extends UBasicContainerScreen<BackpackContainer> {
	
	public BackpackScreen(BackpackContainer container, Inventory playerInventory, Component title) {
		super(container, playerInventory, title, new ResourceLocation(UsefulBackpacksMod.MODID, "textures/gui/backpack/" + container.getBackpack().getName() + ".png"), container.getBackpack().getTextureSizeX(), container.getBackpack().getTextureSizeY());
		setBackgroundDimensions(512);
		setDrawText(true, false);
		setTextLocation(8, 9, 0, 0);
	}
}
