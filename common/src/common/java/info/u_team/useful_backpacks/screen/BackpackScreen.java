package info.u_team.useful_backpacks.screen;

import info.u_team.u_team_core.screen.UContainerMenuScreen;
import info.u_team.useful_backpacks.UsefulBackpacksReference;
import info.u_team.useful_backpacks.menu.BackpackMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class BackpackScreen extends UContainerMenuScreen<BackpackMenu> {
	
	public BackpackScreen(BackpackMenu menu, Inventory playerInventory, Component title) {
		super(menu, playerInventory, title, new ResourceLocation(UsefulBackpacksReference.MODID, "textures/gui/backpack/" + menu.getBackpack().getName() + ".png"), menu.getBackpack().getTextureSizeX(), menu.getBackpack().getTextureSizeY());
		setBackgroundDimensions(512);
		setDrawText(true, false);
		setTextLocation(8, 9, 0, 0);
	}
}
