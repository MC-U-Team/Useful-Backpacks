package info.u_team.useful_backpacks.screen;

import info.u_team.u_team_core.screen.UBasicContainerScreen;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.container.ItemFilterContainer;
import io.netty.buffer.Unpooled;
import net.minecraft.client.gui.widget.button.CheckboxButton;
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
		
		addButton(new CheckboxButton(50, 50, 20, 20, ITextComponent.getTextComponentOrEmpty("Strict"), container.isStrictInitial()) {
			
			@Override
			public void onPress() {
				super.onPress();
				container.getStrictMessage().triggerMessage(() -> new PacketBuffer(Unpooled.buffer(1).writeBoolean(isChecked())));
			}
		});
	}
}
