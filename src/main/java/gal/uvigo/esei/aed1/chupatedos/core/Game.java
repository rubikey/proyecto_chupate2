package gal.uvigo.esei.aed1.chupatedos.core;

import gal.uvigo.esei.aed1.chupatedos.iu.IU;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private final IU iu;
    private final DeckOfCards deck;
    private final Table table;
    private final List<Player> players;

    public Game(IU iu) {
        this.iu = iu;
        this.deck = new DeckOfCards();
        this.table = new Table();
        this.players = new ArrayList<>();

    }

    public void start() {
        int numOfPlayers = 0;
        do {

            numOfPlayers = iu.readNumber("Cuantos jugadores van a jugar? (Entre 2 y 5): ");

        } while (numOfPlayers < 2 || numOfPlayers > 5);

        for (int i = 1; i <= numOfPlayers; i++) {
            Player player = new Player(iu.readString("Nombre del jugador " + i + ": "));

            players.add(player);
        }

        deck.initializeDeck();
        deck.shuffle();

        for (int i = 0; i < 7; i++) {
            for (Player p : players) {
                p.addCard(deck.deal());

            }

        }

        
        table.placeCard(deck.deal());
        
        System.out.println("Carta en la mesa: " + table.getTopCard().toString());
       
        System.out.println("Cartas restantes: " + deck.getRemainingCards());
        
        
        for(Player p : players){
            p.printHand();
            
            
        }
        
    }

}
