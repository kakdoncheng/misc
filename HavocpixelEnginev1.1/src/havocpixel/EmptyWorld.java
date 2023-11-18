package havocpixel;


public class EmptyWorld extends World{

	public EmptyWorld(Game game) {
		super(game);
		worldLabel="Empty Area";
	}

	@Override
	public void load() {
		game.setLoadingText("Loading Empty World");
		System.out.print("havocpixel.EmptyWorld:INFO: Loading.\n");
		loadDefaultWorld();
		//em.addEntity(new BasicPlayer(game,25*32,25*32));
		//em.addEntity(new BasicEntity(game,300,100));
	}

}
