package havocpixel;

import havocpixel.entities.basic.DebugPlayer;

import java.awt.Graphics;

public class EmptyState extends State{

	double x=0,y=0,currentThres=0,nextThres=0;
	private boolean panCamera=false, spawned=false;
	public EmptyState(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(double dt) {
		currentThres+=dt;
		if(currentThres>nextThres){
			game.$currentWorld().spawnRandomMonster();
			nextThres=game.$randomDouble(0.25, 0.5);
			currentThres=0;
		}
		game.$currentWorld().update(dt);
		if(panCamera){
			if(game.$km().up){
				y-=180*dt;
			}
			if(game.$km().down){
				y+=180*dt;
			}
			if(game.$km().left){
				x-=180*dt;
			}
			if(game.$km().right){
				x+=180*dt;
			}
		}else if(!spawned){
			//game.$currentWorld().spawnEntityInWorld(new Jayde(game,0,0));
			game.$currentWorld().spawnEntityInWorld(new DebugPlayer(game,0,0));
			spawned=true;
		}
		
	}

	@Override
	public void render(Graphics g) {
		//g.setColor(new Color(0, 180, 0));
		//g.fillRect(0, 0, game.$width(), game.$height());
		game.$currentWorld().render(g);
		if(panCamera){
			game.$camera().setxOffset(x);
			game.$camera().setyOffset(y);
		}
		
		//if(game.$currentSecond()<0.25){
		//	g.drawImage(CoreAssets.defaultTile[0],(int)x,(int)y,32,32,null);
		//}else if(game.$currentSecond()<0.5){
		//	g.drawImage(CoreAssets.defaultTile[1],(int)x,(int)y,32,32,null);
		//}else if(game.$currentSecond()<0.75){
		//	g.drawImage(CoreAssets.defaultTile[2],(int)x,(int)y,32,32,null);
		//}else{
		//	g.drawImage(CoreAssets.defaultTile[3],(int)x,(int)y,32,32,null);
		//}
	}

}
