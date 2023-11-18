package havocpixel.entities;

public enum Direction {
	DOWN(0, 1),
	DOWNRIGHT(1, 1),
	RIGHT(1, 0),
	UPRIGHT(1, -1),
	UP(0, -1),
	UPLEFT(-1, -1),
	LEFT(-1, 0),
	DOWNLEFT(-1, 1);
	
	private int dx;
	private int dy;
	
	private Direction(int dx, int dy){
		this.dx=dx;
		this.dy=dy;
	}
	public int $dx(){
		return dx;
	}
	public int $dy(){
		return dy;
	}

}
