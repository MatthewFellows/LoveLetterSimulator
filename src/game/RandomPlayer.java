package game;

import java.util.List;
import java.util.Random;

import cards.Card;


public class RandomPlayer extends Player {
	
	@Override
	public void play(Game currentGame) {
		Card cardToPlay = this.decideWhichCardToPlay(currentGame);
		
		List<Player> otherPlayers = currentGame.getOtherUnprotectedPlayers();
		int otherPlayersCount = otherPlayers.size();
		if (otherPlayersCount > 0) {
			Player playerToGuess = otherPlayers.get(new Random().nextInt(otherPlayersCount));
			int rankToGuess = new Random().nextInt(7) + 2;
		
			cardToPlay.discard(currentGame, playerToGuess, rankToGuess);
		}
	}
	
	@Override
	public Card decideWhichCardToPlay(Game currentGame) {
		Card cardToReturn;
		if (new Random().nextBoolean()) {
			cardToReturn = getCurrentDecisionCard();
			setCurrentDecisionCard(null);
		} else {
			cardToReturn = getCurrentHand();
			setCurrentHand(getCurrentDecisionCard());
		}
		addCardThatHasBeenPlayed(cardToReturn);
		return cardToReturn;
	}
}
