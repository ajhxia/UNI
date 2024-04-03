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
    private Product[] products;
    private double saldo;

    Scanner scanner = new Scanner(System.in);

    public VendingMachine(int N) {
        this.products = new Product[N];
        this.saldo = 0;
    }

    public void carica() {
        for (int i = 0; i < this.products.length; i++) {
            int random = (int) (Math.random() * 3);
            switch (random) {
                case 0:
                    this.products[i] = new Product(1.5, "BottigliaDAcqua");
                    break;
                case 1:
                    this.products[i] = new Product(2.5, "BarraDiCioccolato");
                    break;
                case 2:
                    this.products[i] = new Product(1.0, "GommeDaMasticare");
                    break;
            }
        }
    }

    public void inserisciImporto() {
        System.out.print("Inserisci l'importo: ");
        double importo = scanner.nextDouble();
        this.saldo += importo;
    }

    public Product getProdotto() {
        for(int i = 0; i < this.products.length; i++) {
            System.out.println(i + " " + this.products[i].getType());
        }

        System.out.print("Inserisci il numero del prodotto:");
        int productNumber = scanner.nextInt();
        Product product = this.products[productNumber];
        this.saldo -= product.getPrice();
        return product;
    }

    public double getSaldo() {
        return "Saldo: " + this.saldo;
    }

    public double getResto() {
        double resto = this.saldo;
        this.saldo = 0;
        return "Resto:" + resto;
    }
}