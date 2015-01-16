package cards;

import game.Game;

public class Princess extends Card {

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return 8;
	}

	@Override
	public void discard(Game currentGame) {
		super.discard(currentGame);
		currentGame.removePlayer(currentGame.getCurrentPlayer());
	}
}
