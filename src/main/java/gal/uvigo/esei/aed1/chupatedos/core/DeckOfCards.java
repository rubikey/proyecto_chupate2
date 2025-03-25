package gal.uvigo.esei.aed1.chupatedos.core;



public class DeckOfCards {

    public DeckOfCards() {
        CardStack stack = new CardStack();
        for (Card card : Card.values()){
            stack.push(card);
        }
        
        System.out.println(stack);
        
    }
    



}
