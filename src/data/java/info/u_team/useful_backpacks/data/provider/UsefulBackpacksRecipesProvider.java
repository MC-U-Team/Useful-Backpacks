package info.u_team.useful_backpacks.data.provider;

import static info.u_team.useful_backpacks.init.UsefulBackpacksBlocks.FILTER_CONFIGURATOR;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.ENDERCHEST_BACKPACK;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.ITEM_FILTER;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.LARGE_BACKPACK;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.MEDIUM_BACKPACK;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.SMALL_BACKPACK;
import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.TAG_FILTER;

import java.util.function.Consumer;

import info.u_team.u_team_core.data.CommonRecipesProvider;
import info.u_team.u_team_core.data.GenerationData;
import info.u_team.useful_backpacks.data.builder.BackpackCraftingRecipeBuilder;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;

public class UsefulBackpacksRecipesProvider extends CommonRecipesProvider {
	
	public UsefulBackpacksRecipesProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerRecipes(Consumer<FinishedRecipe> consumer) {
		BackpackCraftingRecipeBuilder.backpackRecipe(SMALL_BACKPACK.get()) //
				.patternLine("WLW") //
				.patternLine("LSL") //
				.patternLine("WLW") //
				.key('W', getIngredientOfTag(ItemTags.WOOL)) //
				.key('L', Items.LEATHER) //
				.key('S', Items.STRING) //
				.addCriterion("has_wool", hasItem(ItemTags.WOOL)) //
				.addCriterion("has_leather", hasItem(Items.LEATHER)) //
				.build(consumer);
		
		BackpackCraftingRecipeBuilder.backpackRecipe(MEDIUM_BACKPACK.get()) //
				.patternLine("WLW") //
				.patternLine("LBL") //
				.patternLine("WLW") //
				.key('W', getIngredientOfTag(ItemTags.WOOL)) //
				.key('L', Items.LEATHER) //
				.key('B', SMALL_BACKPACK.get()) //
				.addCriterion("has_small_backpack", hasItem(SMALL_BACKPACK.get())) //
				.build(consumer);
		
		BackpackCraftingRecipeBuilder.backpackRecipe(LARGE_BACKPACK.get()) //
				.patternLine("WLW") //
				.patternLine("LBL") //
				.patternLine("WLW") //
				.key('W', getIngredientOfTag(ItemTags.WOOL)) //
				.key('L', Items.LEATHER) //
				.key('B', MEDIUM_BACKPACK.get()) //
				.addCriterion("has_medium_backpack", hasItem(MEDIUM_BACKPACK.get())) //
				.build(consumer);
		
		ShapedRecipeBuilder.shapedRecipe(ENDERCHEST_BACKPACK.get()) //
				.patternLine("WLW") //
				.patternLine("ESE") //
				.patternLine("WLW") //
				.key('W', getIngredientOfTag(ItemTags.WOOL)) //
				.key('L', Items.LEATHER) //
				.key('E', Items.ENDER_CHEST) //
				.key('S', Items.STRING) //
				.addCriterion("has_enderchest", hasItem(Items.ENDER_CHEST)) //
				.build(consumer);
		
		ShapedRecipeBuilder.shapedRecipe(FILTER_CONFIGURATOR.get()) //
				.patternLine("WLW") //
				.patternLine("PCP") //
				.patternLine("WLW") //
				.key('W', getIngredientOfTag(ItemTags.WOOL)) //
				.key('L', Items.LEATHER) //
				.key('P', getIngredientOfTag(ItemTags.PLANKS)) //
				.key('C', Items.CRAFTING_TABLE) //
				.addCriterion("has_wool", hasItem(ItemTags.WOOL)) //
				.addCriterion("has_leather", hasItem(Items.LEATHER)) //
				.build(consumer);
		
		ShapedRecipeBuilder.shapedRecipe(ITEM_FILTER.get()) //
				.patternLine("WLW") //
				.patternLine("RDS") //
				.patternLine("IEI") //
				.key('W', getIngredientOfTag(ItemTags.WOOL)) //
				.key('L', Items.LEATHER) //
				.key('R', getIngredientOfTag(Tags.Items.DUSTS_REDSTONE)) //
				.key('D', getIngredientOfTag(Tags.Items.GEMS_DIAMOND)) //
				.key('S', Items.STRING) //
				.key('I', getIngredientOfTag(Tags.Items.INGOTS_IRON)) //
				.key('E', getIngredientOfTag(Tags.Items.END_STONES)) //
				.addCriterion("has_small_backpack", hasItem(SMALL_BACKPACK.get())) //
				.build(consumer);
		
		ShapedRecipeBuilder.shapedRecipe(TAG_FILTER.get()) //
				.patternLine("WLW") //
				.patternLine("RAS") //
				.patternLine("IEI") //
				.key('W', getIngredientOfTag(ItemTags.WOOL)) //
				.key('L', Items.LEATHER) //
				.key('R', getIngredientOfTag(Tags.Items.DUSTS_REDSTONE)) //
				.key('A', getIngredientOfTag(Tags.Items.INGOTS_NETHER_BRICK)) //
				.key('S', Items.STRING) //
				.key('I', getIngredientOfTag(Tags.Items.INGOTS_IRON)) //
				.key('E', getIngredientOfTag(Tags.Items.END_STONES)) //
				.addCriterion("has_small_backpack", hasItem(SMALL_BACKPACK.get())) //
				.build(consumer);
	}
}
