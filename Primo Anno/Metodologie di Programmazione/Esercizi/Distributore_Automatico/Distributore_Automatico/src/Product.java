/*Progettare una classe Prodotto con un prezzo e tre tipi diversi di prodotto:
BottigliaDAcqua, BarraDiCioccolato, GommeDaMasticare.

Progettare la classe DistributoreAutomatico che rappresenti un distributore au-
tomatico costruito con un intero N che determina il numero di prodotti nel distributore.
La classe prevede i seguenti metodi:
- un metodo carica() che inserisce N prodotti di tipo e ordine casuale.
- un metodo inserisciImporto() che permette di inserire un importo nella macchinetta.
- un metodo getProdotto() che, dato in ingresso un numero di prodotto, restituisca il prodotto associato a 
quel numero e decrementi il saldo disponibile nel distributore.
- Un metodo getSaldo() che restituisca il saldo attuale del distributore.
- un metodo getResto() che restituisca il resto dovuto e azzeri il saldo. */

public class Product {
    private double price;
    private String id; 

    public Product(double price, String id) { 
        this.price = price;
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public String getId() { 
        return id;
    }

    @Override
	public String toString() {
		return this.getClass().getSimpleName()+" "+getId();//DA FINIRE
	}
}
