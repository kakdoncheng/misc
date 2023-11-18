package havocpixel.gfx;

import havocpixel.Launcher;
import havocpixel.Timer;
import havocpixel.util.Utils;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Assets {
	public static final int px=32;
	public static BufferedImage def,grass,moss,wall,stone,brick,nul,cobble,hurt,hurt1,hurt2;
	public static BufferedImage testLevel;
	public static BufferedImage[][] textures;
			
	public static BufferedImage[] playerUp,playerLeft,playerDown,playerRight,playerIdle;
	public static BufferedImage[] npcUp,npcLeft,npcDown,npcRight,npcIdle;
	public static BufferedImage[] npc1Up,npc1Left,npc1Down,npc1Right,npc1Idle;
	public static BufferedImage[] sansUp,sansLeft,sansDown,sansRight,sansIdle;
	public static BufferedImage sansHurt;
	
	//bunny
	public static BufferedImage[] bUp,bLeft,bDown,bRight,bDeath;
	public static BufferedImage[] biU,biL,biD,biR;
	//armored skeleton
	public static BufferedImage[] sUp,sLeft,sDown,sRight,AsUp,AsLeft,AsDown,AsRight,sIdle;
	public static BufferedImage[] siU,siL,siD,siR;
	public static BufferedImage shU,shL,shD,shR;
	//basic skeleton
	public static BufferedImage[] sUp0,sLeft0,sDown0,sRight0,AsUp0,AsLeft0,AsDown0,AsRight0,sIdle0;
	public static BufferedImage[] siU0,siL0,siD0,siR0;
	public static BufferedImage shU0,shL0,shD0,shR0;
	//grave skeleton
	public static BufferedImage[] gsUp0,gsLeft0,gsDown0,gsRight0,gAsUp0,gAsLeft0,gAsDown0,gAsRight0,gsIdle0;
	public static BufferedImage[] gsiU0,gsiL0,gsiD0,gsiR0,emerge,emerge0;
	public static BufferedImage gshU0,gshL0,gshD0,gshR0;
	//archer skeleton
	public static BufferedImage[] agsUp0,agsLeft0,agsDown0,agsRight0,agAsUp0,agAsLeft0,agAsDown0,agAsRight0,agsIdle0;
	public static BufferedImage[] agsiU0,agsiL0,agsiD0,agsiR0,aemerge;
	public static BufferedImage agshU0,agshL0,agshD0,agshR0;
	//gasterblaster
	public static BufferedImage[] gbu,gbl,gbd,gbr;
	public static BufferedImage bu0,bl0,bd0,br0,bu1,bl1,bd1,br1;
	//human grunt
	public static BufferedImage[] hUp,hLeft,hDown,hRight,AhUp,AhLeft,AhDown,AhRight,hIdle;
	public static BufferedImage[] hiU,hiL,hiD,hiR,hDeath;
	public static BufferedImage hhU,hhL,hhD,hhR;
	//armored human grunt
	public static BufferedImage[] hUp0,hLeft0,hDown0,hRight0,AhUp0,AhLeft0,AhDown0,AhRight0,hIdle0;
	public static BufferedImage[] hiU0,hiL0,hiD0,hiR0,hDeath0;
	public static BufferedImage hhU0,hhL0,hhD0,hhR0,shhU0,shhL0,shhD0,shhR0;
	//possessed human grunt
	public static BufferedImage[] phUp,phLeft,phDown,phRight,pAhUp,pAhLeft,pAhDown,pAhRight,phIdle;
	public static BufferedImage[] phiU,phiL,phiD,phiR,phDeath,phSpawn;
	public static BufferedImage phhU,phhL,phhD,phhR;
	//possessed human grunt0 alt
	public static BufferedImage[] phUp0,phLeft0,phDown0,phRight0,pAhUp0,pAhLeft0,pAhDown0,pAhRight0,phIdle0;
	public static BufferedImage[] phiU0,phiL0,phiD0,phiR0,phDeath0,phSpawn0;
	public static BufferedImage phhU0,phhL0,phhD0,phhR0;
	//possessed alt2
	public static BufferedImage[] phUp01,phLeft01,phDown01,phRight01,pAhUp01,pAhLeft01,pAhDown01,pAhRight01,phIdle01;
	public static BufferedImage[] phiU01,phiL01,phiD01,phiR01,phDeath01,phSpawn01;
	public static BufferedImage phhU01,phhL01,phhD01,phhR01;
	//cultist
	public static BufferedImage[] cphUp0,cphLeft0,cphDown0,cphRight0,cpAhUp0,cpAhLeft0,cpAhDown0,cpAhRight0,cphIdle0;
	public static BufferedImage[] cphiU0,cphiL0,cphiD0,cphiR0,cphDeath0,cphSpawn0;
	public static BufferedImage cphhU0,cphhL0,cphhD0,cphhR0;
	//imp
	public static BufferedImage[] dUp,dLeft,dDown,dRight,AdUp,AdLeft,AdDown,AdRight,dIdle;
	public static BufferedImage[] diU,diL,diD,diR,dDeath,dSpawn;
	public static BufferedImage dhU,dhL,dhD,dhR;
	//pinky
	public static BufferedImage[] pdUp,pdLeft,pdDown,pdRight,pAdUp,pAdLeft,pAdDown,pAdRight,pdIdle;
	public static BufferedImage[] pdiU,pdiL,pdiD,pdiR,pdDeath,pdSpawn;
	public static BufferedImage pdhU,pdhL,pdhD,pdhR;
	//tombstone0
	public static BufferedImage s0,s1,s2,sh0,sh1,sh2,tgib0,tgib1,mb;
	public static BufferedImage s00,s01,s02,sh00,sh01,sh02;
	public static BufferedImage s10,s11,s12,sh10,sh11,sh12;
	public static BufferedImage s20,s21,s22,sh20,sh21,sh22;
	//forest
	public static BufferedImage t0,th0,t1,th1;
	public static BufferedImage t2,th2,t3,th3;
	public static BufferedImage g0,g1,g2,g3,g4,log0;
	public static BufferedImage r0,r1,r2,st0;
	//thrown knife
	public static BufferedImage[] pl,pr;
	public static BufferedImage ku,kl,kd,kr;
	public static BufferedImage au,al,ad,ar,rice,hotdog,rFlesh,adrenal,milk,hpot;
	//explosion
	public static BufferedImage[] boom;
	//pumpkin
	public static BufferedImage p0,ph0,p1,p2,ph1;
	//grenade
	public static BufferedImage gren,fireball,fireball0,wand0;
	//inv
	public static BufferedImage inventory,fog0,control;
	//smoke
	public static BufferedImage[] smoke;
	//gibs
	public static BufferedImage gib0,gib1,gib2,gib3,gibah,gibas,dGib0,dGib1,dGib2,dGibarm,dGibleg,dGibrib;
	//ghost
	public static BufferedImage gu,gl,gd,gr,gAu,gAl,gAd,gAr,ghu,ghl,ghr,gu2,gl2,gd2,gr2,gAu2,gAl2,gAd2,gAr2,ghu2,ghl2,ghr2,gu01,gl01,gd01,gr01,gAu01,gAl01,gAd01,gAr01,ghu01,ghl01,ghr01,gu012,gl012,gd012,gr012,gAu012,gAl012,gAd012,gAr012,ghu012,ghl012,ghr012,rage;
	//incubus
	public static BufferedImage[] iDeath;
	public static BufferedImage gu0,gl0,gd0,gr0,gAu0,gAl0,gAd0,gAr0,ghu0,ghl0,ghr0,bGib;
	//lootcrate
	public static BufferedImage[] uc,dc,rc,lc;
	public static BufferedImage wgib0,wgib1;
	//bear trap
	public static BufferedImage[] bt0;
	public static BufferedImage gbt0,gbt1,gbt2,gbt3;
	//fire
	public static BufferedImage[] fire,gore,gore0;
	//windslash
	public static BufferedImage w0,w1,w2,w3,w02,w03;
	
	public static void load() {
		System.out.print("["+Timer.time()+"] Checking Fonts;\n");
		String fonts[]=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		//for(int i=0;i<fonts.length;i++)System.out.println(fonts[i]);
		boolean exist=false;
		for(String u:fonts)
			if(u.equals("ReservoirGrunge"))
				exist=true;
		if(!exist){
			System.out.print("["+Timer.time()+"] Registering Fonts;\n");
			InputStream is = Launcher.class.getResourceAsStream("/RESEGRG_.TTF");
			Font uniFont = null;
			try {
				uniFont = Font.createFont(Font.TRUETYPE_FONT,is);
			} catch (FontFormatException | IOException e) {
				e.printStackTrace();
			}
			Font f = uniFont.deriveFont(24f);
			GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(f);
		}
		System.out.print("["+Timer.time()+"] Loading Textures;\n");
		Textures def0=new Textures(ImageLoader.loadImage("/txr/default.png"));
		Textures controls=new Textures(ImageLoader.loadImage("/txr/controls0.png"));
		System.out.println(Utils.jarPath+"\\txr0.png");
		Textures txr0=new Textures(ImageLoader.loadImageFromFile(Utils.rawPath(Utils.jarPath)+"txr0.png"));
		Textures player=new Textures(ImageLoader.loadImage("/txr/grim_reaper.png"));
		Textures npc=new Textures(ImageLoader.loadImage("/txr/sheet.png"));
		Textures sans=new Textures(ImageLoader.loadImage("/txr/sans0.png"));
		Textures skel=new Textures(ImageLoader.loadImage("/txr/armor_skel.png"));
		Textures skel0=new Textures(ImageLoader.loadImage("/txr/skel.png"));
		Textures gskel0=new Textures(ImageLoader.loadImage("/txr/altskeleton.png"));
		Textures agskel0=new Textures(ImageLoader.loadImage("/txr/arch_skel.png"));
		Textures gb=new Textures(ImageLoader.loadImage("/txr/gb.png"));
		Textures tomb=new Textures(ImageLoader.loadImage("/txr/tomb.png"));
		Textures inv=new Textures(ImageLoader.loadImage("/txr/inv.png"));
		Textures human0=new Textures(ImageLoader.loadImage("/txr/grunt.png"));
		Textures human01=new Textures(ImageLoader.loadImage("/txr/armored_grunt.png"));
		Textures phuman0=new Textures(ImageLoader.loadImage("/txr/dead_grunt.png"));
		Textures phuman01=new Textures(ImageLoader.loadImage("/txr/dead_grunt0.png"));
		Textures phuman1=new Textures(ImageLoader.loadImage("/txr/possessed1.png"));
		Textures c0=new Textures(ImageLoader.loadImage("/txr/cultist.png"));
		Textures imp=new Textures(ImageLoader.loadImage("/txr/imp.png"));
		Textures pinky=new Textures(ImageLoader.loadImage("/txr/pinky.png"));
		Textures ghost=new Textures(ImageLoader.loadImage("/txr/ghost.png"));
		Textures ghost0=new Textures(ImageLoader.loadImage("/txr/ghost0.png"));
		Textures ghost01=new Textures(ImageLoader.loadImage("/txr/ghost01.png"));
		Textures ghost2=new Textures(ImageLoader.loadImage("/txr/ghost2.png"));
		Textures inc=new Textures(ImageLoader.loadImage("/txr/incubus.png"));
		Textures rage0=new Textures(ImageLoader.loadImage("/txr/hurt_raw.png"));
		Textures tr0=new Textures(ImageLoader.loadImage("/txr/trap.png"));
		Textures fog=new Textures(ImageLoader.loadImage("/txr/fog.png"));
		Textures forest=new Textures(ImageLoader.loadImage("/txr/forest.png"));
		Textures lvl0=new Textures(ImageLoader.loadImage("/txr/test.png"));
		Textures fi=new Textures(ImageLoader.loadImage("/txr/fire.png"));
		Textures bun=new Textures(ImageLoader.loadImage("/txr/bunny.png"));
		Textures hurt0=new Textures(ImageLoader.loadImage("/txr/hurt.png"));
		Textures hurt01=new Textures(ImageLoader.loadImage("/txr/hurt_.png"));
		Textures hurt02=new Textures(ImageLoader.loadImage("/txr/hurt2.png"));
		
		System.out.print("["+Timer.time()+"] Loading Animations;\n");
		rage=rage0.crop(0,0,750,460);
		hurt=hurt0.crop(0,0,750,460);
		hurt1=hurt01.crop(0,0,750,460);
		hurt2=hurt02.crop(0,0,750,460);
		control=controls.crop(0,0,750,460);
		fog0=fog.crop(0,0,512,512);
		testLevel=lvl0.crop();
		
		fire=new BufferedImage[8];
		for(int i=0;i<8;i++)
			fire[i]=fi.crop(px*i, px*0, px, px);
		
		//forest
		t2=forest.crop(px*4, px*0, px*2, px*3);
		t3=forest.crop(px*6, px*0, px*2, px*3);
		th2=forest.crop(px*0, px*5, px*2, px*3);
		th3=forest.crop(px*2, px*5, px*2, px*3);
		g0=forest.crop(px*2, px*3, px*1, px*1);
		g1=forest.crop(px*3, px*3, px*1, px*1);
		g2=forest.crop(px*4, px*3, px*1, px*1);
		g3=forest.crop(px*5, px*3, px*1, px*1);
		g4=forest.crop(px*6, px*3, px*1, px*1);
		r0=forest.crop(px*0, px*4, px*1, px*1);
		r1=forest.crop(px*1, px*4, px*1, px*1);
		r2=forest.crop(px*2, px*4, px*1, px*1);
		log0=forest.crop(px*0, px*3, px*2, px*1);
		st0=forest.crop(px*0, px*8, px*3, px*4);
		
		//bear trap
		bt0=new BufferedImage[5];
		for(int i=0;i<3;i++)
			bt0[i]=tr0.crop(px*i, px*0, px, px);
		gbt0=tr0.crop(px*4, px*0, px, px);
		gbt1=tr0.crop(px*5, px*0, px, px);
		gbt2=tr0.crop(px*6, px*0, px, px);
		gbt3=tr0.crop(px*7, px*0, px, px);
		bt0[4]=gbt3;
		
		//lootcrate
		uc=new BufferedImage[3];
		lc=new BufferedImage[3];
		dc=new BufferedImage[3];
		rc=new BufferedImage[3];
		for(int i=0;i<3;i++)
			dc[i]=tomb.crop(px*0, px*(i+14), px, px);
		for(int i=0;i<3;i++)
			rc[i]=tomb.crop(px*1, px*(i+14), px, px);
		for(int i=0;i<3;i++)
			uc[i]=tomb.crop(px*2, px*(i+14), px, px);
		for(int i=0;i<3;i++)
			lc[i]=tomb.crop(px*3, px*(i+14), px, px);
		
		//wind slash
		w0=tomb.crop(px*9, px*13, px, px);
		w1=Utils.rotate(tomb.crop(px*9, px*13, px, px),180);
		w02=Utils.rotate(tomb.crop(px*9, px*13, px, px),270);
		w03=Utils.rotate(tomb.crop(px*9, px*13, px, px),90);
		w2=tomb.crop(px*4, px*14, px, px);
		w3=tomb.crop(px*5, px*14, px, px);
		mb=tomb.crop(px*4, px*15, px, px);
		wand0=tomb.crop(px*5, px*15, px, px);
		
		//ghost
		gu=ghost.crop(px*2, px*0, px, px);
		gl=ghost.crop(px*1, px*0, px, px);
		gd=ghost.crop(px*0, px*0, px, px);
		gr=ghost.crop(px*3, px*0, px, px);
		gAu=ghost.crop(px*6, px*0, px, px);
		gAl=ghost.crop(px*5, px*0, px, px);
		gAd=ghost.crop(px*4, px*0, px, px);
		gAr=ghost.crop(px*7, px*0, px, px);
		ghu=ghost.crop(px*0, px*1, px, px);
		ghl=ghost.crop(px*1, px*1, px, px);
		ghr=ghost.crop(px*3, px*1, px, px);
		
		//ghost0
		gu01=ghost0.crop(px*2, px*0, px, px);
		gl01=ghost0.crop(px*1, px*0, px, px);
		gd01=ghost0.crop(px*0, px*0, px, px);
		gr01=ghost0.crop(px*3, px*0, px, px);
		gAu01=ghost0.crop(px*6, px*0, px, px);
		gAl01=ghost0.crop(px*5, px*0, px, px);
		gAd01=ghost0.crop(px*4, px*0, px, px);
		gAr01=ghost0.crop(px*7, px*0, px, px);
		ghu01=ghost0.crop(px*0, px*1, px, px);
		ghl01=ghost0.crop(px*1, px*1, px, px);
		ghr01=ghost0.crop(px*3, px*1, px, px);
		
		//ghost1
		gu012=ghost01.crop(px*2, px*0, px, px);
		gl012=ghost01.crop(px*1, px*0, px, px);
		gd012=ghost01.crop(px*0, px*0, px, px);
		gr012=ghost01.crop(px*3, px*0, px, px);
		gAu012=ghost01.crop(px*6, px*0, px, px);
		gAl012=ghost01.crop(px*5, px*0, px, px);
		gAd012=ghost01.crop(px*4, px*0, px, px);
		gAr012=ghost01.crop(px*7, px*0, px, px);
		ghu012=ghost01.crop(px*0, px*1, px, px);
		ghl012=ghost01.crop(px*1, px*1, px, px);
		ghr012=ghost01.crop(px*3, px*1, px, px);
		
		
		//ghost2
		gu2=ghost2.crop(px*2, px*0, px, px);
		gl2=ghost2.crop(px*1, px*0, px, px);
		gd2=ghost2.crop(px*0, px*0, px, px);
		gr2=ghost2.crop(px*3, px*0, px, px);
		gAu2=ghost2.crop(px*6, px*0, px, px);
		gAl2=ghost2.crop(px*5, px*0, px, px);
		gAd2=ghost2.crop(px*4, px*0, px, px);
		gAr2=ghost2.crop(px*7, px*0, px, px);
		ghu2=ghost2.crop(px*0, px*1, px, px);
		ghl2=ghost2.crop(px*1, px*1, px, px);
		ghr2=ghost2.crop(px*3, px*1, px, px);
		
		//gibbed
		gore=new BufferedImage[5];
		for(int i=0;i<5;i++)
			gore[i]=ghost.crop(px*i, px*2, px, px);
		gore0=new BufferedImage[5];
		for(int i=0;i<5;i++)
			gore0[i]=ghost.crop(px*i, px*2, px, px);
		gore0[4]=ghost.crop(px*5, px*2, px, px);

		//incubus
		iDeath=new BufferedImage[5];
		for(int i=0;i<5;i++)
			iDeath[i]=inc.crop(px*i, px*2, px, px);
		gu0=inc.crop(px*2, px*0, px, px);
		gl0=inc.crop(px*1, px*0, px, px);
		gd0=inc.crop(px*0, px*0, px, px);
		gr0=inc.crop(px*3, px*0, px, px);
		gAu0=inc.crop(px*6, px*0, px, px);
		gAl0=inc.crop(px*5, px*0, px, px);
		gAd0=inc.crop(px*4, px*0, px, px);
		gAr0=inc.crop(px*7, px*0, px, px);
		ghu0=inc.crop(px*0, px*1, px, px);
		ghl0=inc.crop(px*1, px*1, px, px);
		ghr0=inc.crop(px*3, px*1, px, px);
		rFlesh=ghost.crop(px*7, px*1, px, px);
		
		inventory=inv.crop(0,0,639,319);
		textures=new BufferedImage[26][26];
		for(int y=0;y<26;y++){
			for(int x=0;x<26;x++){
				textures[x][y]=txr0.crop(px*x, px*y, px, px);
			}
		}
		def=def0.crop(px*0, px*0, px, px);
		grass=txr0.crop(px*0, px*0, px, px);
		moss=txr0.crop(px*1, px*0, px, px);
		wall=txr0.crop(px*2, px*0, px, px);
		stone=txr0.crop(px*3, px*0, px, px);
		cobble=txr0.crop(px*4, px*0, px, px);
		brick=tomb.crop(px*7, px*3, px, px);
		nul=txr0.crop(px*5, px*0, px, px);
		gren=tomb.crop(px*4, px*4, px, px);
		rice=tomb.crop(px*0, px*13, px, px);
		hotdog=tomb.crop(px*1, px*13, px, px);
		tgib0=tomb.crop(px*6, px*7, px, px);
		tgib1=tomb.crop(px*7, px*7, px, px);
		wgib0=tomb.crop(px*3, px*14, px, px);
		wgib1=tomb.crop(px*3, px*15, px, px);
		
		p0=tomb.crop(px*5, px*1, px, px);
		ph0=tomb.crop(px*5, px*4, px, px);
		p1=tomb.crop(px*5, px*2, px, px);
		p2=tomb.crop(px*5, px*3, px, px);
		ph1=tomb.crop(px*4, px*3, px, px);
		gib0=tomb.crop(px*6, px*12, px, px);
		gib1=tomb.crop(px*7, px*12, px, px);
		gib2=tomb.crop(px*8, px*12, px, px);
		gib3=tomb.crop(px*9, px*12, px, px);
		gibah=tomb.crop(px*6, px*13, px, px);
		adrenal=tomb.crop(px*4, px*13, px, px);
		milk=tomb.crop(px*8, px*13, px, px);
		hpot=tomb.crop(px*5, px*13, px, px);
		gibas=tomb.crop(px*7, px*13, px, px);
		
		fireball=tomb.crop(px*2, px*13, px, px);
		fireball0=tomb.crop(px*3, px*13, px, px);
		dGib0=imp.crop(px*5, px*14, px, px);
		dGib1=imp.crop(px*6, px*14, px, px);
		dGib2=imp.crop(px*7, px*14, px, px);
		dGibarm=imp.crop(px*0, px*15, px, px);
		dGibleg=imp.crop(px*1, px*15, px, px);
		dGibrib=imp.crop(px*2, px*15, px, px);
		
		smoke=new BufferedImage[6];
		for(int i=0;i<6;i++)
			smoke[i]=tomb.crop(px*i, px*12, px, px);
		
		boom=new BufferedImage[10];
		for(int i=0;i<5;i++)
			boom[i]=tomb.crop(px*(i*2), px*8, px*2, px*2);
		for(int i=5;i<10;i++)
			boom[i]=tomb.crop(px*(i*2)-(px*10), px*10, px*2, px*2);
		
		s0=tomb.crop(px*0, px*0, px, px);
		s1=tomb.crop(px*1, px*0, px, px);
		s2=tomb.crop(px*2, px*0, px, px);
		sh0=tomb.crop(px*3, px*0, px, px);
		sh1=tomb.crop(px*4, px*0, px, px);
		sh2=tomb.crop(px*5, px*0, px, px);
		
		s00=tomb.crop(px*0, px*5, px, px);
		s01=tomb.crop(px*1, px*5, px, px);
		s02=tomb.crop(px*2, px*5, px, px);
		sh00=tomb.crop(px*3, px*5, px, px);
		sh01=tomb.crop(px*4, px*5, px, px);
		sh02=tomb.crop(px*5, px*5, px, px);
		s10=tomb.crop(px*0, px*6, px, px);
		s11=tomb.crop(px*1, px*6, px, px);
		s12=tomb.crop(px*2, px*6, px, px);
		sh10=tomb.crop(px*3, px*6, px, px);
		sh11=tomb.crop(px*4, px*6, px, px);
		sh12=tomb.crop(px*5, px*6, px, px);
		s20=tomb.crop(px*0, px*7, px, px);
		s21=tomb.crop(px*1, px*7, px, px);
		s22=tomb.crop(px*2, px*7, px, px);
		sh20=tomb.crop(px*3, px*7, px, px);
		sh21=tomb.crop(px*4, px*7, px, px);
		sh22=tomb.crop(px*5, px*7, px, px);
		
		//tree
		t0=tomb.crop(px*6, px*0, px*2, px*3);
		th0=tomb.crop(px*8, px*0, px*2, px*3);
		t1=tomb.crop(px*6, px*4, px*2, px*3);
		th1=tomb.crop(px*8, px*4, px*2, px*3);
		
		//bunny
		bUp=new BufferedImage[8];
		bDown=new BufferedImage[8];
		bRight=new BufferedImage[8];
		bLeft=new BufferedImage[8];
		biD=new BufferedImage[2];
		biL=new BufferedImage[2];
		biU=new BufferedImage[2];
		biR=new BufferedImage[2];
		bDeath=new BufferedImage[5];
		for(int i=0;i<8;i++)
			bUp[i]=bun.crop(px*i, px*0, px, px);
		for(int i=0;i<8;i++)
			bLeft[i]=bun.crop(px*i, px*1, px, px);
		for(int i=0;i<8;i++)
			bDown[i]=bun.crop(px*i, px*2, px, px);
		for(int i=0;i<8;i++)
			bRight[i]=bun.crop(px*i, px*3, px, px);
		for(int i=0;i<5;i++)
			bDeath[i]=bun.crop(px*i, px*14, px, px);
		biD[0]=bun.crop(px*4, px*11, px, px);
		biL[0]=bun.crop(px*5, px*11, px, px);
		biU[0]=bun.crop(px*6, px*11, px, px);
		biR[0]=bun.crop(px*7, px*11, px, px);
		biD[1]=bun.crop(px*4, px*8, px, px);
		biL[1]=bun.crop(px*5, px*8, px, px);
		biU[1]=bun.crop(px*6, px*8, px, px);
		biR[1]=bun.crop(px*7, px*8, px, px);
		bGib=bun.crop(px*5, px*14, px, px);
		
		//imp
		dUp=new BufferedImage[8];
		dDown=new BufferedImage[8];
		dRight=new BufferedImage[8];
		dLeft=new BufferedImage[8];
		dIdle=new BufferedImage[8];
		AdUp=new BufferedImage[4];
		AdDown=new BufferedImage[4];
		AdRight=new BufferedImage[4];
		AdLeft=new BufferedImage[4];
		diD=new BufferedImage[2];
		diL=new BufferedImage[2];
		diU=new BufferedImage[2];
		diR=new BufferedImage[2];
		dDeath=new BufferedImage[5];
		dSpawn=new BufferedImage[8];
		for(int i=0;i<8;i++)
			dUp[i]=imp.crop(px*i, px*0, px, px);
		for(int i=0;i<8;i++)
			dLeft[i]=imp.crop(px*i, px*1, px, px);
		for(int i=0;i<8;i++)
			dDown[i]=imp.crop(px*i, px*2, px, px);
		for(int i=0;i<8;i++)
			dRight[i]=imp.crop(px*i, px*3, px, px);
		for(int i=0;i<8;i++)
			dIdle[i]=imp.crop(px*i, px*4, px, px);
		for(int i=0;i<4;i++)
			AdUp[i]=imp.crop(px*i, px*5, px, px*2);
		for(int i=0;i<4;i++)
			AdLeft[i]=imp.crop(px*i*2, px*7, px*2, px);
		for(int i=0;i<4;i++)
			AdDown[i]=imp.crop(px*i, px*8, px, px*2);
		for(int i=0;i<4;i++)
			AdRight[i]=imp.crop(px*i*2, px*10, px*2, px);
		for(int i=0;i<5;i++)
			dDeath[i]=imp.crop(px*i, px*14, px, px);
		for(int i=0;i<8;i++)
			dSpawn[i]=imp.crop(px*i, px*16, px, px);
		dhD=imp.crop(px*0, px*11, px, px);
		dhL=imp.crop(px*1, px*11, px, px);
		dhU=imp.crop(px*2, px*11, px, px);
		dhR=imp.crop(px*3, px*11, px, px);
		diD[0]=imp.crop(px*4, px*11, px, px);
		diL[0]=imp.crop(px*5, px*11, px, px);
		diU[0]=imp.crop(px*6, px*11, px, px);
		diR[0]=imp.crop(px*7, px*11, px, px);
		diD[1]=imp.crop(px*4, px*8, px, px);
		diL[1]=imp.crop(px*5, px*8, px, px);
		diU[1]=imp.crop(px*6, px*8, px, px);
		diR[1]=imp.crop(px*7, px*8, px, px);
		
		//pinky
		pdUp=new BufferedImage[8];
		pdDown=new BufferedImage[8];
		pdRight=new BufferedImage[8];
		pdLeft=new BufferedImage[8];
		pdIdle=new BufferedImage[8];
		pAdUp=new BufferedImage[4];
		pAdDown=new BufferedImage[4];
		pAdRight=new BufferedImage[4];
		pAdLeft=new BufferedImage[4];
		pdiD=new BufferedImage[2];
		pdiL=new BufferedImage[2];
		pdiU=new BufferedImage[2];
		pdiR=new BufferedImage[2];
		pdDeath=new BufferedImage[5];
		pdSpawn=new BufferedImage[8];
		for(int i=0;i<8;i++)
			pdUp[i]=pinky.crop(px*i, px*0, px, px);
		for(int i=0;i<8;i++)
			pdLeft[i]=pinky.crop(px*i, px*1, px, px);
		for(int i=0;i<8;i++)
			pdDown[i]=pinky.crop(px*i, px*2, px, px);
		for(int i=0;i<8;i++)
			pdRight[i]=pinky.crop(px*i, px*3, px, px);
		for(int i=0;i<8;i++)
			pdIdle[i]=pinky.crop(px*i, px*4, px, px);
		for(int i=0;i<4;i++)
			pAdUp[i]=pinky.crop(px*i, px*5, px, px*2);
		for(int i=0;i<4;i++)
			pAdLeft[i]=pinky.crop(px*i*2, px*7, px*2, px);
		for(int i=0;i<4;i++)
			pAdDown[i]=pinky.crop(px*i, px*8, px, px*2);
		for(int i=0;i<4;i++)
			pAdRight[i]=pinky.crop(px*i*2, px*10, px*2, px);
		for(int i=0;i<5;i++)
			pdDeath[i]=pinky.crop(px*i, px*14, px, px);
		for(int i=0;i<8;i++)
			pdSpawn[i]=pinky.crop(px*i, px*16, px, px);
		pdhD=pinky.crop(px*0, px*11, px, px);
		pdhL=pinky.crop(px*1, px*11, px, px);
		pdhU=pinky.crop(px*2, px*11, px, px);
		pdhR=pinky.crop(px*3, px*11, px, px);
		pdiD[0]=pinky.crop(px*4, px*11, px, px);
		pdiL[0]=pinky.crop(px*5, px*11, px, px);
		pdiU[0]=pinky.crop(px*6, px*11, px, px);
		pdiR[0]=pinky.crop(px*7, px*11, px, px);
		pdiD[1]=pinky.crop(px*4, px*8, px, px);
		pdiL[1]=pinky.crop(px*5, px*8, px, px);
		pdiU[1]=pinky.crop(px*6, px*8, px, px);
		pdiR[1]=pinky.crop(px*7, px*8, px, px);
		
		//thrownknife
		pl=new BufferedImage[4];
		pr=new BufferedImage[4];
		for(int i=0;i<4;i++)
			pl[i]=tomb.crop(px*(1+i), px*1, px, px);
		for(int i=0;i<4;i++)
			pr[i]=tomb.crop(px*(1+i), px*2, px, px);
		kr=tomb.crop(px*0, px*3, px, px);
		kd=tomb.crop(px*1, px*3, px, px);
		kl=tomb.crop(px*2, px*3, px, px);
		ku=tomb.crop(px*3, px*3, px, px);
		ar=tomb.crop(px*0, px*4, px, px);
		ad=tomb.crop(px*1, px*4, px, px);
		al=tomb.crop(px*2, px*4, px, px);
		au=tomb.crop(px*3, px*4, px, px);
		
		//gasterblaster
		gbu=new BufferedImage[12];
		gbl=new BufferedImage[12];
		gbd=new BufferedImage[12];
		gbr=new BufferedImage[12];
		//up
		gbu[0]=gb.crop(px*2*0, px*2*2, px*2, px*2);
		gbu[1]=gb.crop(px*2*1, px*2*2, px*2, px*2);
		gbu[2]=gb.crop(px*2*2, px*2*2, px*2, px*2);
		gbu[3]=gb.crop(px*2*3, px*2*2, px*2, px*2);
		gbu[4]=gb.crop(px*2*2, px*2*2, px*2, px*2);
		gbu[5]=gb.crop(px*2*3, px*2*2, px*2, px*2);
		gbu[6]=gb.crop(px*2*3, px*2*2, px*2, px*2);
		gbu[7]=gb.crop(px*2*2, px*2*2, px*2, px*2);
		gbu[8]=gb.crop(px*2*3, px*2*2, px*2, px*2);
		gbu[9]=gb.crop(px*2*2, px*2*2, px*2, px*2);
		gbu[10]=gb.crop(px*2*1, px*2*2, px*2, px*2);
		gbu[11]=gb.crop(px*2*0, px*2*2, px*2, px*2);
		//left
		gbl[0]=gb.crop(px*2*0, px*2*3, px*2, px*2);
		gbl[1]=gb.crop(px*2*1, px*2*3, px*2, px*2);
		gbl[2]=gb.crop(px*2*2, px*2*3, px*2, px*2);
		gbl[3]=gb.crop(px*2*3, px*2*3, px*2, px*2);
		gbl[4]=gb.crop(px*2*2, px*2*3, px*2, px*2);
		gbl[5]=gb.crop(px*2*3, px*2*3, px*2, px*2);
		gbl[6]=gb.crop(px*2*3, px*2*3, px*2, px*2);
		gbl[7]=gb.crop(px*2*2, px*2*3, px*2, px*2);
		gbl[8]=gb.crop(px*2*3, px*2*3, px*2, px*2);
		gbl[9]=gb.crop(px*2*2, px*2*3, px*2, px*2);
		gbl[10]=gb.crop(px*2*1, px*2*3, px*2, px*2);
		gbl[11]=gb.crop(px*2*0, px*2*3, px*2, px*2);
		//down
		gbd[0]=gb.crop(px*2*0, px*2*4, px*2, px*2);
		gbd[1]=gb.crop(px*2*1, px*2*4, px*2, px*2);
		gbd[2]=gb.crop(px*2*2, px*2*4, px*2, px*2);
		gbd[3]=gb.crop(px*2*3, px*2*4, px*2, px*2);
		gbd[4]=gb.crop(px*2*2, px*2*4, px*2, px*2);
		gbd[5]=gb.crop(px*2*3, px*2*4, px*2, px*2);
		gbd[6]=gb.crop(px*2*3, px*2*4, px*2, px*2);
		gbd[7]=gb.crop(px*2*2, px*2*4, px*2, px*2);
		gbd[8]=gb.crop(px*2*3, px*2*4, px*2, px*2);
		gbd[9]=gb.crop(px*2*2, px*2*4, px*2, px*2);
		gbd[10]=gb.crop(px*2*1, px*2*4, px*2, px*2);
		gbd[11]=gb.crop(px*2*0, px*2*4, px*2, px*2);
		//right
		gbr[0]=gb.crop(px*2*0, px*2*1, px*2, px*2);
		gbr[1]=gb.crop(px*2*1, px*2*1, px*2, px*2);
		gbr[2]=gb.crop(px*2*2, px*2*1, px*2, px*2);
		gbr[3]=gb.crop(px*2*3, px*2*1, px*2, px*2);
		gbr[4]=gb.crop(px*2*2, px*2*1, px*2, px*2);
		gbr[5]=gb.crop(px*2*3, px*2*1, px*2, px*2);
		gbr[6]=gb.crop(px*2*3, px*2*1, px*2, px*2);
		gbr[7]=gb.crop(px*2*2, px*2*1, px*2, px*2);
		gbr[8]=gb.crop(px*2*3, px*2*1, px*2, px*2);
		gbr[9]=gb.crop(px*2*2, px*2*1, px*2, px*2);
		gbr[10]=gb.crop(px*2*1, px*2*1, px*2, px*2);
		gbr[11]=gb.crop(px*2*0, px*2*1, px*2, px*2);
		
		bu0=gb.crop(px*1, px*0, px, px*2);
		bl0=gb.crop(px*2, px*0, px*2, px);
		bd0=gb.crop(px*0, px*0, px, px*2);
		br0=gb.crop(px*2, px*1, px*2, px);
		bu1=gb.crop(px*5, px*0, px, px*2);
		bl1=gb.crop(px*6, px*0, px*2, px);
		bd1=gb.crop(px*4, px*0, px, px*2);
		br1=gb.crop(px*6, px*1, px*2, px);
		
		//human grunt
		hUp=new BufferedImage[8];
		hDown=new BufferedImage[8];
		hRight=new BufferedImage[8];
		hLeft=new BufferedImage[8];
		hIdle=new BufferedImage[8];
		AhUp=new BufferedImage[4];
		AhDown=new BufferedImage[4];
		AhRight=new BufferedImage[4];
		AhLeft=new BufferedImage[4];
		hiD=new BufferedImage[2];
		hiL=new BufferedImage[2];
		hiU=new BufferedImage[2];
		hiR=new BufferedImage[2];
		hDeath=new BufferedImage[5];
		for(int i=0;i<8;i++)
			hUp[i]=human0.crop(px*i, px*0, px, px);
		for(int i=0;i<8;i++)
			hLeft[i]=human0.crop(px*i, px*1, px, px);
		for(int i=0;i<8;i++)
			hDown[i]=human0.crop(px*i, px*2, px, px);
		for(int i=0;i<8;i++)
			hRight[i]=human0.crop(px*i, px*3, px, px);
		for(int i=0;i<8;i++)
			hIdle[i]=human0.crop(px*i, px*4, px, px);
		for(int i=0;i<4;i++)
			AhUp[i]=human0.crop(px*i, px*5, px, px*2);
		for(int i=0;i<4;i++)
			AhLeft[i]=human0.crop(px*i*2, px*7, px*2, px);
		for(int i=0;i<4;i++)
			AhDown[i]=human0.crop(px*i, px*8, px, px*2);
		for(int i=0;i<4;i++)
			AhRight[i]=human0.crop(px*i*2, px*10, px*2, px);
		for(int i=0;i<5;i++)
			hDeath[i]=human0.crop(px*i, px*14, px, px);
		hhD=human0.crop(px*0, px*11, px, px);
		hhL=human0.crop(px*1, px*11, px, px);
		hhU=human0.crop(px*2, px*11, px, px);
		hhR=human0.crop(px*3, px*11, px, px);
		hiD[0]=human0.crop(px*4, px*11, px, px);
		hiL[0]=human0.crop(px*5, px*11, px, px);
		hiU[0]=human0.crop(px*6, px*11, px, px);
		hiR[0]=human0.crop(px*7, px*11, px, px);
		hiD[1]=human0.crop(px*4, px*8, px, px);
		hiL[1]=human0.crop(px*5, px*8, px, px);
		hiU[1]=human0.crop(px*6, px*8, px, px);
		hiR[1]=human0.crop(px*7, px*8, px, px);
		
		//armored human grunt
		hUp0=new BufferedImage[8];
		hDown0=new BufferedImage[8];
		hRight0=new BufferedImage[8];
		hLeft0=new BufferedImage[8];
		hIdle0=new BufferedImage[8];
		AhUp0=new BufferedImage[4];
		AhDown0=new BufferedImage[4];
		AhRight0=new BufferedImage[4];
		AhLeft0=new BufferedImage[4];
		hiD0=new BufferedImage[2];
		hiL0=new BufferedImage[2];
		hiU0=new BufferedImage[2];
		hiR0=new BufferedImage[2];
		hDeath0=new BufferedImage[5];
		for(int i=0;i<8;i++)
			hUp0[i]=human01.crop(px*i, px*0, px, px);
		for(int i=0;i<8;i++)
			hLeft0[i]=human01.crop(px*i, px*1, px, px);
		for(int i=0;i<8;i++)
			hDown0[i]=human01.crop(px*i, px*2, px, px);
		for(int i=0;i<8;i++)
			hRight0[i]=human01.crop(px*i, px*3, px, px);
		for(int i=0;i<8;i++)
			hIdle0[i]=human01.crop(px*i, px*4, px, px);
		for(int i=0;i<4;i++)
			AhUp0[i]=human01.crop(px*i, px*5, px, px*2);
		for(int i=0;i<4;i++)
			AhLeft0[i]=human01.crop(px*i*2, px*7, px*2, px);
		for(int i=0;i<4;i++)
			AhDown0[i]=human01.crop(px*i, px*8, px, px*2);
		for(int i=0;i<4;i++)
			AhRight0[i]=human01.crop(px*i*2, px*10, px*2, px);
		for(int i=0;i<5;i++)
			hDeath0[i]=human01.crop(px*i, px*14, px, px);
		hhD0=human01.crop(px*0, px*11, px, px);
		hhL0=human01.crop(px*1, px*11, px, px);
		hhU0=human01.crop(px*2, px*11, px, px);
		hhR0=human01.crop(px*3, px*11, px, px);
		shhD0=human01.crop(px*0, px*4, px, px);
		shhL0=human01.crop(px*1, px*4, px, px);
		shhU0=human01.crop(px*2, px*4, px, px);
		shhR0=human01.crop(px*3, px*4, px, px);
		hiD0[0]=human01.crop(px*4, px*11, px, px);
		hiL0[0]=human01.crop(px*5, px*11, px, px);
		hiU0[0]=human01.crop(px*6, px*11, px, px);
		hiR0[0]=human01.crop(px*7, px*11, px, px);
		hiD0[1]=human01.crop(px*4, px*8, px, px);
		hiL0[1]=human01.crop(px*5, px*8, px, px);
		hiU0[1]=human01.crop(px*6, px*8, px, px);
		hiR0[1]=human01.crop(px*7, px*8, px, px);
		
		//possessed human grunt 0
		phUp0=new BufferedImage[8];
		phDown0=new BufferedImage[8];
		phRight0=new BufferedImage[8];
		phLeft0=new BufferedImage[8];
		phIdle0=new BufferedImage[8];
		pAhUp0=new BufferedImage[4];
		pAhDown0=new BufferedImage[4];
		pAhRight0=new BufferedImage[4];
		pAhLeft0=new BufferedImage[4];
		phiD0=new BufferedImage[2];
		phiL0=new BufferedImage[2];
		phiU0=new BufferedImage[2];
		phiR0=new BufferedImage[2];
		phDeath0=new BufferedImage[5];
		phSpawn0=new BufferedImage[8];
		for(int i=0;i<8;i++)
			phSpawn0[i]=phuman01.crop(px*i, px*16, px, px);
		for(int i=0;i<8;i++)
			phUp0[i]=phuman01.crop(px*i, px*0, px, px);
		for(int i=0;i<8;i++)
			phLeft0[i]=phuman01.crop(px*i, px*1, px, px);
		for(int i=0;i<8;i++)
			phDown0[i]=phuman01.crop(px*i, px*2, px, px);
		for(int i=0;i<8;i++)
			phRight0[i]=phuman01.crop(px*i, px*3, px, px);
		for(int i=0;i<8;i++)
			phIdle0[i]=phuman01.crop(px*i, px*4, px, px);
		for(int i=0;i<4;i++)
			pAhUp0[i]=phuman01.crop(px*i, px*5, px, px*2);
		for(int i=0;i<4;i++)
			pAhLeft0[i]=phuman01.crop(px*i*2, px*7, px*2, px);
		for(int i=0;i<4;i++)
			pAhDown0[i]=phuman01.crop(px*i, px*8, px, px*2);
		for(int i=0;i<4;i++)
			pAhRight0[i]=phuman01.crop(px*i*2, px*10, px*2, px);
		for(int i=0;i<5;i++)
			phDeath0[i]=phuman01.crop(px*i, px*14, px, px);
		phhD0=phuman0.crop(px*0, px*11, px, px);
		phhL0=phuman0.crop(px*1, px*11, px, px);
		phhU0=phuman0.crop(px*2, px*11, px, px);
		phhR0=phuman0.crop(px*3, px*11, px, px);
		phiD0[0]=phuman01.crop(px*4, px*11, px, px);
		phiL0[0]=phuman01.crop(px*5, px*11, px, px);
		phiU0[0]=phuman01.crop(px*6, px*11, px, px);
		phiR0[0]=phuman01.crop(px*7, px*11, px, px);
		phiD0[1]=phuman01.crop(px*4, px*8, px, px);
		phiL0[1]=phuman01.crop(px*5, px*8, px, px);
		phiU0[1]=phuman01.crop(px*6, px*8, px, px);
		phiR0[1]=phuman01.crop(px*7, px*8, px, px);
		
		//possessed human grunt 0
		phUp=new BufferedImage[8];
		phDown=new BufferedImage[8];
		phRight=new BufferedImage[8];
		phLeft=new BufferedImage[8];
		phIdle=new BufferedImage[8];
		pAhUp=new BufferedImage[4];
		pAhDown=new BufferedImage[4];
		pAhRight=new BufferedImage[4];
		pAhLeft=new BufferedImage[4];
		phiD=new BufferedImage[2];
		phiL=new BufferedImage[2];
		phiU=new BufferedImage[2];
		phiR=new BufferedImage[2];
		phDeath=new BufferedImage[5];
		phSpawn=new BufferedImage[8];
		for(int i=0;i<8;i++)
			phSpawn[i]=phuman0.crop(px*i, px*16, px, px);
		for(int i=0;i<8;i++)
			phUp[i]=phuman0.crop(px*i, px*0, px, px);
		for(int i=0;i<8;i++)
			phLeft[i]=phuman0.crop(px*i, px*1, px, px);
		for(int i=0;i<8;i++)
			phDown[i]=phuman0.crop(px*i, px*2, px, px);
		for(int i=0;i<8;i++)
			phRight[i]=phuman0.crop(px*i, px*3, px, px);
		for(int i=0;i<8;i++)
			phIdle[i]=phuman0.crop(px*i, px*4, px, px);
		for(int i=0;i<4;i++)
			pAhUp[i]=phuman0.crop(px*i, px*5, px, px*2);
		for(int i=0;i<4;i++)
			pAhLeft[i]=phuman0.crop(px*i*2, px*7, px*2, px);
		for(int i=0;i<4;i++)
			pAhDown[i]=phuman0.crop(px*i, px*8, px, px*2);
		for(int i=0;i<4;i++)
			pAhRight[i]=phuman0.crop(px*i*2, px*10, px*2, px);
		for(int i=0;i<5;i++)
			phDeath[i]=phuman0.crop(px*i, px*14, px, px);
		phhD=phuman0.crop(px*0, px*11, px, px);
		phhL=phuman0.crop(px*1, px*11, px, px);
		phhU=phuman0.crop(px*2, px*11, px, px);
		phhR=phuman0.crop(px*3, px*11, px, px);
		phiD[0]=phuman0.crop(px*4, px*11, px, px);
		phiL[0]=phuman0.crop(px*5, px*11, px, px);
		phiU[0]=phuman0.crop(px*6, px*11, px, px);
		phiR[0]=phuman0.crop(px*7, px*11, px, px);
		phiD[1]=phuman0.crop(px*4, px*8, px, px);
		phiL[1]=phuman0.crop(px*5, px*8, px, px);
		phiU[1]=phuman0.crop(px*6, px*8, px, px);
		phiR[1]=phuman0.crop(px*7, px*8, px, px);
		
		//possessed alt2
		phUp01=new BufferedImage[8];
		phDown01=new BufferedImage[8];
		phRight01=new BufferedImage[8];
		phLeft01=new BufferedImage[8];
		phIdle01=new BufferedImage[8];
		pAhUp01=new BufferedImage[4];
		pAhDown01=new BufferedImage[4];
		pAhRight01=new BufferedImage[4];
		pAhLeft01=new BufferedImage[4];
		phiD01=new BufferedImage[2];
		phiL01=new BufferedImage[2];
		phiU01=new BufferedImage[2];
		phiR01=new BufferedImage[2];
		phDeath01=new BufferedImage[5];
		phSpawn01=new BufferedImage[8];
		for(int i=0;i<8;i++)
			phSpawn01[i]=phuman1.crop(px*i, px*16, px, px);
		for(int i=0;i<8;i++)
			phUp01[i]=phuman1.crop(px*i, px*0, px, px);
		for(int i=0;i<8;i++)
			phLeft01[i]=phuman1.crop(px*i, px*1, px, px);
		for(int i=0;i<8;i++)
			phDown01[i]=phuman1.crop(px*i, px*2, px, px);
		for(int i=0;i<8;i++)
			phRight01[i]=phuman1.crop(px*i, px*3, px, px);
		for(int i=0;i<8;i++)
			phIdle01[i]=phuman1.crop(px*i, px*4, px, px);
		for(int i=0;i<4;i++)
			pAhUp01[i]=phuman1.crop(px*i, px*5, px, px*2);
		for(int i=0;i<4;i++)
			pAhLeft01[i]=phuman1.crop(px*i*2, px*7, px*2, px);
		for(int i=0;i<4;i++)
			pAhDown01[i]=phuman1.crop(px*i, px*8, px, px*2);
		for(int i=0;i<4;i++)
			pAhRight01[i]=phuman1.crop(px*i*2, px*10, px*2, px);
		for(int i=0;i<5;i++)
			phDeath01[i]=phuman1.crop(px*i, px*14, px, px);
		phhD01=phuman0.crop(px*0, px*11, px, px);
		phhL01=phuman0.crop(px*1, px*11, px, px);
		phhU01=phuman0.crop(px*2, px*11, px, px);
		phhR01=phuman0.crop(px*3, px*11, px, px);
		phiD01[0]=phuman1.crop(px*4, px*11, px, px);
		phiL01[0]=phuman1.crop(px*5, px*11, px, px);
		phiU01[0]=phuman1.crop(px*6, px*11, px, px);
		phiR01[0]=phuman1.crop(px*7, px*11, px, px);
		phiD01[1]=phuman1.crop(px*4, px*8, px, px);
		phiL01[1]=phuman1.crop(px*5, px*8, px, px);
		phiU01[1]=phuman1.crop(px*6, px*8, px, px);
		phiR01[1]=phuman1.crop(px*7, px*8, px, px);
		
		//cultist
		cphUp0=new BufferedImage[8];
		cphDown0=new BufferedImage[8];
		cphRight0=new BufferedImage[8];
		cphLeft0=new BufferedImage[8];
		cphIdle0=new BufferedImage[8];
		cpAhUp0=new BufferedImage[4];
		cpAhDown0=new BufferedImage[4];
		cpAhRight0=new BufferedImage[4];
		cpAhLeft0=new BufferedImage[4];
		cphiD0=new BufferedImage[2];
		cphiL0=new BufferedImage[2];
		cphiU0=new BufferedImage[2];
		cphiR0=new BufferedImage[2];
		cphDeath0=new BufferedImage[5];
		cphSpawn0=new BufferedImage[8];
		for(int i=0;i<8;i++)
			cphSpawn0[i]=c0.crop(px*i, px*16, px, px);
		for(int i=0;i<8;i++)
			cphUp0[i]=c0.crop(px*i, px*0, px, px);
		for(int i=0;i<8;i++)
			cphLeft0[i]=c0.crop(px*i, px*1, px, px);
		for(int i=0;i<8;i++)
			cphDown0[i]=c0.crop(px*i, px*2, px, px);
		for(int i=0;i<8;i++)
			cphRight0[i]=c0.crop(px*i, px*3, px, px);
		for(int i=0;i<8;i++)
			cphIdle0[i]=c0.crop(px*i, px*4, px, px);
		for(int i=0;i<4;i++)
			cpAhUp0[i]=c0.crop(px*i, px*5, px, px*2);
		for(int i=0;i<4;i++)
			cpAhLeft0[i]=c0.crop(px*i*2, px*7, px*2, px);
		for(int i=0;i<4;i++)
			cpAhDown0[i]=c0.crop(px*i, px*8, px, px*2);
		for(int i=0;i<4;i++)
			cpAhRight0[i]=c0.crop(px*i*2, px*10, px*2, px);
		for(int i=0;i<5;i++)
			cphDeath0[i]=c0.crop(px*i, px*14, px, px);
		cphhD0=c0.crop(px*0, px*11, px, px);
		cphhL0=c0.crop(px*1, px*11, px, px);
		cphhU0=c0.crop(px*2, px*11, px, px);
		cphhR0=c0.crop(px*3, px*11, px, px);
		cphiD0[0]=c0.crop(px*4, px*11, px, px);
		cphiL0[0]=c0.crop(px*5, px*11, px, px);
		cphiU0[0]=c0.crop(px*6, px*11, px, px);
		cphiR0[0]=c0.crop(px*7, px*11, px, px);
		cphiD0[1]=c0.crop(px*4, px*8, px, px);
		cphiL0[1]=c0.crop(px*5, px*8, px, px);
		cphiU0[1]=c0.crop(px*6, px*8, px, px);
		cphiR0[1]=c0.crop(px*7, px*8, px, px);
				
		//armored skeleton
		sUp=new BufferedImage[8];
		sDown=new BufferedImage[8];
		sRight=new BufferedImage[8];
		sLeft=new BufferedImage[8];
		sIdle=new BufferedImage[8];
		AsUp=new BufferedImage[4];
		AsDown=new BufferedImage[4];
		AsRight=new BufferedImage[4];
		AsLeft=new BufferedImage[4];
		siD=new BufferedImage[2];
		siL=new BufferedImage[2];
		siU=new BufferedImage[2];
		siR=new BufferedImage[2];
		for(int i=0;i<8;i++)
			sUp[i]=skel.crop(px*i, px*0, px, px);
		for(int i=0;i<8;i++)
			sLeft[i]=skel.crop(px*i, px*1, px, px);
		for(int i=0;i<8;i++)
			sDown[i]=skel.crop(px*i, px*2, px, px);
		for(int i=0;i<8;i++)
			sRight[i]=skel.crop(px*i, px*3, px, px);
		for(int i=0;i<8;i++)
			sIdle[i]=skel.crop(px*i, px*4, px, px);
		for(int i=0;i<4;i++)
			AsUp[i]=skel.crop(px*i, px*5, px, px*2);
		for(int i=0;i<4;i++)
			AsLeft[i]=skel.crop(px*i*2, px*7, px*2, px);
		for(int i=0;i<4;i++)
			AsDown[i]=skel.crop(px*i, px*8, px, px*2);
		for(int i=0;i<4;i++)
			AsRight[i]=skel.crop(px*i*2, px*10, px*2, px);
		shD=skel.crop(px*0, px*11, px, px);
		shL=skel.crop(px*1, px*11, px, px);
		shU=skel.crop(px*2, px*11, px, px);
		shR=skel.crop(px*3, px*11, px, px);
		siD[0]=skel.crop(px*4, px*11, px, px);
		siL[0]=skel.crop(px*5, px*11, px, px);
		siU[0]=skel.crop(px*6, px*11, px, px);
		siR[0]=skel.crop(px*7, px*11, px, px);
		siD[1]=skel.crop(px*4, px*8, px, px);
		siL[1]=skel.crop(px*5, px*8, px, px);
		siU[1]=skel.crop(px*6, px*8, px, px);
		siR[1]=skel.crop(px*7, px*8, px, px);
		
		//basic skeleton
		sUp0=new BufferedImage[8];
		sDown0=new BufferedImage[8];
		sRight0=new BufferedImage[8];
		sLeft0=new BufferedImage[8];
		sIdle0=new BufferedImage[8];
		AsUp0=new BufferedImage[4];
		AsDown0=new BufferedImage[4];
		AsRight0=new BufferedImage[4];
		AsLeft0=new BufferedImage[4];
		siD0=new BufferedImage[2];
		siL0=new BufferedImage[2];
		siU0=new BufferedImage[2];
		siR0=new BufferedImage[2];
		emerge=new BufferedImage[16];
		emerge0=new BufferedImage[16];
		for(int i=0;i<8;i++)
			sUp0[i]=skel0.crop(px*i, px*0, px, px);
		for(int i=0;i<8;i++)
			sLeft0[i]=skel0.crop(px*i, px*1, px, px);
		for(int i=0;i<8;i++)
			sDown0[i]=skel0.crop(px*i, px*2, px, px);
		for(int i=0;i<8;i++)
			sRight0[i]=skel0.crop(px*i, px*3, px, px);
		for(int i=0;i<8;i++)
			sIdle0[i]=skel0.crop(px*i, px*4, px, px);
		for(int i=0;i<4;i++)
			AsUp0[i]=skel0.crop(px*i, px*5, px, px*2);
		for(int i=0;i<4;i++)
			AsLeft0[i]=skel0.crop(px*i*2, px*7, px*2, px);
		for(int i=0;i<4;i++)
			AsDown0[i]=skel0.crop(px*i, px*8, px, px*2);
		for(int i=0;i<4;i++)
			AsRight0[i]=skel0.crop(px*i*2, px*10, px*2, px);
		shD0=skel0.crop(px*0, px*11, px, px);
		shL0=skel0.crop(px*1, px*11, px, px);
		shU0=skel0.crop(px*2, px*11, px, px);
		shR0=skel0.crop(px*3, px*11, px, px);
		siD0[0]=skel0.crop(px*4, px*11, px, px);
		siL0[0]=skel0.crop(px*5, px*11, px, px);
		siU0[0]=skel0.crop(px*6, px*11, px, px);
		siR0[0]=skel0.crop(px*7, px*11, px, px);
		siD0[1]=skel0.crop(px*4, px*8, px, px);
		siL0[1]=skel0.crop(px*5, px*8, px, px);
		siU0[1]=skel0.crop(px*6, px*8, px, px);
		siR0[1]=skel0.crop(px*7, px*8, px, px);
		for(int i=0;i<8;i++)
			emerge0[i]=skel.crop(px*i, px*14, px, px);
		for(int i=8;i<16;i++)
			emerge0[i]=skel.crop(px*(i-8), px*15, px, px);
		emerge0[15]=skel.crop(px*6, px*15, px, px);
		
		//grave skeleton
		gsUp0=new BufferedImage[8];
		gsDown0=new BufferedImage[8];
		gsRight0=new BufferedImage[8];
		gsLeft0=new BufferedImage[8];
		gsIdle0=new BufferedImage[8];
		gAsUp0=new BufferedImage[4];
		gAsDown0=new BufferedImage[4];
		gAsRight0=new BufferedImage[4];
		gAsLeft0=new BufferedImage[4];
		gsiD0=new BufferedImage[2];
		gsiL0=new BufferedImage[2];
		gsiU0=new BufferedImage[2];
		gsiR0=new BufferedImage[2];
		for(int i=0;i<8;i++)
			gsUp0[i]=gskel0.crop(px*i, px*0, px, px);
		for(int i=0;i<8;i++)
			gsLeft0[i]=gskel0.crop(px*i, px*1, px, px);
		for(int i=0;i<8;i++)
			gsDown0[i]=gskel0.crop(px*i, px*2, px, px);
		for(int i=0;i<8;i++)
			gsRight0[i]=gskel0.crop(px*i, px*3, px, px);
		for(int i=0;i<8;i++)
			gsIdle0[i]=gskel0.crop(px*i, px*4, px, px);
		for(int i=0;i<4;i++)
			gAsUp0[i]=gskel0.crop(px*i, px*5, px, px*2);
		for(int i=0;i<4;i++)
			gAsLeft0[i]=gskel0.crop(px*i*2, px*7, px*2, px);
		for(int i=0;i<4;i++)
			gAsDown0[i]=gskel0.crop(px*i, px*8, px, px*2);
		for(int i=0;i<4;i++)
			gAsRight0[i]=gskel0.crop(px*i*2, px*10, px*2, px);
		gshD0=gskel0.crop(px*0, px*11, px, px);
		gshL0=gskel0.crop(px*1, px*11, px, px);
		gshU0=gskel0.crop(px*2, px*11, px, px);
		gshR0=gskel0.crop(px*3, px*11, px, px);
		gsiD0[0]=gskel0.crop(px*4, px*11, px, px);
		gsiL0[0]=gskel0.crop(px*5, px*11, px, px);
		gsiU0[0]=gskel0.crop(px*6, px*11, px, px);
		gsiR0[0]=gskel0.crop(px*7, px*11, px, px);
		gsiD0[1]=gskel0.crop(px*4, px*8, px, px);
		gsiL0[1]=gskel0.crop(px*5, px*8, px, px);
		gsiU0[1]=gskel0.crop(px*6, px*8, px, px);
		gsiR0[1]=gskel0.crop(px*7, px*8, px, px);
		for(int i=0;i<8;i++)
			emerge[i]=gskel0.crop(px*i, px*14, px, px);
		for(int i=8;i<16;i++)
			emerge[i]=gskel0.crop(px*(i-8), px*15, px, px);
		emerge[15]=gskel0.crop(px*6, px*15, px, px);
		
		//archer skeleton
		aemerge=new BufferedImage[16];
		agsUp0=new BufferedImage[8];
		agsDown0=new BufferedImage[8];
		agsRight0=new BufferedImage[8];
		agsLeft0=new BufferedImage[8];
		agsIdle0=new BufferedImage[8];
		agAsUp0=new BufferedImage[4];
		agAsDown0=new BufferedImage[4];
		agAsRight0=new BufferedImage[4];
		agAsLeft0=new BufferedImage[4];
		agsiD0=new BufferedImage[2];
		agsiL0=new BufferedImage[2];
		agsiU0=new BufferedImage[2];
		agsiR0=new BufferedImage[2];
		for(int i=0;i<8;i++)
			agsUp0[i]=agskel0.crop(px*i, px*0, px, px);
		for(int i=0;i<8;i++)
			agsLeft0[i]=agskel0.crop(px*i, px*1, px, px);
		for(int i=0;i<8;i++)
			agsDown0[i]=agskel0.crop(px*i, px*2, px, px);
		for(int i=0;i<8;i++)
			agsRight0[i]=agskel0.crop(px*i, px*3, px, px);
		for(int i=0;i<8;i++)
			agsIdle0[i]=agskel0.crop(px*i, px*4, px, px);
		for(int i=0;i<4;i++)
			agAsUp0[i]=agskel0.crop(px*i, px*5, px, px*2);
		for(int i=0;i<4;i++)
			agAsLeft0[i]=agskel0.crop(px*i*2, px*7, px*2, px);
		for(int i=0;i<4;i++)
			agAsDown0[i]=agskel0.crop(px*i, px*8, px, px*2);
		for(int i=0;i<4;i++)
			agAsRight0[i]=agskel0.crop(px*i*2, px*10, px*2, px);
		agshD0=agskel0.crop(px*0, px*11, px, px);
		agshL0=agskel0.crop(px*1, px*11, px, px);
		agshU0=agskel0.crop(px*2, px*11, px, px);
		agshR0=agskel0.crop(px*3, px*11, px, px);
		agsiD0[0]=agskel0.crop(px*4, px*11, px, px);
		agsiL0[0]=agskel0.crop(px*5, px*11, px, px);
		agsiU0[0]=agskel0.crop(px*6, px*11, px, px);
		agsiR0[0]=agskel0.crop(px*7, px*11, px, px);
		agsiD0[1]=agskel0.crop(px*4, px*8, px, px);
		agsiL0[1]=agskel0.crop(px*5, px*8, px, px);
		agsiU0[1]=agskel0.crop(px*6, px*8, px, px);
		agsiR0[1]=agskel0.crop(px*7, px*8, px, px);
		for(int i=0;i<8;i++)
			aemerge[i]=agskel0.crop(px*i, px*14, px, px);
		for(int i=8;i<16;i++)
			aemerge[i]=agskel0.crop(px*(i-8), px*15, px, px);
		aemerge[15]=agskel0.crop(px*6, px*15, px, px);
		
		//npc0
		npcUp=new BufferedImage[2];
		npcUp[0]=npc.crop(px*6, px*0, px, px);
		npcUp[1]=npc.crop(px*7, px*0, px, px);
		npcLeft=new BufferedImage[2];
		npcLeft[0]=npc.crop(px*6, px*1, px, px);
		npcLeft[1]=npc.crop(px*7, px*1, px, px);
		npcDown=new BufferedImage[2];
		npcDown[0]=npc.crop(px*4, px*0, px, px);
		npcDown[1]=npc.crop(px*5, px*0, px, px);
		npcRight=new BufferedImage[2];
		npcRight[0]=npc.crop(px*4, px*1, px, px);
		npcRight[1]=npc.crop(px*5, px*1, px, px);
		
		//npc0
		npc1Up=new BufferedImage[2];
		npc1Up[0]=npc.crop(px*6, px*2, px, px);
		npc1Up[1]=npc.crop(px*7, px*2, px, px);
		npc1Left=new BufferedImage[2];
		npc1Left[0]=npc.crop(px*6, px*3, px, px);
		npc1Left[1]=npc.crop(px*7, px*3, px, px);
		npc1Down=new BufferedImage[2];
		npc1Down[0]=npc.crop(px*4, px*2, px, px);
		npc1Down[1]=npc.crop(px*5, px*2, px, px);
		npc1Right=new BufferedImage[2];
		npc1Right[0]=npc.crop(px*4, px*3, px, px);
		npc1Right[1]=npc.crop(px*5, px*3, px, px);
		
		//grim reaper
		playerUp=new BufferedImage[16];
		for(int i=0;i<16;i++)
			playerUp[i]=player.crop(px*i, px*0, px, px);
		playerLeft=new BufferedImage[16];
		for(int i=0;i<16;i++)
			playerLeft[i]=player.crop(px*i, px*1, px, px);
		playerDown=new BufferedImage[16];
		for(int i=0;i<16;i++)
			playerDown[i]=player.crop(px*i, px*2, px, px);
		playerRight=new BufferedImage[16];
		for(int i=0;i<16;i++)
			playerRight[i]=player.crop(px*i, px*3, px, px);
		playerIdle=new BufferedImage[16];
		for(int i=0;i<16;i++)
			playerIdle[i]=player.crop(px*i, px*4, px, px);
		
		//sans
		sansUp=new BufferedImage[4];
		for(int i=0;i<4;i++)
			sansUp[i]=sans.crop(px*i, px*3, px, px);
		sansDown=new BufferedImage[4];
		for(int i=0;i<4;i++)
			sansDown[i]=sans.crop(px*i, px*2, px, px);
		sansLeft=new BufferedImage[4];
		for(int i=0;i<4;i++)
			sansLeft[i]=sans.crop(px*i, px*5, px, px);
		sansRight=new BufferedImage[4];
		for(int i=0;i<4;i++)
			sansRight[i]=sans.crop(px*i, px*4, px, px);
		sansIdle=new BufferedImage[16];
		sansIdle[0]=sans.crop(px*0, px*0, px, px);
		sansIdle[1]=sans.crop(px*1, px*0, px, px);
		sansIdle[2]=sans.crop(px*2, px*0, px, px);
		sansIdle[3]=sans.crop(px*3, px*0, px, px);
		
		sansIdle[4]=sans.crop(px*0, px*0, px, px);
		sansIdle[5]=sans.crop(px*1, px*0, px, px);
		sansIdle[6]=sans.crop(px*2, px*0, px, px);
		sansIdle[7]=sans.crop(px*3, px*0, px, px);
		
		sansIdle[8]=sans.crop(px*0, px*0, px, px);
		sansIdle[9]=sans.crop(px*1, px*0, px, px);
		sansIdle[10]=sans.crop(px*2, px*0, px, px);
		sansIdle[11]=sans.crop(px*3, px*0, px, px);
		
		sansIdle[12]=sans.crop(px*0, px*1, px, px);
		sansIdle[13]=sans.crop(px*1, px*1, px, px);
		sansIdle[14]=sans.crop(px*2, px*1, px, px);
		sansIdle[15]=sans.crop(px*3, px*1, px, px);
		
		sansHurt=sans.crop(px*0, px*6, px, px);
	}
}
