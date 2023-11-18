package havocpixel;

public class Notes {
//MAIN CHANGE
	//A FAIRY TALE: A story about a princess, and the kingdom she lost.
	
	//NEW CHANGES THAT NEED TO BE IMPLEMENTED:
		//NOTE: DIRECTIONS //d0r1u2l3
		//lambs to the slaughter
		//Need to implement Secret Names
		//OR redo target system to target with references to the entity, not by using the label
	
	//CODE INFRASTRUCTURE EFFICENCY OVERHAUL TODO
		//Objects are non-living entities
		//Particles are non-living entities which can't be damaged or targeted and have no physical form
		//Overhaul all entity loops to minimize amount of cycles
		//Getters where nessessary
	
	//IMPORTANT NOTES
		//items have been disabled
		//floating stings have been disabled
		//MUST UPDATE GRAPHICS FOR GHOSTS AND SKELETONS ASAP
	    //JESUS FUCKING CHRIST WHY
	
	//STAGE ONE: MECHANICS: WIP
	
	//NEW GUI
		//main menu portal
		//pause screen menu
		//control info & options
	
	//NEW REVAMPED INVENTORY
		//updated inventory gui
		//updated method of displaying descriptions
		//armor worn will affect ac?
		//limited carrying capacity but no limit to stacks for small objects
		//different weapons and shields can be carried
		//each weapon has their own corruption(AP) and durability(mass) values
		//each monster slain increases corruption, which increases damage
		//if corruption exceeds durability, weapon starts breaking and dealing less damage
	
	//NEW ENTITY MECHANICS
		//-enemies have fairer and more random stun ticks and attack patterns ADDED
		//-camera smoothing update ADDED
		//-critical hits make entities bleed out
		//-maiming and crawling animations
		//-poison DONE
	
	//NEW PLAYER MECHANICS
		//-new healing mechanic - energy drops
		//-monsters have some way of dropping energy, picking them up will resplenish health.
		//-player throwing items
		//-if an item is useless, using the item will cause the player to throw the object as a projectile
		//-player can polymorph into different charactes via consuming magical extracts; code will be put into player class
		//-int value which controls current state of player, 0=human, 1=imp, etc.
		//-duration int value decides how long until int value resets to human
	
	//ADD NEW ATTACK SPRITES TO POSSESSED HUMANS <<<<<<<<<<<<<<<<<<<<<<
	
	//NEW MONSTERS
		//-fleshy skeletons w/ spikes
		//-suicide monsters
		//-goblins
		//-parasytes
		//-dolls
		//-weeping angels WIP
		//-gelatinous blob DONE
		//-dryads
		//-demon trees
	
	//NEW TRAPS
		//-dart launcher DONE
		//-arrow launcher
		//-landmines DONE
		//-spikes DONE
		//-hidden traps
		//-leaves
	
	//PROGRAM MAP MAKER DONE
	//NEW MAPS
		//-mountainous forest WIP
		//-catacombs WIP
		//-hellish landscape
		//-stone bridge in hell
		//-gates of hell
	
	//NEW MAP EFFECTS
		//cosmetic changes
		//falling snow
		//falling sparks/ash
		//falling leaves
		//rain
		//fog DONE
	
	//NEW ITEMS
		//-coins? currency? DONE
		//-slimeballs can be used as currency
		//-drugs
		//-heorin
		//-cocaine
		//-slimeball launcher
		//-enemy controller
		//-energy pickups ADDED
		//-wands WIP
		//-kill wand DONE
		//-food WIP
		//-bread
		//-pie
		//-canned food
		//-meat
		//-wire
		//-sticks
		//-trash
		//-bones
		//-new rotten flesh textures
	
	//LISTS OF SPELLS BLESSINGS AND CURSES
	//CURSES
		//-slow
		//-paralyze
		//-poison DONE
		//-burning spray
		//-fatigue
		//-power word kill DONE
		//-healing particles?
	
	//STAGE TWO: STORYLINE DYNAMICS: PLANNED
	
	//DIALOGUE CUTSCENES
		//-shopkeeper
		//-shopkeeper will give random tips according to whichever item has been selected
		//-some tips can be completely useless
		//-some tips can refer to the outside environment
		//-some tips hint at the mechanics within the game
		//-dialogue boxes DONE
		//-storyline cutscene dialogue WIP
		//-special weapons dealer can sell unique weapons he makes himself
	
	//BOSSES
		//-bosses will resemble bullet hell shooters, with attack patterns and vulnerable spots.
		//-each boss will be unique and have their own attack patterns.
	
		//Possible theme songs:
			//Crusade - Issac Rebirth OST;
			//Ambush - Issac Rebirth OST;
			//Destruction of Determination - Chara Undertale FanOST;
			//Complex - Parasyte OST;
			//Final Battle - Overlord OST
			//Dance of Curse - Escaflowne;
			//S - One Punch Man OST;
			//One punch man - OST - 07. Monster;
			//Sign - Bererk OST;
			//Dr. Doom Theme - MvC3 OST;
			//
	
		//Penta-headed daemon;
			//Theme: Dark Energy - One Punch Man OST;
			//No shields; Once taken enough damage, blows up to reveal life source;
			//Life source takes permenent damage; each time it decreases by 20%, power and difficulty of daemon increases;
			//on its last head; goes berserk;
		//Giant Three-Headed Skeleton;
			//Theme: 
	
	
	
	
	//? ? OLD LIST ? ?
	//DIRECTIONS //d0r1u2l3
	//AMBIENCE
		//-split into two parts
		//-ambience will be assigned for each level and boss.
		//-for now, there will be no sound effects
		//-part 1 will have 8bit soundtrack
		// METAL 8 BIT sountrack;
		//parts 2 and 3 will have realistic soundtrack
		//Imagined Herbal Flows - Departure
		//CloZee - Koto
		//Odesza- Koto?
		//Bonobo - Kiara 
		//Daniel Ryan - Nagasaki
		//Varien (feat. Miyoki) - Kamisama?
		//ODESZA - IPlayYouListen 
		//Phaeleh - Tokoi?
		//aKu - The Final Blow
		//O Kuma - ryoto
		
		//BOSSES pt 1
		//-Kuma (mountain top)
		//-Succubus (mansion)
		//-Minotaur (Labryinth)
		//-Warlord (Ending 2)
		//-Lava Elemental (Magma)
		//-Demonic Tree (forest)
		//-Dracula (???)
		//-Winged Bird Demon (???)
		//-Fallen Samurai (???)
		//-Fallen Hero (???)
		//-Fallen Female Sorcerer (???)
		//-Fallen Angel (Road to the top)
		//-One Winged Angel (Bonus 1)
		//-Sans (Bonus 2)
		
		//BOSSES pt 2
	
	//Leave area on maps DONE
	//Basic Mechanics:
	//-when a player steps on the area, leaves map to go to another area
	//-player will move to the leave area adjacent to the direction player is in;
	//-the leave area is made active again when the player steps off of the leave area;
	//-leave areas will only affect players
	
	//teleport tiles CHANGE LEAVE AREA TILES TO FORMAT TO THIS
	//steping on a tile wil teleport you to the correct destination
	//black background phase in phase out
	
	//want to add telefrag DONE
	
	//want to add blocking, parrying, and counter mechanics DONE
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
//Fire YES
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
