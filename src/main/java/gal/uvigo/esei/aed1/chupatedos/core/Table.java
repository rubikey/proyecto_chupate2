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
        cards.addAll(faceupCards); //Insertamos todas las cartas del Stack<> de this a el nuevo Objeto
        cards.remove(this.getTopCard()); //Eliminamos la ultima carta que corresponde a la ultima que esta boca arriba
        
        faceupCards.removeIf(card -> card != this.getTopCard()); //Del Stack<> de this eliminamos todas las Card excepto la ultima boca arriba
        
        return cards;
    }

    // Número de cartas que hay boca arriba
    public int getFaceupCounter() {
        return faceupCards.size();
    }

}
