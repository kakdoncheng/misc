package havocpixel;

import havocpixel.gfx.CoreAssets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	//implement textures to switch between tile textures?
	//each tileset is 100 tiles long
	//premake all available tile slots
	public static Tile[] tiles=new Tile[102];
	public static Tile defaultTile=new Tile(CoreAssets.defaultTile[2], 100, false, null);
	public static Tile nullTile=new Tile(CoreAssets.blackout, 101, false, null);
	public static boolean[] isSolidDat;
	
	public static final int TILE_WIDTH=32, TILE_HEIGHT=32;
	private BufferedImage texture;
	private int ID;
	private String hexcode,label;
	private Color code;
	private boolean isSolid;

	public Tile(BufferedImage texture, int ID, boolean isSolid, Color code) {
		this.texture=texture;
		this.ID=ID;
		this.code=code;
		tiles[ID]=this;
		this.isSolid=isSolid;
		if(code!=null){
			hexcode=String.format("%02x%02x%02x",code.getRed(),code.getGreen(),code.getBlue());
		}else{
			hexcode="000000";
		}
	}
	
	public static void loadTiles(){
		isSolidDat=new boolean[100];
		isSolidDat[0]=true;
		isSolidDat[1]=true;
		isSolidDat[2]=true;
		isSolidDat[4]=true;
		isSolidDat[5]=true;
		for(int i=0; i<10; i++){
			//System.out.println(isSolidDat[i]);
			new Tile(CoreAssets.defaultTileset[i], i, isSolidDat[i], new Color(25*i,25*i,25*i));
		}
	}
	
	public static int matchColorCode(Color code){
		//System.out.println("havocpixel.Tile:INFO: "+code.getRGB());
		for(Tile t:tiles){
			if(t==null)
				t=defaultTile;
			if(t.code.equals(code))
				return t.$ID();
		}
		return defaultTile.$ID();
	}
	
	public static int matchHexcode(String hex){
		for(Tile t:tiles){
			//System.out.println(hex);
			if(t==null)
				t=defaultTile;
			if(t.$hexcode().equals(hex))
				return t.$ID();
		}
		return defaultTile.$ID();
	}
	
	public void update(double dt) {
		
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
	}
	
	public int $ID() {
		return ID;
	}
	public boolean impassable() {
		return isSolid;
	}
	public String $label(){
		return label!=null?label:"Default";
	}
	public String $hexcode(){
		return hexcode!=null?hexcode:"Unused";
	}
	
	
}
