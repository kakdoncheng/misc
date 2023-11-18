package havocpixel.amb;

import havocpixel.CoreLauncher;

import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javazoom.jl.player.advanced.AdvancedPlayer;

public class AmbPlayer {
	
	//outdated player, do not use
	private static Clip clip=null;
	private static InputStream audioSrc=null;
	private static InputStream bufferedIn=null;
	private static AudioInputStream audioStream=null;

	public static void playWav(String in){
		try{
			clip=AudioSystem.getClip();
			audioSrc=CoreLauncher.class.getResourceAsStream(in);
			bufferedIn=new BufferedInputStream(audioSrc);
			audioStream=AudioSystem.getAudioInputStream(bufferedIn);
			clip=AudioSystem.getClip();
			clip.open(audioStream);
			clip.start();
		}catch (Exception e){
			System.err.println(e.getMessage());
		}
	}
	public static void stopAudioWav(){
		clip.stop();
		while(clip.isRunning())
			clip.stop();
		clip.flush();
		clip.close();
	}
	private static AdvancedPlayer player=null;
	static Thread audio=null;
	static boolean playing=false;
	public static void playMp3Loop(String in){
		playing=true;
		audio=new Thread(new Runnable(){
			public void run(){
				try {
					while(playing){
						audioSrc=CoreLauncher.class.getResourceAsStream(in);
						player=new AdvancedPlayer(audioSrc);
						player.play();
						player.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		audio.start();
	}
	public static void playMp3(String in){
		playing=true;
		audio=new Thread(new Runnable(){
			public void run(){
				try {
					audioSrc=CoreLauncher.class.getResourceAsStream(in);
					player=new AdvancedPlayer(audioSrc);
					player.play();
					player.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		audio.start();
	}
	public static void stopMp3(){
		playing=false;
		if(player!=null)
			player.close();
		try {
			if(audio!=null&&audio.isAlive())
			audio.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
