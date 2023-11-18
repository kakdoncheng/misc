package havocpixel;

import havocpixel.util.Utils;

public class CoreLauncher {
	
	public static final String CREDITS="gAme_PROgrAmMIng&DesIGN:LiNH-HAN_vAn'J `/ '` `/;ARt&AnIMATIOns:LH&sARa_PelaYO&JayDE_ToM";
	public static boolean bootstrap=false;

	public static void main(String[] args) {
        if(args.length < 1 && bootstrap){
        	Utils.whisperInit();
        	System.out.println("Rebooting...");
        	String cmd="cmd /c C:&&set Path="+Utils.jvmPath+";%Path%&&java -cp "+Utils.jarName+" -Xmx500m -Xms500m havocpixel.CoreLauncher START";
        	Utils.newProcess(cmd);
        	return;
        }
        long heapsize = Runtime.getRuntime().totalMemory();
        System.out.println("havocpixel.CoreLauncher:INFO: Current Heapsize: " + heapsize/1048576.0 +" MB");
		System.out.print("havocpixel.CoreLauncher:INFO: Launching Game.\n");
		System.out.print("havocpixel.CoreLauncher:WARNING: Risi Soru kol feri asi'ril. Ned'ai Risi Soru. Fele khamik Nari felil sani.\n");
		launch();
	}
	
	
	protected static void launch(){
		Game game=new Game("a Fairy Tale.", 480, 300, false, true);
		//Game game=new Game("a Fairy Tale.", 562, 345, false, true);
		game.setFPSLimit(60);
		//game.setFixedStep(true);
		//game.setPRNGSeed(123456789);
		//game.setRunSpeed(0.667);
		game.setRenderScale(1.75);
		game.start();
	}

}
