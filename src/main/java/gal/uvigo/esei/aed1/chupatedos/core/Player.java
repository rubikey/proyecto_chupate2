package gal.uvigo.esei.aed1.chupatedos.core;




public class Player {

    private String name;
    private List<Card> hand; // Lista de cartas

    // Método constructor
    
    public Player(String name) {
        this.name = name;
        this.hand = new LinkedList<>();
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
        
        hand.addLast(card);
    }
    
    /*public void playCard(Card card) {
        hand.removeValue(card);
    }*/
    
    
    // Método para obtener las cartas que le han tocado al jugador cuando...
    // se muestra el estado de la mesa
    public List<Card> getHand() {
        return hand;
    
    }
    
    
     
    
    
    // crear toString del nombre del jugador y la mano que le ha tocado*
    
}
