package game;

import java.util.ArrayList;
import java.util.List;

import cards.Card;

public abstract class Player {
	
	private Card currentHand;
	private Game currentGame;
	private String playerName;
	private Card currentDecisionCard;
	private List<Card> cardsPlayed;
	private int numWins = 0;
	
	public void setupPlayer(Card currentHand, Game currentGame, String playerName) {
		this.setCurrentHand(currentHand);
		this.setCurrentGame(currentGame);
		this.setPlayerName(playerName);
		this.setCardsPlayed(new ArrayList<Card>());
	}
	
	public void reset(Card currentHand, Game currentGame) {
		this.setCurrentHand(currentHand);
		this.setCurrentGame(currentGame);
		this.setCardsPlayed(new ArrayList<Card>());
	}

	public Card getCurrentHand() {
		return currentHand;
	}

	public void setCurrentHand(Card currentHand) {
		this.currentHand = currentHand;
	}

	public Game getCurrentGame() {
		return currentGame;
	}

	public void setCurrentGame(Game currentGame) {
		this.currentGame = currentGame;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public Card getCurrentDecisionCard() {
		return currentDecisionCard;
	}

	public void setCurrentDecisionCard(Card currentDecisionCard) {
		this.currentDecisionCard = currentDecisionCard;
	}

	public List<Card> getCardsPlayed() {
		return cardsPlayed;
	}

	public void setCardsPlayed(List<Card> cardsPlayed) {
		this.cardsPlayed = cardsPlayed;
	}
	
	public void addCardThatHasBeenPlayed(Card cardPlayed) {
		cardsPlayed.add(cardPlayed);
	}
	
	public boolean isProtected() {
		return cardsPlayed.size() > 0 && (cardsPlayed.get(cardsPlayed.size() - 1).getValue() == 4);
	}
	
	public String toString() {
		return this.getPlayerName();
	}
	
	public void win() {
		numWins++;
	}
	
	public int getWins() {
		return numWins;
	}
	
	public abstract void play(Game currentGame);
	
	public abstract Card decideWhichCardToPlay(Game currentGame);
}
