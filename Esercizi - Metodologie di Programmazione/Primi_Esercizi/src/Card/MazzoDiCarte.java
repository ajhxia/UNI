package Card;
import java.util.Random;

public class MazzoDiCarte {
    private Card[] cards = new Card[52];
    private int puntatore = 0;

    MazzoDiCarte(){
        String[] seme = {"Cuori", "Quadri", "Picche", "Fiori"};
        String[] cardsTot = {"1","2","3","4","5","6","7","8","9","10","J","Q","K"};
        int count = 0;
        for(int i = 0; i<seme.length; i++){
            for(int j = 0; j<cardsTot.length; j++){
                cards[count] = new Card(seme[i], cardsTot[j]);
                count++;
            }
        }
    }

    public void deckShuffle(){
        Random rand = new Random();
        // shuffla le carte 
        for (int i = 0; i < this.cards.length; i++) {
            int randomIndexToSwap = rand.nextInt(this.cards.length);
            Card temp = this.cards[randomIndexToSwap];
            this.cards[randomIndexToSwap] = this.cards[i];
            this.cards[i] = temp;
        }

        for (int j = 0; j < this.cards.length; j++){
            System.out.println(this.cards[j].getCard());
        }
    }

    public String drawCard(){
        Card cardSelected = this.cards[this.puntatore];
        this.puntatore ++;
        System.out.println("Carta selezionata: "+cardSelected.getCard());
        return cardSelected.getCard();
    }

    public static void main(String[] args) {
        MazzoDiCarte simoDesk = new MazzoDiCarte();
        simoDesk.deckShuffle();
        simoDesk.drawCard();
    }
    
}
