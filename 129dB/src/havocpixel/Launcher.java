package havocpixel;

import havocpixel.main.Game;

public class Launcher {
	
	public static void main(String[] BEN) {
		Game game=new Game(null,1150,650,false);
		System.out.print("[2P5DHavocPixel Engine][32bit]["+game.version+"][Initialized "+Timer.date()+" "+Timer.time()+"]\n");
		game.start();
	}
	
}
