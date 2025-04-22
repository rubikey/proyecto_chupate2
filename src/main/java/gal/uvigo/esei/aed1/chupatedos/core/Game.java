package gal.uvigo.esei.aed1.chupatedos.core;

import gal.uvigo.esei.aed1.chupatedos.iu.IU;
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
        boolean partidaFinalizada = false;
        
        while (!partidaFinalizada) {
            Player currentPlayer = players[turno];

            //Mostramos la mano jugador activo
            iu.displayMessageSeparator("Turno de " + currentPlayer.getName());
            iu.displayMessage(currentPlayer.toString());

            boolean pierdeTurno = false;

            //Extraemos todas las cartas validas que tiene el usuario disponible para la carta boca arriba
            List<Card> validCards = currentPlayer.getPlayableCards(table.getTopCard());
            
            //Si la lista de cartas validas esta vacia (El usuario NO tiene cartas para jugar)
            if (validCards.isEmpty()) { //El usuario debera robar una carta 
                iu.displayMessage("El jugador no tiene ninguna carta valida por lo que debera robar una de la mesa.");
                Card stolenCard = deck.deal();

                if (!table.getTopCard().isCardPlayable(stolenCard)) { //Si la carta ROBADA NO es jugable: Pierde turno
                    iu.displayMessage("Como la carta robada " + stolenCard.toString() + " no es jugable, el jugador pierde turno.");
                    pierdeTurno = true;
                } else { //Si la carta ROBADA es jugable: NO pierde turno y la anadimos a Lista de cartas validas
                    iu.displayMessage("Carta robada " + stolenCard.toString());
                    validCards.add(stolenCard);
                }
                currentPlayer.addCard(stolenCard); //La carta siempre se anadira a la baraja del jugador independientemente de los casos anteriores
            }

            //Comprobamos si el usuario ha perdido el turno (No tiene cartas validas)
            if (!pierdeTurno) {
                Card playedCard = iu.selectPlayableCard(validCards); //Pasamos la lista de cartas mediante un metodo ui para que el usuario mediante inputs selecciona una carta
                iu.displayMessage("El jugador juega " + playedCard.toString());
                currentPlayer.removeCard(playedCard); //Eliminamos carta de la mano del jugador
                table.placeCard(playedCard); //Anadimos la carta a la barja boca arriba de table
            }

            //SComprobamos si la mano del jugador esta vacia (Ha ganado)
            if (currentPlayer.isHandEmpty()) {
                iu.displayMessageSeparator("El jugador " + currentPlayer.getName() + " es el Ganador.");
                partidaFinalizada = true;
            } else { //Si el jugador aun no ha ganado comprobamos que haya cartas en la mesa para el proximo turno
                if (this.deck.getRemainingCards() == 0) {
                    deck.setNewDeck(table.deleteAllExceptLastCard());//Si no hay cartas en la mesa las extraemos de las boca arriba en table excepto la ultima
                }

                //Mostramos el estado de la mesa tras cada jugada
                this.tableState();
            }

            //Metodo con modulador para hacer un ciclo en contra de las agujas del reloj
            turno = (turno - 1 + players.length) % players.length;
        }
    }

    private void tableState() {
        iu.displayMessageSeparator("Carta en la mesa: " + table.getTopCard().toString());
        // Cartas que quedan en la baraja
        iu.displayMessage("Cartas restantes: " + deck.getRemainingCards());
        // Mostrar la mano de cada jugador
        for (Player p : players) {
            iu.displayMessage(p.toString());
        }
    }
}
