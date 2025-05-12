package gal.uvigo.esei.aed1.chupatedos.core;

import java.util.EmptyStackException;
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

    //Metodo para eliminar todas las cartas excepto la última y devolver las cartas eliminadas en un Stack<> para volver a barajarlas, y que los players tengan de donde robar cartas
    public Stack<Card> deleteAllExceptLastCard() {
        Stack<Card> cardsToShuffleAgain = new Stack<>(); // Nuevo Stack de cartas
        Card lastCard = faceupCards.pop(); // Guardamos la carta que está en la cima del Stack en una variable local

        while (!faceupCards.isEmpty()) {
            cardsToShuffleAgain.push(faceupCards.pop()); // Hacemos un recorrido de la pila, y vamos guardando las cartas en la nueva pila
        }

        faceupCards.push(lastCard); // Ahora la carta que estaba en la cima antes, es la primera que se pone en la mesa

        return cardsToShuffleAgain; // Cartas que se volverán a barajar
    }

    // Número de cartas que hay boca arriba
    public int getFaceupCounter() {
        return faceupCards.size();
    }

}
