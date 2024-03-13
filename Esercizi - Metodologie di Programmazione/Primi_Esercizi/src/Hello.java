/*Esercizio L.3.2 Scrivere un metodo che legge una stringa da console (ovvero
da input args) e la stampa in verticale un carattere per linea.
Ad esempio, dato in input “ciao”, viene stampato:
c
i
a
o */

import java.util.*;

public class Hello {
    //input ricordalo
    static Scanner inputScanner = new Scanner(System.in);
    public static void main(String[] args) {
        String temp = inputScanner.nextLine();
        char[] tempArray = temp.toCharArray();

        for(int i = 0; i<tempArray.length; i++){
            System.out.println(tempArray[i]);
        }
    }
}
