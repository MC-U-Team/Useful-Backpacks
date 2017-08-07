package info.u_team.usefullbackpacks.mod;

import info.u_team.u_team_core.util.ModMetadataFetcher;
import info.u_team.usefullbackpacks.Reference;
import net.minecraftforge.fml.common.ModMetadata;

public class MetadataFetcher extends ModMetadataFetcher {
	
	public MetadataFetcher() {
		super("/usefullbackpacks.json", Reference.modid);
	}
	
	@Override
	public ModMetadata getModmeta() {
		ModMetadata modmeta = super.getModmeta();
		modmeta.name = Reference.name;
		modmeta.version = Reference.version;
		return modmeta;
	}
}
