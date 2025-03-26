package gal.uvigo.esei.aed1.chupatedos.core;

import java.util.EmptyStackException;
import java.util.Stack;





public class Table {

    // Stack para cartas boca arriba
    private Stack<Card> faceupCards;
    
    
    // Constructor de stack cartas boca arriba vacío
    public Table(){
        faceupCards = new Stack<>();
        
    }

    // Colocar una carta en la pila
    public void placeCard(Card card){
        faceupCards.push(card);
    }
    
    // Hace visible la carta que está en la cima de la pila
    public Card getTopCard() throws EmptyStackException{
        
        return faceupCards.peek();
    }
    
    
    // Número de cartas boca arriba
    public int getFaceupCounter(){
        return faceupCards.size();
        
    }
    
    
    
   
    

    

    
}
