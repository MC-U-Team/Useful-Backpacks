package info.u_team.useful_backpacks.data.provider;

import static info.u_team.useful_backpacks.init.UsefulBackpacksItems.*;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

import info.u_team.u_team_core.data.*;
import info.u_team.useful_backpacks.data.builder.BackpackCraftingRecipeBuilder;
import net.minecraft.advancements.criterion.*;
import net.minecraft.advancements.criterion.MinMaxBounds.IntBound;
import net.minecraft.data.*;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.Ingredient.TagList;
import net.minecraft.tags.*;
import net.minecraft.util.IItemProvider;

public class UsefulBackpacksRecipesProvider extends CommonRecipesProvider {
	
	public UsefulBackpacksRecipesProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
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
	}
	
	// TEST ---- REMOVEL LATER
	protected InventoryChangeTrigger.Instance hasItem(ITag<Item> tag) {
		return hasItem(ItemPredicate.Builder.create().tag(tag).build());
	}
	
	protected InventoryChangeTrigger.Instance hasItem(IItemProvider item) {
		return hasItem(ItemPredicate.Builder.create().item(item).build());
	}
	
	protected InventoryChangeTrigger.Instance hasItem(ItemPredicate... predicates) {
		return new InventoryChangeTrigger.Instance(EntityPredicate.AndPredicate.field_234582_a_, IntBound.UNBOUNDED, IntBound.UNBOUNDED, IntBound.UNBOUNDED, predicates);
	}
	
	public static Ingredient getIngredientOfTag(ITag<Item> tag) {
		return Ingredient.fromItemListStream(Stream.of(new TagList(tag) {
			
			@Override
			public Collection<ItemStack> getStacks() {
				return Arrays.asList(new ItemStack(Items.ACACIA_BOAT)); // Return default value, so the ingredient will serialize our tag.
			}
		}));
	}
	
	// TEST
}
