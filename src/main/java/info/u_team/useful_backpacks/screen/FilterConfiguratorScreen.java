package info.u_team.useful_backpacks.screen;

import info.u_team.u_team_core.screen.UBasicContainerScreen;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.container.FilterConfiguratorContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class FilterConfiguratorScreen extends UBasicContainerScreen<FilterConfiguratorContainer> {
	
	private static final ResourceLocation BACKGROUND = new ResourceLocation(UsefulBackpacksMod.MODID, "textures/gui/filter_configurator.png");
	
	public FilterConfiguratorScreen(FilterConfiguratorContainer container, PlayerInventory playerInventory, ITextComponent title) {
		super(container, playerInventory, title, BACKGROUND, 176, 193);
	}
}
