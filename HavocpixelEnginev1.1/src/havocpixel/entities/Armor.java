package havocpixel.entities;

public enum Armor {
	SKELETON(-150, 0),
	FLESH(0, 0),
	ROTTING_FLESH(-125, 0),
	DEMON_SKIN(50, 0),
	CHITIN_PLATE(150, 0),
	DAMAGED_PLATE(175, 100),
	WROUGHT_IRON_PLATE(335, 500),
	STEEL_PLATE(300, 150),
	JUGGERNAUT_ARMOR(900, 500),
	POWER_ARMOR(900, 0);
	
	private int AC;
	private int weight;
	
	private Armor(int AC, int weight){
		this.AC=AC;
		this.weight=weight;
	}
	public int $AC(){
		return AC;
	}
	public int $weight(){
		return weight;
	}

}
