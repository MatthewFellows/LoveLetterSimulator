package cards;

import game.Game;
import game.Player;

public class Guard extends Card {

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public void discard(Game currentGame, Player otherPlayerEffected, int otherRank) {
		super.discard(currentGame, otherPlayerEffected, otherRank);
		if (otherPlayerEffected != null && otherPlayerEffected.getCurrentHand().getValue() == otherRank && otherRank != 1) {
				currentGame.removePlayer(otherPlayerEffected);
		}
	}
}
