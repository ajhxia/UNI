package Card;
/*Progettare una classe Carta che rappresenti una singola carta
da gioco (con seme e valore).
La classe deve restituire su richiesta la propria rappresentazione sotto forma di
stringa.
Progettare quindi una classe MazzoDiCarte che rappresenti un intero mazzo da
52 carte.
La classe deve implementare i seguenti metodi:
- mescola il mazzo di carte
- distribuisci la prossima carta
Infine si progetti una classe di collaudo che crea un mazzo, mescoli le carte e ne
distribuisca carte fino ad esaurimento del mazzo. 
*/

public class Card {
    private String seme;
    private String value;

    public Card(String semeIn, String valueIn){
        this.seme = semeIn;
        this.value = valueIn;
    }

    public String getCard(){
        return this.value + " di " + this.seme; 
    }
}
