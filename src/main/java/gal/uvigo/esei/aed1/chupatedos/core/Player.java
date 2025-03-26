package gal.uvigo.esei.aed1.chupatedos.core;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private List<Card> hand;
    // Lista de cartas (mano/hand)

    // Método constructor
    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    // Getter para obtener el nombre del jugador al mostrar el estado de la mesa
    public String getName() {
        return name;
    }

    // Setter por si se quiere modificar el nombre del jugador
    public void setName(String name) {
        this.name = name;
    }

    // Método para añadir carta a la mano del jugador
    public void addCard(Card card) {

        hand.add(card);
    }

    // Retorna la lista de cartas que tiene el jugador
    public List<Card> getHand() {
        return hand;
    }

    // Mostrar las cartas del jugador
    public void printHand() {
        System.out.println("Cartas de " + name + ":");
        for (Card card : hand) {
            System.out.println(card.toString() + " ");
        }

    }

}
