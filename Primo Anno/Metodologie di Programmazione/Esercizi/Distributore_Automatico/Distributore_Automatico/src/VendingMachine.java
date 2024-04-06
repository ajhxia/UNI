import java.util.Scanner;
/*Progettare la classe DistributoreAutomatico che rappresenti un distributore au-
tomatico costruito con un intero N che determina il numero di prodotti nel distributore.
La classe prevede i seguenti metodi:
- un metodo carica() che inserisce N prodotti di tipo e ordine casuale.
- un metodo inserisciImporto() che permette di inserire un importo nella macchinetta.
- un metodo getProdotto() che, dato in ingresso un numero di prodotto, restituisca il prodotto associato a quel numero e decrementi il saldo disponibile nel distributore.
- Un metodo getSaldo() che restituisca il saldo attuale del distributore.
- un metodo getResto() che restituisca il resto dovuto e azzeri il saldo. */

public class VendingMachine {
    private final int N;
    private Product[] products;
    private double saldo;

    public VendingMachine(int N) {
        this.products = new Product[N];
        this.N = N;
    }

    public void carica() {
        Random r = new Random();
		
		for(int i=0;i<N;i++) {
			int rInt = r.nextInt(3);
			switch(rInt) {
			case 0: products[i] = new BottleOfWater();
					break;
			case 1: products[i] = new ChocolateBar();
					break;
			case 2: products[i] = new ChewingGum();
					break;
			default: products[i] = null;
					 System.out.println("SPAZIO VUOTO");
					 break;
			}
		}
    }

    public void inserisciImporto(double importo) {
        saldo += importo;
    }

    public boolean isDisponibile(String id){
        for(int i=0;i<N;i++) {
            if(products[i]!=null && products[i].getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    private Product rimuoviProdotto(String id) {
        if(!isDisponibile(id)) return null;
        for (int i = 0; i < N; i++) {
            if(products[i]!=null && products[i].getId().equals(id) && saldo >= products[i].getPrice()) {
                Product pApp = products[i];
                products[i] = null;
                return pApp;
            }
        }
        return null;
    }

    public Product getProdotto(String id) {
        Product p = rimuoviProdotto(id);
        if(p!=null) {
            saldo -= p.getPrice();
            return p;
        }
        return null;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getResto() {
        double resto = saldo;
        saldo = 0;
        return resto;
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < N; i++) {
            if(products[i]!=null) {
                s += products[i].toString() + "\n";
            }
        }
        return s;
    }
}