package gal.uvigo.esei.aed1.chupatedos.core;

import java.util.ArrayList;
import java.util.List;



public class Player {

    private String name;
    private List<Card> hand; // Lista de cartas (mano)

    // Método constructor
    
    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    // Getter para obtener el nombre del jugador al mostrar el estado de la mesa
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }
    
    
    
    
    // Método para añadir carta a la mano del jugador
    public void addCard (Card card){
        
        hand.add(card);
    }
    
    
    
    
    // Método para obtener las cartas que le han tocado al jugador cuando...
    // se muestra el estado de la mesa
    public List<Card> getHand() {
        return hand;
    
    }
    
    
    public void printHand(){
        System.out.println("Cartas de " + name + ":");
        for (Card card: hand){
            System.out.println(card.toString() + " ");
        }
    
    }
    
     
    
    
    
    
}
