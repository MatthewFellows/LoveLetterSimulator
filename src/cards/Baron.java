package cards;

import java.util.List;
import java.util.Random;

import game.Game;
import game.Player;

public class Baron extends Card {

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public void discard(Game currentGame) {
		super.discard(currentGame);
		List<Player> otherPlayers = currentGame.getOtherUnprotectedPlayers();
		int otherPlayersCount = otherPlayers.size();
		if (otherPlayersCount > 0) {
			Player playerToGuess = otherPlayers.get(new Random().nextInt(otherPlayersCount));
			
			if (playerToGuess.getCurrentHand().getValue() < currentGame.getCurrentPlayer().getCurrentHand().getValue()) {
				currentGame.removePlayer(playerToGuess);
			} else if (playerToGuess.getCurrentHand().getValue() > currentGame.getCurrentPlayer().getCurrentHand().getValue()) {
				currentGame.removePlayer(currentGame.getCurrentPlayer());
			}
		}
	}
}
