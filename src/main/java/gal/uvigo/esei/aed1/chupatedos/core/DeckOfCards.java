package gal.uvigo.esei.aed1.chupatedos.core;

import java.util.Collections;
import java.util.Stack;
import java.util.EmptyStackException;

public class DeckOfCards {

    private final Stack<Card> deck;

    // Método constructor
    public DeckOfCards() {
        deck = new Stack<>();
        for (Card card : Card.values()) {
            deck.push(card);
        }
        this.shuffle();
    }

    // Método para barajar
    public void shuffle() {
        Collections.shuffle(deck);
    }

    // Coger la siguiente carta (top) de la baraja usando pop. Elimina la carta de
    // la baraja al repartir.
    public Card deal() {
        try {
            if (!deck.isEmpty()) {
                System.out.println(deck.size());
                return deck.pop();
            }
        } catch (EmptyStackException e) {
            // Error si se intenta extraer carta de la pila vacía
            throw new IllegalStateException("No se puede repartir una carta. La baraja está vacía.", e);
        }
        return null;
    }

    // Método para saber la cantidad de cartas que quedan en la baraja
    public int getRemainingCards() {
        return deck.size();
    }

}
