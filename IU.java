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

    private final Scanner keyboard; // Scanner para leer entrada del usuario

    public IU() {
        keyboard = new Scanner(System.in); // Inicializa el scanner
    }

    // Lee un número entero del usuario con validación
    public int readNumber(String msg) {
        boolean repeat;
        int toret = 0;
        do {
            repeat = false;
            System.out.print(msg);
            try {
                toret = Integer.parseInt(keyboard.nextLine());
            } catch (NumberFormatException exc) {
                repeat = true; // Repetir si no es un número válido
            }
        } while (repeat);
        return toret;
    }

    // Lee un string del usuario
    public String readString(String msg) {
        System.out.print(msg);
        return keyboard.nextLine();
    }

    // Muestra un mensaje en pantalla
    public void displayMessage(String msg) {
        System.out.println(msg);
    }

    // Obtiene los jugadores y sus nombres
    public Player[] getPlayers() {
        int numOfPlayers = 0;
        // Pide número de jugadores (entre 2 y 5)
        do {
            numOfPlayers = this.readNumber("Cuantos jugadores van a jugar? (Entre 2 y 5): ");
        } while (numOfPlayers < 2 || numOfPlayers > 5);

        Player[] players = new Player[numOfPlayers];
        // Pide nombre de cada jugador
        for (int i = 0; i < numOfPlayers; i++) {
            players[i] = new Player(this.readString("Nombre del jugador " + (i + 1) + ": "));
        }
        return players;
    }

    public void showHand(List<Card> hand, List<Card> playableCards) {
        // Muestra mano completa 
        StringBuilder handDisplay = new StringBuilder("Tu mano: ");
        for (int i = 0; i < hand.size(); i++) {
            handDisplay.append("(").append(i).append(")").append(hand.get(i)).append(" ");
        }
        displayMessage(handDisplay.toString().trim());

        // Muestra cartas jugables 
        if (!playableCards.isEmpty()) {
            StringBuilder playableDisplay = new StringBuilder("Cartas jugables: ");
            for (int i = 0; i < playableCards.size(); i++) {
                playableDisplay.append(i).append(":").append(playableCards.get(i)).append(" ");
            }
            displayMessage(playableDisplay.toString().trim());
        } else {
            displayMessage("No tienes cartas jugables");
        }
    }

    // Pide al jugador que seleccione una carta para jugar
    public int askCardToPlay(List<Card> playableCards) {
        int choice;
        do {
            choice = readNumber("Elige carta (número): ");
            if (choice < 0 || choice >= playableCards.size()) {
                displayMessage("Selección inválida. Intentalo nuevamente.");
            }
        } while (choice < 0 || choice >= playableCards.size());
        return choice;
    }
}