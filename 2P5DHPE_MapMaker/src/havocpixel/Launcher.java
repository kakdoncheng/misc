package havocpixel;

import havocpixel.main.Game;

public class Launcher {
	public static void main(String[] BEN) {
		Game game=new Game(null,1600,1000);
		System.out.print(game.define+" [Initialized "+Timer.date()+" "+Timer.time()+"]\n");
		game.start();
	}
}
