package info.u_team.useful_backpacks.screen;

import info.u_team.u_team_core.gui.elements.CheckboxButton;
import info.u_team.u_team_core.screen.UBasicContainerScreen;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.container.ItemFilterContainer;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ItemFilterScreen extends UBasicContainerScreen<ItemFilterContainer> {
	
	private static final ResourceLocation BACKGROUND = new ResourceLocation(UsefulBackpacksMod.MODID, "textures/gui/item_filter.png");
	
	public ItemFilterScreen(ItemFilterContainer container, PlayerInventory playerInventory, ITextComponent title) {
		super(container, playerInventory, title, BACKGROUND, 176, 130);
	}
	
	@Override
	protected void init() {
		super.init();
		
		final CheckboxButton isStrictCheckbox = addButton(new CheckboxButton(guiLeft + xSize - (17 + 16), guiTop + 17, 16, 16, ITextComponent.getTextComponentOrEmpty("Strict"), container.isStrictInitial(), false));
		isStrictCheckbox.setPressable(() -> {
			container.getStrictMessage().triggerMessage(() -> new PacketBuffer(Unpooled.copyBoolean(isStrictCheckbox.isChecked())));
		});
	}
}
