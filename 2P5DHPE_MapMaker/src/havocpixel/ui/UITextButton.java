package havocpixel.ui;

import havocpixel.main.Handler;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class UITextButton extends UIObject{

	private String label;
	private boolean boundsSet=false;
	private int fs;
	private ClickListener cl;
	public UITextButton(Handler hdlr,float x,float y,int w,int h,int fontSize,String label,ClickListener cl) {
		super(hdlr,x, y, w, h);
		this.label=label;
		this.cl=cl;
		this.fs=fontSize;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		if(!boundsSet){
			this.bounds=new Rectangle((int)(x-g.getFontMetrics().stringWidth(label))*hdlr.$game().$renderScale(),(int)(y-g.getFontMetrics().getHeight())*hdlr.$game().$renderScale(),
					g.getFontMetrics().stringWidth(label)*2*hdlr.$game().$renderScale(),g.getFontMetrics().getHeight()*hdlr.$game().$renderScale());
			boundsSet=true;
		}
		g.setColor(Color.WHITE);
		g.setFont(new Font("ReservoirGrunge", Font.BOLD, fs));
		if(!hover)
			g.drawString(label,(int)x-g.getFontMetrics().stringWidth(label)/2,(int)y);
		else
			g.drawString("> "+label+" <",(int)x-g.getFontMetrics().stringWidth("> "+label+" <")/2,(int)y);
	}

	@Override
	public void onClick() {
		cl.onClick();
	}

}
