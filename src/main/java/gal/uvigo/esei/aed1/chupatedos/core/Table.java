package gal.uvigo.esei.aed1.chupatedos.core;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class Table {

    // Stack para cartas boca arriba
    private final Stack<Card> faceupCards;

    // Constructor del stack de cartas vacío;
    public Table() {
        faceupCards = new Stack<>();
    }

    // Colocar una carta en la pila (método push)
    public void placeCard(Card card) {
        faceupCards.push(card);
    }

    // Hace visible la carta que está en la cima de la pila (método peek)
    public Card getTopCard() throws EmptyStackException {
        return faceupCards.peek();
    }

    // Número de cartas que hay boca arriba
    public int getFaceupCounter() {
        return faceupCards.size();
    }

    
    
    // Hay que crear un metodo que pueda eliminar todas las cartas que hay en la mesa excepto la qque esta encima del todo, y estas meterlas en una lista para barajar
    
    
     
    
}
