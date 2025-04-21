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

    // Inicia el juego
    public void start() {
        dealInitialCards(); // Reparte cartas iniciales
        playGame(); // Comienza el bucle principal
    }

    // Reparte 7 cartas a cada jugador y coloca la primera en mesa
    private void dealInitialCards() {
        for (int i = 0; i < 7; i++) {
            for (Player player : players) {
                player.addCard(deck.deal());
            }
        }
        table.placeCard(deck.deal());
        iu.displayMessage("Carta en la mesa: " + table.getTopCard());
        iu.displayMessage("Cartas restantes: " + deck.getRemainingCards());
        // Muestra manos iniciales
        for (Player p : players) {
            iu.displayMessage(p.toString());
        }
        iu.displayMessage("\nCOMIENZA EL JUEGO");
    }

    // Bucle principal del juego
    private void playGame() {
        int turn = 0;
        while (true) {
            Player currentPlayer = players[turn];
            iu.displayMessage("\nTurno de " + currentPlayer.getName());
            iu.displayMessage("Mesa: " + table.getTopCard());

            //Verifica si el jugador ya no tiene cartas (ganó)
            if (currentPlayer.getHand().isEmpty()) {
                iu.displayMessage("\n¡" + currentPlayer.getName() + " ha ganado!");
                break;
            }

            //Obtener cartas jugables
            List<Card> playableCards = currentPlayer.getPlayableCard(table.getTopCard());
            iu.showHand(currentPlayer.getHand(), playableCards);

            //jugar o robar
            if (!playableCards.isEmpty()) {
                int choice = iu.askCardToPlay(playableCards);
                Card selectedCard = playableCards.get(choice);
                currentPlayer.playCard(selectedCard);
                table.placeCard(selectedCard);
                iu.displayMessage("Jugaste: " + selectedCard);
            } else {
                handleNoPlayableCards(currentPlayer);
            }

            //Verificar victoria
            if (currentPlayer.getHand().isEmpty()) {
                iu.displayMessage("\n¡" + currentPlayer.getName() + " ha ganado!");
                break;
            }

            //siguiente jugador
            turn = (turn + 1) % players.length;
        }
    }

    // Maneja cuando el jugador no tiene cartas jugables
    private void handleNoPlayableCards(Player player) {
        iu.displayMessage("No hay cartas jugables. Robando carta...");
        Card drawnCard = deck.deal();

        if (drawnCard != null) {
            player.addCard(drawnCard);
            iu.displayMessage("Robaste: " + drawnCard);

            // Si la carta robada es jugable, se juega automáticamente
            if (drawnCard.isPlayable(table.getTopCard())) {
                player.playCard(drawnCard);
                table.placeCard(drawnCard);
                iu.displayMessage("Jugaste automáticamente: " + drawnCard);
            }
        } else {
            // Reciclar cartas si el mazo está vacío
            iu.displayMessage("No quedan cartas en el mazo. Reciclando...");
            List<Card> recycledCards = table.recycleCards();
            if (recycledCards.isEmpty()) {
                iu.displayMessage("No hay cartas para reciclar. ¡Juego bloqueado!");
                System.exit(0);  // Terminar si no hay cartas
            }
            for (Card card : recycledCards) {
                deck.addCard(card);
            }
            deck.shuffle();
            iu.displayMessage("Cartas recicladas: " + deck.getRemainingCards());
        }
    }
}
