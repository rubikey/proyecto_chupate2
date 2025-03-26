package gal.uvigo.esei.aed1.chupatedos.core;

import java.util.EmptyStackException;
import java.util.Stack;





public class Table {

    private Stack<Card> cartasDescartadas;
    private int numCartasDesc;
    
    
    public Table(){
        cartasDescartadas = new Stack<>();
        numCartasDesc=0;
        
    }

    public void colocarCarta(Card carta){
        cartasDescartadas.push(carta);
        numCartasDesc++;
    }
    
    public int getNumCartas(){
        return numCartasDesc;
    }
    
    public Card getCartaCima(){
        if(!cartasDescartadas.isEmpty()){
            return cartasDescartadas.peek();
        }
        return null;
    }
    
   
    

    

    
}
