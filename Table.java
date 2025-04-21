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
    // Método para quitar y devolver la carta superior
    public Card removeCard() throws EmptyStackException {
        return faceupCards.pop();
    }
    
    public List<Card> recycleCards() {
        List<Card> recycled = new ArrayList<>();
        while (faceupCards.size() > 1) {
            recycled.add(faceupCards.pop());
        }
        return recycled;
    }

}
