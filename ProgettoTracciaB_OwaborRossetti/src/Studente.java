import java.util.ArrayList; /*importa la classe ArrayList per creare liste di dimensioni variabili,
dove è facile aggiungere e rimuovere elementi*/
import java.util.List; /*importa l'interfaccia List per definire una sequenza ordinata di elementi
che può contenere duplicati*/

public class Studente {
    //attributi privati della classe Studente
    private String cognome;
    private String classe;                //classe frequentata dallo studente
    private int punteggioTotale;          //punteggio totale accumulato dallo studente non contando i bonus
    private int bonusAssegnato;
    private int punteggioTotaleFinale;    //punteggio totale contando i bonus
    private List<Libro> libriInPrestito;  /*lista di tipo Libro, contiene oggetti di tipo Libro che rappresentano
                                            i libri in prestito*/

    //costruttore della classe Studente
    public Studente(String cognome, String classe) {
        //inizializza gli attributi della classe Studente
        this.cognome = cognome;
        this.classe = classe;
        this.punteggioTotale = 0;
        this.bonusAssegnato = 0;
        this.punteggioTotaleFinale = 0;
        this.libriInPrestito = new ArrayList<>(); //inizializza la lista di libri in prestito come una nuova ArrayList vuota
    }

    //metodi getter
    public String getCognome() {
        return cognome;
    }

    public String getClasse() {
        return classe;
    }

    //getter per ottenere il punteggio totale dello studente
    public int getPunteggioTotale() {
        return punteggioTotale;
    }

    //metodo per incrementare il punteggio ogni volta che lo studente prenota un libro//
    public void aggiungiPunti(int punti) {
        this.punteggioTotale += punti;
    }

    //getter per ottenere l'eventuale bonus assegnato allo studente
    public int getBonusAssegnato() {
        return bonusAssegnato;
    }

    //metodo per assegnare il bonus allo studente//
    public void aggiungiBonus() {
        this.bonusAssegnato = 20;
    }

    //getter per ottenere il punteggio finale
    public int getPunteggioTotaleFinale() {
        return punteggioTotale + bonusAssegnato;
    }

    //metodo per aggiungere un libro alla lista dei libri in prestito
    public void aggiungiLibroInPrestito(Libro libro) {
        libriInPrestito.add(libro);
    }

    //getter per ottenere la lista dei libri in prestito
    public List<Libro> getLibriInPrestito() {
        return libriInPrestito;
    }
}
