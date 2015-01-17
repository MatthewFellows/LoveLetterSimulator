package game;

import java.util.List;
import java.util.Random;

import cards.Card;

public class HighestValueCardPlayer extends Player {
	
	@Override
	public void play(Game currentGame) {
		Card cardToPlay = this.decideWhichCardToPlay();
		
		List<Player> otherPlayers = currentGame.getOtherUnprotectedPlayers();
		int otherPlayersCount = otherPlayers.size();
		if (otherPlayersCount > 0) {
			Player playerToGuess = otherPlayers.get(new Random().nextInt(otherPlayersCount));
			int rankToGuess = new Random().nextInt(7) + 2;
		
			cardToPlay.discard(currentGame, playerToGuess, rankToGuess);
		}
	}
	
	@Override
	public Card decideWhichCardToPlay() {
		Card cardToReturn;
		if (getCurrentDecisionCard().getValue() > getCurrentHand().getValue()) {
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
