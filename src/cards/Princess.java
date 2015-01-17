package cards;

import game.Game;
import game.Player;

public class Princess extends Card {

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return 8;
	}

	@Override
	public void discard(Game currentGame, Player otherPlayerEffected, int otherRank) {
		super.discard(currentGame, otherPlayerEffected, otherRank);
		currentGame.removePlayer(currentGame.getCurrentPlayer());
	}
}
