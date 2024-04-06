import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine(10);
        vendingMachine.carica();
        vendingMachine.inserisciImporto(4.50);
        System.out.println(vendingMachine.toString());
        vendingMachine.getProdotto("003");
        System.out.println(vendingMachine.toString());
    }
}
