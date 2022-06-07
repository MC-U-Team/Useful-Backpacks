package info.u_team.useful_backpacks.screen;

import info.u_team.u_team_core.screen.UContainerMenuScreen;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.menu.FilterConfiguratorMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class FilterConfiguratorScreen extends UContainerMenuScreen<FilterConfiguratorMenu> {
	
	private static final ResourceLocation BACKGROUND = new ResourceLocation(UsefulBackpacksMod.MODID, "textures/gui/filter_configurator.png");
	
	public FilterConfiguratorScreen(FilterConfiguratorMenu container, Inventory playerInventory, Component title) {
		super(container, playerInventory, title, BACKGROUND);
	}
}
