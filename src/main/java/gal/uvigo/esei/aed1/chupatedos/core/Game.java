package gal.uvigo.esei.aed1.chupatedos.core;

import gal.uvigo.esei.aed1.chupatedos.iu.IU;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Game {

    // Atributos de la clase Game
    private final IU iu; // Referencia a la interfaz de usuario
    private final DeckOfCards deck;
    private final Table table;
    private Player[] players; // Lista de jugadores

    private boolean sentidoHorario = false; // atributo para especificar el sentido del juego (antihorario por defecto)

    // Constructor para inicializar los atributos
    public Game(IU iu) {
        this.iu = iu; // Se asigna la interfaz de usuario pasada como parámetro
        this.deck = new DeckOfCards(); // Se crea una nueva baraja
        this.table = new Table(); // Se crea una nueva mesa

    }

    // Método para avanzar el turno según el sentido, 
    private int avanzarTurno(int turnoActual) {
        if (sentidoHorario) {
            return (turnoActual + 1) % players.length;
        } else {
            return (turnoActual - 1 + players.length) % players.length;
        }
    }

    /**
     * Método para iniciar el juego. Solicita el número de jugadores, crea los
     * jugadores, inicializa la baraja y reparte las cartas.
     */
    public void start() {
        this.players = iu.getPlayers();  // Pedimos los jugadores
        deck.shuffle(); // Barajamos antes de repartir

        // Repartimos 7 cartas a cada jugador
        for (int i = 0; i < 7; i++) {
            for (Player player : players) {
                player.addCard(deck.deal());
            }
        }

        // Se coloca una carta en la mesa
        table.placeCard(deck.deal());
        Card cartaInicial = table.getTopCard();
        iu.displayMessageSeparator("Carta inicial en la mesa: " + cartaInicial);

        int turno = 0;

        // Comprobamos si la carta inicial es especial
        if (cartaInicial.getNumber() == 2) {
            iu.displayMessage("La carta inicial es un 2 por lo tanto, el primer jugador roba 2 cartas del mazo y pierde su turno");
            for (int i = 0; i < 2; i++) {
                players[0].addCard(deck.deal());
            }
            turno = avanzarTurno(0);
        }

        if (cartaInicial.getNumber() == 7) {
            iu.displayMessage("La carta inicial es un 7 por lo tanto, se cambia el sentido del juego y el primer jugador pierde su turno.");
            sentidoHorario = true; // cambia a sentido horario
            turno = avanzarTurno(0);
        }

        // Mostramos el estado de la mesa
        this.tableState();

        boolean partidaFinalizada = false;

        while (!partidaFinalizada) {
            Player currentPlayer = players[turno];
            iu.displayMessageSeparator("Turno de " + currentPlayer.getName()); // Mano del jugador activo
            iu.displayMessage(currentPlayer.toString());

            boolean pierdeTurno = false;

            // Extraemos todas las cartas válidas que tiene el usuario disponible para la carta boca arriba y se guardan en una lista
            List<Card> validCards = currentPlayer.getPlayableCards(table.getTopCard());

            // Si la lista de cartas validas esta vacia (El jugador NO tiene cartas para jugar)
            if (validCards.isEmpty()) { // El jugador deberá robar una carta 
                iu.displayMessage("El jugador no tiene ninguna carta valida por lo que debera robar una de la mesa.");
                Card stolenCard = deck.deal();

                if (!table.getTopCard().isCardPlayable(stolenCard)) { // Si la carta ROBADA NO es jugable: Pierde turno
                    iu.displayMessage("Como la carta robada " + stolenCard.toString() + " no es jugable, el jugador pierde turno.");
                    pierdeTurno = true;
                } else { // Si la carta ROBADA es jugable: NO pierde turno y la añadimos a Lista de cartas válidas
                    iu.displayMessage("Carta robada " + stolenCard.toString());
                    validCards.add(stolenCard);
                }
                currentPlayer.addCard(stolenCard); // La carta siempre se añadirá a la baraja del jugador independientemente de los casos anteriores
            }

            // Comprobamos si el usuario ha perdido el turno (No tiene cartas válidas)
            if (!pierdeTurno) {
                Card playedCard = iu.selectPlayableCard(validCards); // Pasamos la lista de cartas mediante un metodo iu para que el usuario mediante inputs selecciona una carta
                iu.displayMessage("El jugador juega " + playedCard.toString());
                currentPlayer.removeCard(playedCard); // Eliminamos carta de la mano del jugador
                table.placeCard(playedCard); // Añadimos la carta a la baraja boca arriba de table

                // --- Cartas Especiales --- //
                // Método para verificar si una carta jugada es especial
                if (playedCard.getNumber() == 2) {
                    // El siguiente jugador roba 2 y pierde su turno
                    int siguienteTurno = avanzarTurno(turno);
                    iu.displayMessage("El siguiente jugador " + players[siguienteTurno].getName() + " debe robar 2 cartas y pierde turno");
                    for (int i = 0; i < 2; i++) {
                        if (i != 0 && this.deck.getRemainingCards() == 0) { //Comprobamos de manera prematura antes de robar la segunda (i no sea 0) carta si no se han acabado en la baraja
                            this.recycleCards();
                        }
                        players[siguienteTurno].addCard(deck.deal());
                    }
                    turno = avanzarTurno(siguienteTurno); // Saltamos turno
                    continue; // Comtinua a la siguiente sentencia
                }

                if (playedCard.getNumber() == 7) {
                    sentidoHorario = !sentidoHorario; // Se cambia el sentido del juego
                    iu.displayMessage("¡Se ha cambiado el sentido del juego!");
                }
            }

            // Comprobamos si la mano del jugador esta vacía (Ha ganado)
            if (currentPlayer.isHandEmpty()) {
                iu.displayMessageSeparator("El jugador " + currentPlayer.getName() + " es el Ganador.");
                partidaFinalizada = true;
            } else { // Si el jugador aun no ha ganado comprobamos que haya cartas en el mazo para robar
                if (this.deck.getRemainingCards() == 0) {
                    this.recycleCards();
                }

                //Mostramos el estado de la mesa tras cada jugada
                this.tableState();
            }

            //Metodo con modulador para hacer un ciclo en contra de las agujas del reloj
            turno = avanzarTurno(turno);
        }
    }

    private void recycleCards() {
        Stack<Card> recycledCards = table.deleteAllExceptLastCard(); // Si no hay cartas se llama al método para quitar las que hay en la mesa y volver a barajarlas, dejando la que está en la cima (top)
        Collections.shuffle(recycledCards);
        deck.setNewDeck(recycledCards); // Carga la baraja actual con las cartas recicladas
        deck.shuffle(); // Barajamos
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
