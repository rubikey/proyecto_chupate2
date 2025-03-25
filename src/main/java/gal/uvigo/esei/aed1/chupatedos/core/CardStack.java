/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gal.uvigo.esei.aed1.chupatedos.core;

/**
 *
 * @author ricardo
 */
public class CardStack {

    // Tamaño inicial de la pila (en este ejemplo, 40 para la baraja española).
    // Si necesitas un comportamiento dinámico, puedes implementar redimensionado.
    private static final int DEFAULT_CAPACITY = 40;

    // Array interno donde almacenamos las cartas.
    private Card[] data;

    // Índice que apunta al elemento en la cima. -1 indica que está vacía.
    private int top;

    /**
     * Constructor que crea una pila vacía con capacidad por defecto.
     */
    public CardStack() {
        data = new Card[DEFAULT_CAPACITY];
        top = -1;
    }

    /**
     * Devuelve el número de elementos en la pila.
     *
     * @return tamaño de la pila
     */
    public int size() {
        return top + 1;
    }

    /**
     * Indica si la pila está vacía.
     *
     * @return true si no hay elementos, false en caso contrario
     */
    public boolean isEmpty() {
        return (top == -1);
    }

    /**
     * Devuelve el elemento en la cima de la pila sin eliminarlo.
     *
     * @return la carta en la cima
     * @throws EmptyException si la pila está vacía
     */
    public Card top() throws Exception {
        if (isEmpty()) {
            throw new Exception("La pila está vacía (top).");
        }
        return data[top];
    }

    /**
     * Elimina la carta en la cima de la pila y la devuelve.
     *
     * @return la carta en la cima
     * @throws EmptyException si la pila está vacía
     */
    public Card pop() throws Exception {
        if (isEmpty()) {
            throw new Exception("La pila está vacía (pop).");
        }
        Card cardToReturn = data[top];
        data[top] = null;  // Opcional, para evitar referencias sobrantes
        top--;
        return cardToReturn;
    }

    /**
     * Inserta una nueva carta en la cima de la pila.
     *
     * @param value la carta a insertar
     * @throws NullPointerException si la carta es null
     */
    public void push(Card value) {
        if (value == null) {
            throw new NullPointerException("No se permite 'null' en la pila.");
        }
        // Si la pila está llena, podríamos lanzar excepción o redimensionar.
        if (top == data.length - 1) {
            // En este ejemplo, redimensionamos automáticamente.
            expandCapacity();
        }
        data[++top] = value;
    }

    /**
     * Aumenta la capacidad del array interno cuando está lleno.
     */
    private void expandCapacity() {
        Card[] newData = new Card[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i <= top; i++) {
            sb.append(data[i]);
            if (i < top) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
