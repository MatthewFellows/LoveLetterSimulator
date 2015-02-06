package game;

import java.util.List;
import java.util.Random;

import cards.Card;

public class SmartCardPlayer extends Player {
	
	@Override
	public void play(Game currentGame) {
		Guess guess = makeBestGuess(currentGame);
		guess.getCard().discard(currentGame, guess.getPlayer(), guess.getRank());
	}
	
	@Override
	public Card decideWhichCardToPlay(Game currentGame) {
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
		return cardToReturn;
	}
	
	private Guess checkAndPlayGuard(Game currentGame) {
		
		
		return null;
	}

	public Guess makeBestGuess(Game currentGame) {
		Guess guardGuess = checkAndPlayGuard(currentGame);		
		if (guardGuess != null) {
			return guardGuess;
		}
		
		Card cardToPlay = this.decideWhichCardToPlay(currentGame);
		addCardThatHasBeenPlayed(cardToPlay);
		
		List<Player> otherPlayers = currentGame.getOtherUnprotectedPlayers();
		int otherPlayersCount = otherPlayers.size();
		if (otherPlayersCount > 0) {
			Player playerToGuess = otherPlayers.get(new Random().nextInt(otherPlayersCount));
			int rankToGuess = new Random().nextInt(7) + 2;
		
			return new Guess(cardToPlay, rankToGuess, playerToGuess);
		}
		return new Guess(cardToPlay, 0, currentGame.getCurrentPlayer());
	}
	
	class Guess {
		private Card card;
		private Player player;
		private int rank;
		
		public Guess(Card card, int rank, Player player) {
			super();
			this.card = card;
			this.rank = rank;
			this.setPlayer(player);
		}
		public Card getCard() {
			return card;
		}
		public void setCard(Card card) {
			this.card = card;
		}
		public int getRank() {
			return rank;
		}
		public void setRank(int rank) {
			this.rank = rank;
		}
		public Player getPlayer() {
			return player;
		}
		public void setPlayer(Player player) {
			this.player = player;
		}
		
	}
}
