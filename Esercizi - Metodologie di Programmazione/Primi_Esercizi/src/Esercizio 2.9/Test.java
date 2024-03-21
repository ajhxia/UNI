/*Esercizio L.2.9 
Progettare una classe Punto per la rappresentazione di un punto nello spazio tridimensionale.

E una classe Segmento per rappresentare un segmento nello spazio tridimensionale.

Scrivere una classe di test che crei:
- due oggetti della classe Punto con coordinate (1, 3, 8) e (4, 4, 7)
- un oggetto della classe Segmento che rappresenti il segmento che unisce i due punti di cui sopra.
Raffigurare l’evoluzione dello stato della memoria, distinguendo tra stack, heap e metaspace. */

public class Test {
    public static void main(String[] args) {
        // Creazione dei due oggetti della classe Punto
        Point punto1 = new Point(1, 3, 8);
        Point punto2 = new Point(4, 4, 7);

        // Creazione dell'oggetto della classe Segmento che unisce i due punti sopra
        Segment segmento = new Segment(punto1, punto2);

        // Stampa delle coordinate dei punti e dei punti che compongono il segmento
        System.out.println("Coordinate del punto 1: (" + punto1.getX() + ", " + punto1.getY() + ", " + punto1.getZ() + ")");
        System.out.println("Coordinate del punto 2: (" + punto2.getX() + ", " + punto2.getY() + ", " + punto2.getZ() + ")");
        System.out.println("Il segmento unisce il punto 1 al punto 2.");
    }
}
