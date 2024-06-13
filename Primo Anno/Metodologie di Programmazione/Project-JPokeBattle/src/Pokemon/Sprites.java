package Pokemon;

/*
 * Questa classe rappresenta uno sprite (immagine) di un pokemon
 * Ogni sprite ha un front e un back
 */

public class Sprites {
    private String front;
    private String back;

    /**
     * Costruttore della classe Sprites
     * @param front
     * @param back
     */
    public Sprites(String front, String back) {
        this.front = front;
        this.back = back;
    }

    /**
     * Metodo per ottenere lo sprite frontale di un pokemon
      @return stringa contenente il path dello sprite frontale
     */
    public String getFront() {
        return front;
    }

    /**
     * Metodo per ottenere lo sprite posteriore di un pokemon
      @return stringa contenente il path dello sprite posteriore
     */
    public String getBack() {
        return back;
    }
    
}

