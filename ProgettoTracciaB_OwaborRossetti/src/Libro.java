//definizione della classe astratta Libro
public abstract class Libro {

    //attributi privati della classe Libro
    private String titolo;
    private String autore;
    private String categoria;
    private int punteggio;

    //costruttore della classe Libro che inizializza i suoi attributi
    public Libro(String titolo, String autore, String categoria, int punteggio) {
        this.titolo = titolo;
        this.autore = autore;
        this.categoria = categoria;
        this.punteggio = punteggio;
    }

    //metodi getter per ottenere gli attributi del libro
    public String getTitolo() {
        return titolo;
    }
    public String getAutore() {
        return autore;
    }
    public String getCategoria() {
        return categoria;
    }
    public int getPunteggio() {
        return punteggio;
    }

    /*metodo astratto per ottenere i dettagli del libro.
    Questo metodo deve essere implementato dalle sottoclassi concrete*/
    public abstract String getDettagli();
}
