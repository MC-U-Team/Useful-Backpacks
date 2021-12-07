package info.u_team.useful_backpacks.screen;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import info.u_team.u_team_core.gui.elements.ScrollableList;
import info.u_team.useful_backpacks.container.TagFilterContainer;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.SerializationTags;
import net.minecraftforge.registries.ForgeRegistries;

public class TagFilterTagList extends ScrollableList<TagFilterTagListEntry> {
	
	private final TagFilterContainer container;
	
	public TagFilterTagList(TagFilterContainer container, int x, int y, int width, int height, String tag) {
		super(x, y, width, height, 10, 15);
		this.container = container;
		
		setRenderTopAndBottom(false);
		setShouldUseScissor(true);
		setRenderTransparentBorder(true);
		
		updateEntries(unused -> true);
		
		if (!tag.isEmpty()) {
			children().stream().filter(entry -> entry.getTag().toString().equals(tag)).findAny().ifPresent(entry -> {
				super.setSelected(entry);
				centerScrollOn(entry);
			});
		}
	}
	
	@Override
	public void setSelected(TagFilterTagListEntry entry) {
		super.setSelected(entry);
		
		final var tag = entry.getTag().toString();
		
		container.getTagMessage().triggerMessage(() -> new FriendlyByteBuf(Unpooled.buffer(100)).writeUtf(tag));
		container.setTag(tag);
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
		final List<ResourceLocation> list = SerializationTags.getInstance().getOrEmpty(ForgeRegistries.Keys.ITEMS).getAvailableTags().stream().filter(predicate).collect(Collectors.toList());
		
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
