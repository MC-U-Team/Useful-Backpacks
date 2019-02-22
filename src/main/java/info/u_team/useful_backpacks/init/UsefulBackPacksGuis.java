package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.registry.GuiRegistry;
import info.u_team.useful_backpacks.UsefulBackPacksMod;
import info.u_team.useful_backpacks.gui.GuiBackPack;
import info.u_team.useful_backpacks.inventory.InventoryBackPack;
import info.u_team.useful_backpacks.item.ItemBackPack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.*;
import net.minecraftforge.api.distmarker.*;

@OnlyIn(Dist.CLIENT)
public class UsefulBackPacksGuis {
	
	public static void construct() {
		GuiRegistry.register(new ResourceLocation(UsefulBackPacksMod.modid, "backpack"), openContainer -> {
			EntityPlayerSP player = Minecraft.getInstance().player;
			PacketBuffer buffer = openContainer.getAdditionalData();
			EnumHand hand = buffer.readEnumValue(EnumHand.class);
			
			if (hand == null) {
				return null;
			}
			
			ItemStack stack = player.getHeldItem(hand);
			Item item = stack.getItem();
			
			if (!(item instanceof ItemBackPack)) {
				return null;
			}
			ItemBackPack backpack = (ItemBackPack) item;
			return new GuiBackPack(player.inventory, new InventoryBackPack(stack, backpack.getType()));
		});
	}
}
