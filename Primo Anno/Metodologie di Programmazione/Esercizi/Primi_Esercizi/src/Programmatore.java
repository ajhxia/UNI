/*Esercizio L.2.6 Progettare una classe Programmatore i cui oggetti rappre-
sentano persone che svolgono il lavoro di sviluppo presso un’azienda 
La classe implementa i seguenti metodi:
- un costruttore con il nome e cognome della persona.
- un metodo setAzienda che imposta il nome dell’azienda per cui la persona lavora.
- un metodo addLinguaggio che aggiunge un linguaggio di programmazione a quelli inizialmente usati dal programmatore.
- i metodi getNome, getCognome e getAzienda che restituiscono i valori corrispondenti.
- un metodo getLinguaggi che restituisce la stringa dei linguaggi (separati da spazio) noti al programmatore. */


/**
 * Programmatore
 */
public class Programmatore {
    private String name;
    private String surname;
    private String nomeAzienda;
    private String lenguage;
    
    Programmatore(String nameIn, String surnameIn){
        this.name = nameIn;
        this.surname = surnameIn;
    }
    
    public void setAzienda(String nomeAziendaIn){
        this.nomeAzienda = nomeAziendaIn;
    }

    public String getNome(){
        return this.name;
    }

    public String getCognome(){
        return this.surname;
    }

    public String getAzienda(){
        return nomeAzienda;
    }

    public void addLinguaggio(String lenguageIn){
        this.lenguage += lenguageIn + " ";
    }

    public String getLinguaggio(){
       return this.lenguage;
    }

    public static void main(String[] args) {
        Programmatore p1 = new Programmatore("Bjarne","Stroustrup");
        Programmatore p2 = new Programmatore("Brian","Kernighan");
        Programmatore p3 = new Programmatore("James","Gosling");

        p1.addLinguaggio("C");
        p1.addLinguaggio("C++");
        p1.setAzienda("Morgan Stanley");
        p2.addLinguaggio("C");
        p2.addLinguaggio("AWK");
        p3.addLinguaggio("Java");
        p3.addLinguaggio("Oracle");
        // Stampa Morgan Stanley
        System.out.println(p1.getAzienda());
        //Stampa C AWK
        System.out.println(p2.getLinguaggio());
    }
    
}