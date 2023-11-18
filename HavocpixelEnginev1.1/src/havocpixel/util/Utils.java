package havocpixel.util;

import havocpixel.CoreLauncher;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Locale;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class Utils {
	public static String jvmPath=null;
	public static String jarPath=null;
	public static String jarName=null;
	private static String u;
	
	public static void whisperInit(){
		System.out.print("Initializing system.\n");
		try {
			try {
				jarPath=new File(CoreLauncher.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getAbsolutePath();
				jarName=new File(CoreLauncher.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getName();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally{
			jvmPath=System.getProperty("java.home");
		}
	}
	
	public static void init(){
		System.out.print("havocpixel.util.Utils:INFO: Calibrating system paths.\n");
		try {
			try {
				jarPath=new File(CoreLauncher.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getAbsolutePath();
				jarName=new File(CoreLauncher.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getName();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally{
			jvmPath=System.getProperty("java.home");
		}
	}
	static String uy="   ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖ×ØÙÚÛÜÝÞßðñòóôõö÷øùúûüýþÿ€µƒABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"#$%&\'()*+,-./:;<=>?@[\\]^_`{|}~‚„…†‡ˆ‰‹ŒŽ‘’“”•–—˜™š›œšžŸ¡¢£¤¥¦§¨©ª«¬­®¯°±²³´¶·¸¹º»¼½¾¿";
	public static String rndString(int k){
		String u="";
		for(int i=0;i<k;i++)
			u+=uy.charAt((int)(Math.random()*(uy.length())));
		return u;
	}
	public void delay(long ms){
		long d=ms*1000;
		long last=System.nanoTime();
		long now=last;
		while(now-last<d){
			now=System.nanoTime();
		}
		return;
	}
	public static void drawStringWithOutline(Graphics g,String text,int ux,int uy,Color c){
		g.setColor(Color.BLACK);
		g.drawString(text,ux,uy-1);
		g.drawString(text,ux-1,uy-1);
		g.drawString(text,ux-1,uy);
		g.drawString(text,ux-1,uy+1);
		g.drawString(text,ux+1,uy-1);
		g.drawString(text,ux+1,uy);
		g.drawString(text,ux+1,uy+1);
		g.drawString(text,ux,uy+1);
		g.setColor(c);
		g.drawString(text,ux,uy);
	}
	public static BufferedImage invertImage(BufferedImage img) {
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                int rgba = img.getRGB(x, y);
                Color col = new Color(rgba, true);
                if(col.getAlpha()!=0){
                	col = new Color(255 - col.getRed(),
                                	255 - col.getGreen(),
                                	255 - col.getBlue());
                }
                img.setRGB(x, y, col.getRGB());
            }
        }
        return img;
	}
	
	public static int newProcess(String cmd){
		Process p=null;
		int exit=0;
    	try {
    		try {
        		p=Runtime.getRuntime().exec(cmd);
        		Proxy se=new Proxy(p.getErrorStream(),System.err);
        		Proxy so=new Proxy(p.getInputStream(),System.out);
        		se.start();
        		so.start();
        		exit=p.waitFor();
        		System.out.println("Exit Code: "+exit);
        	} catch (Exception ex){
        		System.err.println("System Process Error: ");
        		ex.printStackTrace(System.err);
        		if(p!=null){
        			try{
        				p.destroy();
        			} catch (Exception e){
        				System.err.println("Error Terminating Process: "+e.getMessage());
        			}
        		}
        	}
    	} catch (Exception ex){
    		System.err.println("System Process Error: ");
    		ex.printStackTrace(System.err);
    		if(p!=null){
    			try{
    				p.destroy();
    			} catch (Exception e){
    				System.err.println("Error Terminating Process: "+e.getMessage());
    			}
    		}
    	} finally{
    		exit+=0;
    	}
    	return exit;
	}
	public static String fixws(String in){
		boolean alt=false;
		StringBuilder k=new StringBuilder(in.length());
		char a;
		try{
			try{
				for(int i=0;;i++){
					if(in.charAt(i)!='\\'){
						continue;
					}else{
						break;
					}
				}
			}catch(StringIndexOutOfBoundsException sie){
				alt=true;
			}
		}catch(StringIndexOutOfBoundsException sie){
			alt=true;
		}finally{
			a=alt?'/':'\\';
		}
		k.append(in);
		for(int u=0;u<k.length();u++){
			if(k.charAt(u)!=' '){
				continue;
			}else{
				for(int b=u;;b--){
					if(k.charAt(b)!=a){
						continue;
					}else{
						if(k.charAt(u+1)!='\"'){
							k.replace(b,b+1,"\\\"");
							for(int f=u;;f++){
								if(k.charAt(f)!=a){
									continue;
								}else{
									k.replace(f,f+1,"\"\\");
									break;
								}
							}
						}else{
							break;
						}
						break;
					}
				}
				u+=2;
			}
		}
		return k.toString();
	}
	public static String rawPath(String path){
		StringBuilder sb=new StringBuilder(path.length());
		sb.append(path);
		try{
			for(int i=path.length()-1;;i--){
				if(path.charAt(i)!='\\'){
					sb.deleteCharAt(i);
				}else{
					break;
				}
			}
		}catch(StringIndexOutOfBoundsException sie){
			sb.delete(0,sb.length());
			sb.append(path);
			for(int i=path.length()-1;;i--){
				if(path.charAt(i)!='/'){
					sb.deleteCharAt(i);
				}else{
					break;
				}
			}
		}
		return sb.toString();
	}
	public static void write(String in,String path,String name){
		path=rawPath(path);
		BufferedWriter k = null;
        try {
        	try {
                File file=new File(path+name);
                //System.out.println(path+name);
                k=new BufferedWriter(new FileWriter(file,true));
                k.write(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                k.close();
            } catch (Exception e) {
            }
        }
	}
	public static String[] loadProperties(){
		System.out.print("havocpixel.util.Utils:INFO: Loading config files;\n");
	    String[] dat = new String[6];
	    Properties prp = new Properties();
	    FileInputStream file = null;
	    String path = rawPath(fixws(jarPath))+"/blackjack.sav";
	    try {
			file = new FileInputStream(path);
			prp.load(file);
		    file.close();
		} catch (FileNotFoundException e) {
			System.out.print("havocpixel.util.Utils:WARNING: Config file not found.\n");
		} catch (IOException e) {
			System.out.print("havocpixel.util.Utils:INFO: IOException occured.\n");
			e.printStackTrace();
		}
	    dat[0]=prp.getProperty("value0");
	    dat[1]=prp.getProperty("value1");
	    dat[2]=prp.getProperty("value2");
	    dat[3]=prp.getProperty("value3");
	    dat[4]=prp.getProperty("value4");
	    dat[5]=prp.getProperty("value5");
	    return dat;
	}
	public static void export(File dst){
		try{
			/*
			int counter = 1;
			File folder = new File(dst+"/html"+counter);
			while(folder.exists()){
				folder = new File(dst+"/html"+counter);
				counter++;
			}
			folder.mkdir();*/
			BufferedInputStream bis=new BufferedInputStream(CoreLauncher.class.getResourceAsStream("/uassets.jar"));
			JarInputStream jis=new JarInputStream(bis);
			JarEntry je=null;
			while ((je=jis.getNextJarEntry())!=null) {
				File f=new File(dst.toString()+File.separator+je.getName());
				if(!f.exists()){
					if(je.isDirectory()){
						f.mkdirs();
					}
					FileOutputStream fos = new FileOutputStream(f);
					byte[] buf=new byte[1024];
					int len;
					while ((len=jis.read(buf))>0){
						fos.write(buf,0,len);
					}
					fos.close(); 
				}else{
					System.out.print("havocpixel.util.Utils:WARNING: Exported file already exists.\n");
				}
			}
			bis.close();
			jis.close();
		}catch(Exception exc){
			exc.printStackTrace();
	    }
	}
	public static String getOSType(){
		if (u==null) {
			String ui=System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
			if((ui.indexOf("mac")>=0)||(ui.indexOf("darwin")>=0)) {
				u="MacOS";
			}else if(ui.indexOf("win")>=0){
				u="NT";
			}else if(ui.indexOf("nux")>=0){
				u="Unix";
			}else{
				u="def";
			}
		}
	    return u;
	}
	public String loadFileFromClassAsString(String path) {
		StringBuilder k = new StringBuilder();
		//InputStream file;
		URL url = this.getClass().getResource(path);
		try {
			//file = this.getClass().getResourceAsStream(path);
			InputStream stream = url.openStream();
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(stream));
				String line;
				while((line=br.readLine())!=null) {
					k.append(line);
				}
				br.close();
				
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			return k.toString();
		} catch (Exception e) {
			System.out.print("havocpixel.util.Utils:WARNING: Failed to load resource from class.\n");
			return null;
		}
	}
	public static String loadFileAsString(String path) {
		StringBuilder k = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while((line=br.readLine())!=null) {
				k.append(line+"\n");
			}
			br.close();
			
		} catch (IOException ioe) {
			System.out.print("havocpixel.util.Utils:WARNING: Failed to load file.\n");
		}
		return k.toString();
	}
	public static int parseInt(String n) {
		try {
			return Integer.parseInt(n);
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
			return 0;
		}
	}
	
	/*
	public static void decreaseAlpha(BufferedImage img, int opacity){
		    int alfaDecrease = 255-opacity;
		    for(int x=0; x<img.getWidth(); x++)
		        for(int y=0; y<img.getHeight(); y++)
		        {
		            int pixel = img.getRGB(x, y);
		            int red = getR(pixel); //getXs are local static methods, that exctract the X - component from the pixel value
		            int green = getG(pixel);
		            int blue = getB(pixel);
		            int alpha = getA(pixel) - alfaDecrease;
		            if(alpha < 0) alpha = 0;
		            else if(alpha > 255) alpha = 255;
		            Color color = new Color(red,green,blue,alpha);
		            img.setRGB(x, y, color.getRGB());
		        }
		}*/
	public static BufferedImage setAlpha(BufferedImage img, byte alpha) {       
	    alpha %= 0xff; 
	    BufferedImage median=new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_4BYTE_ABGR);
	    BufferedImage newImg=createTranslucentImage(img.getWidth(),img.getHeight());
	    for (int cx=0;cx<img.getWidth();cx++) {          
	        for (int cy=0;cy<img.getHeight();cy++) {
	            int color = img.getRGB(cx, cy);
	            int mc = (alpha << 24) | 0x00ffffff;
	            int newcolor = color & mc;
	            median.setRGB(cx, cy, newcolor);            
	        }
	    }
	    newImg.getGraphics().drawImage(median,0,0,null);
	    return newImg;
	}
	public static BufferedImage createTranslucentImage(int width, int height) {
		return GraphicsEnvironment.getLocalGraphicsEnvironment().
		           getDefaultScreenDevice().getDefaultConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
	}
	public static void drawTranslucentImage(BufferedImage image, int x, int y,float alpha, int w,int h,Graphics g){
		if(alpha<0)
			alpha=0;
		if(alpha>1)
			alpha=1;
		Graphics2D g2d = (Graphics2D)g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));
		g2d.drawImage(image,x,y,w,h,null);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1.0F));
	}
	public static BufferedImage rotate(BufferedImage image, int deg){
		AffineTransform xform = new AffineTransform();
		boolean u=true;
		end:{
			if(u)
				break end;
			if (image.getWidth() > image.getHeight()){
				xform.setToTranslation(0.5 * image.getWidth(), 0.5 * image.getWidth());
				xform.rotate(Math.toRadians(deg));

				int diff = image.getWidth() - image.getHeight();

				switch (deg){
					case 90:
						xform.translate(-0.5 * image.getWidth(), -0.5 * image.getWidth() + diff);
						break;
					case 180:
						xform.translate(-0.5 * image.getWidth(), -0.5 * image.getWidth() + diff);
						break;
					default:
						xform.translate(-0.5 * image.getWidth(), -0.5 * image.getWidth());
						break;
				}
			}else if (image.getHeight() > image.getWidth()){
				xform.setToTranslation(0.5 * image.getHeight(), 0.5 * image.getHeight());
				xform.rotate(Math.toRadians(deg));
				int diff = image.getHeight() - image.getWidth();

				switch (deg){
					case 180:
						xform.translate(-0.5 * image.getHeight() + diff, -0.5 * image.getHeight());
		      			break;
					case 270:
						xform.translate(-0.5 * image.getHeight() + diff, -0.5 * image.getHeight());
						break;
					default:
						xform.translate(-0.5 * image.getHeight(), -0.5 * image.getHeight());
		      			break;
				}
			}else{
				xform.setToTranslation(0.5 * image.getWidth(), 0.5 * image.getHeight());
				xform.rotate(Math.toRadians(deg));
				xform.translate(-0.5 * image.getHeight(), -0.5 * image.getWidth());
			}
		}
		xform.setToTranslation(0.5 * image.getWidth(), 0.5 * image.getHeight());
		xform.rotate(Math.toRadians(deg));
		xform.translate(-0.5 * image.getHeight(), -0.5 * image.getWidth());
		AffineTransformOp op = new AffineTransformOp(xform, AffineTransformOp.TYPE_BILINEAR);
		return op.filter(image, null);
	}
	
	public static Image makeColorTransparent(Image im, final Color color) {
		ImageFilter filter = new RGBImageFilter() {
			// the color we are looking for... Alpha bits are set to opaque
			public int markerRGB = color.getRGB() | 0xFF000000;

			@Override
			public final int filterRGB(int x, int y, int rgb) {
				if ((rgb | 0xFF000000) == markerRGB) {
					// Mark the alpha bits as zero - transparent
					return 0x00FFFFFF & rgb;
				} else {
					// nothing to do
					return rgb;
				}
			}
		};

		ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
		return Toolkit.getDefaultToolkit().createImage(ip);
	}

	public static BufferedImage makeImageTranslucent(BufferedImage source, double alpha) {
	    BufferedImage target = new BufferedImage(source.getWidth(), source.getHeight(), java.awt.Transparency.TRANSLUCENT);
	    // Get the images graphics
	    Graphics2D g = target.createGraphics();
	    // Set the Graphics composite to Alpha
	    g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) alpha));
	    // Draw the image into the prepared reciver image
	    g.drawImage(source, null, 0, 0);
	    // let go of all system resources in this Graphics
	    g.dispose();
	    // Return the image
	    return target;
	}
}
