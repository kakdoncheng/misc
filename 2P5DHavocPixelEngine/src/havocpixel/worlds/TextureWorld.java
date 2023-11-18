package havocpixel.worlds;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import havocpixel.gfx.Assets;
import havocpixel.main.Handler;
import havocpixel.util.Utils;

public class TextureWorld extends World{

	protected BufferedImage world, background;
	public TextureWorld(Handler hdlr) {
		super(hdlr);
		// uses a bufferedimage texture to render world
		// void and grass tiles to denote bounds
	}
	
	public void render(Graphics g){
		if(world!=null)
			g.drawImage(world.getSubimage((int)hdlr.$camera().$xOffset(), (int)hdlr.$camera().$yOffset(), hdlr.$game().$width(), hdlr.$game().$height()), 0, 0, hdlr.$game().$width(), hdlr.$game().$height(), null);
		if(background!=null)
			g.drawImage(background.getSubimage((int)hdlr.$camera().$xOffset()/2, (int)hdlr.$camera().$yOffset()/2, hdlr.$game().$width(), hdlr.$game().$height()), 0, 0, hdlr.$game().$width(), hdlr.$game().$height(), null);
		for(LeaveArea u:area){
			u.render(g);
		}
		em.render(g);
		if(renderFog)
			renderFog(g);
		if(hdlr.showInv){
			hdlr.$inv().render(g);
		}
		hdlr.$player().renderQU(g);
		if(canInspect){
			g.setFont(new Font("Lucida Console", Font.BOLD, 12));
			Utils.drawStringWithOutline(g,"PRESS [T] TO INSPECT",(hdlr.$game().$width()/2)-(g.getFontMetrics().stringWidth("PRESS [T] TO INSPECT")/2), hdlr.$game().$height()-100+(22*3),Color.WHITE);
		}
		if(isTalking()){
			int h=100;
			g.setFont(new Font("ReservoirGrunge", Font.BOLD, 14));
			g.setColor(Color.BLACK);
			g.fillRect(10,hdlr.$game().$height()-h-39,g.getFontMetrics().stringWidth(voice)+20,26);
			g.setColor(Color.WHITE);
			g.drawRect(10,hdlr.$game().$height()-h-39,g.getFontMetrics().stringWidth(voice)+20,26);
			g.drawString(voice,20,hdlr.$game().$height()-h-43+7+16);
			if(setupTalk){
				g.setFont(new Font("Lucida Console", Font.BOLD, 14));
				for(int i=0;i<box.length;i++)
					box[i]="EMPTY";
				int si=0,ei=0,lim=hdlr.$game().$width()-40;
				String ti=dia;
				//System.out.println(ti.length());
				for(int i=0;i<ti.length();i++){
					//System.out.println(ti.substring(si,i));
					if(g.getFontMetrics().stringWidth(ti.substring(si,i))>lim){
						String di=ti.substring(si,i);
						//System.out.println(di);
						box[ei]=di;
						ei++;
						si=i;
						//System.out.println(si);
						for(int c=di.length()-1;c>0;c--){
							if(di.charAt(c)==' '&&g.getFontMetrics().stringWidth(di.substring(0,c))<=lim){
								box[ei-1]=di.substring(0,c);
								si-=(di.length()-c-1);
								//System.out.println(si);
								break;
							}
						}
					}
					if(ei>box.length-2){
						break;
					}
				}
				box[ei]=ti.substring(si,ti.length());
				//System.out.println(box[1]);
				endRow=ei+1;
				//System.out.println(endRow);
				//setup text here
				setupTalk=false;
			}
			//render text here
			g.setColor(Color.BLACK);
			g.fillRect(10,hdlr.$game().$height()-h-10,hdlr.$game().$width()-20,h);
			g.setColor(Color.WHITE);
			g.drawRect(10,hdlr.$game().$height()-h-10,hdlr.$game().$width()-20,h);
			
			//update index & string values
			//startRow=0,endRow=0,diaRow=0,diaInd=0;
			String[] te=new String[3];
			for(int i=0;i<3;i++){
				int k=startRow+i;
				if(box[k].equals("EMPTY")){
					te[i]="EMPTY";
					if(k==diaRow){
						diaRow++;
						diaInd=0;
					}
					continue;
				}
				if(k<diaRow){
					te[i]=box[k];
				}else if(k==diaRow){
					diaInd+=3;
					if(diaInd>=box[k].length()){
						te[i]=box[k];
						diaRow++;
						diaInd=0;
					}else{
						te[i]=box[k].substring(0,diaInd);
					}
				}else{
					te[i]="";
				}
				
			}
			//render text
			g.setFont(new Font("Lucida Console", Font.BOLD, 14));
			for(int i=0;i<3;i++){
				g.drawString(!te[i].equals("EMPTY")?te[i]:"",20,hdlr.$game().$height()-h+(22*i)+14);
			}
			if(diaRow<endRow){
				if(hdlr.$game().$tick()<30)
					g.drawString(">>",hdlr.$game().$width()-22-10,hdlr.$game().$height()-h+(22*3)+14);
			}
		}
		if(renderBlackScreen){
			Utils.drawTranslucentImage(Assets.control,0,0,((blackScreen>1)?1:blackScreen),hdlr.$game().$width(),hdlr.$game().$height(),g);
		}
	}

}
