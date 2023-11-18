package main;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MusicBox implements KeyListener{
	boolean[] keys;
	public MusicBox(){
		keys=new boolean[256];
	}
	public BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(MusicBox.class.getResource(path));
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	public AudioInputStream convertToPCM(AudioInputStream audioInputStream){
		AudioFormat m_format = audioInputStream.getFormat();

        if ((m_format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) &&
            (m_format.getEncoding() != AudioFormat.Encoding.PCM_UNSIGNED))
        {
            AudioFormat targetFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                m_format.getSampleRate(), 16,
                m_format.getChannels(), m_format.getChannels() * 2,
                m_format.getSampleRate(), m_format.isBigEndian());
            audioInputStream = AudioSystem.getAudioInputStream(targetFormat, audioInputStream);
        }
        return audioInputStream;
	}
	public void playSound(String path) {
		System.out.print(path.length()==7?path.substring(1,3)+"~":path.substring(1,4));
		try {
			Clip clip = AudioSystem.getClip();
			InputStream audioSrc = MusicBox.class.getResourceAsStream(path);
			InputStream bufferedIn = new BufferedInputStream(audioSrc);
	        AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
	        clip.addLineListener(new LineListener() {
	            public void update(LineEvent event) {
	                if (event.getType()==LineEvent.Type.STOP)
	                    clip.close();
	            }
	        });
	        
	        clip.open(audioStream);
	        clip.start();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MusicBox box=new MusicBox();
		
		JFrame frame;
		frame = new JFrame("waht");
		frame.setSize(200,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setBackground(Color.BLACK);
		frame.addKeyListener(box);
		
		BufferedImage img=box.loadImage("/default64x64.png");
		
		frame.getContentPane().setLayout(new FlowLayout());
		frame.getContentPane().add(new JLabel(new ImageIcon(img)));
		//frame.pack();
		frame.setVisible(true);
		
		//lp.playSound("/1ab.wav");
		box.playTrack(box.track1, (int)(0.4*1000000000));
		while(true){
			box.tick();
		}

	}
	
	String chord1m="2a~2bb1g~2a~1fb1g~1e~1fb";
	String chord2m="1g~2a~1fb1g~1e~1fb1d~1e~";
	String chord3m="1fb1g~1e~1fb1d~1e~1fb1d~";
	String chord4m="1e~---------------2db---2d~";
	String chord5m="---------------------------------";
	
	String chord1b="0d~1a~1d~------------0a~";
	String chord2b="0db1a~1db------------0a~";
	String chord3b="0c~0g~1c~------------0g~";
	String chord4b="0b~0g~1b~------------0g~";
	String chord5b="0bb0fb1bb------------0fb";
	String chord6b="0a~0e~1a~1bb0g~---1db---0d~";
	String chord7b="1bb0g~1a~0fb0g~0e~0fb1a~---------";
	
	String track1=chord1m+chord1m+chord1m+chord1m+chord2m+chord2m+chord3m+chord4m+chord5m+","+
				  chord1b+chord1b+chord2b+chord2b+chord3b+chord4b+chord5b+chord6b+chord7b;
	
	
	String chord1m1="2ab---------------------------------------------------2ab---2bb---2c~---2db---2eb---2db---2c~---";
	String chord1n1="1fb---------------------------------------------------1fb---2ab---2bb---2bb---2db---2bb---2bb---";
	String chord1b1="1fb---1fb---1fb---1fb---1fb---1fb---1fb---1fb---1fb---1fb---1fb---1fb---1fb---1fb---1fb---1fb---";
	
	String chord2m1="2ab---------------1g~2ab1g~---------------------------1g~---2ab---2bb---2c~---2bb---2ab---1g~---";
	String chord2n1="1db---------------1fb1db1fb---------------------------1fb---1db---2ab---2bb---2ab---1db---1fb---";
	String chord2b1="1db---1db---1db---1db---1db---1db---1db---1db---1db---1db---1db---1db---1db---1db---1db---1db---";
	String chord3b1="1eb---1eb---1eb---1eb---1eb---1eb---1eb---1eb---1eb---1eb---1eb---1eb---1eb---1eb---1eb---1eb---";
	String chord4b1="1fb---1fb---1fb---1fb---1eb---1eb---1eb---1eb---1eb---1eb---1eb---1eb---1eb---1eb---1eb---1eb---";
	
	String track2=chord1m1+chord2m1+chord1m1+chord2m1+","+
				  chord1n1+chord2n1+chord1n1+chord2n1+","+
				  chord1b1+chord2b1+chord3b1+chord4b1;
	
	public void playTrack(String track, int delay){
		String[] lines=track.split(",");
		System.out.println("Track loaded. "+lines[0].length()/3+" frames long.");
		for(String s : lines){
			System.out.println(s);
		}
		System.out.println();
		new Thread(new Runnable(){
    	    public void run(){
    	    	while(true)
    	    	for(int frame=0;frame<lines[0].length();frame+=3){
    				delay(delay);
    				for(String s : lines){
    					String note=s.substring(frame,frame+3);
    					if(note.equals("---"))
    						continue;
    					System.out.print(frame/3+":");
    					playSound("/"+(note.charAt(2)!='~'?note:note.substring(0,2))+".wav");
    					System.out.println();
    				}
    				
    			}
    	    }
    	}).start();
		
	}
	
	public void delay(long nano){
		long last=System.nanoTime();
		while(System.nanoTime()-last<(nano))
			continue;
	}

	public void tick(){
		
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		if(!keys[arg0.getKeyCode()]){
			keys[arg0.getKeyCode()]=true;
			if(arg0.getKeyCode()==KeyEvent.VK_Q){
				playSound("/1a.wav");
			}else if(arg0.getKeyCode()==KeyEvent.VK_W){
				playSound("/1b.wav");
			}else if(arg0.getKeyCode()==KeyEvent.VK_E){
				playSound("/1c.wav");
			}else if(arg0.getKeyCode()==KeyEvent.VK_R){
				playSound("/1d.wav");
			}else if(arg0.getKeyCode()==KeyEvent.VK_T){
				playSound("/1e.wav");
			}else if(arg0.getKeyCode()==KeyEvent.VK_Y){
				playSound("/1f.wav");
			}else if(arg0.getKeyCode()==KeyEvent.VK_U){
				playSound("/1g.wav");
			}else if(arg0.getKeyCode()==KeyEvent.VK_I){
				playSound("/2a.wav");
			}else if(arg0.getKeyCode()==KeyEvent.VK_O){
				playSound("/2b.wav");
			}else if(arg0.getKeyCode()==KeyEvent.VK_P){
				playSound("/2c.wav");
			}
			else if(arg0.getKeyCode()==KeyEvent.VK_1){
				playSound("/1ab.wav");
			}else if(arg0.getKeyCode()==KeyEvent.VK_2){
				playSound("/1bb.wav");
			}else if(arg0.getKeyCode()==KeyEvent.VK_4){
				playSound("/1db.wav");
			}else if(arg0.getKeyCode()==KeyEvent.VK_5){
				playSound("/1eb.wav");
			}else if(arg0.getKeyCode()==KeyEvent.VK_6){
				playSound("/1fb.wav");
			}else if(arg0.getKeyCode()==KeyEvent.VK_8){
				playSound("/2ab.wav");
			}else if(arg0.getKeyCode()==KeyEvent.VK_9){
				playSound("/2bb.wav");
			}
			
			else if(arg0.getKeyCode()==KeyEvent.VK_Z){
				playSound("/2a.wav");
			}else if(arg0.getKeyCode()==KeyEvent.VK_X){
				playSound("/2b.wav");
			}else if(arg0.getKeyCode()==KeyEvent.VK_C){
				playSound("/2c.wav");
			}else if(arg0.getKeyCode()==KeyEvent.VK_V){
				playSound("/2d.wav");
			}else if(arg0.getKeyCode()==KeyEvent.VK_B){
				playSound("/2e.wav");
			}else if(arg0.getKeyCode()==KeyEvent.VK_N){
				playSound("/2f.wav");
			}else if(arg0.getKeyCode()==KeyEvent.VK_M){
				playSound("/2g.wav");
			}
			else if(arg0.getKeyCode()==KeyEvent.VK_A){
				playSound("/2ab.wav");
			}else if(arg0.getKeyCode()==KeyEvent.VK_S){
				playSound("/2bb.wav");
			}else if(arg0.getKeyCode()==KeyEvent.VK_F){
				playSound("/2db.wav");
			}else if(arg0.getKeyCode()==KeyEvent.VK_G){
				playSound("/2eb.wav");
			}else if(arg0.getKeyCode()==KeyEvent.VK_H){
				playSound("/2fb.wav");
			}
			else if(arg0.getKeyCode()==KeyEvent.VK_SPACE){
				System.out.print("---");
			}else if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
				System.out.println();
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		keys[arg0.getKeyCode()]=false;
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

}
