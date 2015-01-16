package cards;

import game.Game;

public abstract class Card {
	public abstract int getValue();
	public void discard(Game currentGame) {
		//System.out.println(currentGame.getCurrentPlayer().getPlayerName() + " plays: " + this.getClass());
	}
	public String toString() {
		return this.getClass().getName();
	}
}
