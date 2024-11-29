import java.util.ArrayList;
import java.util.List;

//uso dei generics: definizione della classe GestoreLibro con un tipo generico T che estende la classe Libro
public class GestoreLibro<T extends Libro> {

    //attributo privato della classe GestoreLibro
    private List<T> libri; //lista di oggetti di tipo T, dove T è un sottotipo di Libro

    //costruttore della classe GestoreLibro
    public GestoreLibro() {
        this.libri = new ArrayList<>(); //inizializza la lista di libri come una nuova ArrayList vuota
    }

    //metodo per aggiungere un libro alla lista
    public void aggiungiLibro(T libro) {
        libri.add(libro);
    }

    //metodo per rimuovere un libro dalla lista
    public void rimuoviLibro(T libro) {
        libri.remove(libro);
    }

    //getter per ottenere la lista di libri
    public List<T> getLibri() {
        return libri;
    }

    //metodo per aggiungere un libro alla lista di libri in prestito di uno studente
    public void assegnaLibro(Studente studente, T libro) {
        studente.aggiungiLibroInPrestito(libro);
    }

    //metodo per generare un report dei punteggi degli studenti
    public void generaReportPunteggi(List<Studente> studenti) {
        for (Studente studente : studenti) {
            //stampa il cognome dello studente e il suo punteggio totale finale
            System.out.println(studente.getCognome() + ": " + studente.getPunteggioTotaleFinale());
        }
    }
}
