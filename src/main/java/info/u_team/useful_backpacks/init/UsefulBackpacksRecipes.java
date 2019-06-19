package info.u_team.useful_backpacks.init;

import info.u_team.u_team_core.util.registry.BaseRegistryUtil;
import info.u_team.useful_backpacks.UsefulBackpacksMod;
import info.u_team.useful_backpacks.recipe.*;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = UsefulBackpacksMod.modid, bus = Bus.MOD)
public class UsefulBackpacksRecipes {
	
	public static final IRecipeSerializer<BackpackDyeRecipe> backpack_dye = new USpecialRecipeSerializer<>("crafting_special_backpackdye", BackpackDyeRecipe::new);
	public static final IRecipeSerializer<BackpackCraftingRecipe> backpack = new BackpackCraftingRecipe.Serializer("crafting_backpack");
	
	@SubscribeEvent
	public static void register(Register<IRecipeSerializer<?>> event) {
		BaseRegistryUtil.getAllGenericRegistryEntriesAndApplyNames(UsefulBackpacksMod.modid, IRecipeSerializer.class).forEach(event.getRegistry()::register);
	}
	
}
