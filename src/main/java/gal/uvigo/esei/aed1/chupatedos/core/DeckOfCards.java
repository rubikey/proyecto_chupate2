package gal.uvigo.esei.aed1.chupatedos.core;


import java.util.Collections;
import java.util.Random;
import java.util.Stack;


public class DeckOfCards {

    private Stack<Card> deck;

    // Método constructor
    public DeckOfCards() {
        deck = new Stack<>();
        initializeDeck();
    }

    public void initializeDeck() {

        // Bucle para añadir las 40 cartas
        for (Card card : Card.values()) {
            deck.push(card);
        }

        /* Cartas sin barajar
        while (!deck.isEmpty()) {
            System.out.println(deck.pop().toString());

        }*/
    }

    // Método para barajar
    public void shuffle() {
        // Volcar a un array, mezclar, volver a meter en deck

        // Array rawDeck que va a contener las cartas
        Card[] rawDeck = new Card[deck.size()];

        int i = 0;
        while (!deck.isEmpty()) {
            try {
                // Extrae (pop) la carta que está más arriba del deck y la guarda en el array
                rawDeck[i++] = deck.pop();
            } catch (EmptyException e) {
                // No puede pasar, porque deck está vacío
            }
        }

        // Mezclar el array 
        Random random = new Random();
        for (int j = 0; j < rawDeck.length; i++) {
            int randomIndex = random.nextInt(rawDeck.length);
            Card aux = rawDeck[j]; // se guardan los valores reales del array en aux
            rawDeck[j] = rawDeck[randomIndex]; // se almacenan posiciones aleatorias en el rawDeck
            rawDeck[randomIndex] = aux;
        }

        // Volver a apilar las cartas mezcladas
        for (int k = 0; k < rawDeck.length; k++) {
            deck.push(rawDeck[k]);
        }

    
    }


    // Coger la siguiente carta (top) de la baraja (deck) usando pop, para repartir
     public Card deal() {
        try {
            if (!deck.isEmpty()) {
                return deck.pop();
            }
        } catch (EmptyException e) {
            // Error si se intenta extraer carta de la pila vacía
        }
        return null;
    }

    
    // Cantidad de cartas que quedan en la baraja
    public int left() {
        return deck.size();
    }
    
    
    
    
    
}
