import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine(10);
        vendingMachine.carica();
        vendingMachine.inserisciImporto();

        

        Product product = vendingMachine.getProdotto();
        System.out.println("Prodotto scelto:" + product.getType());
        System.out.println(vendingMachine.getSaldo());
        System.out.println(vendingMachine.getResto());
    }
}
