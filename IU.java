package gal.uvigo.esei.aed1.chupatedos.iu;

import gal.uvigo.esei.aed1.chupatedos.core.Card;
import gal.uvigo.esei.aed1.chupatedos.core.DeckOfCards;
import gal.uvigo.esei.aed1.chupatedos.core.Player;
import gal.uvigo.esei.aed1.chupatedos.core.Suit;
import gal.uvigo.esei.aed1.chupatedos.core.Table;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class IU {

    private final Scanner keyboard;

    public IU() {
        keyboard = new Scanner(System.in);
    }

    /**
     * Lee un num. de teclado
     *
     * @param msg El mensaje a visualizar.
     * @return El num., como entero
     */
    public int readNumber(String msg) {
        boolean repeat;
        int toret = 0;

        do {
            repeat = false;
            System.out.print(msg);
            try {
                toret = Integer.parseInt(keyboard.nextLine());
            } catch (NumberFormatException exc) {
                repeat = true;
            }
        } while (repeat);

        return toret;
    }

    /**
     * Lee un string de teclado
     *
     * @param msg mensaje a mostrar antes de la lectura
     * @return el string leido
     */
    public String readString(String msg) {
        String toret;
        System.out.print(msg);
        toret = keyboard.nextLine();
        return toret;
    }

    /**
     * muestra un mensaje por pantalla
     *
     * @param msg el mensaje a mostrar
     */
    public void displayMessage(String msg) {
        System.out.println(msg);
    }

    /**
     * Pregunta al usuario el numero de jugadores & posteriormente pide el
     * nombre de cada uno de ellos
     *
     * @return devuelve un array primitivo de elmentos Player
     */
    public Player[] getPlayers() {
        // Declaramos e inicializamos la variable para el número de jugadores
        int numOfPlayers = 0;

        // Bucle do-while para solicitar el número de jugadores hasta que sea válido
        // (entre 2 y 5)
        do {
            numOfPlayers = this.readNumber("Cuantos jugadores van a jugar? (Entre 2 y 5): ");
        } while (numOfPlayers < 2 || numOfPlayers > 5);

        Player[] players = new Player[numOfPlayers];

        // Creamos los jugadores y los agregamos a la lista
        for (int i = 0; i < numOfPlayers; i++) {
            players[i] = new Player(this.readString("Nombre del jugador " + (i + 1) + ": "));
        }

        return players;
    }
    // seleccionar cartas jugables
    public int selectPlayableCard(List<Card> playableCards) {
        displayMessage("\nCartas jugables:");
        for (int i = 0; i < playableCards.size(); i++) {
            displayMessage(i + ": " + playableCards.get(i).toString());
        }

        int selection;
        do {
            selection = readNumber("Selecciona una carta (0-" + (playableCards.size() - 1) + "): ");
            if (selection < 0 || selection >= playableCards.size()) {
                displayMessage("Selección inválida. Elige un número entre 0 y " + (playableCards.size() - 1));
            }
        } while (selection < 0 || selection >= playableCards.size());

        return selection;
    }
    // Muestra la mano del jugador 
    public void showPlayerHand(List<Card> hand) {
        displayMessage("\nTu mano actual:");
        for (int i = 0; i < hand.size(); i++) {
            displayMessage(i + ": " + hand.get(i).toString());
        }
    }
    // Muestra el estado del juego
    public void showGameStatus(Card topCard, int remainingCards) {
        displayMessage("\n--- Estado del juego ---");
        displayMessage("Carta en la mesa: " + topCard.toString());
        displayMessage("Cartas restantes en el mazo: " + remainingCards);
        displayMessage("------------------------");
    }
}
