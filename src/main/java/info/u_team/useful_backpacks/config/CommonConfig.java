package info.u_team.useful_backpacks.config;

import info.u_team.useful_backpacks.UsefulBackPacksConstants;
import net.minecraftforge.common.config.Config;

@Config(modid = UsefulBackPacksConstants.MODID, category = "common")
public class CommonConfig {

    @Config.Comment(value = { "This option controls if backpacks in backpacks are allowed.", "If set to true you can put backpacks in existing backpacks and stack them together.", "If set to false you cannot put backpacks in backpacks" })
    @Config.Name("allowStackingBackpacks")
    public static boolean allowStackingBackpacks = true;

}