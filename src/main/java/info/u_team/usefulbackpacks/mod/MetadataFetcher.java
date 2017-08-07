package info.u_team.usefulbackpacks.mod;

import info.u_team.u_team_core.util.ModMetadataFetcher;
import info.u_team.usefulbackpacks.Reference;
import net.minecraftforge.fml.common.ModMetadata;

public class MetadataFetcher extends ModMetadataFetcher {
	
	public MetadataFetcher() {
		super("/usefulbackpacks.json", Reference.modid);
	}
	
	@Override
	public ModMetadata getModmeta() {
		ModMetadata modmeta = super.getModmeta();
		modmeta.name = Reference.name;
		modmeta.version = Reference.version;
		return modmeta;
	}
}
