//stesso principio della classe Cultura
public class Avventura extends Libro {
    private String ambientazione;

    //imposta il parametro categoria a "Avventura"
    public Avventura(String titolo, String autore, int punteggio, String ambientazione) {
        super(titolo, autore, "Avventura", punteggio);
        this.ambientazione = ambientazione;
    }

    @Override
    public String getDettagli() {
        return "Ambientazione: " + ambientazione;
    }
}
