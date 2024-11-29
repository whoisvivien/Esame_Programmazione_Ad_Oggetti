import java.time.LocalDate; //importa la classe LocalDate per gestire le date

public class Prenotazione {
    //attributi privati della classe Prenotazione
    private Libro libro;            //libro oggetto della prenotazione
    private Studente studente;      //studente che ha effettuato la prenotazione
    private LocalDate dataConsegna; //data in cui il libro è stato consegnato
    private LocalDate dataScadenza; //data entro la quale il libro deve essere restituito

    //costruttore della classe Prenotazione
    public Prenotazione(Libro libro, Studente studente, LocalDate dataConsegna, LocalDate dataScadenza) {
        //inizializza gli attributi
        this.libro = libro;
        this.studente = studente;
        this.dataConsegna = dataConsegna;
        this.dataScadenza = dataScadenza;
    }

    //getter per ottenere il libro della prenotazione
    public Libro getLibro() {
        return libro;
    }

    //getter per ottenere lo studente che ha effettuato la prenotazione
    public Studente getStudente() {
        return studente;
    }

    //getter per ottenere la data di consegna del libro
    public LocalDate getDataConsegna() {
        return dataConsegna;
    }

    //getter per ottenere la data di scadenza della prenotazione
    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    //confronta la data di consegna con la data di scadenza utilizzando il metodo .isAfter
    public boolean isInRitardo() {
        return dataConsegna.isAfter(dataScadenza);
    }
}
