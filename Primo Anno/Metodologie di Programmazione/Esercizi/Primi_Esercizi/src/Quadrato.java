/*Esercizio L.2.2 Progettare una classe Quadrato, i cui oggetti sono costruiti con
il lato dello stesso. La classe `e dotata di un metodo getPerimetro che restituisce
il perimetro. E di un metodo main che crea un quadrato di lato 4 e ne stampa
a video il valore del perimetro. */

public class Quadrato {
    private double side;

    public Quadrato(double side) {
        this.side = side;
    }

    public double getPerimeter() {
        return 4 * side;
    }
    public static void main(String[] args) {
        Quadrato square = new Quadrato(4);
        System.out.println("Perimetro del Quadrato: " + square.getPerimeter());
    }
}