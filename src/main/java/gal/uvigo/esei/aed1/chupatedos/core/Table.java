package gal.uvigo.esei.aed1.chupatedos.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Table {

    private static final int CARDS_PER_PLAYER_INIT = 7;
    private final List<Player> players;
    private final DeckOfCards deck;
    private final Stack<Card> usedCards;

    public Table(List<Player> players) {
        this.players = players;
        this.usedCards = new Stack<>();
        this.deck = new DeckOfCards();
    }

    public void initializeDecks() {
        deck.initializeDeck();

        for (Player player : players) {
            List<Card> cards = new ArrayList<>();

            for (int i = 0; i < CARDS_PER_PLAYER_INIT; i++) {
                cards.add(deck.dealCard());
            }

            player.setDeck(cards);
        }

        usedCards.push(deck.dealCard());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Carta Boca Arriba Actual: ").append(usedCards.lastElement().getNumber()).append(" de ").append(usedCards.lastElement().getSuit().name()).append("\n");
        sb.append("Cartas Boca Arriba: ").append(usedCards.size()).append("\n");
        sb.append("Cartas Baraja: ").append(deck.size()).append("\n");

        sb.append("Cartas Jugadores: \n");

        for (Player player : players) {
            sb.append("\t").append(player.getName()).append("\n");

            for (Card c : player.getDeck()) {
                sb.append("\t").append("\t").append(c.getNumber()).append(" de ").append(c.getSuit().name()).append("\n");
            }
        }

        return sb.toString();
    }
}
