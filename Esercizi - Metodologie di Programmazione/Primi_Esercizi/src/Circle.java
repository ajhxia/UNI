/*Progettare una classe Cerchio i cui oggetti rappresentano un
cerchio. La classe `e dotata dei metodi getCirconferenza e getArea (si usi la
costante Math.PI). La classe espone anche un metodo main che crea due cerchi
(di raggio 1 e di raggio 5) e ne stampa la circonferenza (per il primo) e l’area
(per il secondo). */

public class Circle {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getCircumference() {
        return 2 * Math.PI * this.radius;
    }
    public double getArea() {
        return Math.PI * radius * this.radius;
    }

    public static void main(String[] args) {

        Circle circle1 = new Circle(1);
        Circle circle2 = new Circle(5);

        System.out.println("Circumference of circle 1: " + circle1.getCircumference());

        System.out.println("Area of circle 2: " + circle2.getArea());
    }
}
