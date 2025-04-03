package gal.uvigo.esei.aed1.chupatedos.core;

import gal.uvigo.esei.aed1.chupatedos.iu.IU;

public class Game {

    // Atributos de la clase Game
    private final IU iu; // Referencia a la interfaz de usuario
    private final DeckOfCards deck;
    private final Table table;
    private final Player[] players;
    // Lista de jugadores

    // Constructor para inicializar los atributos
    public Game(IU iu) {
        this.iu = iu; // Se asigna la interfaz de usuario pasada como parámetro
        this.deck = new DeckOfCards(); // Se crea una nueva baraja
        this.table = new Table(); // Se crea una nueva mesa
        this.players = iu.getPlayers(); // Se inicializa la lista de jugadores

    }

    /**
     * Método para iniciar el juego. Solicita el número de jugadores, crea los
     * jugadores, inicializa la baraja y reparte las cartas.
     */

    public void start() {
        // Repartimos 7 cartas a cada jugador
        for (int i = 0; i < 7; i++) {
            for (Player player : players) {
                // Recorremos la lista de jugadores para darles una carta a cada uno
                player.addCard(deck.deal());
            }

        }

        // Se coloca una carta en la mesa
        table.placeCard(deck.deal());
        // Se muestra la carta
        iu.displayMessage("Carta en la mesa: " + table.getTopCard().toString());
        // Cartas que quedan en la baraja
        iu.displayMessage("Cartas restantes: " + deck.getRemainingCards());
        // Mostrar la mano de cada jugador
        for (Player p : players) {
            iu.displayMessage(p.toString());
        }

    }
}
