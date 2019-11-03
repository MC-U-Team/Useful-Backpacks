package info.u_team.useful_backpacks.data.provider;

import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.*;

import java.util.function.Consumer;

import info.u_team.u_team_core.data.*;
import info.u_team.useful_backpacks.data.builder.BackpackCraftingRecipeBuilder;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;

public class UsefulBackpacksRecipesProvider extends CommonRecipesProvider {
	
	public UsefulBackpacksRecipesProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
		BackpackCraftingRecipeBuilder.backpackRecipe(SMALL_BACKPACK) //
				.patternLine("WLW") //
				.patternLine("LSL") //
				.patternLine("WLW") //
				.key('W', getIngredientOfTag(ItemTags.WOOL)) //
				.key('L', Items.LEATHER) //
				.key('S', Items.STRING) //
				.addCriterion("has_wool", hasItem(ItemTags.WOOL)) //
				.addCriterion("has_leather", hasItem(Items.LEATHER)) //
				.build(consumer);
		
		BackpackCraftingRecipeBuilder.backpackRecipe(MEDIUM_BACKPACK) //
				.patternLine("WLW") //
				.patternLine("LBL") //
				.patternLine("WLW") //
				.key('W', getIngredientOfTag(ItemTags.WOOL)) //
				.key('L', Items.LEATHER) //
				.key('B', SMALL_BACKPACK) //
				.addCriterion("has_small_backpack", hasItem(SMALL_BACKPACK)) //
				.build(consumer);
		
		BackpackCraftingRecipeBuilder.backpackRecipe(LARGE_BACKPACK) //
				.patternLine("WLW") //
				.patternLine("LBL") //
				.patternLine("WLW") //
				.key('W', getIngredientOfTag(ItemTags.WOOL)) //
				.key('L', Items.LEATHER) //
				.key('B', MEDIUM_BACKPACK) //
				.addCriterion("has_medium_backpack", hasItem(MEDIUM_BACKPACK)) //
				.build(consumer);
		
		ShapedRecipeBuilder.shapedRecipe(ENDERCHEST_BACKPACK) //
				.patternLine("WLW") //
				.patternLine("ESE") //
				.patternLine("WLW") //
				.key('W', getIngredientOfTag(ItemTags.WOOL)) //
				.key('L', Items.LEATHER) //
				.key('E', Items.ENDER_CHEST) //
				.key('S', Items.STRING) //
				.addCriterion("has_enderchest", hasItem(Items.ENDER_CHEST)) //
				.build(consumer);
	}
}
