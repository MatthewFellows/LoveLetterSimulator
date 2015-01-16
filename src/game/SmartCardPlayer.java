package game;

import cards.Card;

public class SmartCardPlayer extends Player {
	@Override
	public Card decideWhichCardToPlay() {
		Card cardToReturn;
		if (getCurrentDecisionCard().getValue() < getCurrentHand().getValue() && getCurrentDecisionCard().getValue() != 8) {
			cardToReturn = getCurrentDecisionCard();
			setCurrentDecisionCard(null);
		} else if (getCurrentHand().getValue() != 8) {
			cardToReturn = getCurrentHand();
			setCurrentHand(getCurrentDecisionCard());
		} else {
			cardToReturn = getCurrentDecisionCard();
			setCurrentDecisionCard(null);
		}
		addCardThatHasBeenPlayed(cardToReturn);
		return cardToReturn;
	}
}
