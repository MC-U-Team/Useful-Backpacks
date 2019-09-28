package info.u_team.useful_backpacks.data.builder;

import java.util.function.Consumer;

import com.google.gson.JsonObject;

import info.u_team.useful_backpacks.init.UsefulBackpacksRecipeSerializers;
import net.minecraft.data.*;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.*;

public class BackpackCraftingRecipeBuilder extends ShapedRecipeBuilder {
	
	public BackpackCraftingRecipeBuilder(IItemProvider item, int count) {
		super(item, count);
	}
	
	public static ShapedRecipeBuilder backpackRecipe(IItemProvider item) {
		return backpackRecipe(item, 1);
	}
	
	public static ShapedRecipeBuilder backpackRecipe(IItemProvider item, int count) {
		return new BackpackCraftingRecipeBuilder(item, count);
	}
	
	@Override
	public void build(Consumer<IFinishedRecipe> consumer, ResourceLocation id) {
		super.build(recipe -> {
			consumer.accept(new IFinishedRecipe() {
				
				@Override
				public void serialize(JsonObject json) {
					recipe.serialize(json);
				}
				
				@Override
				public IRecipeSerializer<?> getSerializer() {
					return UsefulBackpacksRecipeSerializers.BACKPACK;
				}
				
				@Override
				public ResourceLocation getID() {
					return recipe.getID();
				}
				
				@Override
				public JsonObject getAdvancementJson() {
					return recipe.getAdvancementJson();
				}
				
				@Override
				public ResourceLocation getAdvancementID() {
					return recipe.getAdvancementID();
				}
			});
		}, id);
	}
}
