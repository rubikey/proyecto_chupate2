package gal.uvigo.esei.aed1.chupatedos.core;

import java.util.Collections;
import java.util.Stack;

public class DeckOfCards {

    private final Stack<Card> deck;

    public DeckOfCards() {
        deck = new Stack<>();
    }

    public void initializeDeck() {
        for (Card card : Card.values()) {
            deck.push(card);
        }

        Collections.shuffle(deck);
    }
}
