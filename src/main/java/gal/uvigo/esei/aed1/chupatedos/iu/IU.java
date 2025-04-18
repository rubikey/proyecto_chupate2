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

    public void showHand(List<Card> hand, List<Card> playableCards) {
        System.out.println("Tu mano: ");
        for (int i = 0; i < hand.size(); i++) {
            System.out.println(i + ": " + hand.get(i));
        }

        System.out.println("Cartas jugables: ");
        for (int i = 0; i < playableCards.size(); i++) {
            System.out.println(i + ": " + playableCards.get(i));
        }
    }

    public int askCardToPlay(List<Card> jugables) {
        return readNumber("Selecciona la posición de la carta que quieras jugar: ");
    }

}
