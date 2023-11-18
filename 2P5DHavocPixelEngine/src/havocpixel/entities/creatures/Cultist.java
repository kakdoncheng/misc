package havocpixel.entities.creatures;

import havocpixel.gfx.Animation;
import havocpixel.gfx.Assets;
import havocpixel.main.Handler;

public class Cultist extends PossessedGrunt{

	public Cultist(Handler hdlr, float x, float y, String label0) {
		super(hdlr, x, y, label0);

		death=new Animation(hdlr,200,Assets.cphDeath0);
		spawn=new Animation(hdlr,120,Assets.cphSpawn0);
		
		su=new Animation(hdlr,120,Assets.cphUp0);
		sl=new Animation(hdlr,120,Assets.cphLeft0);
		sd=new Animation(hdlr,120,Assets.cphDown0);
		sr=new Animation(hdlr,120,Assets.cphRight0);
		sui=new Animation(hdlr,240,Assets.cphiU0);
		sli=new Animation(hdlr,240,Assets.cphiL0);
		sdi=new Animation(hdlr,240,Assets.cphiD0);
		sri=new Animation(hdlr,240,Assets.cphiR0);
		
		asu=new Animation(hdlr,70,Assets.cpAhUp0);
		asl=new Animation(hdlr,70,Assets.cpAhLeft0);
		asd=new Animation(hdlr,70,Assets.cpAhDown0);
		asr=new Animation(hdlr,70,Assets.cpAhRight0);
		
		su0=new Animation(hdlr,120,Assets.cphUp0);
		sl0=new Animation(hdlr,120,Assets.cphLeft0);
		sd0=new Animation(hdlr,120,Assets.cphDown0);
		sr0=new Animation(hdlr,120,Assets.cphRight0);
		sui0=new Animation(hdlr,240,Assets.cphiU0);
		sli0=new Animation(hdlr,240,Assets.cphiL0);
		sdi0=new Animation(hdlr,240,Assets.cphiD0);
		sri0=new Animation(hdlr,240,Assets.cphiR0);
		asu0=new Animation(hdlr,70,Assets.cpAhUp0);
		asl0=new Animation(hdlr,70,Assets.cpAhLeft0);
		asd0=new Animation(hdlr,70,Assets.cpAhDown0);
		asr0=new Animation(hdlr,70,Assets.cpAhRight0);
		helmeted=false;
		
		altSpawn=Assets.cphDeath0;
		
		hU=Assets.cphiU0[1];
		hL=Assets.cphiL0[1];
		hD=Assets.cphiD0[1];
		hR=Assets.cphiR0[1];
		shU=hU;
		shL=hL;
		shD=hD;
		shR=hR;
		if(helmeted){
			this.AC=35;
		}else{
			this.AC=20;
		}
		//spawning=false;
		//armored=false;
		//possessed=true;
	}

}
