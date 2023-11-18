package havocpixel.amb;

import havocpixel.CoreLauncher;

import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class LoopingAudioPlayer {
	
	public static void playMusic(String in){
		InputStream raw;
		try{
			raw=CoreLauncher.class.getResourceAsStream(in);
			AudioInputStream music=AudioSystem.getAudioInputStream(raw);
			Clip clip=AudioSystem.getClip();
			clip.open(music);
			clip.start();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
