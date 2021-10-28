package info.u_team.useful_backpacks.data.builder;

import java.util.function.Consumer;

import info.u_team.u_team_core.util.RecipeBuilderUtil;
import info.u_team.useful_backpacks.init.UsefulBackpacksRecipeSerializers;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.level.ItemLike;
import net.minecraft.resources.ResourceLocation;

public class BackpackCraftingRecipeBuilder extends ShapedRecipeBuilder {
	
	public BackpackCraftingRecipeBuilder(ItemLike item, int count) {
		super(item, count);
	}
	
	public static ShapedRecipeBuilder backpackRecipe(ItemLike item) {
		return backpackRecipe(item, 1);
	}
	
	public static ShapedRecipeBuilder backpackRecipe(ItemLike item, int count) {
		return new BackpackCraftingRecipeBuilder(item, count);
	}
	
	@Override
	public void build(Consumer<FinishedRecipe> consumer, ResourceLocation location) {
		super.build(recipe -> consumer.accept(RecipeBuilderUtil.getRecipeWithSerializer(recipe, UsefulBackpacksRecipeSerializers.BACKPACK.get())), location);
	}
}
