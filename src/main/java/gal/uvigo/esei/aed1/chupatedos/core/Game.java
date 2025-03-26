package gal.uvigo.esei.aed1.chupatedos.core;


import gal.uvigo.esei.aed1.chupatedos.iu.IU;

public class Game {

    private final IU iu;
    private final DeckOfCards deck;
    private final Table table;
    //private final List<Player> players;

    public Game(IU iu) {
        this.iu = iu;
        this.deck = new DeckOfCards();
        this.table = new Table();
       //  this.players = new LinkedList<>();

    }

   
    
}

