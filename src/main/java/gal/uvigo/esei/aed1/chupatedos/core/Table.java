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
    
    //Metodo para eliminar todas las cartas excepto la ultima y devolver las cartas eliminadas en un Stack<>
    public Stack<Card> deleteAllExceptLastCard(){
        Stack<Card> cards = new Stack<>(); //Declaramos vacio para que la referencia de objeto sea diferente
        
        //Eliminamos el primer indice hasta que solo quede 1 carta ; significa que queda la ultima carta que estaba boca arriba
        Card topCard = faceupCards.pop(); // guardamos el tope
        while (!faceupCards.isEmpty()) {
            cards.push(faceupCards.pop()); // quitamos todo lo demas
        }
        faceupCards.push(topCard); // restauramos la ultima carta jugada (top)
        
        return cards;
    }

    // Número de cartas que hay boca arriba
    public int getFaceupCounter() {
        return faceupCards.size();
    }

}
