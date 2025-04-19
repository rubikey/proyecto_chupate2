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
    public List<Card> getPlayableCards(Card topCard) {
        List<Card> playableCards = new ArrayList<>();
        for (Card card : hand) {
            if (card.getNumber() == topCard.getNumber() || card.getSuit() == topCard.getSuit()) {
                playableCards.add(card);
            }
        }
        return playableCards;
    }
    
    // devuelve la carta jugada
    public Card playCard(int index) {
        if (index >= 0 && index < hand.size()) {
            return hand.remove(index);
        }
        return null;
    }
    
    //Para robar cartas del mazo
    public void drawCard(DeckOfCards deck) {
        Card drawnCard = deck.deal();
        if (drawnCard != null) {
            hand.add(drawnCard);
        }
    }

    // Verifica si hay cartas jugables
    public boolean hasPlayableCards(Card topCard) {
        return !getPlayableCards(topCard).isEmpty();
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
