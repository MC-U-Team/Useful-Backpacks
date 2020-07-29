package info.u_team.useful_backpacks.integration.curious.data.provider;

import info.u_team.u_team_core.data.*;
import info.u_team.u_team_core.util.TagUtil;
import info.u_team.useful_backpacks.init.UsefulBackpacksTags;

public class CuriousIntegrationItemTagsProvider extends CommonItemTagsProvider {
	
	public CuriousIntegrationItemTagsProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerTags() {
		getBuilder(TagUtil.createItemTag("curios", "back")).addTag(UsefulBackpacksTags.Items.BACKPACK);
	}
	
}
