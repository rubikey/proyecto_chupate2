package gal.uvigo.esei.aed1.chupatedos.core;

import gal.uvigo.esei.aed1.chupatedos.iu.IU;
import java.util.ArrayList;
import java.util.List;


public class Game {

    // Atributos de la clase Game
    private final IU iu; // Referencia a la interfaz de usuario
    private final DeckOfCards deck;
    private final Table table;
    private final List<Player> players;
    // Lista de jugadores

    // Constructor para inicializar los atributos
    public Game(IU iu) {
        this.iu = iu; // Se asigna la interfaz de usuario pasada como parámetro
        this.deck = new DeckOfCards(); // Se crea una nueva baraja
        this.table = new Table(); // Se crea una nueva mesa
        this.players = new ArrayList<>(); // Se inicializa la lista de jugadores

    }

    /**
     * Método para iniciar el juego. Solicita el número de jugadores, crea los
     * jugadores, inicializa la baraja y reparte las cartas.
     */
    
    public void start() {
        // Declaramos e inicializamos la variable para el número de jugadores
        int numOfPlayers = 0;
        
         // Bucle do-while para solicitar el número de jugadores hasta que sea válido (entre 2 y 5)
        do { 
            numOfPlayers = iu.readNumber("Cuantos jugadores van a jugar? (Entre 2 y 5): ");
        } while (numOfPlayers < 2 || numOfPlayers > 5);

        
        // Creamos los jugadores y los agregamos a la lista
        for (int i = 1; i <= numOfPlayers; i++) {
            Player player = new Player(iu.readString("Nombre del jugador " + i + ": "));

            // Añadimos el jugador a la lista de jugadores
            players.add(player);
        }

        // Inicializamos la baraja
        deck.initializeDeck();
        // Mezclamos la baraja
        deck.shuffle();
        
        // Repartimos 7 cartas a cada jugador
        for (int i = 0; i < 7; i++) {
            for (Player p : players) {
                // Recorremos la lista de jugadores para darles una carta a cada uno
                p.addCard(deck.deal());

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
            p.printHand();

        }

    }

}
