package gal.uvigo.esei.aed1.chupatedos.core;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final String name;
    private final List<Card> hand;
    // Lista de cartas (mano/hand)

    // Método constructor
    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    // Getter para obtener el nombre del jugador al mostrar el estado de la mesa
    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }
   
    
    // Método para añadir carta a la mano del jugador
    public void addCard(Card card) {
        hand.add(card);
    }
    
    
    // Método para eliminar una carta de la mano del jugador
    public Card playCard (Card card){
        hand.remove(card);
        return card;
    }
    

    // Método para mostrar cartas jugables de cada jugador
    public List<Card> getPlayableCard(Card topCard){
        
        List<Card> playable = new ArrayList<>(); // Lista de cartas jugables (inicialmente vacía)
        for (Card card : hand){
            if(card.isPlayable(topCard)) // se van añadiendo a la lista si es jugable
                playable.add(card);
        }
        return playable;
        
    }
    
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Mano de ").append(this.name).append(": ");
        for (Card card : this.hand){
            sb.append(card.toString()).append(" ");
        }
        
        return sb.toString();
    }
}
