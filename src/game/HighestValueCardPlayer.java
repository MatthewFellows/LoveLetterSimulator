package game;

import cards.Card;

public class HighestValueCardPlayer extends Player {
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
