import java.io.BufferedReader; //legge il testo da un input stream
import java.io.FileReader; //stream che legge i caratteri da un file
import java.io.IOException; /*classe di eccezioni che rappresenta un errore di input/output che può
verificarsi durante l'accesso a un file, ad esempio se il file non esiste o non è accessibile*/
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    //percorso del file di testo contenente i dati da leggere
    private static final String FILE_PATH = "traccia_B.txt";
    private final List<Cultura> libriCultura;          //lista dei libri di categoria Cultura
    private final List<Sport> libriSport;              //lista dei libri di categoria Sport
    private final List<Avventura> libriAvventura;      //lista dei libri di categoria Avventura
    private final List<Studente> studenti;             //lista degli studenti
    private final List<Prenotazione> prenotazioni;     //lista delle prenotazioni effettuate

    //costruttore della classe Biblioteca
    public Biblioteca() {
        //inizializza le liste
        libriCultura = new ArrayList<>();
        libriSport = new ArrayList<>();
        libriAvventura = new ArrayList<>();
        studenti = new ArrayList<>();
        prenotazioni = new ArrayList<>();
        caricaDati(FILE_PATH); //metodo per caricare i dati dal file specificato
    }

    //metodo privato per caricare i dati da un file specificato
    private void caricaDati(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int section = 1; //variabile per tenere traccia della sezione corrente

            while ((line = reader.readLine()) != null) {
                if (line.equals("###")) {
                    section++; //passa alla sezione successiva
                    continue;
                }

                switch (section) {
                    case 1:
                        //sezione Cultura: carica e aggiunge un nuovo libro di cultura alla lista
                        String[] culturaData = line.split(",");
                        libriCultura.add(new Cultura(culturaData[0], culturaData[1], Integer.parseInt(culturaData[2]), culturaData[3]));
                        break;
                    case 2:
                        //sezione Sport: carica e aggiunge un nuovo libro di sport alla lista
                        String[] sportData = line.split(",");
                        libriSport.add(new Sport(sportData[0], sportData[1], Integer.parseInt(sportData[2]), sportData[3]));
                        break;
                    case 3:
                        //sezione Avventura: carica e aggiunge un nuovo libro di avventura alla lista
                        String[] avventuraData = line.split(",");
                        libriAvventura.add(new Avventura(avventuraData[0], avventuraData[1], Integer.parseInt(avventuraData[2]), avventuraData[3]));
                        break;
                    case 4:
                        //sezione Studenti: carica e aggiunge un nuovo studente alla lista
                        String[] studenteData = line.split(",");
                        studenti.add(new Studente(studenteData[0], studenteData[1]));
                        break;
                    case 5:
                        //sezione Prenotazioni: carica e aggiunge una nuova prenotazione alla lista
                        String[] prenotazioneData = line.split(",");
                        Libro libroPrenotato = cercaLibro(prenotazioneData[0]);
                        Studente studentePrenotato = cercaStudente(prenotazioneData[1]);
                        if (libroPrenotato != null && studentePrenotato != null) {
                            studentePrenotato.aggiungiLibroInPrestito(libroPrenotato);
                            LocalDate dataConsegna = LocalDate.parse(prenotazioneData[2]);
                            LocalDate dataScadenza = LocalDate.parse(prenotazioneData[3]);
                            prenotazioni.add(new Prenotazione(libroPrenotato, studentePrenotato, dataConsegna, dataScadenza));
                        }
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); //stampa l'eccezione se si verifica un errore di I/O
        }
    }

    //metodo privato per cercare un libro per titolo in tutte le categorie
    private Libro cercaLibro(String titolo) {
        for (Cultura libro : libriCultura) {
            if (libro.getTitolo().equals(titolo)) {
                return libro;
            }
        }
        for (Sport libro : libriSport) {
            if (libro.getTitolo().equals(titolo)) {
                return libro;
            }
        }
        for (Avventura libro : libriAvventura) {
            if (libro.getTitolo().equals(titolo)) {
                return libro;
            }
        }
        return null; //restituisce null se il libro non è trovato
    }

    //metodo privato per cercare uno studente per cognome nella lista degli studenti
    private Studente cercaStudente(String cognome) {
        for (Studente studente : studenti) {
            if (studente.getCognome().equals(cognome)) {
                return studente;
            }
        }
        return null; //restituisce null se lo studente non è trovato
    }

    //metodo pubblico per stampare un riassunto delle categorie di libri e degli studenti per categoria
    public void stampaRiassuntoCategorie() {
        System.out.println("TASK 1: Catalogazione dei libri e Analisi delle preferenze\n");
        System.out.println("Categoria: Cultura, Numero Libri: " + libriCultura.size() + ", Numero Studenti: " + contaStudentiPerCategoria("Cultura"));
        System.out.println("Categoria: Sport, Numero Libri: " + libriSport.size() + ", Numero Studenti: " + contaStudentiPerCategoria("Sport"));
        System.out.println("Categoria: Avventura, Numero Libri: " + libriAvventura.size() + ", Numero Studenti: " + contaStudentiPerCategoria("Avventura") + "\n");
    }

    //metodo privato per contare gli studenti che hanno libri in prestito di una data categoria
    private int contaStudentiPerCategoria(String categoria) {
        int count = 0;
        for (Studente studente : studenti) {
            boolean hasBookInCategory = false;
            for (Libro libro : studente.getLibriInPrestito()) {
                if (libro.getCategoria().equals(categoria)) {
                    hasBookInCategory = true;
                    break;
                }
            }
            if (hasBookInCategory) {
                count++;
            }
        }
        return count; //restituisce il conteggio degli studenti
    }

    //metodo pubblico per calcolare i punteggi degli studenti in base alle prenotazioni
    public void calcolaPunteggi() {
        for (Prenotazione prenotazione : prenotazioni) {
            Studente studente = prenotazione.getStudente();
            int punteggio = prenotazione.getLibro().getPunteggio();
            if (prenotazione.isInRitardo()) {
                punteggio -= 5; //sottrae 5 punti se la prenotazione è in ritardo
            }
            studente.aggiungiPunti(punteggio);
        }

        System.out.println("TASK 2: Calcolo punteggi per categoria\n");
        for (Studente studente : studenti) {
            System.out.println(studente.getCognome() + ": " + studente.getPunteggioTotale());
        }
        System.out.println();
    }

    //metodo pubblico per assegnare bonus agli studenti con più di una prenotazione puntuale
    public void assegnaBonus() {
        for (Studente studente : studenti) {
            int libriPuntuali = 0;
            for (Prenotazione prenotazione : prenotazioni) {
                if (prenotazione.getStudente().equals(studente) && !prenotazione.isInRitardo()) {
                    libriPuntuali++;
                }
            }
            if (libriPuntuali > 1) {
                studente.aggiungiBonus(); //assegna un bonus di 20 punti
            }
        }

        System.out.println("TASK 3: Assegnazione bonus\n");
        for (Studente studente : studenti) {
            System.out.println(studente.getCognome() + ": " + studente.getBonusAssegnato());
        }
        System.out.println();
    }

    //metodo pubblico per stampare i punteggi finali degli studenti
    public void stampaPunteggiFinali() {
        System.out.println("I seguenti sono i punteggi totali finali per ciascun studente:\n");
        for (Studente studente : studenti) {
            System.out.println(studente.getCognome() + ": " + studente.getPunteggioTotaleFinale());
        }
    }
}
