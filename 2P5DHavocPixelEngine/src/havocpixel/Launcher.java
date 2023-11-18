package havocpixel;

import havocpixel.main.Game;

public class Launcher {
	
	public static void main(String[] args) {
		//Game game=new Game(null,820,502,false);
		//System.setProperty("sun.java2d.opengl", "True");
		Game game=null;
		if(args.length==0) {
			game=new Game(null,562,345,true
					,true); //750x460
		} else if(args.length==1){
			game=new Game(null,562,345,Boolean.parseBoolean(args[0]),true);
		} else if(args.length==2){
			game=new Game(null,562,345,Boolean.parseBoolean(args[0]),Boolean.parseBoolean(args[1]));
		}
		System.out.print("[2P5DHavocPixel Engine][32bit]["+game.version+"][Initialized "+Timer.date()+" "+Timer.time()+"]\n");
		game.start();
	}
	
}
