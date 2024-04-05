public class Main {
    public static void main(String[] args) {
        //Example 
        Animal[] animals = new Animal[3];
        animals[0] = new Tiger();
        animals[1] = new Cat();
        animals[2] = new Mammal(4);
        for (Animal animal : animals) {
            System.out.println(animal.emitSound());
        }

        //Or
        System.out.println("Type: Tiger");
        System.out.println("Sound: " + animals[0].emitSound());
        System.out.println("Size: " + animals[0].getSize());
    }
}
