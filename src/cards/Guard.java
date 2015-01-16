package cards;

import java.util.List;
import java.util.Random;

import game.Game;
import game.Player;

public class Guard extends Card {

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public void discard(Game currentGame) {
		super.discard(currentGame);
		List<Player> otherPlayers = currentGame.getOtherUnprotectedPlayers();
		int otherPlayersCount = otherPlayers.size();
		if (otherPlayersCount > 0) {
			Player playerToGuess = otherPlayers.get(new Random().nextInt(otherPlayersCount));
			int rankToGuess = new Random().nextInt(7) + 2;
			
			if (playerToGuess.getCurrentHand().getValue() == rankToGuess) {
				currentGame.removePlayer(playerToGuess);
			}
		}
	}
}
