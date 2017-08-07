package info.u_team.usefullbackpacks.creativetab;

import info.u_team.u_team_core.util.ItemUtil;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ModCreativeTabs {
	
	public CreativeTabs usefullbackpacks;
	
	public ModCreativeTabs() {
		init();
	}
	
	private void init() {
		usefullbackpacks = new CreativeTabs("usefullbackpacks") {
			
			@Override
			public Item getTabIconItem() {
				return ItemUtil.from(null);
			}
		};
	}
	
}
