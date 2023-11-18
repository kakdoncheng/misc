package havocpixel.entities;

public enum Faction {
	UNALIGNED,
	HUMAN,
	GREATER_DEMON,
	LESSER_DEMON,
	POSSESSED,
	NATURE_SPIRIT;
	
	public boolean isEnemy(Faction faction){
		switch(this){
		case UNALIGNED:
			return true;
		case HUMAN:
			return faction!=Faction.HUMAN;
		case GREATER_DEMON:
			return faction!=Faction.GREATER_DEMON && faction!=Faction.LESSER_DEMON;
		case LESSER_DEMON:
			return faction!=Faction.GREATER_DEMON && faction!=Faction.LESSER_DEMON;
		case POSSESSED:
			return faction!=Faction.POSSESSED;
		case NATURE_SPIRIT:
			return faction!=Faction.NATURE_SPIRIT;
		default:
			return true;
		}
	}
	
	public boolean canAttack(Faction faction){
		switch(this){
		case UNALIGNED:
			return true;
		case HUMAN:
			return faction!=Faction.HUMAN;
		case GREATER_DEMON:
			return true;
		case LESSER_DEMON:
			return faction!=Faction.GREATER_DEMON;
		case POSSESSED:
			return true;
		case NATURE_SPIRIT:
			return faction!=Faction.NATURE_SPIRIT;
		default:
			return true;
		}
	}
	

}
