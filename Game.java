package gal.uvigo.esei.aed1.chupatedos.core;
import java.util.List;
import gal.uvigo.esei.aed1.chupatedos.iu.IU;

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
        dealInitialCards();    // reparto
        playGame();            // Bucle principal 
    }

    // Reparte 7 cartas a cada jugador 
    private void dealInitialCards() {
        for (int i = 0; i < 7; i++) {
            for (Player player : players) {
                player.addCard(deck.deal());
            }
        }
        table.placeCard(deck.deal());        // Colocar primera carta en la mesa
        iu.displayMessage("Carta en mesa: " + table.getTopCard());
        iu.displayMessage("Cartas en mazo: " + deck.getRemainingCards());
        showAllHands();  // Muestra manos iniciales
        iu.displayMessage("\nCOMIENZA EL JUEGO");
    }

    // Muestra las cartas de todos los jugadores
    private void showAllHands() {
        for (Player player : players) {
            iu.displayMessage(player.getName() + ": " + formatHand(player.getHand()));
        }
    }

    //BUCLE PRINCIPAL 
    private void playGame() {
        int currentPlayer = 0;
        while (true) {
            Player player = players[currentPlayer];
            playTurn(player); // Gestiona el turno
            if (player.getHand().isEmpty()) { // Verifica si el jugador ganó
                iu.displayMessage("\n¡" + player.getName() + " ha ganado!");
                break;
            }
            currentPlayer = (currentPlayer + 1) % players.length; // Siguiente jugador
        }
    }

    private void playTurn(Player player) {
        iu.displayMessage("\nTurno de " + player.getName());
        iu.displayMessage("Mesa: " + table.getTopCard());
        iu.displayMessage("Tu mano: " + formatHand(player.getHand()));

        List<Card> playable = player.getPlayableCards(table.getTopCard());

        if (!playable.isEmpty()) {
            playCard(player, playable); // Jugar carta si es posible
        } else {
            drawCard(player); // Robar carta si no hay jugables
        }
    }

    private void playCard(Player player, List<Card> playable) {
        iu.displayMessage("Jugables: " + formatPlayable(playable));
        int choice = getValidChoice(playable.size()); // Obtiene selección válida

        Card selected = playable.get(choice);
        player.playCard(player.getHand().indexOf(selected)); // Elimina carta de mano
        table.placeCard(selected); // Coloca carta en mesa
        iu.displayMessage("Jugaste: " + selected);
    }

// Valida la selección de carta del jugador
    private int getValidChoice(int max) {
        int choice = iu.readNumber("Elige carta (número): ");
        while (choice < 0 || choice >= max) {
            choice = iu.readNumber("Inválido. Elige 0-" + (max - 1) + ": ");
        }
        return choice;
    }

    // Maneja el robo de carta 
    private void drawCard(Player player) {
        iu.displayMessage("No hay cartas jugables. Roba");
        Card newCard = deck.deal();
        if (newCard != null) {
            player.addCard(newCard);
            iu.displayMessage("Robaste: " + newCard);

            if (newCard.getNumber() == table.getTopCard().getNumber()
                    || newCard.getSuit() == table.getTopCard().getSuit()) {
                player.playCard(player.getHand().size() - 1);
                table.placeCard(newCard);
                iu.displayMessage("Jugada automática: " + newCard);
            }
        } else {
            iu.displayMessage("No quedan cartas en el mazo");
            recycleCards();
        }
    }

    // Recicla cartas si el mazo está vacío    
    private void recycleCards() {
        Card lastCard = table.getTopCard(); // Guarda la última carta
        // Devuelve las demás cartas al mazo
        while (table.getFaceupCounter() > 1) {
            deck.addCard(table.removeCard());
        }
        deck.shuffle(); // Baraja las cartas recicladas
        iu.displayMessage("Nuevo mazo: " + deck.getRemainingCards());
    }
    
    //StringBuilder
    private String formatHand(List<Card> hand) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hand.size(); i++) {
            if (i > 0) sb.append(" ");
            sb.append("(").append(i).append(")").append(hand.get(i));
        }
        return sb.toString();
    }

    private String formatPlayable(List<Card> playable) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < playable.size(); i++) {
            if (i > 0) sb.append(" ");
            sb.append(i).append(":").append(playable.get(i));
        }
        return sb.toString();
    }
}