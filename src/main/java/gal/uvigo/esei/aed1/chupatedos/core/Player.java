package gal.uvigo.esei.aed1.chupatedos.core;

import java.util.List;

public class Player {

    private final String name;
    private List<Card> deck;

    public Player(String name) {
        this.name = name;
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public String getName() {
        return name;
    }
}
