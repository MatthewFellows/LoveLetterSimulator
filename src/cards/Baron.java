package cards;

import game.Game;
import game.Player;

public class Baron extends Card {

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public void discard(Game currentGame, Player otherPlayerEffected, int otherRank) {
		super.discard(currentGame, otherPlayerEffected, otherRank);
		
		if (otherPlayerEffected != null && otherPlayerEffected.getCurrentHand().getValue() < currentGame.getCurrentPlayer().getCurrentHand().getValue()) {
			currentGame.removePlayer(otherPlayerEffected);
		} else if (otherPlayerEffected != null && otherPlayerEffected.getCurrentHand().getValue() > currentGame.getCurrentPlayer().getCurrentHand().getValue()) {
			currentGame.removePlayer(currentGame.getCurrentPlayer());
		}
	}
}
