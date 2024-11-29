//definizione della classe Cultura che estende la classe astratta Libro
public class Cultura extends Libro {

    //attributo specifico della classe Cultura
    private String argomento;  //argomento trattato nel libro di cultura

    //Costruttore della classe Cultura. Si chiama il costruttore della superclasse Libro e si imposta il parametro categoria a "Cultura"//
    public Cultura(String titolo, String autore, int punteggio, String argomento) {

        //chiamata al costruttore della superclasse Libro
        super(titolo, autore, "Cultura", punteggio);
        this.argomento = argomento;  //inizializza l'attributo argomento
    }

    /*override del metodo astratto getDettagli() definito nella superclasse Libro.
    Restituisce una stringa con l'argomento del libro*/
    @Override
    public String getDettagli() {
        return "Argomento: " + argomento;
    }
}
