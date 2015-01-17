package cards;

import game.Game;
import game.Player;

public class King extends Card {

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return 6;
	}

	@Override
	public void discard(Game currentGame, Player otherPlayerEffected, int otherRank) {
		super.discard(currentGame, otherPlayerEffected, otherRank);
		if (otherPlayerEffected != null) {
			Card swap = otherPlayerEffected.getCurrentHand();
			otherPlayerEffected.setCurrentHand(currentGame.getCurrentPlayer().getCurrentHand());
			currentGame.getCurrentPlayer().setCurrentHand(swap);
		}
	}

}
