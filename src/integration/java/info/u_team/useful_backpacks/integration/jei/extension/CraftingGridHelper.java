/*
 * The MIT License (MIT)
 * 
 * Copyright (c) 2014-2015 mezz
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package info.u_team.useful_backpacks.integration.jei.extension;

import java.util.List;

import mezz.jei.api.gui.ingredient.*;

/**
 * This class is from jei by mezz licensed under MIT. Find it here on <a href=
 * "https://github.com/mezz/JustEnoughItems/blob/b6c3363868fc6cd39950d980eb5dabb143fdd8bb/src/main/java/mezz/jei/gui/CraftingGridHelper.java">Github</a>
 */
public class CraftingGridHelper implements ICraftingGridHelper {
	
	private final int craftInputSlot1;
	
	public CraftingGridHelper(int craftInputSlot1) {
		this.craftInputSlot1 = craftInputSlot1;
	}
	
	@Override
	public <T> void setInputs(IGuiIngredientGroup<T> ingredientGroup, List<List<T>> inputs) {
		int width, height;
		if (inputs.size() > 4) {
			width = height = 3;
		} else if (inputs.size() > 1) {
			width = height = 2;
		} else {
			width = height = 1;
		}
		
		setInputs(ingredientGroup, inputs, width, height);
	}
	
	@Override
	public <T> void setInputs(IGuiIngredientGroup<T> ingredientGroup, List<List<T>> inputs, int width, int height) {
		for (int i = 0; i < inputs.size(); i++) {
			final List<T> recipeItem = inputs.get(i);
			final int index = getCraftingIndex(i, width, height);
			
			setInput(ingredientGroup, index, recipeItem);
		}
	}
	
	private <T> void setInput(IGuiIngredientGroup<T> guiIngredients, int inputIndex, List<T> input) {
		guiIngredients.set(craftInputSlot1 + inputIndex, input);
	}
	
	private int getCraftingIndex(int i, int width, int height) {
		int index;
		if (width == 1) {
			if (height == 3) {
				index = (i * 3) + 1;
			} else if (height == 2) {
				index = (i * 3) + 1;
			} else {
				index = 4;
			}
		} else if (height == 1) {
			index = i + 3;
		} else if (width == 2) {
			index = i;
			if (i > 1) {
				index++;
				if (i > 3) {
					index++;
				}
			}
		} else if (height == 2) {
			index = i + 3;
		} else {
			index = i;
		}
		return index;
	}
}