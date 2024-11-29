//stesso principio della classe Cultura
public class Sport extends Libro {
    private String disciplina;

    //imposta il parametro categoria a "Sport"
    public Sport(String titolo, String autore, int punteggio, String disciplina) {
        super(titolo, autore,"Sport", punteggio);
        this.disciplina = disciplina;
    }

    @Override
    public String getDettagli() {
        return "Argomento: " + disciplina;
    }
}
