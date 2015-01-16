package cards;

import java.util.List;
import java.util.Random;

import game.Game;
import game.Player;

public class King extends Card {

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return 6;
	}

	@Override
	public void discard(Game currentGame) {
		super.discard(currentGame);
		List<Player> otherPlayers = currentGame.getOtherUnprotectedPlayers();
		int otherPlayersCount = otherPlayers.size();
		if (otherPlayersCount > 0) {
			Player playerToGuess = otherPlayers.get(new Random().nextInt(otherPlayersCount));
			
			Card swap = playerToGuess.getCurrentHand();
			playerToGuess.setCurrentHand(currentGame.getCurrentPlayer().getCurrentHand());
			currentGame.getCurrentPlayer().setCurrentHand(swap);
		}
	}

}