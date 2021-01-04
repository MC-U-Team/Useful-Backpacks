package info.u_team.useful_backpacks.screen;

import info.u_team.u_team_core.screen.UBasicContainerScreen;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.container.TagFilterContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class TagFilterScreen extends UBasicContainerScreen<TagFilterContainer> {
	
	private static final ResourceLocation BACKGROUND = new ResourceLocation(UsefulBackpacksMod.MODID, "textures/gui/item_filter.png");
	
	public TagFilterScreen(TagFilterContainer container, PlayerInventory playerInventory, ITextComponent title) {
		super(container, playerInventory, title, BACKGROUND, 176, 130);
	}
	
	@Override
	protected void init() {
		super.init();
	}
}
