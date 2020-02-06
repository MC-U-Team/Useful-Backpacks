package info.u_team.useful_backpacks.enums;

public enum EnumBackPacks {
	
	SMALL(0, "small", 5, 3),
	MEDIUM(1, "medium", 9, 6),
	LARGE(2, "large", 13, 9);
	
	private int meta, sizex, sizey;
	private String name;
	
	private EnumBackPacks(int meta, String name, int sizex, int sizey) {
		this.meta = meta;
		this.name = name;
		this.sizex = sizex;
		this.sizey = sizey;
	}
	
	public int getMetadata() {
		return meta;
	}
	
	public String getName() {
		return name;
	}
	
	public int getSizeX() {
		return sizex;
	}
	
	public int getSizeY() {
		return sizey;
	}
	
	public int getCount() {
		return getSizeX() * getSizeY();
	}
	
	private static final EnumBackPacks[] all = new EnumBackPacks[values().length];
	static {
		for (EnumBackPacks type : values()) {
			all[type.getMetadata()] = type;
		}
	}
	
	public static EnumBackPacks byMetadata(int meta) {
		if (meta < 0 || meta >= all.length) {
			meta = 0;
		}
		
		return all[meta];
	}
}
