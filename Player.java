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

    // Método para añadir carta a la mano del jugador
    public void addCard(Card card) {
        hand.add(card);
    }
    
    public List<Card> getHand() {
        return hand;
    }


    //las cartas jugables según la carta superior en mesa
     public List<Card> getPlayableCard(Card topCard) {
        List<Card> playable = new ArrayList<>();
        for (Card card : hand) {
            if (card.isPlayable(topCard)) {
                playable.add(card);
            }
        }
        return playable;
    }
   
    // devuelve la carta jugada
   public Card playCard(Card card) {
        hand.remove(card);
        return card;
    }

    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mano de ").append(name).append(": ");
        for (int i = 0; i < hand.size(); i++) {
            sb.append("(").append(i).append(")").append(hand.get(i)).append(" ");
        }
        return sb.toString().trim();
    }
}
