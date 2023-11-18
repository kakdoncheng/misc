package havocpixel.gfx;

import havocpixel.CoreLauncher;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class CoreAssets {
	
	private final static int px=32;
	public static BufferedImage[] defaultTile;
	public static BufferedImage[] defaultTileset;
	public static BufferedImage title,pak,blackout,hurt,hurt2,hurtd,crit;
	
	//skeleton
	public static BufferedImage[] sUp,sLeft,sDown,sRight,AsUp,AsLeft,AsDown,AsRight;
	public static BufferedImage[] siU,siL,siD,siR,sEmerge;
	//armor skeleton
	public static BufferedImage[] asUp,asLeft,asDown,asRight,aAsUp,aAsLeft,aAsDown,aAsRight;
	public static BufferedImage[] asiU,asiL,asiD,asiR,asEmerge;
	//hooded skeleton
	public static BufferedImage[] hsUp,hsLeft,hsDown,hsRight,hAsUp,hAsLeft,hAsDown,hAsRight;
	public static BufferedImage[] hsiU,hsiL,hsiD,hsiR,hsEmerge;
	//zombie
	public static BufferedImage[] zsUp,zsLeft,zsDown,zsRight,zAsUp,zAsLeft,zAsDown,zAsRight;
	public static BufferedImage[] zsiU,zsiL,zsiD,zsiR,zsEmerge;
	//imp
	public static BufferedImage[] iUp,iLeft,iDown,iRight,AiUp,AiLeft,AiDown,AiRight;
	public static BufferedImage[] iiU,iiL,iiD,iiR,iEmerge;
	public static BufferedImage[] iDeath;
	//reanimated imp
	public static BufferedImage[] riUp,riLeft,riDown,riRight,rAiUp,rAiLeft,rAiDown,rAiRight;
	public static BufferedImage[] riiU,riiL,riiD,riiR,riEmerge;
	public static BufferedImage[] riDeath;
	
	//hellsing
	public static BufferedImage[] hiUp,hiLeft,hiDown,hiRight,hAiUp,hAiLeft,hAiDown,hAiRight;
	public static BufferedImage[] hiiU,hiiL,hiiD,hiiR,hiEmerge;
	public static BufferedImage[] hiDeath;
	//reanimated hellsing
	public static BufferedImage[] rhiUp,rhiLeft,rhiDown,rhiRight,rhAiUp,rhAiLeft,rhAiDown,rhAiRight;
	public static BufferedImage[] rhiiU,rhiiL,rhiiD,rhiiR,rhiEmerge;
	public static BufferedImage[] rhiDeath;
	
	//navigator cultist
	public static BufferedImage[] ncUp,ncLeft,ncDown,ncRight,nAcUp,nAcLeft,nAcDown,nAcRight;
	public static BufferedImage[] nciU,nciL,nciD,nciR,ncEmerge;
	public static BufferedImage[] ncDeath;
	//shotgun cultist
	public static BufferedImage[] cUp,cLeft,cDown,cRight,AcUp,AcLeft,AcDown,AcRight;
	public static BufferedImage[] ciU,ciL,ciD,ciR,cEmerge;
	public static BufferedImage[] cDeath, cReload, cReloadR;
	//rifle cultist
	public static BufferedImage[] rcUp,rcLeft,rcDown,rcRight,rAcUp,rAcLeft,rAcDown,rAcRight;
	public static BufferedImage[] rciU,rciL,rciD,rciR,rcEmerge;
	public static BufferedImage[] rcDeath;
	//melee cultist
	public static BufferedImage[] mcUp,mcLeft,mcDown,mcRight,mAcUp,mAcLeft,mAcDown,mAcRight;
	public static BufferedImage[] mciU,mciL,mciD,mciR,mcEmerge;
	public static BufferedImage[] mcDeath;
	//possessed human
	public static BufferedImage[] phUp,phLeft,phDown,phRight,AphUp,AphLeft,AphDown,AphRight;
	public static BufferedImage[] phiU,phiL,phiD,phiR,phEmerge;
	public static BufferedImage[] phDeath;
	//armored ph
	public static BufferedImage[] aphUp,aphLeft,aphDown,aphRight,aAphUp,aAphLeft,aAphDown,aAphRight;
	public static BufferedImage[] aphiU,aphiL,aphiD,aphiR,aphEmerge;
	public static BufferedImage[] aphDeath;
	//zwei jayde
	public static BufferedImage[] zU,zL,zD,zR;
	public static BufferedImage[][] zAU,zAL,zAD,zAR;
	public static BufferedImage[] ziU,ziL,ziD,ziR,zDeath,zSpawn;
	//bunny
	public static BufferedImage[] bUp,bLeft,bDown,bRight,bDeath;
	public static BufferedImage[] biU,biL,biD,biR;
	//deer
	public static BufferedImage[] dbUp,dbLeft,dbDown,dbRight,dbDeath;
	public static BufferedImage[] dbiU,dbiL,dbiD,dbiR;
	//incubus
	public static BufferedImage[] incubus, incubusDeath;
	//reanimated incubus
	public static BufferedImage[] rincubus, rincubusSpawn;
	
	//ghost
	public static BufferedImage[] ghost, wisp, warp, warpR;
	//revenant
	public static BufferedImage[] revenant, reDeath, reSpawn;
	
	//gibbed corpse
	public static BufferedImage[][] gibCorpse;
	public static BufferedImage[][] bloodEffect;
	public static BufferedImage[] blood;
	public static BufferedImage[] gib;
	public static BufferedImage bGib, helmGib;
	
	//projectiles
	public static BufferedImage knife,bolt,bullet,bigFireball,fireball,grenade,rocket,cursedKnife;
	public static BufferedImage[] explosion;
	public static BufferedImage[][] bulletImpact, fireballImpact;
	public static BufferedImage[] fire;
	//smoke
	public static BufferedImage[] smoke, bigSmoke, bigSmokeEffect;
	//moveable objects
	public static BufferedImage steelBarrel;
	//immoveable objects
	public static BufferedImage[] tree;
	public static BufferedImage[] ctree;
	
	public static void registerFont(String path){
		InputStream is = CoreLauncher.class.getResourceAsStream(path);
		Font uniFont = null;
		try {
			uniFont = Font.createFont(Font.TRUETYPE_FONT,is);
		} catch (FontFormatException | IOException e) {
			System.out.print("havocpixel.CoreAssets:WARNING: Exception loading font "+path+".\n");
			e.printStackTrace();
			return;
		}
		Font f = uniFont.deriveFont(24f);
		GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(f);
	}
	
	public static void load() {
		System.out.print("havocpixel.CoreAssets:INFO: Loading fonts.\n");
		registerFont("/RESEGRG_.TTF");
		
		System.out.print("havocpixel.CoreAssets:INFO: Loading default textures.\n");
		Texture defTile=new Texture(Texture.loadImage("/txr/default64x64.png"));
		Texture defaultTileset=new Texture(Texture.loadImage("/txr/default_tileset.png"));
		Texture blackout=new Texture(Texture.loadImage("/txr/blackout.png"));
		Texture blood=new Texture(Texture.loadImage("/txr/blood.png"));
		Texture bunny=new Texture(Texture.loadImage("/txr/bunny.png"));
		Texture deer=new Texture(Texture.loadImage("/txr/deer_calciumtrice_mod_alt.png"));
		Texture bulletImpact=new Texture(Texture.loadImage("/txr/bullet_impact0.png"));
		Texture fireImpact=new Texture(Texture.loadImage("/txr/fireball_impact.png"));
		Texture hskel=new Texture(Texture.loadImage("/txr/hood_skel_alt.png"));
		Texture skel=new Texture(Texture.loadImage("/txr/skel.png"));
		Texture askel=new Texture(Texture.loadImage("/txr/armor_skel.png"));
		Texture zombie=new Texture(Texture.loadImage("/txr/zombie.png"));
		Texture cultist=new Texture(Texture.loadImage("/txr/shotgun_cultist.png"));
		Texture cultistR=new Texture(Texture.loadImage("/txr/shotgun_cultist_reload.png"));
		Texture cultistRR=new Texture(Texture.loadImage("/txr/shotgun_cultist_reload_right.png"));
		Texture rcultist=new Texture(Texture.loadImage("/txr/rifle_cultist_alt.png"));
		Texture mcultist=new Texture(Texture.loadImage("/txr/cultist.png"));
		Texture ncultist=new Texture(Texture.loadImage("/txr/navigator_cultist.png"));
		Texture aphuman0=new Texture(Texture.loadImage("/txr/armored_phuman.png"));
		Texture phuman0=new Texture(Texture.loadImage("/txr/phuman0.png"));
		//Texture phuman1=new Texture(Texture.loadImage("/txr/phuman1.png"));
		Texture imp=new Texture(Texture.loadImage("/txr/imp.png"));
		Texture rimp=new Texture(Texture.loadImage("/txr/reanimated_imp.png"));
		Texture hellsing=new Texture(Texture.loadImage("/txr/hellsing.png"));
		Texture rhellsing=new Texture(Texture.loadImage("/txr/reanimated_hellsing.png"));
		Texture projectiles=new Texture(Texture.loadImage("/txr/projectiles_alt.png"));
		Texture expl=new Texture(Texture.loadImage("/txr/explosion.png"));
		Texture smoke=new Texture(Texture.loadImage("/txr/smoke.png"));
		Texture bigsmoke=new Texture(Texture.loadImage("/txr/big_smoke.png"));
		Texture fire=new Texture(Texture.loadImage("/txr/fire.png"));
		Texture ghost=new Texture(Texture.loadImage("/txr/ghost.png"));
		Texture wisp=new Texture(Texture.loadImage("/txr/wisp.png"));
		Texture warp=new Texture(Texture.loadImage("/txr/warp_shadow.png"));
		Texture objects=new Texture(Texture.loadImage("/txr/objects.png"));
		Texture trees=new Texture(Texture.loadImage("/txr/tree.png"));
		Texture zwei0=new Texture(Texture.loadImage("/txr/blood_zwei_alt.png"));
		Texture inc=new Texture(Texture.loadImage("/txr/incubus.png"));
		Texture rinc=new Texture(Texture.loadImage("/txr/reanimated_incubus.png"));
		Texture hurt=new Texture(Texture.loadImage("/txr/hurt.png"));
		Texture hurt2=new Texture(Texture.loadImage("/txr/hurt2.png"));
		Texture hurtd=new Texture(Texture.loadImage("/txr/hurt_dead.png"));
		Texture crit=new Texture(Texture.loadImage("/txr/crit.png"));
		Texture title=new Texture(Texture.loadImage("/txr/demotitle.png"));
		Texture pak=new Texture(Texture.loadImage("/txr/pressanykey.png"));
		Texture rev=new Texture(Texture.loadImage("/txr/revenant.png"));
		
		defaultTile=new BufferedImage[4];
		defaultTile[0]=defTile.crop(px*0,px*0,px,px);
		defaultTile[1]=defTile.crop(px*1,px*0,px,px);
		defaultTile[2]=defTile.crop(px*1,px*1,px,px);
		defaultTile[3]=defTile.crop(px*0,px*1,px,px);
		
		CoreAssets.hurt=hurt.crop();
		CoreAssets.hurt2=hurt2.crop();
		CoreAssets.hurtd=hurtd.crop();
		CoreAssets.crit=crit.crop();
		CoreAssets.title=title.crop();
		CoreAssets.pak=pak.crop();
		
		CoreAssets.defaultTileset=new BufferedImage[100];
		for(int y=0;y<10;y++){
			for(int x=0;x<10;x++){
				CoreAssets.defaultTileset[(y*10)+x]=defaultTileset.crop(px*x,px*y,px,px);
			}
		}
		
		//revenant
		CoreAssets.revenant=new BufferedImage[6];
		CoreAssets.reSpawn=new BufferedImage[5];
		CoreAssets.reDeath=new BufferedImage[5];
		for(int i=0;i<6;i++){
			CoreAssets.revenant[i]=rev.crop(px*i*2,px*0,px*2,px*2);
		}
		for(int i=0;i<5;i++){
			CoreAssets.reSpawn[i]=rev.crop(px*i*2,px*1*2,px*2,px*2);
		}
		for(int i=0;i<5;i++){
			CoreAssets.reDeath[i]=rev.crop(px*i*2,px*2*2,px*2,px*2);
		}
		
		//wisp
		CoreAssets.wisp=new BufferedImage[8];
		for(int i=0;i<8;i++){
			CoreAssets.wisp[i]=wisp.crop(px*i,px*0,px,px);
		}
		
		//wisp
		CoreAssets.warp=new BufferedImage[8];
		CoreAssets.warpR=new BufferedImage[8];
		for(int i=0;i<8;i++){
			CoreAssets.warp[i]=warp.crop(px*i,px*0,px,px);
			CoreAssets.warpR[i]=warp.crop(px*(warpR.length-1-i),px*0,px,px);
		}
		
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
			bUp[i]=bunny.crop(px*i, px*0, px, px);
		for(int i=0;i<8;i++)
			bLeft[i]=bunny.crop(px*i, px*1, px, px);
		for(int i=0;i<8;i++)
			bDown[i]=bunny.crop(px*i, px*2, px, px);
		for(int i=0;i<8;i++)
			bRight[i]=bunny.crop(px*i, px*3, px, px);
		for(int i=0;i<5;i++)
			bDeath[i]=bunny.crop(px*i, px*14, px, px);
		biD[0]=bunny.crop(px*4, px*11, px, px);
		biL[0]=bunny.crop(px*5, px*11, px, px);
		biU[0]=bunny.crop(px*6, px*11, px, px);
		biR[0]=bunny.crop(px*7, px*11, px, px);
		biD[1]=bunny.crop(px*4, px*8, px, px);
		biL[1]=bunny.crop(px*5, px*8, px, px);
		biU[1]=bunny.crop(px*6, px*8, px, px);
		biR[1]=bunny.crop(px*7, px*8, px, px);
		bGib=bunny.crop(px*5, px*14, px, px);
		
		//deer
		dbUp=new BufferedImage[5];
		dbDown=new BufferedImage[5];
		dbRight=new BufferedImage[5];
		dbLeft=new BufferedImage[5];
		dbiD=new BufferedImage[2];
		dbiL=new BufferedImage[2];
		dbiU=new BufferedImage[2];
		dbiR=new BufferedImage[2];
		dbDeath=new BufferedImage[5];
		for(int i=0;i<5;i++)
			dbUp[i]=deer.crop(px*i, px*0, px, px);
		for(int i=0;i<5;i++)
			dbLeft[i]=deer.crop(px*i, px*1, px, px);
		for(int i=0;i<5;i++)
			dbDown[i]=deer.crop(px*i, px*2, px, px);
		for(int i=0;i<5;i++)
			dbRight[i]=deer.crop(px*i, px*3, px, px);
		for(int i=0;i<5;i++)
			dbDeath[i]=deer.crop(px*i, px*4, px, px);
		dbiD[0]=deer.crop(px*5, px*3, px, px);
		dbiL[0]=deer.crop(px*5, px*1, px, px);
		dbiU[0]=deer.crop(px*5, px*2, px, px);
		dbiR[0]=deer.crop(px*5, px*0, px, px);
		dbiD[1]=deer.crop(px*6, px*3, px, px);
		dbiL[1]=deer.crop(px*6, px*1, px, px);
		dbiU[1]=deer.crop(px*6, px*2, px, px);
		dbiR[1]=deer.crop(px*6, px*0, px, px);
		
		//fire
		CoreAssets.fire=new BufferedImage[8];
		for(int i=0;i<8;i++){
			CoreAssets.fire[i]=fire.crop(px*i,px*1,px,px);
		}
		
		//smoke
		CoreAssets.smoke=new BufferedImage[6];
		CoreAssets.bigSmoke=new BufferedImage[8];
		CoreAssets.bigSmokeEffect=new BufferedImage[6];
		for(int i=0;i<6;i++){
			CoreAssets.smoke[i]=smoke.crop(px*i,px*0,px,px);
		}
		for(int i=0;i<8;i++){
			CoreAssets.bigSmoke[i]=smoke.crop(px*i,px*1,px,px);
		}
		for(int i=0;i<6;i++){
			CoreAssets.bigSmokeEffect[i]=bigsmoke.crop(px*i,px*0,px,px);
		}
		
		//projectiles
		CoreAssets.blackout=blackout.crop();
		CoreAssets.knife=projectiles.crop(px*0,px*0,px,px);
		CoreAssets.bolt=projectiles.crop(px*1,px*0,px,px);
		CoreAssets.bullet=projectiles.crop(px*2,px*0,px,px);
		CoreAssets.bigFireball=projectiles.crop(px*3,px*0,px,px);
		CoreAssets.fireball=projectiles.crop(px*4,px*0,px,px);
		CoreAssets.grenade=projectiles.crop(px*5,px*0,px,px);
		CoreAssets.rocket=projectiles.crop(px*6,px*0,px,px);
		CoreAssets.cursedKnife=projectiles.crop(px*0,px*1,px,px);
		
		//objects
		CoreAssets.steelBarrel=objects.crop(px*0,px*0,px,px);
		CoreAssets.tree=new BufferedImage[4];
		for(int i=0;i<4;i++){
			tree[i]=trees.crop(px*2*i, px*0, px*2, px*3);
		}
		
		explosion=new BufferedImage[10];
		for(int i=0;i<5;i++){
			explosion[i]=expl.crop(px*2*i, px*0, px*2, px*2);
		}
		for(int i=0;i<5;i++){
			explosion[i+5]=expl.crop(px*2*i, px*2, px*2, px*2);
		}
		
		//ghost
		CoreAssets.ghost=new BufferedImage[8];
		for(int i=0;i<8;i++){
			CoreAssets.ghost[i]=ghost.crop(px*i, px*0, px, px);
		}
		
		gibCorpse=new BufferedImage[4][5];
		for(int j=0;j<4;j++){
			for(int i=0;i<4;i++){
				gibCorpse[j][i]=ghost.crop(px*i, px*2, px, px);
			}
		}
		gibCorpse[0][4]=ghost.crop(px*4, px*2, px, px);
		gibCorpse[1][4]=ghost.crop(px*5, px*2, px, px);
		gibCorpse[2][4]=ghost.crop(px*6, px*2, px, px);
		gibCorpse[3][4]=ghost.crop(px*7, px*2, px, px);
		
		//gibs
		gib=new BufferedImage[24];
		for(int i=0;i<8;i++){
			gib[i]=ghost.crop(px*i, px*1, px, px);
		}
		for(int i=0;i<4;i++){
			gib[i+8]=ghost.crop(px*i, px*4, px, px);
		}
		for(int i=0;i<3;i++){
			gib[i+8+4]=ghost.crop(px*(i+5), px*10, px, px);
		}
		CoreAssets.helmGib=ghost.crop(px*4, px*4, px, px);
		
		//bullet impact
		CoreAssets.bulletImpact=new BufferedImage[2][2];
		for(int i=0;i<2;i++){
			CoreAssets.bulletImpact[0][i]=bulletImpact.crop(px*i, px*0, px, px);
		}
		for(int i=0;i<2;i++){
			CoreAssets.bulletImpact[1][i]=bulletImpact.crop(px*(i+2), px*0, px, px);
		}
		
		//fire impact
		CoreAssets.fireballImpact=new BufferedImage[2][2];
		for(int i=0;i<2;i++){
			CoreAssets.fireballImpact[0][i]=fireImpact.crop(px*i, px*0, px, px);
		}
		for(int i=0;i<2;i++){
			CoreAssets.fireballImpact[1][i]=fireImpact.crop(px*(i+2), px*0, px, px);
		}
		
		//blood
		CoreAssets.bloodEffect=new BufferedImage[4][4];
		for(int i=0;i<4;i++){
			bloodEffect[0][i]=blood.crop(px*i, px*4, px, px);
		}
		for(int i=0;i<4;i++){
			bloodEffect[1][i]=blood.crop(px*(i+4), px*4, px, px);
		}
		for(int i=0;i<4;i++){
			bloodEffect[2][i]=blood.crop(px*i, px*5, px, px);
		}
		for(int i=0;i<4;i++){
			bloodEffect[3][i]=blood.crop(px*(i+4), px*5, px, px);
		}
		CoreAssets.blood=new BufferedImage[32];
		for(int j=0;j<4;j++){
			for(int i=0;i<8;i++){
				CoreAssets.blood[i+(j*8)]=blood.crop(px*i, px*j, px, px);
			}
		}
		
		//incubus
		incubus=new BufferedImage[8];
		incubusDeath=new BufferedImage[5];
		for(int i=0;i<8;i++){
			incubus[i]=inc.crop(px*i, px*0, px, px);
		}
		for(int i=0;i<5;i++){
			incubusDeath[i]=ghost.crop(px*i, px*9, px, px);
		}
		
		//incubus
		rincubus=new BufferedImage[8];
		rincubusSpawn=new BufferedImage[5];
		for(int i=0;i<8;i++){
			rincubus[i]=rinc.crop(px*i, px*0, px, px);
		}
		for(int i=0;i<5;i++){
			rincubusSpawn[i]=rinc.crop(px*i, px*1, px, px);
		}
		
		//skeleton
		sUp=new BufferedImage[8];
		sDown=new BufferedImage[8];
		sRight=new BufferedImage[8];
		sLeft=new BufferedImage[8];
		AsUp=new BufferedImage[4];
		AsDown=new BufferedImage[4];
		AsRight=new BufferedImage[4];
		AsLeft=new BufferedImage[4];
		siD=new BufferedImage[2];
		siL=new BufferedImage[2];
		siU=new BufferedImage[2];
		siR=new BufferedImage[2];
		sEmerge=new BufferedImage[16];
		for(int i=0;i<8;i++)
			sUp[i]=skel.crop(px*i, px*0, px, px);
		for(int i=0;i<8;i++)
			sLeft[i]=skel.crop(px*i, px*1, px, px);
		for(int i=0;i<8;i++)
			sDown[i]=skel.crop(px*i, px*2, px, px);
		for(int i=0;i<8;i++)
			sRight[i]=skel.crop(px*i, px*3, px, px);
		for(int i=0;i<4;i++)
			AsUp[i]=skel.crop(px*i, px*4, px, px*2);
		for(int i=0;i<4;i++)
			AsLeft[i]=skel.crop(px*i*2, px*6, px*2, px);
		for(int i=0;i<4;i++)
			AsDown[i]=skel.crop(px*i, px*7, px, px*2);
		for(int i=0;i<4;i++)
			AsRight[i]=skel.crop(px*i*2, px*9, px*2, px);
		siD[0]=skel.crop(px*4, px*7, px, px);
		siL[0]=skel.crop(px*5, px*7, px, px);
		siU[0]=skel.crop(px*6, px*7, px, px);
		siR[0]=skel.crop(px*7, px*7, px, px);
		siD[1]=skel.crop(px*4, px*8, px, px);
		siL[1]=skel.crop(px*5, px*8, px, px);
		siU[1]=skel.crop(px*6, px*8, px, px);
		siR[1]=skel.crop(px*7, px*8, px, px);
		for(int i=0;i<8;i++)
			sEmerge[i]=skel.crop(px*i, px*10, px, px);
		for(int i=8;i<16;i++)
			sEmerge[i]=skel.crop(px*(i-8), px*11, px, px);
		sEmerge[15]=skel.crop(px*6, px*11, px, px);
		
		//armor skeleton
		asUp=new BufferedImage[8];
		asDown=new BufferedImage[8];
		asRight=new BufferedImage[8];
		asLeft=new BufferedImage[8];
		aAsUp=new BufferedImage[4];
		aAsDown=new BufferedImage[4];
		aAsRight=new BufferedImage[4];
		aAsLeft=new BufferedImage[4];
		asiD=new BufferedImage[2];
		asiL=new BufferedImage[2];
		asiU=new BufferedImage[2];
		asiR=new BufferedImage[2];
		asEmerge=new BufferedImage[16];
		for(int i=0;i<8;i++)
			asUp[i]=askel.crop(px*i, px*0, px, px);
		for(int i=0;i<8;i++)
			asLeft[i]=askel.crop(px*i, px*1, px, px);
		for(int i=0;i<8;i++)
			asDown[i]=askel.crop(px*i, px*2, px, px);
		for(int i=0;i<8;i++)
			asRight[i]=askel.crop(px*i, px*3, px, px);
		for(int i=0;i<4;i++)
			aAsUp[i]=askel.crop(px*i, px*4, px, px*2);
		for(int i=0;i<4;i++)
			aAsLeft[i]=askel.crop(px*i*2, px*6, px*2, px);
		for(int i=0;i<4;i++)
			aAsDown[i]=askel.crop(px*i, px*7, px, px*2);
		for(int i=0;i<4;i++)
			aAsRight[i]=askel.crop(px*i*2, px*9, px*2, px);
		asiD[0]=askel.crop(px*4, px*7, px, px);
		asiL[0]=askel.crop(px*5, px*7, px, px);
		asiU[0]=askel.crop(px*6, px*7, px, px);
		asiR[0]=askel.crop(px*7, px*7, px, px);
		asiD[1]=askel.crop(px*4, px*8, px, px);
		asiL[1]=askel.crop(px*5, px*8, px, px);
		asiU[1]=askel.crop(px*6, px*8, px, px);
		asiR[1]=askel.crop(px*7, px*8, px, px);
		for(int i=0;i<8;i++)
			asEmerge[i]=askel.crop(px*i, px*10, px, px);
		for(int i=8;i<16;i++)
			asEmerge[i]=askel.crop(px*(i-8), px*11, px, px);
		asEmerge[15]=askel.crop(px*6, px*11, px, px);
		
		//hooded skeleton
		hsUp=new BufferedImage[8];
		hsDown=new BufferedImage[8];
		hsRight=new BufferedImage[8];
		hsLeft=new BufferedImage[8];
		hAsUp=new BufferedImage[4];
		hAsDown=new BufferedImage[4];
		hAsRight=new BufferedImage[4];
		hAsLeft=new BufferedImage[4];
		hsiD=new BufferedImage[2];
		hsiL=new BufferedImage[2];
		hsiU=new BufferedImage[2];
		hsiR=new BufferedImage[2];
		hsEmerge=new BufferedImage[16];
		for(int i=0;i<8;i++)
			hsUp[i]=hskel.crop(px*i, px*0, px, px);
		for(int i=0;i<8;i++)
			hsLeft[i]=hskel.crop(px*i, px*1, px, px);
		for(int i=0;i<8;i++)
			hsDown[i]=hskel.crop(px*i, px*2, px, px);
		for(int i=0;i<8;i++)
			hsRight[i]=hskel.crop(px*i, px*3, px, px);
		for(int i=0;i<4;i++)
			hAsUp[i]=hskel.crop(px*i, px*4, px, px*2);
		for(int i=0;i<4;i++)
			hAsLeft[i]=hskel.crop(px*i*2, px*6, px*2, px);
		for(int i=0;i<4;i++)
			hAsDown[i]=hskel.crop(px*i, px*7, px, px*2);
		for(int i=0;i<4;i++)
			hAsRight[i]=hskel.crop(px*i*2, px*9, px*2, px);
		hsiD[0]=hskel.crop(px*4, px*7, px, px);
		hsiL[0]=hskel.crop(px*5, px*7, px, px);
		hsiU[0]=hskel.crop(px*6, px*7, px, px);
		hsiR[0]=hskel.crop(px*7, px*7, px, px);
		hsiD[1]=hskel.crop(px*4, px*8, px, px);
		hsiL[1]=hskel.crop(px*5, px*8, px, px);
		hsiU[1]=hskel.crop(px*6, px*8, px, px);
		hsiR[1]=hskel.crop(px*7, px*8, px, px);
		for(int i=0;i<8;i++)
			hsEmerge[i]=hskel.crop(px*i, px*10, px, px);
		for(int i=8;i<16;i++)
			hsEmerge[i]=hskel.crop(px*(i-8), px*11, px, px);
		hsEmerge[15]=hskel.crop(px*6, px*11, px, px);
		
		//zombie
		zsUp=new BufferedImage[8];
		zsDown=new BufferedImage[8];
		zsRight=new BufferedImage[8];
		zsLeft=new BufferedImage[8];
		zAsUp=new BufferedImage[4];
		zAsDown=new BufferedImage[4];
		zAsRight=new BufferedImage[4];
		zAsLeft=new BufferedImage[4];
		zsiD=new BufferedImage[2];
		zsiL=new BufferedImage[2];
		zsiU=new BufferedImage[2];
		zsiR=new BufferedImage[2];
		zsEmerge=new BufferedImage[16];
		for(int i=0;i<8;i++)
			zsUp[i]=zombie.crop(px*i, px*0, px, px);
		for(int i=0;i<8;i++)
			zsLeft[i]=zombie.crop(px*i, px*1, px, px);
		for(int i=0;i<8;i++)
			zsDown[i]=zombie.crop(px*i, px*2, px, px);
		for(int i=0;i<8;i++)
			zsRight[i]=zombie.crop(px*i, px*3, px, px);
		for(int i=0;i<4;i++)
			zAsUp[i]=zombie.crop(px*i, px*4, px, px*2);
		for(int i=0;i<4;i++)
			zAsLeft[i]=zombie.crop(px*i*2, px*6, px*2, px);
		for(int i=0;i<4;i++)
			zAsDown[i]=zombie.crop(px*i, px*7, px, px*2);
		for(int i=0;i<4;i++)
			zAsRight[i]=zombie.crop(px*i*2, px*9, px*2, px);
		zsiD[0]=zombie.crop(px*4, px*7, px, px);
		zsiL[0]=zombie.crop(px*5, px*7, px, px);
		zsiU[0]=zombie.crop(px*6, px*7, px, px);
		zsiR[0]=zombie.crop(px*7, px*7, px, px);
		zsiD[1]=zombie.crop(px*4, px*8, px, px);
		zsiL[1]=zombie.crop(px*5, px*8, px, px);
		zsiU[1]=zombie.crop(px*6, px*8, px, px);
		zsiR[1]=zombie.crop(px*7, px*8, px, px);
		for(int i=0;i<8;i++)
			zsEmerge[i]=zombie.crop(px*i, px*10, px, px);
		for(int i=8;i<16;i++)
			zsEmerge[i]=zombie.crop(px*(i-8), px*11, px, px);
		zsEmerge[15]=zombie.crop(px*6, px*11, px, px);
		
		//imp
		iUp=new BufferedImage[8];
		iDown=new BufferedImage[8];
		iRight=new BufferedImage[8];
		iLeft=new BufferedImage[8];
		AiUp=new BufferedImage[4];
		AiDown=new BufferedImage[4];
		AiRight=new BufferedImage[4];
		AiLeft=new BufferedImage[4];
		iiD=new BufferedImage[2];
		iiL=new BufferedImage[2];
		iiU=new BufferedImage[2];
		iiR=new BufferedImage[2];
		iEmerge=new BufferedImage[8];
		iDeath=new BufferedImage[5];
		for(int i=0;i<8;i++)
			iUp[i]=imp.crop(px*i, px*0, px, px);
		for(int i=0;i<8;i++)
			iLeft[i]=imp.crop(px*i, px*1, px, px);
		for(int i=0;i<8;i++)
			iDown[i]=imp.crop(px*i, px*2, px, px);
		for(int i=0;i<8;i++)
			iRight[i]=imp.crop(px*i, px*3, px, px);
		for(int i=0;i<4;i++)
			AiUp[i]=imp.crop(px*i, px*4, px, px*2);
		for(int i=0;i<4;i++)
			AiLeft[i]=imp.crop(px*i*2, px*6, px*2, px);
		for(int i=0;i<4;i++)
			AiDown[i]=imp.crop(px*i, px*7, px, px*2);
		for(int i=0;i<4;i++)
			AiRight[i]=imp.crop(px*i*2, px*9, px*2, px);
		iiD[0]=imp.crop(px*4, px*7, px, px);
		iiL[0]=imp.crop(px*5, px*7, px, px);
		iiU[0]=imp.crop(px*6, px*7, px, px);
		iiR[0]=imp.crop(px*7, px*7, px, px);
		iiD[1]=imp.crop(px*4, px*8, px, px);
		iiL[1]=imp.crop(px*5, px*8, px, px);
		iiU[1]=imp.crop(px*6, px*8, px, px);
		iiR[1]=imp.crop(px*7, px*8, px, px);
		for(int i=0;i<8;i++)
			iEmerge[i]=imp.crop(px*i, px*10, px, px);
		for(int i=0;i<5;i++){
			iDeath[i]=ghost.crop(px*i, px*3, px, px);
		}
		
		//reanimated imp
		riUp=new BufferedImage[8];
		riDown=new BufferedImage[8];
		riRight=new BufferedImage[8];
		riLeft=new BufferedImage[8];
		rAiUp=new BufferedImage[4];
		rAiDown=new BufferedImage[4];
		rAiRight=new BufferedImage[4];
		rAiLeft=new BufferedImage[4];
		riiD=new BufferedImage[2];
		riiL=new BufferedImage[2];
		riiU=new BufferedImage[2];
		riiR=new BufferedImage[2];
		riEmerge=new BufferedImage[5];
		riDeath=new BufferedImage[5];
		for(int i=0;i<8;i++)
			riUp[i]=rimp.crop(px*i, px*0, px, px);
		for(int i=0;i<8;i++)
			riLeft[i]=rimp.crop(px*i, px*1, px, px);
		for(int i=0;i<8;i++)
			riDown[i]=rimp.crop(px*i, px*2, px, px);
		for(int i=0;i<8;i++)
			riRight[i]=rimp.crop(px*i, px*3, px, px);
		for(int i=0;i<4;i++)
			rAiUp[i]=rimp.crop(px*i, px*4, px, px*2);
		for(int i=0;i<4;i++)
			rAiLeft[i]=rimp.crop(px*i*2, px*6, px*2, px);
		for(int i=0;i<4;i++)
			rAiDown[i]=rimp.crop(px*i, px*7, px, px*2);
		for(int i=0;i<4;i++)
			rAiRight[i]=rimp.crop(px*i*2, px*9, px*2, px);
		riiD[0]=rimp.crop(px*4, px*7, px, px);
		riiL[0]=rimp.crop(px*5, px*7, px, px);
		riiU[0]=rimp.crop(px*6, px*7, px, px);
		riiR[0]=rimp.crop(px*7, px*7, px, px);
		riiD[1]=rimp.crop(px*4, px*8, px, px);
		riiL[1]=rimp.crop(px*5, px*8, px, px);
		riiU[1]=rimp.crop(px*6, px*8, px, px);
		riiR[1]=rimp.crop(px*7, px*8, px, px);
		for(int i=0;i<5;i++)
			riEmerge[i]=rimp.crop(px*i, px*10, px, px);
		for(int i=0;i<5;i++){
			riDeath[i]=ghost.crop(px*i, px*3, px, px);
		}
		
		//hellsing
		hiUp=new BufferedImage[8];
		hiDown=new BufferedImage[8];
		hiRight=new BufferedImage[8];
		hiLeft=new BufferedImage[8];
		hAiUp=new BufferedImage[5];
		hAiDown=new BufferedImage[5];
		hAiRight=new BufferedImage[5];
		hAiLeft=new BufferedImage[5];
		hiiD=new BufferedImage[2];
		hiiL=new BufferedImage[2];
		hiiU=new BufferedImage[2];
		hiiR=new BufferedImage[2];
		hiEmerge=new BufferedImage[8];
		hiDeath=new BufferedImage[5];
		for(int i=0;i<8;i++)
			hiUp[i]=hellsing.crop(px*i, px*0, px, px);
		for(int i=0;i<8;i++)
			hiLeft[i]=hellsing.crop(px*i, px*1, px, px);
		for(int i=0;i<8;i++)
			hiDown[i]=hellsing.crop(px*i, px*2, px, px);
		for(int i=0;i<8;i++)
			hiRight[i]=hellsing.crop(px*i, px*3, px, px);
		
		//attack sprite fix for hellsing
		hAiUp[0]=hellsing.crop(px*0, px*4, px, px*2);
		hAiLeft[0]=hellsing.crop(px*0*2, px*6, px*2, px);
		hAiDown[0]=hellsing.crop(px*0, px*7, px, px*2);
		hAiRight[0]=hellsing.crop(px*0*2, px*9, px*2, px);
		for(int i=0;i<4;i++)
			hAiUp[i+1]=hellsing.crop(px*i, px*4, px, px*2);
		for(int i=0;i<4;i++)
			hAiLeft[i+1]=hellsing.crop(px*i*2, px*6, px*2, px);
		for(int i=0;i<4;i++)
			hAiDown[i+1]=hellsing.crop(px*i, px*7, px, px*2);
		for(int i=0;i<4;i++)
			hAiRight[i+1]=hellsing.crop(px*i*2, px*9, px*2, px);
		
		hiiD[0]=hellsing.crop(px*4, px*7, px, px);
		hiiL[0]=hellsing.crop(px*5, px*7, px, px);
		hiiU[0]=hellsing.crop(px*6, px*7, px, px);
		hiiR[0]=hellsing.crop(px*7, px*7, px, px);
		hiiD[1]=hellsing.crop(px*4, px*8, px, px);
		hiiL[1]=hellsing.crop(px*5, px*8, px, px);
		hiiU[1]=hellsing.crop(px*6, px*8, px, px);
		hiiR[1]=hellsing.crop(px*7, px*8, px, px);
		for(int i=0;i<8;i++)
			hiEmerge[i]=hellsing.crop(px*i, px*10, px, px);
		for(int i=0;i<5;i++){
			hiDeath[i]=ghost.crop(px*i, px*10, px, px);
		}
		
		
		//reanimated hellsing
		rhiUp=new BufferedImage[8];
		rhiDown=new BufferedImage[8];
		rhiRight=new BufferedImage[8];
		rhiLeft=new BufferedImage[8];
		rhAiUp=new BufferedImage[5];
		rhAiDown=new BufferedImage[5];
		rhAiRight=new BufferedImage[5];
		rhAiLeft=new BufferedImage[5];
		rhiiD=new BufferedImage[2];
		rhiiL=new BufferedImage[2];
		rhiiU=new BufferedImage[2];
		rhiiR=new BufferedImage[2];
		rhiEmerge=new BufferedImage[5];
		rhiDeath=new BufferedImage[5];
		for(int i=0;i<8;i++)
			rhiUp[i]=rhellsing.crop(px*i, px*0, px, px);
		for(int i=0;i<8;i++)
			rhiLeft[i]=rhellsing.crop(px*i, px*1, px, px);
		for(int i=0;i<8;i++)
			rhiDown[i]=rhellsing.crop(px*i, px*2, px, px);
		for(int i=0;i<8;i++)
			rhiRight[i]=rhellsing.crop(px*i, px*3, px, px);
		rhAiUp[0]=rhellsing.crop(px*0, px*4, px, px*2);
		rhAiLeft[0]=rhellsing.crop(px*0*2, px*6, px*2, px);
		rhAiDown[0]=rhellsing.crop(px*0, px*7, px, px*2);
		rhAiRight[0]=rhellsing.crop(px*0*2, px*9, px*2, px);
		for(int i=0;i<4;i++)
			rhAiUp[i+1]=rhellsing.crop(px*i, px*4, px, px*2);
		for(int i=0;i<4;i++)
			rhAiLeft[i+1]=rhellsing.crop(px*i*2, px*6, px*2, px);
		for(int i=0;i<4;i++)
			rhAiDown[i+1]=rhellsing.crop(px*i, px*7, px, px*2);
		for(int i=0;i<4;i++)
			rhAiRight[i+1]=rhellsing.crop(px*i*2, px*9, px*2, px);
				
		rhiiD[0]=rhellsing.crop(px*4, px*7, px, px);
		rhiiL[0]=rhellsing.crop(px*5, px*7, px, px);
		rhiiU[0]=rhellsing.crop(px*6, px*7, px, px);
		rhiiR[0]=rhellsing.crop(px*7, px*7, px, px);
		rhiiD[1]=rhellsing.crop(px*4, px*8, px, px);
		rhiiL[1]=rhellsing.crop(px*5, px*8, px, px);
		rhiiU[1]=rhellsing.crop(px*6, px*8, px, px);
		rhiiR[1]=rhellsing.crop(px*7, px*8, px, px);
		for(int i=0;i<5;i++)
			rhiEmerge[i]=rhellsing.crop(px*i, px*10, px, px);
		for(int i=0;i<5;i++){
			rhiDeath[i]=ghost.crop(px*i, px*10, px, px);
		}
		
		
		//navigator cultist
		ncUp=new BufferedImage[8];
		ncDown=new BufferedImage[8];
		ncRight=new BufferedImage[8];
		ncLeft=new BufferedImage[8];
		nAcUp=new BufferedImage[4];
		nAcDown=new BufferedImage[4];
		nAcRight=new BufferedImage[4];
		nAcLeft=new BufferedImage[4];
		nciD=new BufferedImage[2];
		nciL=new BufferedImage[2];
		nciU=new BufferedImage[2];
		nciR=new BufferedImage[2];
		ncEmerge=new BufferedImage[8];
		ncDeath=new BufferedImage[5];
		for(int i=0;i<8;i++)
			ncUp[i]=ncultist.crop(px*i, px*0, px, px);
		for(int i=0;i<8;i++)
			ncLeft[i]=ncultist.crop(px*i, px*1, px, px);
		for(int i=0;i<8;i++)
			ncDown[i]=ncultist.crop(px*i, px*2, px, px);
		for(int i=0;i<8;i++)
			ncRight[i]=ncultist.crop(px*i, px*3, px, px);
		for(int i=0;i<4;i++)
			nAcUp[i]=ncultist.crop(px*i, px*4, px, px*2);
		for(int i=0;i<4;i++)
			nAcLeft[i]=ncultist.crop(px*i*2, px*6, px*2, px);
		for(int i=0;i<4;i++)
			nAcDown[i]=ncultist.crop(px*i, px*7, px, px*2);
		for(int i=0;i<4;i++)
			nAcRight[i]=ncultist.crop(px*i*2, px*9, px*2, px);
		nciD[0]=ncultist.crop(px*4, px*7, px, px);
		nciL[0]=ncultist.crop(px*5, px*7, px, px);
		nciU[0]=ncultist.crop(px*6, px*7, px, px);
		nciR[0]=ncultist.crop(px*7, px*7, px, px);
		nciD[1]=ncultist.crop(px*4, px*8, px, px);
		nciL[1]=ncultist.crop(px*5, px*8, px, px);
		nciU[1]=ncultist.crop(px*6, px*8, px, px);
		nciR[1]=ncultist.crop(px*7, px*8, px, px);
		for(int i=0;i<8;i++)
			ncEmerge[i]=ncultist.crop(px*i, px*10, px, px);
		for(int i=0;i<5;i++){
			ncDeath[i]=ghost.crop(px*i, px*12, px, px);
		}
		
		//shotgun cultist
		cUp=new BufferedImage[8];
		cDown=new BufferedImage[8];
		cRight=new BufferedImage[8];
		cLeft=new BufferedImage[8];
		AcUp=new BufferedImage[4];
		AcDown=new BufferedImage[4];
		AcRight=new BufferedImage[4];
		AcLeft=new BufferedImage[4];
		ciD=new BufferedImage[2];
		ciL=new BufferedImage[2];
		ciU=new BufferedImage[2];
		ciR=new BufferedImage[2];
		cEmerge=new BufferedImage[8];
		cDeath=new BufferedImage[5];
		for(int i=0;i<8;i++)
			cUp[i]=cultist.crop(px*i, px*0, px, px);
		for(int i=0;i<8;i++)
			cLeft[i]=cultist.crop(px*i, px*1, px, px);
		for(int i=0;i<8;i++)
			cDown[i]=cultist.crop(px*i, px*2, px, px);
		for(int i=0;i<8;i++)
			cRight[i]=cultist.crop(px*i, px*3, px, px);
		for(int i=0;i<4;i++)
			AcUp[i]=cultist.crop(px*i, px*4, px, px*2);
		for(int i=0;i<4;i++)
			AcLeft[i]=cultist.crop(px*i*2, px*6, px*2, px);
		for(int i=0;i<4;i++)
			AcDown[i]=cultist.crop(px*i, px*7, px, px*2);
		for(int i=0;i<4;i++)
			AcRight[i]=cultist.crop(px*i*2, px*9, px*2, px);
		ciD[0]=cultist.crop(px*4, px*7, px, px);
		ciL[0]=cultist.crop(px*5, px*7, px, px);
		ciU[0]=cultist.crop(px*6, px*7, px, px);
		ciR[0]=cultist.crop(px*7, px*7, px, px);
		ciD[1]=cultist.crop(px*4, px*8, px, px);
		ciL[1]=cultist.crop(px*5, px*8, px, px);
		ciU[1]=cultist.crop(px*6, px*8, px, px);
		ciR[1]=cultist.crop(px*7, px*8, px, px);
		for(int i=0;i<8;i++)
			cEmerge[i]=cultist.crop(px*i, px*10, px, px);
		for(int i=0;i<5;i++){
			cDeath[i]=ghost.crop(px*i, px*5, px, px);
		}
		
		//shotgun cultist reload
		cReload=new BufferedImage[6];
		for(int i=0;i<4;i++)
			cReload[i]=cultistR.crop(px*0, px*i, px*2, px);
		cReload[4]=cReload[5]=cReload[0];
		
		cReloadR=new BufferedImage[6];
		for(int i=0;i<4;i++)
			cReloadR[i]=cultistRR.crop(px*0, px*i, px*2, px);
		cReloadR[4]=cReloadR[5]=cReloadR[0];
		
		//rifle cultist
		rcUp=new BufferedImage[8];
		rcDown=new BufferedImage[8];
		rcRight=new BufferedImage[8];
		rcLeft=new BufferedImage[8];
		rAcUp=new BufferedImage[4];
		rAcDown=new BufferedImage[4];
		rAcRight=new BufferedImage[4];
		rAcLeft=new BufferedImage[4];
		rciD=new BufferedImage[2];
		rciL=new BufferedImage[2];
		rciU=new BufferedImage[2];
		rciR=new BufferedImage[2];
		rcEmerge=new BufferedImage[8];
		rcDeath=new BufferedImage[5];
		for(int i=0;i<8;i++)
			rcUp[i]=rcultist.crop(px*i, px*0, px, px);
		for(int i=0;i<8;i++)
			rcLeft[i]=rcultist.crop(px*i, px*1, px, px);
		for(int i=0;i<8;i++)
			rcDown[i]=rcultist.crop(px*i, px*2, px, px);
		for(int i=0;i<8;i++)
			rcRight[i]=rcultist.crop(px*i, px*3, px, px);
		for(int i=0;i<4;i++)
			rAcUp[i]=rcultist.crop(px*i, px*4, px, px*2);
		for(int i=0;i<4;i++)
			rAcLeft[i]=rcultist.crop(px*i*2, px*6, px*2, px);
		for(int i=0;i<4;i++)
			rAcDown[i]=rcultist.crop(px*i, px*7, px, px*2);
		for(int i=0;i<4;i++)
			rAcRight[i]=rcultist.crop(px*i*2, px*9, px*2, px);
		rciD[0]=rcultist.crop(px*4, px*7, px, px);
		rciL[0]=rcultist.crop(px*5, px*7, px, px);
		rciU[0]=rcultist.crop(px*6, px*7, px, px);
		rciR[0]=rcultist.crop(px*7, px*7, px, px);
		rciD[1]=rcultist.crop(px*4, px*8, px, px);
		rciL[1]=rcultist.crop(px*5, px*8, px, px);
		rciU[1]=rcultist.crop(px*6, px*8, px, px);
		rciR[1]=rcultist.crop(px*7, px*8, px, px);
		for(int i=0;i<8;i++)
			rcEmerge[i]=rcultist.crop(px*i, px*10, px, px);
		for(int i=0;i<5;i++){
			rcDeath[i]=ghost.crop(px*i, px*5, px, px);
		}
		
		//possessed human
		phUp=new BufferedImage[8];
		phDown=new BufferedImage[8];
		phRight=new BufferedImage[8];
		phLeft=new BufferedImage[8];
		AphUp=new BufferedImage[4];
		AphDown=new BufferedImage[4];
		AphRight=new BufferedImage[4];
		AphLeft=new BufferedImage[4];
		phiD=new BufferedImage[2];
		phiL=new BufferedImage[2];
		phiU=new BufferedImage[2];
		phiR=new BufferedImage[2];
		phEmerge=new BufferedImage[8];
		phDeath=new BufferedImage[5];
		for(int i=0;i<8;i++)
			phUp[i]=phuman0.crop(px*i, px*0, px, px);
		for(int i=0;i<8;i++)
			phLeft[i]=phuman0.crop(px*i, px*1, px, px);
		for(int i=0;i<8;i++)
			phDown[i]=phuman0.crop(px*i, px*2, px, px);
		for(int i=0;i<8;i++)
			phRight[i]=phuman0.crop(px*i, px*3, px, px);
		for(int i=0;i<4;i++)
			AphUp[i]=phuman0.crop(px*i, px*4, px, px*2);
		for(int i=0;i<4;i++)
			AphLeft[i]=phuman0.crop(px*i*2, px*6, px*2, px);
		for(int i=0;i<4;i++)
			AphDown[i]=phuman0.crop(px*i, px*7, px, px*2);
		for(int i=0;i<4;i++)
			AphRight[i]=phuman0.crop(px*i*2, px*9, px*2, px);
		phiD[0]=phuman0.crop(px*4, px*7, px, px);
		phiL[0]=phuman0.crop(px*5, px*7, px, px);
		phiU[0]=phuman0.crop(px*6, px*7, px, px);
		phiR[0]=phuman0.crop(px*7, px*7, px, px);
		phiD[1]=phuman0.crop(px*4, px*8, px, px);
		phiL[1]=phuman0.crop(px*5, px*8, px, px);
		phiU[1]=phuman0.crop(px*6, px*8, px, px);
		phiR[1]=phuman0.crop(px*7, px*8, px, px);
		for(int i=0;i<8;i++)
			phEmerge[i]=phuman0.crop(px*i, px*10, px, px);
		for(int i=0;i<5;i++){
			phDeath[i]=ghost.crop(px*i, px*7, px, px);
		}
		
		//armored ph
		aphUp=new BufferedImage[8];
		aphDown=new BufferedImage[8];
		aphRight=new BufferedImage[8];
		aphLeft=new BufferedImage[8];
		aAphUp=new BufferedImage[4];
		aAphDown=new BufferedImage[4];
		aAphRight=new BufferedImage[4];
		aAphLeft=new BufferedImage[4];
		aphiD=new BufferedImage[2];
		aphiL=new BufferedImage[2];
		aphiU=new BufferedImage[2];
		aphiR=new BufferedImage[2];
		aphEmerge=new BufferedImage[8];
		aphDeath=new BufferedImage[5];
		for(int i=0;i<8;i++)
			aphUp[i]=aphuman0.crop(px*i, px*0, px, px);
		for(int i=0;i<8;i++)
			aphLeft[i]=aphuman0.crop(px*i, px*1, px, px);
		for(int i=0;i<8;i++)
			aphDown[i]=aphuman0.crop(px*i, px*2, px, px);
		for(int i=0;i<8;i++)
			aphRight[i]=aphuman0.crop(px*i, px*3, px, px);
		for(int i=0;i<4;i++)
			aAphUp[i]=aphuman0.crop(px*i, px*4, px, px*2);
		for(int i=0;i<4;i++)
			aAphLeft[i]=aphuman0.crop(px*i*2, px*6, px*2, px);
		for(int i=0;i<4;i++)
			aAphDown[i]=aphuman0.crop(px*i, px*7, px, px*2);
		for(int i=0;i<4;i++)
			aAphRight[i]=aphuman0.crop(px*i*2, px*9, px*2, px);
		aphiD[0]=aphuman0.crop(px*4, px*7, px, px);
		aphiL[0]=aphuman0.crop(px*5, px*7, px, px);
		aphiU[0]=aphuman0.crop(px*6, px*7, px, px);
		aphiR[0]=aphuman0.crop(px*7, px*7, px, px);
		aphiD[1]=aphuman0.crop(px*4, px*8, px, px);
		aphiL[1]=aphuman0.crop(px*5, px*8, px, px);
		aphiU[1]=aphuman0.crop(px*6, px*8, px, px);
		aphiR[1]=aphuman0.crop(px*7, px*8, px, px);
		for(int i=0;i<8;i++)
			aphEmerge[i]=aphuman0.crop(px*i, px*10, px, px);
		for(int i=0;i<5;i++){
			aphDeath[i]=ghost.crop(px*i, px*6, px, px);
		}
		
		//melee cultist
		mcUp=new BufferedImage[8];
		mcDown=new BufferedImage[8];
		mcRight=new BufferedImage[8];
		mcLeft=new BufferedImage[8];
		mAcUp=new BufferedImage[4];
		mAcDown=new BufferedImage[4];
		mAcRight=new BufferedImage[4];
		mAcLeft=new BufferedImage[4];
		mciD=new BufferedImage[2];
		mciL=new BufferedImage[2];
		mciU=new BufferedImage[2];
		mciR=new BufferedImage[2];
		mcEmerge=new BufferedImage[8];
		mcDeath=new BufferedImage[5];
		for(int i=0;i<8;i++)
			mcUp[i]=mcultist.crop(px*i, px*0, px, px);
		for(int i=0;i<8;i++)
			mcLeft[i]=mcultist.crop(px*i, px*1, px, px);
		for(int i=0;i<8;i++)
			mcDown[i]=mcultist.crop(px*i, px*2, px, px);
		for(int i=0;i<8;i++)
			mcRight[i]=mcultist.crop(px*i, px*3, px, px);
		for(int i=0;i<4;i++)
			mAcUp[i]=mcultist.crop(px*i, px*4, px, px*2);
		for(int i=0;i<4;i++)
			mAcLeft[i]=mcultist.crop(px*i*2, px*6, px*2, px);
		for(int i=0;i<4;i++)
			mAcDown[i]=mcultist.crop(px*i, px*7, px, px*2);
		for(int i=0;i<4;i++)
			mAcRight[i]=mcultist.crop(px*i*2, px*9, px*2, px);
		mciD[0]=mcultist.crop(px*4, px*7, px, px);
		mciL[0]=mcultist.crop(px*5, px*7, px, px);
		mciU[0]=mcultist.crop(px*6, px*7, px, px);
		mciR[0]=mcultist.crop(px*7, px*7, px, px);
		mciD[1]=mcultist.crop(px*4, px*8, px, px);
		mciL[1]=mcultist.crop(px*5, px*8, px, px);
		mciU[1]=mcultist.crop(px*6, px*8, px, px);
		mciR[1]=mcultist.crop(px*7, px*8, px, px);
		for(int i=0;i<8;i++)
			mcEmerge[i]=mcultist.crop(px*i, px*10, px, px);
		for(int i=0;i<5;i++){
			mcDeath[i]=ghost.crop(px*i, px*5, px, px);
		}
		
		//zwei jayde
		zU=new BufferedImage[8];
		zL=new BufferedImage[8];
		zD=new BufferedImage[8];
		zR=new BufferedImage[8];
		zAU=new BufferedImage[2][5];
		zAL=new BufferedImage[2][5];
		zAD=new BufferedImage[2][5];
		zAR=new BufferedImage[2][5];
		ziU=new BufferedImage[2];
		ziL=new BufferedImage[2];
		ziD=new BufferedImage[2];
		ziR=new BufferedImage[2];
		for(int i=0;i<8;i++)
			zU[i]=zwei0.crop(px*(i+4), px*2, px, px*2);
		for(int i=0;i<8;i++)
			zL[i]=zwei0.crop(px*0, px*(i+2), px*2, px*1);
		for(int i=0;i<8;i++)
			zD[i]=zwei0.crop(px*i, px*0, px, px*2);
		for(int i=0;i<8;i++)
			zR[i]=zwei0.crop(px*2, px*(i+2), px*2, px);
		for(int i=0;i<5;i++)
			zAU[0][i]=zwei0.crop(px*((i*3)+12), px*0, px*3, px*3);
		for(int i=0;i<5;i++)
			zAL[0][i]=zwei0.crop(px*(i*3), px*16, px*3, px*2);
		for(int i=0;i<5;i++)
			zAD[0][i]=zwei0.crop(px*(i*3), px*10, px*3, px*3);
		for(int i=0;i<5;i++)
			zAR[0][i]=zwei0.crop(px*((i*3)+12), px*6, px*3, px*2);
		for(int i=0;i<5;i++)
			zAU[1][i]=zwei0.crop(px*((i*3)+12), px*(0+3), px*3, px*3);
		for(int i=0;i<5;i++)
			zAL[1][i]=zwei0.crop(px*(i*3), px*(16+2), px*3, px*2);
		for(int i=0;i<5;i++)
			zAD[1][i]=zwei0.crop(px*(i*3), px*(10+3), px*3, px*3);
		for(int i=0;i<5;i++)
			zAR[1][i]=zwei0.crop(px*((i*3)+12), px*(6+2), px*3, px*2);
		ziU[0]=zwei0.crop(px*7, px*6, px, px*2);
		ziL[0]=zwei0.crop(px*5, px*7, px*2, px);
		ziD[0]=zwei0.crop(px*4, px*6, px, px*2);
		ziR[0]=zwei0.crop(px*5, px*6, px*2, px);
		ziU[1]=zwei0.crop(px*(7+4), px*6, px, px*2);
		ziL[1]=zwei0.crop(px*(5+4), px*7, px*2, px);
		ziD[1]=zwei0.crop(px*(4+4), px*6, px, px*2);
		ziR[1]=zwei0.crop(px*(5+4), px*6, px*2, px);
	}

}
