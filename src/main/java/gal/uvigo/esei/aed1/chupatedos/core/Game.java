package gal.uvigo.esei.aed1.chupatedos.core;

import gal.uvigo.esei.aed1.chupatedos.iu.IU;
import java.util.ArrayList;
import java.util.List;

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
        //Mostramos el estado de la mesa
        this.tableState();

        int turno = 0;
        Player jugadorGanador = null;
        while (jugadorGanador == null) {
            Player currentPlayer = players[turno];

            //Mostramos la mano jugador activo
            iu.displayMessage("-----------------------------------------------------");
            iu.displayMessage("Turno de " + currentPlayer.getName());
            iu.displayMessage(currentPlayer.toString());

            boolean pierdeTurno = false;

            //Mostramos las cartas disponibles para jugar & hacemos escoger al usuario una carta
            List<Card> validCards = currentPlayer.getPlayableCards(table.getTopCard());
            if (validCards.isEmpty()) { //El usuario debera robar una carta 
                iu.displayMessage("El jugador no tiene ninguna carta valida por lo que debera robar una de la mesa.");
                Card stolenCard = deck.deal();

                if (!table.getTopCard().equals(stolenCard)) {
                    iu.displayMessage("Como la carta robada " + stolenCard.toString() + " no es jugable, el jugador pierde turno.");
                    pierdeTurno = true;
                } else {
                    iu.displayMessage("Carta robada " + stolenCard.toString());
                    validCards.add(stolenCard);
                }
                currentPlayer.addCard(stolenCard);
            }

            if (!pierdeTurno) {
                Card playedCard = iu.selectPlayableCard(validCards);
                iu.displayMessage("El jugador juega " + playedCard.toString());
                currentPlayer.removeCard(playedCard);
                table.placeCard(playedCard);
            }

            if (currentPlayer.isHandEmpty()) {
                jugadorGanador = currentPlayer;
            } else {
                if (this.deck.getRemainingCards() == 0) {
                    deck.setNewDeck(table.deleteAllButLastCard());
                }

                this.tableState();
            }

            turno = (turno - 1 + players.length) % players.length;
        }

        iu.displayMessage("----------------------------------------");
        iu.displayMessage("El jugador " + jugadorGanador.getName() + " es el Ganador.");
    }

    private void tableState() {
        iu.displayMessage("-----------------------------------------------------");
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
