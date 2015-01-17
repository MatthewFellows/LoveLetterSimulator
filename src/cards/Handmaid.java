package cards;

import game.Game;
import game.Player;

public class Handmaid extends Card {

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public void discard(Game currentGame, Player otherPlayerEffected, int otherRank) {
		super.discard(currentGame, otherPlayerEffected, otherRank);
	}

}
