package game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cards.Card;

public class Game {

	public Deck deck = new Deck();
	public List<Player> originalPlayers = new ArrayList<Player>();
	public List<Player> players = new ArrayList<Player>();
	int currentGo = 0;
	public boolean finishedGame = false;
	
	public void setup() {
		deck.setup();
		
		Player player1 = new LowestValueCardPlayer();
		player1.setupPlayer(deck.getNextCard(), this, "Player 1");
		originalPlayers.add(player1);
		Player player2 = new RandomPlayer();
		player2.setupPlayer(deck.getNextCard(), this, "Player 2");
		originalPlayers.add(player2);
		Player player3 = new RandomPlayer();
		player3.setupPlayer(deck.getNextCard(), this, "Player 3");
		originalPlayers.add(player3);
		
	}
	
	public void resetGame() {
		finishedGame = false;
		deck.setup();

		players = new ArrayList<Player>();
		for (Player p : originalPlayers) {
			p.setCurrentDecisionCard(null);
			p.setCurrentHand(deck.getNextCard());
			p.setCurrentGame(this);
			players.add(p);
		}
	}
	
	public void play() {
		for (int i = 0; i < 100000; i++) {
			resetGame();
			currentGo = i % players.size();
			while (deck.hasMoreCards()) {
				Player currentPlayer = players.get(currentGo % players.size());
				currentPlayer.setCurrentDecisionCard(deck.getNextCard());
				if (holdsCountess(currentPlayer)
					&& (holdsPrince(currentPlayer) || holdsKing(currentPlayer))) {
					Card cardToPlay = getCountess(currentPlayer);	
					cardToPlay.discard(this);
					currentGo++;
				} else {
					currentPlayer.play(this);
					currentGo++;
				}
			}
			resolveGame();
		}
		for (Player p : originalPlayers) {
			System.out.println(p.getPlayerName() + " has won " + p.getWins() + " times");
		}
	}

	private Card getCountess(Player currentPlayer) {
		if (currentPlayer.getCurrentDecisionCard().getValue() == 7) {
			Card countess = currentPlayer.getCurrentDecisionCard();
			currentPlayer.setCurrentDecisionCard(null);
			return countess; 
		} 
		currentPlayer.setCurrentHand(currentPlayer.getCurrentDecisionCard());
		Card countess = currentPlayer.getCurrentHand();
		currentPlayer.setCurrentDecisionCard(null);
		return countess; 
	}

	private boolean holdsPrince(Player currentPlayer) {
		return currentPlayer.getCurrentDecisionCard().getValue() == 5 || currentPlayer.getCurrentHand().getValue() == 5;
	}

	private boolean holdsKing(Player currentPlayer) {
		return currentPlayer.getCurrentDecisionCard().getValue() == 6 || currentPlayer.getCurrentHand().getValue() == 6;
	}

	private boolean holdsCountess(Player currentPlayer) {
		return currentPlayer.getCurrentDecisionCard().getValue() == 7 || currentPlayer.getCurrentHand().getValue() == 7;
	}
	
	public Player getCurrentPlayer() {
		return players.get(currentGo % players.size());
	}
	
	public List<Player> getOtherPlayers(Player thisPlayer, boolean unprotectedOnly) {
		return players.stream().filter(g -> !g.equals(thisPlayer)).filter(g -> !unprotectedOnly || !g.isProtected()).collect(Collectors.toList());
	}
	
	public List<Player> getOtherPlayers() {
		return getOtherPlayers(getCurrentPlayer(), false);
	}
	
	public List<Player> getOtherUnprotectedPlayers() {
		return getOtherPlayers(getCurrentPlayer(), true);
	}
	
	public void removePlayer(Player player) {
		//System.out.println(player.getPlayerName() + " is knocked out");
		player.addCardThatHasBeenPlayed(player.getCurrentHand());
		players.remove(player);
		if (players.size() == 1) {
			resolveGame();
		}
	}
	
	public void playerDiscardHand(Player player) {
		//System.out.println(player.getPlayerName() + " discards");
		player.addCardThatHasBeenPlayed(player.getCurrentHand());
		if (deck.hasMoreCards()) {
			player.setCurrentHand(deck.getNextCard());
		} else {
			player.setCurrentHand(deck.getExtraCard());
			resolveGame();
		}
	}
	
	public void resolveGame() {
		if (!finishedGame) {
			if (players.size() > 1) {
				Player currentHighest = null;
				for (Player p : players) {
					if (currentHighest == null || p.getCurrentHand().getValue() > currentHighest.getCurrentHand().getValue()) {
						currentHighest = p;
					} else if (currentHighest != null && p.getCurrentHand().getValue() == currentHighest.getCurrentHand().getValue()) {
						currentHighest = getTotalPlayed(currentHighest) > getTotalPlayed(p) ? currentHighest : p;
					}
				}
				/*System.out.println(currentHighest.getPlayerName() + " wins with: " + currentHighest.getCurrentHand());
				for (Player p : getOtherPlayers(currentHighest, false)) {
					System.out.println("\t" + p.getPlayerName() + " looses with: " + p.getCurrentHand());
				}*/
				currentHighest.win();
			} else if (players.size() > 0) {
				//System.out.println(players.get(0) + " wins with: " + players.get(0).getCurrentHand());
				players.get(0).win();
			}
			finishedGame = true;
			deck.empty();
		}
	}
	
	public int getTotalPlayed(Player player) {
		int sum = 0;
		for(Card c : player.getCardsPlayed()) {
			sum += c.getValue();
		}
		return sum;
	}
	
	public void forceDiscard(Player player) {
		//System.out.println(player.getPlayerName() + " is forced to discard: " + player.getCurrentHand());
		if (player.getCurrentHand().getValue() == 8) {
			player.getCurrentHand().discard(this);
		}
		if (players.contains(player)) {
			if (deck.hasMoreCards()) {
				player.setCurrentHand(deck.getNextCard());
			} else {
				player.setCurrentHand(deck.getExtraCard());
				resolveGame();
			}
		}
	}
	
	public static void main(String[] args) {
		Game g = new Game();
		g.setup();
		g.play();
	}
}
