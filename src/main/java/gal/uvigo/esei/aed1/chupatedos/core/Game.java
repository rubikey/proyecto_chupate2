package gal.uvigo.esei.aed1.chupatedos.core;

import gal.uvigo.esei.aed1.chupatedos.iu.IU;
import java.util.List;
import java.util.ArrayList;

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

    // Método para turnos
    public void playable() {
        int turn = 0;

        while (true) {
            Player currentPlayer = players[turn];
            iu.displayMessage("----------------------------------------- \n"
                    + "Turno de " + currentPlayer.getName());

            List<Card> playableCards = currentPlayer.getPlayableCard(table.getTopCard()); // Lista de cartas jugables
            iu.showHand(currentPlayer.getHand(), playableCards); // Muestra la mano y las cartas jugables del jugador
            
            if (!playableCards.isEmpty()) { // Si se puede jugar alguna carta, pregunta al usuario cual quiere jugar
                int choice = iu.askCardToPlay(playableCards);
                Card selectedCard = playableCards.get(choice);
                currentPlayer.playCard(selectedCard);
                table.placeCard(selectedCard); // Se coloca en la pila la carta escogida por el player
            } else {
                iu.displayMessage("No se puede jugar ninguna carta. Roba una."); // Si el player no tiene cartas jugables, debe robar una
                if (!deck.isEmpty()) {
                    Card drawnCard = deck.deal();
                    currentPlayer.addCard(drawnCard);
                    if (drawnCard.isPlayable(table.getTopCard())) { // Si la carta robada se puede jugar, se juega
                        iu.displayMessage("La carta robada es jugable y se puede usar.");
                        currentPlayer.playCard(drawnCard);
                        table.placeCard(drawnCard);
                    } else {
                        iu.displayMessage("La carta robada no es jugable."); // Si la carta robada no se puede jugar, el jugador pierde turno
                    }
                } else { // Si el deck para robar está vacío, se barajan las cartas de la mesa
                    iu.displayMessage("El deck está vacío. Se vuelve a barajar el deck.");
                    
                }
            }

            if (currentPlayer.getHand().isEmpty()) { // si la mano del jugador no tiene elementos, significa que ha ganado la partida
                iu.displayMessage(currentPlayer.getName() + " ha ganado la partida!");
                break;
            }

            turn = (turn + 1) % players.length; // se incrementa el turno en 1, para pasar al siguiente jugador
        }
    }

}


