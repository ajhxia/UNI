/* Esercizio L.2.1 Progettare una classe Persona i cui oggetti rappresentano una
persona e ne memorizzano il nome e il cognome. La classe espone un metodo
main che crea un’istanza della Persona. La classe espone anche un metodo
stampa che visualizza nome e cognome della persona.*/

import java.util.Scanner;

public class Persona {
	private String name;
    private String surname;

	//costruttore
	public Persona(String nameIn, String surnameIn){
		this.name = nameIn;
		this.surname = surnameIn;
	}

	public void printValue(){
		System.out.println("Name: " + name);
		System.out.println("Surname: " + surname);
	}

	public static void main(String[] args) {
		 Scanner scanner = new Scanner(System.in);

        System.out.print("Inserisci il nome: ");
        String nameInInput = scanner.nextLine();

        System.out.print("Inserisci il cognome: ");
        String surnameInInput = scanner.nextLine();

        // Creazione di un'istanza della classe Persona utilizzando l'input dell'utente
        Persona persona = new Persona(nameInInput, surnameInInput);

        // Stampare nome e cognome della persona
        persona.printValue();

        // Chiudi lo scanner
        scanner.close();

	}

}
