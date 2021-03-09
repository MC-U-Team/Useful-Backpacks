package info.u_team.useful_backpacks.screen;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import info.u_team.u_team_core.gui.elements.ScrollableList;
import info.u_team.useful_backpacks.container.TagFilterContainer;
import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tags.TagCollectionManager;
import net.minecraft.util.ResourceLocation;

public class TagFilterTagList extends ScrollableList<TagFilterTagListEntry> {
	
	private final TagFilterContainer container;
	
	public TagFilterTagList(TagFilterContainer container, int x, int y, int width, int height, String tag) {
		super(x, y, width, height, 10, 15);
		this.container = container;
		
		func_244606_c(false);
		setShouldUseScissor(true);
		setShouldRenderTransparentBorder(true);
		
		updateEntries(unused -> true);
		
		if (!tag.isEmpty()) {
			getEventListeners().stream().filter(entry -> entry.getTag().toString().equals(tag)).findAny().ifPresent(entry -> {
				super.setSelected(entry);
				centerScrollOn(entry);
			});
		}
	}
	
	@Override
	public void setSelected(TagFilterTagListEntry entry) {
		super.setSelected(entry);
		container.getTagMessage().triggerMessage(() -> new PacketBuffer(Unpooled.buffer(100)).writeString(entry.getTag().toString()));
		container.setTag(entry.getTag().toString());
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
		setScrollAmount(0);
		list.forEach(tag -> {
			final TagFilterTagListEntry entry = new TagFilterTagListEntry(tag);
			addEntry(entry);
			
			if (selected != null && selected.getTag().equals(tag)) {
				super.setSelected(entry);
			}
		});
		
		if (getSelected() != null) {
			centerScrollOn(getSelected());
		}
	}
	
}
