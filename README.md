**Card.java**

-Es un enum que representa cada una de las 40 cartas de la baraja española.

-Cada constante del enum debe guardar la información necesaria: el número (1,2,3,4,5,6,7,10,11,12) y el palo (Suit).

-Método toString() para mostrar la carta con un formato legible y personalizado.


**Suit.java**

-Es un enum para los palos: OROS, COPAS, ESPADAS, BASTOS.


**DeckOfCards.java**

-Una lista (o array) de Card con las 40 cartas.

-Métodos para barajar

-Métodos para repartir cartas 
  - dealCard() -> devuelve la siguiente carta de la baraja.

-Método para reiniciar la baraja, si hiciera falta (volver a tener las 40 cartas y barajar) (revisar)


**Player.java**

-Atributos para el nombre del jugador/a.

-Una mano (lista de Card que representan las cartas que tiene en su poder).

-Métodos para añadir cartas a la mano (addCard(Card c)), mostrar la mano, etc. (revisar)


**Game.java**

-La lógica principal del juego: cuántos jugadores hay, cómo se reparten las cartas, a quién le toca jugar, etc.

-Suele tener referencias a la baraja (DeckOfCards) y a la lista de jugadores (Player).

-Métodos para iniciar el juego, controlar turnos, aplicar reglas, etc.


**Table.java**

(Revisar)
