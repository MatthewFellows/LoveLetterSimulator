package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cards.Baron;
import cards.Card;
import cards.Countess;
import cards.Guard;
import cards.Handmaid;
import cards.King;
import cards.Priest;
import cards.Prince;
import cards.Princess;

public class Deck {
	public List<Card> mainDeck = new ArrayList<Card>();
	public Card extraCard;
	
	public void setup() {
		mainDeck.add(new Guard());
		mainDeck.add(new Guard());
		mainDeck.add(new Guard());
		mainDeck.add(new Guard());
		mainDeck.add(new Guard());
		mainDeck.add(new Priest());
		mainDeck.add(new Priest());
		mainDeck.add(new Baron());
		mainDeck.add(new Baron());
		mainDeck.add(new Handmaid());
		mainDeck.add(new Handmaid());
		mainDeck.add(new Prince());
		mainDeck.add(new Prince());
		mainDeck.add(new King());
		mainDeck.add(new Countess());
		mainDeck.add(new Princess());
		
		Collections.shuffle(mainDeck);
		
		extraCard = mainDeck.remove(0);
		
	}
	
	public Card getNextCard() {
		if (mainDeck.size() > 0){
			return mainDeck.remove(0);
		} else {
			return null;
		}
	}
	
	public Card getExtraCard() {
		return extraCard;
	}
	
	public boolean hasMoreCards() {
		return mainDeck.size() > 0;
	}

	public void empty() {
		mainDeck = new ArrayList<Card>();
		extraCard = null;
	}
}
