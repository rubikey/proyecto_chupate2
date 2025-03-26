package gal.uvigo.esei.aed1.chupatedos.core;

import gal.uvigo.esei.aed1.chupatedos.iu.IU;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private final IU iu;

    public Game(IU iu) {
        this.iu = iu;
    }

    public List<Player> createPlayers() {
        List<Player> players = new ArrayList<>();
        int numberPlayers = 0;
        boolean isValid = false;

        while (!isValid) {
            numberPlayers = iu.readNumber("Introduce el numero de jugadores (2-5): ");
            if (numberPlayers >= 2 && numberPlayers <= 5) {
                isValid = true;
            }
        }

        for (int i = 0; i < numberPlayers; i++) {
            players.add(new Player(iu.readString("Introduce el nombre del jugador [" + (i + 1) + "]: ")));
        }

        return players;
    }

    /**
     * Metodo principal para jugar
     */
    public void play() {
        Table table = new Table(createPlayers());
        table.initializeDecks();
        iu.displayMessage(table.toString());
    }

}
