package havocpixel;

public class Notes {
//MAIN CHANGE
	//NEW CHANGES THAT NEED TO BE IMPLEMENTED:
	
	//MECHANIC CHANGES
	//-shields
	//-weapons
	
	//NEW MONSTERS
	//-spellcasters
	//-suicide monsters
	//-parasytes
	
	//NEW MAPS
	//-mountainous forest
	//-stone bridge in hell
	//-gates of hell
	
	//NEW ITEMS
	//-wands
	//-food
	//-bread
	//-pie
	//-canned food
	//-meat
	
	//DIALOGUE CUTSCENES
	//-shopkeeper
	//-storyline dialogue
	
	
	
	//? ? OLD LIST ? ?
	//Leave area on maps
	//Basic Mechanics:
	//-when a player steps on the area, leaves map to go to another area
	//-player will move to the leave area adjacent to the direction player is in;
	//-the leave area is made active again when the player steps off of the leave area;
	//-leave areas will only affect players
	
	//teleport tiles
	//steping on a tile wil teleport you to the correct destination
	//black background phase in phase out
	//
	//enemy maiming and crawling animations
	//
	//want to add telefrag
	//want to add blocking, parrying, and counter mechanics
	//
	//player attacking mechanic change
	//when the player attacks in a row without getting hit within the same period of time, the player will deal a combo of successively
	//stronger attacks
	//attacks can chain up to 25 levels, 2*lvl damage successively
	//player can charge up a power attack which has increased range and power [A key]
	//the longer the charge ~up to 2secs the stronger the attack up to 300% damage
	//charging up all the way without attacking will start tiring out and damaging the player
	//
	//player blocking mechanics [s key]
	//once triggered, after a small delay, player will attempt to block with sword or shield for a certain amount of points
	//block can be held for as long as shield doesnt break
	//once block is released, player is vulnurable or a short period of time where they cannot move or attack
	//if while blocking the player attacks when the guard is able to deflect points, player will launch a counter attack
	//counter attack damage is equal to the amount of damage recieved to guard + player damage
	//
	//player throwing items
	//if an item is useless, using the item will cause the player to throw the object as a projectile
	//
	//terrain additions
	//mountain forest
	//extreme hills
	//forest
	//tundra
	//wasteland
	//ocean/coastline
	//dungoens
	//mineshafts
	//caves
	//ravines
	//bridges
	//snowy parts
	//castles
	//ruins
	//temple
	//sans mansion
	//mercenary camps
	//
	//lion dance drummer
	//
	//HUD mech
	//
	
//next patch notes
//Ghosts
//-Reduced health to 1
//-Reduced AC to 0
//-Can now possess dead human corpses
//Monsters
//-Nerfed AC and health on all monsters
//Player
//-reduced health to 1000
//-now will regen to full health
	
//Added Bear Traps
//-Deals massive damage to all targets caught in the trap (750-1000)
//-Powerful spring destroys the trap after its been set off
//-Can be disarmed by hurting it
//Possessed Humans
//-Now starts off being possessed by an evil spirit
//-Added New Texture
//HUD Update
//-removed hp bar
//-Screen edge now tinges red when health is below 50%
//-Rendering improved
//MISC
//-Added fog


//CHECKLIST FOR NEXT UPDATE
//ghost able to possess corpses? YES
//hidden & visible traps? WIP
//parastes NO
//cultist textures YES
//suicide forest WIP

//Optional Checklist
//add resurrection animation NO
//Fire NO
//Bear Traps YES
//floor spike traps NO
//wall spike traps NO
//wall saw traps NO
//leaves NO
//vomiting NO


//CHECKLIST FOR IMPROVEMENTS
//ghosts able to possess ANY corpse with their own zombie sprites
//more cultist sprites
//ambient evironment
//-falling snow
//-falling leaves
//-improved wall textures


	//IMPROVED player inventory
	//dynamic worlds
	//menus
	//real time cutscenes
	//acheivements
//additional monsters
// zombies
// parastyes
// status effects
// loot crates
// man eating treasure chests
// trapped loot crates
// partially hidden traps (leaves)
// stone gibs
// demon butterflies
// demon bats
// hidden doors/false walls
// compass & suicide forest
	
	/*
	public void run() {
		init();
		double n=1000000000/FPSLimit;
		double d=0;
		int overdrive=0;
		long ts=0;
		long now;
		long last=System.nanoTime();
		long last0=System.nanoTime();
		long timer=0,timer0=0;
		float q=0,r=0;
		tick=0;
		while (running) {
			try{
				now=System.nanoTime();
				d+=(now-last)/n;
				timer+=(now-last);
				if (d>2) {
					ts+=(long)(d-1);
					d=1;
				}
				if (d>=1) {
					///*
					timer0+=(now-last0);
					last0=now;
					//calculated fps
					ae=(1000000000/(float)timer0);
					if(tick%15==0){
						ae0=ae;
					}
					//System.out.println(ae);
					if(this.$FPS()>59){
						tick();
						q=0;
						timer0=0;
					}else{
						tick();
						float m=((float)this.$FPS()/(60.0F-(float)this.$FPS()));
						if(r!=ae)
							q=0;
						r=ae;
						q+=m/(float)this.$FPS();
						if(q>m*2)
							q=m;
						if(q>=m){
							//tick();
							q-=m;
						}
						timer0=0;
					}
					if(ae<59.8F&&overdrive<4800){
						overdrive+=5;
					}
					if(overdrive>0){
						if(tick%((overdrive>3600)?3:2)==0)
							render();
						overdrive--;
					}else{
						render();
					}
					//*//*
					//tick();
					//render();
					tick++;
					d--;
				}
				last=now;
				if (timer>=1000000000) {
					if(overdrive>0)
						System.out.print("["+Timer.time()+"] [WARNING] Rendering at overdrive for "+overdrive+" tick(s);\n");
					if(ts>0){
						System.out.print("["+Timer.time()+"] [WARNING] FPS Rate Cap Exceeded; Did the system time change? Or is this thing lagging? Skipping "+ts+" tick(s);\n");
						ts=0;
					}
					fps=tick;
					//System.out.print("["+Timer.time()+"] FPS "+fps+";\n");
					tick=0;
					timer=0;
				}
			}catch(Exception e){
				System.out.print("["+Timer.time()+"] [ERROR] ");
				e.printStackTrace();
			}
		}
		stop();
	}//*/
	//em.addEntity(new Npc0(hdlr,16*Tile.TILE_WIDTH,11*Tile.TILE_HEIGHT));
			/*
			em.addEntity(new Tombstone(hdlr,5*Tile.TILE_WIDTH,5*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,6*Tile.TILE_WIDTH,5*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,7*Tile.TILE_WIDTH,5*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,10*Tile.TILE_WIDTH,5*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,11*Tile.TILE_WIDTH,5*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,12*Tile.TILE_WIDTH,5*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,28*Tile.TILE_WIDTH,5*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,29*Tile.TILE_WIDTH,5*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,30*Tile.TILE_WIDTH,5*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,33*Tile.TILE_WIDTH,5*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,34*Tile.TILE_WIDTH,5*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,35*Tile.TILE_WIDTH,5*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,5*Tile.TILE_WIDTH,7*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,6*Tile.TILE_WIDTH,7*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,7*Tile.TILE_WIDTH,7*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,10*Tile.TILE_WIDTH,7*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,11*Tile.TILE_WIDTH,7*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,12*Tile.TILE_WIDTH,7*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,28*Tile.TILE_WIDTH,7*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,29*Tile.TILE_WIDTH,7*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,30*Tile.TILE_WIDTH,7*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,33*Tile.TILE_WIDTH,7*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,34*Tile.TILE_WIDTH,7*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,35*Tile.TILE_WIDTH,7*Tile.TILE_HEIGHT));
			
			em.addEntity(new Tombstone(hdlr,5*Tile.TILE_WIDTH,12*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,6*Tile.TILE_WIDTH,12*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,7*Tile.TILE_WIDTH,12*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,10*Tile.TILE_WIDTH,12*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,11*Tile.TILE_WIDTH,12*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,12*Tile.TILE_WIDTH,12*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,28*Tile.TILE_WIDTH,12*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,29*Tile.TILE_WIDTH,12*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,30*Tile.TILE_WIDTH,12*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,33*Tile.TILE_WIDTH,12*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,34*Tile.TILE_WIDTH,12*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,35*Tile.TILE_WIDTH,12*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,5*Tile.TILE_WIDTH,14*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,6*Tile.TILE_WIDTH,14*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,7*Tile.TILE_WIDTH,14*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,10*Tile.TILE_WIDTH,14*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,11*Tile.TILE_WIDTH,14*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,12*Tile.TILE_WIDTH,14*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,28*Tile.TILE_WIDTH,14*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,29*Tile.TILE_WIDTH,14*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,30*Tile.TILE_WIDTH,14*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,33*Tile.TILE_WIDTH,14*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,34*Tile.TILE_WIDTH,14*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,35*Tile.TILE_WIDTH,14*Tile.TILE_HEIGHT));
			
			em.addEntity(new Tombstone(hdlr,5*Tile.TILE_WIDTH,26*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,6*Tile.TILE_WIDTH,26*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,7*Tile.TILE_WIDTH,26*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,10*Tile.TILE_WIDTH,26*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,11*Tile.TILE_WIDTH,26*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,12*Tile.TILE_WIDTH,26*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,28*Tile.TILE_WIDTH,26*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,29*Tile.TILE_WIDTH,26*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,30*Tile.TILE_WIDTH,26*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,33*Tile.TILE_WIDTH,26*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,34*Tile.TILE_WIDTH,26*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,35*Tile.TILE_WIDTH,26*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,5*Tile.TILE_WIDTH,28*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,6*Tile.TILE_WIDTH,28*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,7*Tile.TILE_WIDTH,28*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,10*Tile.TILE_WIDTH,28*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,11*Tile.TILE_WIDTH,28*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,12*Tile.TILE_WIDTH,28*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,28*Tile.TILE_WIDTH,28*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,29*Tile.TILE_WIDTH,28*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,30*Tile.TILE_WIDTH,28*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,33*Tile.TILE_WIDTH,28*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,34*Tile.TILE_WIDTH,28*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,35*Tile.TILE_WIDTH,28*Tile.TILE_HEIGHT));
			
			em.addEntity(new Tombstone(hdlr,5*Tile.TILE_WIDTH,33*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,6*Tile.TILE_WIDTH,33*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,7*Tile.TILE_WIDTH,33*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,10*Tile.TILE_WIDTH,33*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,11*Tile.TILE_WIDTH,33*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,12*Tile.TILE_WIDTH,33*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,28*Tile.TILE_WIDTH,33*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,29*Tile.TILE_WIDTH,33*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,30*Tile.TILE_WIDTH,33*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,33*Tile.TILE_WIDTH,33*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,34*Tile.TILE_WIDTH,33*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,35*Tile.TILE_WIDTH,33*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,5*Tile.TILE_WIDTH,35*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,6*Tile.TILE_WIDTH,35*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,7*Tile.TILE_WIDTH,35*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,10*Tile.TILE_WIDTH,35*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,11*Tile.TILE_WIDTH,35*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,12*Tile.TILE_WIDTH,35*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,28*Tile.TILE_WIDTH,35*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,29*Tile.TILE_WIDTH,35*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,30*Tile.TILE_WIDTH,35*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,33*Tile.TILE_WIDTH,35*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,34*Tile.TILE_WIDTH,35*Tile.TILE_HEIGHT));
			em.addEntity(new Tombstone(hdlr,35*Tile.TILE_WIDTH,35*Tile.TILE_HEIGHT));
			
			em.addEntity(new Sans(hdlr,21*Tile.TILE_WIDTH,20*Tile.TILE_HEIGHT));
			em.addEntity(new Tree(hdlr,12*Tile.TILE_WIDTH,12*Tile.TILE_HEIGHT));
			//em.addEntity(new Pumpkin(hdlr,15*Tile.TILE_WIDTH,16*Tile.TILE_HEIGHT));
			//em.addEntity(new Pumpkin(hdlr,15*Tile.TILE_WIDTH,24*Tile.TILE_HEIGHT));
			//em.addEntity(new Pumpkin(hdlr,24*Tile.TILE_WIDTH,16*Tile.TILE_HEIGHT));
			//em.addEntity(new Pumpkin(hdlr,24*Tile.TILE_WIDTH,24*Tile.TILE_HEIGHT));
			em.addEntity(new Tree(hdlr,6*Tile.TILE_WIDTH,20*Tile.TILE_HEIGHT));
			em.addEntity(new Tree(hdlr,30*Tile.TILE_WIDTH,3*Tile.TILE_HEIGHT));
			em.addEntity(new Tree(hdlr,14*Tile.TILE_WIDTH,32*Tile.TILE_HEIGHT));
			//em.addEntity(new PossessedGrunt(hdlr,14*Tile.TILE_WIDTH,15*Tile.TILE_HEIGHT,"SKELETON_0"));
			//em.addEntity(new PossessedGrunt(hdlr,14*Tile.TILE_WIDTH,25*Tile.TILE_HEIGHT,"SKELETON_1"));
			//em.addEntity(new PossessedGrunt(hdlr,25*Tile.TILE_WIDTH,15*Tile.TILE_HEIGHT,"SKELETON_2"));
			//em.addEntity(new PossessedGrunt(hdlr,25*Tile.TILE_WIDTH,25*Tile.TILE_HEIGHT,"SKELETON_3"));
			//em.addEntity(new PossessedGrunt(hdlr,5*Tile.TILE_WIDTH,20*Tile.TILE_HEIGHT,"SKELETON_4"));
			//em.addEntity(new PossessedGrunt(hdlr,35*Tile.TILE_WIDTH,20*Tile.TILE_HEIGHT,"SKELETON_5"));
			//em.addEntity(new LootCrate(hdlr,23*Tile.TILE_WIDTH,17*Tile.TILE_HEIGHT));
			//em.addEntity(new LootCrate(hdlr,23*Tile.TILE_WIDTH,23*Tile.TILE_HEIGHT));
			//em.addEntity(new LootCrate(hdlr,16*Tile.TILE_WIDTH,17*Tile.TILE_HEIGHT));
			//em.addEntity(new LootCrate(hdlr,16*Tile.TILE_WIDTH,23*Tile.TILE_HEIGHT));
			//em.addEntity(new HumanGrunt(hdlr,6*Tile.TILE_WIDTH,21*Tile.TILE_HEIGHT,"GRUNT_0"));
			//em.addEntity(new HumanGrunt(hdlr,36*Tile.TILE_WIDTH,21*Tile.TILE_HEIGHT,"GRUNT_1"));
			 */
}
