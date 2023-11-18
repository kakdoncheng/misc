package havocpixel.tiles;

import havocpixel.gfx.Assets;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	//implement textures to switch between tile textures?
	//premake all available tile slots
	public static Tile[] tiles=new Tile[1024];
	public static Tile def0=new def0(1023);
	//public static Tile t0=new t0(0);
	//public static Tile t1=new t1(1);
	//public static Tile stone=new Stone(2);
	//public static Tile nul=new NullTile(3);
	
	//fix tile slots so they match up with textures
	public static Tile grass=new FormlessTile(Assets.textures[25][25],444,false,"00ff00","TILE:Grass");
	
	public static Tile a=new FormlessTile(Assets.textures[0][0],0,true,"000000","TILE:Void");
	public static Tile b=new FormlessTile(Assets.textures[1][0],1,false,"0a0a0a","TILE:StoneGround");
	public static Tile c=new FormlessTile(Assets.textures[2][0],2,false,"141414","TILE:StoneLeftEdge");
	public static Tile d=new FormlessTile(Assets.textures[3][0],3,true,"1e1e1e","TILE:StoneLeftEdgeBlock");
	public static Tile e=new FormlessTile(Assets.textures[4][0],4,false,"282828","TILE:StoneRightEdge");
	public static Tile f=new FormlessTile(Assets.textures[5][0],5,true,"323232","TILE:StoneRightEdgeBlock");
	public static Tile g=new FormlessTile(Assets.textures[6][0],6,true,"3c3c3c","TILE:LeftEdge");
	public static Tile h=new FormlessTile(Assets.textures[7][0],7,true,"464646","TILE:RightEdge");
	public static Tile i=new FormlessTile(Assets.textures[8][0],8,true,"505050","TILE:GreyBrickWall");
	public static Tile j=new FormlessTile(Assets.textures[9][0],9,false,"5a5a5a","TILE:CrackedCheckerBoardFloor");
	public static Tile k=new FormlessTile(Assets.textures[10][0],10,false,"646464","TILE:GreyTiledFloor");
	public static Tile l=new FormlessTile(Assets.textures[11][0],11,false,"6e6e6e","TILE:LightGreyTiledFloor");
	public static Tile m=new FormlessTile(Assets.textures[12][0],12,true,"787878","TILE:BlankLeftEdge");
	public static Tile n=new FormlessTile(Assets.textures[13][0],13,true,"828282","TILE:BlankSWInnerCorner");
	public static Tile o=new FormlessTile(Assets.textures[14][0],14,true,"8c8c8c","TILE:BlankSWOutsideCorner");
	public static Tile p=new FormlessTile(Assets.textures[15][0],15,true,"969696","TILE:BlankWallBottom");
	public static Tile q=new FormlessTile(Assets.textures[16][0],16,true,"a0a0a0","TILE:BlankRightEdge");
	public static Tile r=new FormlessTile(Assets.textures[17][0],17,true,"aaaaaa","TILE:BlankSEInnerCorner");
	public static Tile s=new FormlessTile(Assets.textures[18][0],18,true,"b4b4b4","TILE:BlankSEOutsideCorner");
	public static Tile t=new FormlessTile(Assets.textures[19][0],19,true,"bebebe","TILE:BlankTopEdge");
	public static Tile u=new FormlessTile(Assets.textures[20][0],20,true,"c8c8c8","TILE:BlankNWInnerCorner");
	public static Tile v=new FormlessTile(Assets.textures[21][0],21,true,"d2d2d2","TILE:BlankNWOutsideCorner");
	public static Tile w=new FormlessTile(Assets.textures[22][0],22,true,"dcdcdc","TILE:BlankBottomEdge");
	public static Tile x=new FormlessTile(Assets.textures[23][0],23,true,"e6e6e6","TILE:BlankNEInnerCorner");
	public static Tile y=new FormlessTile(Assets.textures[24][0],24,true,"f0f0f0","TILE:BlankNEOutsideCorner");
	public static Tile z=new FormlessTile(Assets.textures[25][0],25,true,"fafafa","TILE:EmptySlot");
	
	public static Tile aa=new FormlessTile(Assets.textures[0][1],26,true,"050505","TILE:EmptySlot");
	public static Tile ab=new FormlessTile(Assets.textures[1][1],27,false,"0f0f0f","TILE:EmptySlot");
	public static Tile ac=new FormlessTile(Assets.textures[2][1],28,false,"191919","TILE:EmptySlot");
	public static Tile ad=new FormlessTile(Assets.textures[3][1],29,true,"232323","TILE:EmptySlot");
	public static Tile ae=new FormlessTile(Assets.textures[4][1],30,false,"2d2d2d","TILE:EmptySlot");
	public static Tile af=new FormlessTile(Assets.textures[5][1],31,true,"373737","TILE:EmptySlot");
	public static Tile ag=new FormlessTile(Assets.textures[6][1],32,true,"414141","TILE:EmptySlot");
	public static Tile ah=new FormlessTile(Assets.textures[7][1],33,true,"4b4b4b","TILE:EmptySlot");
	public static Tile ai=new FormlessTile(Assets.textures[8][1],34,true,"555555","TILE:EmptySlot");
	public static Tile aj=new FormlessTile(Assets.textures[9][1],35,false,"5f5f5f","TILE:EmptySlot");
	public static Tile ak=new FormlessTile(Assets.textures[10][1],36,false,"696969","TILE:EmptySlot");
	public static Tile al=new FormlessTile(Assets.textures[11][1],37,false,"737373","TILE:EmptySlot");
	public static Tile am=new FormlessTile(Assets.textures[12][1],38,true,"7d7d7d","TILE:EmptySlot");
	public static Tile an=new FormlessTile(Assets.textures[13][1],39,true,"878787","TILE:EmptySlot");
	public static Tile ao=new FormlessTile(Assets.textures[14][1],40,true,"919191","TILE:EmptySlot");
	public static Tile ap=new FormlessTile(Assets.textures[15][1],41,true,"9b9b9b","TILE:EmptySlot");
	public static Tile aq=new FormlessTile(Assets.textures[16][1],42,true,"a5a5a5","TILE:EmptySlot");
	public static Tile ar=new FormlessTile(Assets.textures[17][1],43,true,"afafaf","TILE:EmptySlot");
	public static Tile as=new FormlessTile(Assets.textures[18][1],44,true,"b9b9b9","TILE:EmptySlot");
	public static Tile at=new FormlessTile(Assets.textures[19][1],45,true,"c3c3c3","TILE:EmptySlot");
	public static Tile au=new FormlessTile(Assets.textures[20][1],46,true,"cdcdcd","TILE:EmptySlot");
	public static Tile av=new FormlessTile(Assets.textures[21][1],47,true,"d7d7d7","TILE:EmptySlot");
	public static Tile aw=new FormlessTile(Assets.textures[22][1],48,true,"e1e1e1","TILE:EmptySlot");
	public static Tile ax=new FormlessTile(Assets.textures[23][1],49,true,"ebebeb","TILE:EmptySlot");
	public static Tile ay=new FormlessTile(Assets.textures[24][1],50,true,"f5f5f5","TILE:EmptySlot");
	public static Tile az=new FormlessTile(Assets.textures[25][1],51,true,"ffffff","TILE:EmptySlot");
	
	public static Tile ba=new FormlessTile(Assets.textures[0][2],52,true,"000000","TILE:Void");
	public static Tile bb=new FormlessTile(Assets.textures[1][2],53,false,"0a0000","TILE:StoneGround");
	public static Tile bc=new FormlessTile(Assets.textures[2][2],54,false,"140000","TILE:StoneLeftEdge");
	public static Tile bd=new FormlessTile(Assets.textures[3][2],55,true,"1e0000","TILE:StoneLeftEdgeBlock");
	public static Tile be=new FormlessTile(Assets.textures[4][2],56,false,"280000","TILE:StoneRightEdge");
	public static Tile bf=new FormlessTile(Assets.textures[5][2],57,true,"320000","TILE:StoneRightEdgeBlock");
	public static Tile bg=new FormlessTile(Assets.textures[6][2],58,true,"3c0000","TILE:LeftEdge");
	public static Tile bh=new FormlessTile(Assets.textures[7][2],59,true,"460000","TILE:RightEdge");
	public static Tile bi=new FormlessTile(Assets.textures[8][2],60,true,"500000","TILE:GreyBrickWall");
	public static Tile bj=new FormlessTile(Assets.textures[9][2],61,false,"5a0000","TILE:CrackedCheckerBoardFloor");
	public static Tile bk=new FormlessTile(Assets.textures[10][2],62,false,"640000","TILE:GreyTiledFloor");
	public static Tile bl=new FormlessTile(Assets.textures[11][2],63,false,"6e0000","TILE:LightGreyTiledFloor");
	public static Tile bm=new FormlessTile(Assets.textures[12][2],64,true,"780000","TILE:BlankLeftEdge");
	public static Tile bn=new FormlessTile(Assets.textures[13][2],65,true,"820000","TILE:BlankSWInnerCorner");
	public static Tile bo=new FormlessTile(Assets.textures[14][2],66,true,"8c0000","TILE:BlankSWOutsideCorner");
	public static Tile bp=new FormlessTile(Assets.textures[15][2],67,true,"960000","TILE:BlankWallBottom");
	public static Tile bq=new FormlessTile(Assets.textures[16][2],68,true,"a00000","TILE:BlankRightEdge");
	public static Tile br=new FormlessTile(Assets.textures[17][2],69,true,"aa0000","TILE:BlankSEInnerCorner");
	public static Tile bs=new FormlessTile(Assets.textures[18][2],70,true,"b40000","TILE:BlankSEOutsideCorner");
	public static Tile bt=new FormlessTile(Assets.textures[19][2],71,true,"be0000","TILE:BlankTopEdge");
	public static Tile bu=new FormlessTile(Assets.textures[20][2],72,true,"c80000","TILE:BlankNWInnerCorner");
	public static Tile bv=new FormlessTile(Assets.textures[21][2],73,true,"d20000","TILE:BlankNWOutsideCorner");
	public static Tile bw=new FormlessTile(Assets.textures[22][2],74,true,"dc0000","TILE:BlankBottomEdge");
	public static Tile bx=new FormlessTile(Assets.textures[23][2],75,true,"e60000","TILE:BlankNEInnerCorner");
	public static Tile by=new FormlessTile(Assets.textures[24][2],76,true,"f00000","TILE:BlankNEOutsideCorner");
	public static Tile bz=new FormlessTile(Assets.textures[25][2],77,true,"fa0000","TILE:EmptySlot");

	public static Tile ca=new FormlessTile(Assets.textures[0][3],78,true,"050000","TILE:EmptySlot");
	public static Tile cb=new FormlessTile(Assets.textures[1][3],79,false,"0f0000","TILE:EmptySlot");
	public static Tile cc=new FormlessTile(Assets.textures[2][3],80,false,"190000","TILE:EmptySlot");
	public static Tile cd=new FormlessTile(Assets.textures[3][3],81,true,"230000","TILE:EmptySlot");
	public static Tile ce=new FormlessTile(Assets.textures[4][3],82,false,"2d0000","TILE:EmptySlot");
	public static Tile cf=new FormlessTile(Assets.textures[5][3],83,true,"370000","TILE:EmptySlot");
	public static Tile cg=new FormlessTile(Assets.textures[6][3],84,true,"410000","TILE:EmptySlot");
	public static Tile ch=new FormlessTile(Assets.textures[7][3],85,true,"4b0000","TILE:EmptySlot");
	public static Tile ci=new FormlessTile(Assets.textures[8][3],86,true,"550000","TILE:EmptySlot");
	public static Tile cj=new FormlessTile(Assets.textures[9][3],87,false,"5f0000","TILE:EmptySlot");
	public static Tile ck=new FormlessTile(Assets.textures[10][3],88,false,"690000","TILE:EmptySlot");
	public static Tile cl=new FormlessTile(Assets.textures[11][3],89,false,"730000","TILE:EmptySlot");
	public static Tile cm=new FormlessTile(Assets.textures[12][3],90,true,"7d0000","TILE:EmptySlot");
	public static Tile cn=new FormlessTile(Assets.textures[13][3],91,true,"870000","TILE:EmptySlot");
	public static Tile co=new FormlessTile(Assets.textures[14][3],92,true,"910000","TILE:EmptySlot");
	public static Tile cp=new FormlessTile(Assets.textures[15][3],93,true,"9b0000","TILE:EmptySlot");
	public static Tile cq=new FormlessTile(Assets.textures[16][3],94,true,"a00000","TILE:EmptySlot");
	public static Tile cr=new FormlessTile(Assets.textures[17][3],95,true,"af0000","TILE:EmptySlot");
	public static Tile cs=new FormlessTile(Assets.textures[18][3],96,true,"b90000","TILE:EmptySlot");
	public static Tile ct=new FormlessTile(Assets.textures[19][3],97,true,"c30000","TILE:EmptySlot");
	public static Tile cu=new FormlessTile(Assets.textures[20][3],98,true,"cd0000","TILE:EmptySlot");
	public static Tile cv=new FormlessTile(Assets.textures[21][3],99,true,"d70000","TILE:EmptySlot");
	public static Tile cw=new FormlessTile(Assets.textures[22][3],100,true,"e10000","TILE:EmptySlot");
	public static Tile cx=new FormlessTile(Assets.textures[23][3],101,true,"eb0000","TILE:EmptySlot");
	public static Tile cy=new FormlessTile(Assets.textures[24][3],102,true,"f50000","TILE:EmptySlot");
	public static Tile cz=new FormlessTile(Assets.textures[25][3],103,true,"ff0000","TILE:EmptySlot");
	//make more tiles; make more tile images in assets
	
	public static final int TILE_WIDTH=32, TILE_HEIGHT=32;
	protected BufferedImage texture;
	protected int ID;
	protected String hexcode,tlabel;

	public Tile(BufferedImage texture, int ID) {
		this.texture=texture;
		this.ID=ID;
		tiles[ID]=this;
	}
	
	public static int matchHexcode(String hex){
		for(Tile u:tiles){
			//System.out.println(hex);
			if(u==null)
				u=def0;
			if(u.$hexcode().equals(hex))
				return u.$ID();
		}
		return def0.$ID();
	}
	
	public void tick() {
		
	}
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
	}
	
	public int $ID() {
		return ID;
	}
	public boolean impassable() {
		return false;
	}
	public String $tlabel(){
		return tlabel!=null?tlabel:"Default";
	}
	public String $hexcode(){
		return hexcode!=null?hexcode:"Unused";
	}
	
	
}
