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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Jugador ").append(this.name).append(": ");
        for (Card card : this.hand){
            sb.append(card.toString()).append(" ");
        }
        
        return sb.toString();
    }
}
