package info.u_team.useful_backpacks.enums;

import net.minecraft.item.EnumRarity;
import net.minecraft.util.IStringSerializable;

public enum EnumBackPacks implements IStringSerializable {
	
	SMALL("small", EnumRarity.COMMON, 5, 3),
	MEDIUM("medium", EnumRarity.UNCOMMON, 9, 6),
	LARGE("large", EnumRarity.RARE, 13, 9);
	
	private final String name;
	private final EnumRarity rarity;
	private final int sizeX, sizeY;
	
	private EnumBackPacks(String name, EnumRarity rarity, int sizeX, int sizeY) {
		this.name = name;
		this.rarity = rarity;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}
	
	public String getName() {
		return name;
	}
	
	public EnumRarity getRarity() {
		return rarity;
	}
	
	public int getSizeX() {
		return sizeX;
	}
	
	public int getSizeY() {
		return sizeY;
	}
	
	public int getCount() {
		return getSizeX() * getSizeY();
	}
}
