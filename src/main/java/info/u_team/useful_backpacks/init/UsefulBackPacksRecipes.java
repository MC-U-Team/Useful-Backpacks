package info.u_team.useful_backpacks.init;

import info.u_team.useful_backpacks.UsefulBackPacksMod;
import info.u_team.useful_backpacks.recipe.*;
import net.minecraft.item.crafting.*;
import net.minecraft.item.crafting.RecipeSerializers.SimpleSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.*;

public class UsefulBackPacksRecipes {
	
	public static final SimpleSerializer<RecipesBackPackDyes> CRAFTING_SPECIAL_BACKPACKDYE = new SimpleSerializer<>(UsefulBackPacksMod.modid + ":crafting_special_backpackdye", RecipesBackPackDyes::new);
	
	public static final IRecipeSerializer<RecipeShapedCopyNBT> CRAFTING_SPECIAL_COPY_NBT = new RecipeShapedCopyNBT.Serializer(UsefulBackPacksMod.modid + ":crafting_special_copy_nbt");
	
	public static final IIngredientSerializer<IngredientCopyNBT> INGREDIENT_COPY_NBT = new IngredientCopyNBT.Serializer();
	
	public static void construct() {
		RecipeSerializers.register(CRAFTING_SPECIAL_BACKPACKDYE);
		RecipeSerializers.register(CRAFTING_SPECIAL_COPY_NBT);
		CraftingHelper.register(new ResourceLocation(UsefulBackPacksMod.modid, "copy_nbt"), INGREDIENT_COPY_NBT);
	}
	
}
