package gal.uvigo.esei.aed1.chupatedos.core;





public class Table {

    // Stack para cartas boca arriba
    private Stack<Card> faceupCards;
    
    
    // Constructor de stack cartas boca arriba vacío
    public Table(){
        faceupCards = new LinkedStack<>();
        
    }

    // Colocar una carta en la pila
    public void placeCard(Card card){
        faceupCards.push(card);
    }
    
    // Hace visible la carta que está en la cima de la pila
    public Card getTopCard() throws EmptyException{
        
        return faceupCards.top();
    }
    
    
    // Número de cartas boca arriba
    public int getFaceupCounter(){
        return faceupCards.size();
        
    }
    
    
    
   
    

    

    
}
