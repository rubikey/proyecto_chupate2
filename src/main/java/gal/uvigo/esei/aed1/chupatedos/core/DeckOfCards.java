package gal.uvigo.esei.aed1.chupatedos.core;

import java.util.Collections;
import java.util.Stack;

public class DeckOfCards {

    private Stack<Card> deck;

    // Método constructor
    public DeckOfCards() {
        deck = new Stack<>();
        for (Card card : Card.values()) {
            deck.push(card);
        }
        
    }

    // Método para barajar privado ya que siempre barajamos desde la propia clase
    public void shuffle() {
        Collections.shuffle(deck);
    }
    
    //Cuando se acaben las cartas del deck este metodo servira para establecer las nuevas cartas del deck
    public void setNewDeck(Stack<Card> deck){ 
        this.deck = deck;
    }

    // Coger la siguiente carta (top) de la baraja usando pop. Elimina la carta de
    // la baraja al repartir.
    public Card deal() {
        if (!deck.isEmpty()) {
            return deck.pop();
        }
        return null;
    }

    // Método para saber la cantidad de cartas que quedan en la baraja
    public int getRemainingCards() {
        return deck.size();
    }

}
