package info.u_team.useful_backpacks.screen;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import info.u_team.u_team_core.gui.elements.ScrollableList;
import net.minecraft.tags.TagCollectionManager;
import net.minecraft.util.ResourceLocation;

public class TagFilterTagList extends ScrollableList<TagFilterTagListEntry> {
	
	public TagFilterTagList(int x, int y, int width, int height) {
		super(x, y, width, height, 10, 15);
		func_244606_c(false);
		shouldUseScissor = true;
		updateEntries(tag -> true);
	}
	
	public void updateSearch(String search) {
		if (search.isEmpty()) {
			updateEntries(tag -> true);
		} else {
			updateEntries(tag -> {
				if (tag.getNamespace().startsWith(search)) {
					return true;
				}
				if (tag.getPath().startsWith(search)) {
					return true;
				}
				return false;
			});
		}
	}
	
	private void updateEntries(Predicate<ResourceLocation> predicate) {
		final List<ResourceLocation> list = TagCollectionManager.getManager().getItemTags().getRegisteredTags().stream().filter(predicate).collect(Collectors.toList());
		
		Collections.sort(list, (a, b) -> {
			return a.toString().compareTo(b.toString());
		});
		final TagFilterTagListEntry selected = getSelected();
		clearEntries();
		list.forEach(tag -> {
			final TagFilterTagListEntry entry = new TagFilterTagListEntry(tag);
			addEntry(entry);
			
			if (selected != null && selected.getTag().equals(tag)) {
				setSelected(entry);
			}
		});
	}
	
}
