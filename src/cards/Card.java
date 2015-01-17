package cards;

import game.Game;
import game.Player;

public abstract class Card {
	public abstract int getValue();
	public void discard(Game currentGame) {
		discard(currentGame, null, 0);
	}
	public void discard(Game currentGame, Player otherPlayerEffected, int otherRank) {
		
	}
	public String toString() {
		return this.getClass().getName();
	}
}
