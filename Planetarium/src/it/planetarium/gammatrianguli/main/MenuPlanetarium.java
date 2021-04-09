package it.planetarium.gammatrianguli.main;
import it.planetarium.gammatrianguli.classes.*;
import it.unibs.fp.mylib.InputDati;

import java.util.ArrayList;

public class MenuPlanetarium {
	
	//COSTANTI
    public static final String MSG_TITLE = "************* %s ************";
    public static final String MSG_CODICE_ID = "Inserire codice id %s: ";
    public static final String MSG_MASSA = "Inserire massa %s: ";
    public static final String MSG_POSIZIONE = "Inserire posizione %s: ";
    public static final String MSG_POSIZIONE_COORDINATA = "Inserire coordinata %s: ";
    public static final String MSG_RIFERIMENTO = "Inserire codice id %s di riferimento: ";
    public static final String MSG_CORPO_CELESTE = "Corpo celeste: %s (%s)";
    public static final String MSG_PERCORSO = "Percorso: %s";
    public static final String MSG_ERRORE_ESISTENTE = "%s con questo codice identificativo gia' esistente, riprovare!";
    public static final String MSG_ERRORE_NON_ESISTENTE = "Il codice non corrisponde a nessun %s del sistema, riprovare!";
    public static final String MSG_ERRORE_POSIZIONE = "La posizione (%.1f, %.1f) e' gia' occupata, riprovare!";
    public static final String MSG_ERRORE_RIFERIMENTO = "%s di riferimento non esiste, riprovare!";
    public static final String MSG_CONFERMA_INSERIMENTO = "%s con codice \"%s\" inserito con successo!";
    public static final String MSG_CONFERMA_RIMOZIONE = "Il %s con codice \"%s\" e' stato rimosso dal sistema!";
    public static final String MSG_STOP = "Premere invio per tornare al menu principale...";
    public static final String MSG_ARRIVEDERCI = "Grazie per aver visitato il nostro planetario, alla prossima!";
    public static final String STRUTTURA_STELLA = "|=> %s  (%s)  %s";
    public static final String STRUTTURA_PIANETA = "|====> %s  (%s)  %s";
    public static final String STRUTTURA_SATELLITE = "|--------> %s  (%s)  %s";
	
	public static void menuWelcome()
    {
        System.out.println("+------------------------------------------------+");
        System.out.println("|            BENVENUTO NEL PLANETARIO            |");
        System.out.println("+------------------------------------------------+");
    }

    public static Sistema creaSistema()
    {
        // HEADER METODO
        menuWelcome();
        System.out.println(String.format(MSG_TITLE, "CREAZIONE SISTEMA"));

        //INPUT DATI
        String nomeSistema = InputDati.leggiStringa("Inserire nome sistema: ");
        System.out.println(String.format(MSG_TITLE, "CREAZIONE STELLA"));
        String codiceStella = InputDati.leggiStringa(String.format(MSG_CODICE_ID, "stella"));
        double massaStella = InputDati.leggiDouble(String.format(MSG_MASSA, "stella"));
        Posizione posizioneStella = new Posizione(0.0, 0.0);
        Stella stella = new Stella(codiceStella, massaStella, posizioneStella);
        cls();
        return new Sistema(nomeSistema, stella);
    }

    public static void strutturaSistema(Sistema sistema)
    {
        //HEADER METODO
        System.out.println("+===================   SISTEMA   ===================+");
        System.out.println("Nome sistema: " + sistema.getNomeSistema());

        //STAMPA STRUTTURA
        System.out.println("Struttura sistema:");
        System.out.println(String.format(STRUTTURA_STELLA, sistema.getStella().getCodiceId(), "Stella", sistema.getStella().getPosizioneFormat()));
        for(Pianeta p : sistema.getStella().getPianetiOrbitanti()) {
            System.out.println(String.format(STRUTTURA_PIANETA, p.getCodiceId(), "Pianeta", p.getPosizioneFormat()));
            for (Satellite s : p.getSatellitiOrbitanti()) {
                System.out.println(String.format(STRUTTURA_SATELLITE, s.getCodiceId(), "Satellite", s.getPosizioneFormat()));
            }
        }

        System.out.println("+===================================================+");
    }

    public static void menuHeader(Sistema sistema)
    {
        strutturaSistema(sistema);
        System.out.println("Seleziona una delle seguenti opzioni:");
        System.out.println("1)  Aggiungi un pianeta");
        System.out.println("2)  Rimuovi un pianeta");
        System.out.println("3)  Aggiungi un satellite");
        System.out.println("4)  Rimuovi un satellite");
        System.out.println("5)  Visualizza satelliti di un pianeta");
        System.out.println("6)  Cerca corpo celeste");
        System.out.println("7)  Calcola centro di massa");
        System.out.println("0)  Esci dal programma");
    }

    public static void cls() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception E) {
            System.out.println(E);
        }
    }

    public static void exit()
    {
        System.out.println(MSG_ARRIVEDERCI);
    }

    public static void headerMetodo(Sistema sistema, String headerMessage) {
        cls();
        strutturaSistema(sistema);
        System.out.println(String.format(MSG_TITLE, headerMessage));
    }

    public static void footerMetodo() {
        InputDati.leggiStringa(MSG_STOP);
        cls();
    }

    public static void aggiungiPianeta(Sistema sistema)
    {

        headerMetodo(sistema, "AGGIUNGI PIANETA");

        //INPUT DATI
        String codicePianeta = InputDati.leggiStringa(String.format(MSG_CODICE_ID, "pianeta"));
        double massaPianeta = InputDati.leggiDouble(String.format(MSG_MASSA, "pianeta"));
        System.out.println(String.format(MSG_POSIZIONE, "pianeta"));
        double x = InputDati.leggiDouble(String.format(MSG_POSIZIONE_COORDINATA, "x"));
        double y = InputDati.leggiDouble(String.format(MSG_POSIZIONE_COORDINATA, "y"));
        Posizione posizionePianeta = new Posizione(x, y);

        //CONTROLLI E AGGIUNTA PIANETA
        if(sistema.cercaPianeta(codicePianeta) != null) {
            System.out.println(String.format(MSG_ERRORE_ESISTENTE, "pianeta"));
        } else if(sistema.isPosizioneOccupata(posizionePianeta)) {
            System.out.println(String.format(MSG_ERRORE_POSIZIONE, x, y));
        } else {
            Pianeta pianeta = new Pianeta(codicePianeta, massaPianeta, posizionePianeta, sistema.getStella());
            sistema.aggiungiPianeta(pianeta);
            System.out.println(String.format(MSG_CONFERMA_INSERIMENTO, "pianeta", pianeta.getCodiceId()));
        }

        footerMetodo();
    }

    public static void rimuoviPianeta(Sistema sistema)
    {
        headerMetodo(sistema, "RIMUOVI PIANETA");

        //INPUT DATI
        String codicePianeta = InputDati.leggiStringa(String.format(MSG_CODICE_ID, "pianeta"));
        Pianeta pianeta = sistema.cercaPianeta(codicePianeta);

        //CONTROLLI E RIMOZIONE PIANETA
        if(pianeta == null) {
            System.out.println(String.format(MSG_ERRORE_NON_ESISTENTE, "Pianeta"));
        } else {
            sistema.rimuoviPianeta(pianeta);
            System.out.println(String.format(MSG_CONFERMA_RIMOZIONE ,"pianeta", pianeta.getCodiceId()));
        }
        
        footerMetodo();
    }

    public static void aggiungiSatellite(Sistema sistema)
    {
        headerMetodo(sistema, "AGGIUNGI SATELLITE");

        // INPUT DATI
        String codiceSatellite = InputDati.leggiStringa(String.format(MSG_CODICE_ID, "satellite"));
        double massaSatellite = InputDati.leggiDouble(String.format(MSG_MASSA, "satellite"));
        System.out.println(String.format(MSG_POSIZIONE, "satellite"));
        double x = InputDati.leggiDouble(String.format(MSG_POSIZIONE_COORDINATA, "x"));
        double y = InputDati.leggiDouble(String.format(MSG_POSIZIONE_COORDINATA, "y"));
        Posizione posizioneSatellite = new Posizione(x, y);
        String codicePianetaRif = InputDati.leggiStringa(String.format(MSG_RIFERIMENTO, "pianeta"));
        Pianeta pianetaRif = sistema.cercaPianeta(codicePianetaRif);

        // CONTROLLI E AGGIUNTA SATELLITE
        if(pianetaRif == null) {
            System.out.println(String.format(MSG_ERRORE_RIFERIMENTO, "pianeta"));
        } else if(sistema.cercaSatellite(codiceSatellite) != null) {
            System.out.println(String.format(MSG_ERRORE_ESISTENTE, "satellite"));
        } else if(sistema.isPosizioneOccupata(posizioneSatellite)) {
            System.out.println(String.format(MSG_ERRORE_POSIZIONE, x, y));
        } else {
            Satellite satellite = new Satellite(codiceSatellite, massaSatellite, posizioneSatellite, pianetaRif);
            sistema.aggiungiSatellite(satellite);
            System.out.println(String.format(MSG_CONFERMA_INSERIMENTO, "satellite", satellite.getCodiceId()));
        }

        footerMetodo();
    }

    public static void rimuoviSatellite(Sistema sistema)
    {
        headerMetodo(sistema, "RIMUOVI SATELLITE");

        // INPUT DATI
        String codiceSatellite = InputDati.leggiStringa(String.format(MSG_CODICE_ID, "satellite"));
        Satellite satellite = sistema.cercaSatellite(codiceSatellite);

        // CONTROLLI E RIMOZIONE PIANETA
        if (satellite == null) {
            System.out.println(String.format(MSG_ERRORE_NON_ESISTENTE, "satellite"));
        } else {
            sistema.rimuoviSatellite(satellite);
            System.out.println(String.format(MSG_CONFERMA_RIMOZIONE, "satellite", satellite.getCodiceId()));
        }

        footerMetodo();
    }

    public static void cercaSatelliti(Sistema sistema)
    {
        headerMetodo(sistema, "VISUALIZZA SATELLITI PIANETA");

        // INPUT DATI
        String codicePianeta = InputDati.leggiStringa("Inserire codice id del pianeta di cui visualizzare i satelliti: ");
        Pianeta pianeta = sistema.cercaPianeta(codicePianeta);

        // CONTROLLI E CERCA SATELLITI
        if(pianeta == null) {
            System.out.println(String.format(MSG_ERRORE_NON_ESISTENTE, "pianeta"));
        } else {
            ArrayList<Satellite> satellitiPianeta = sistema.cercaSatellitiPianeta(pianeta);
            if(satellitiPianeta == null) {
                System.out.println("Il pianeta non ha satelliti!");
            } else {
                System.out.println("Satelliti:");
                for (Satellite s : satellitiPianeta) {
                    System.out.println("=> " + s.getCodiceId());
                }
            }
        }
        
        footerMetodo();
    }

    public static void cercaCorpoCeleste(Sistema sistema)
    {
        headerMetodo(sistema, "CERCA CORPO CELESTE");

        // INPUT DATI
        String codiceCorpoCeleste = InputDati.leggiStringa("Inserire il codice del corpo celeste: ");
        
        //CONTROLLI E RICERCA CORPO CELESTE
        if(sistema.getStella().getCodiceId().equals(codiceCorpoCeleste)) {
            System.out.println(String.format(MSG_CORPO_CELESTE, sistema.getStella().getCodiceId(), "Stella"));
        } else {
            Pianeta pianeta = sistema.cercaPianeta(codiceCorpoCeleste);
            if(pianeta != null) {
                System.out.println(String.format(MSG_CORPO_CELESTE, pianeta.getCodiceId(), "Pianeta"));         
                System.out.println(String.format(MSG_PERCORSO ,pianeta.getPercorso()));
            } else {
                Satellite satellite = sistema.cercaSatellite(codiceCorpoCeleste);
                if(satellite != null) {
                    System.out.println(String.format(MSG_CORPO_CELESTE, satellite.getCodiceId(), "Satellite"));
                    System.out.println(String.format(MSG_PERCORSO, satellite.getPercorso()));
                } else {
                    System.out.println(String.format(MSG_ERRORE_NON_ESISTENTE, "corpo celeste"));
                }
            }
        }
        
        footerMetodo();
    }

    public static void calcolaCentroMassa(Sistema sistema)
    {
        headerMetodo(sistema, "CALCOLA CENTRO DI MASSA");

        // STAMPA CENTRO MASSA
        System.out.println("Il centro di massa corrisponde a:");
        Posizione centroDiMassa = sistema.calcolaCentroMassa();
        System.out.println("Coordinata x cdm: " + centroDiMassa.getX());
        System.out.println("Coordinata y cdm: " + centroDiMassa.getY());
        char choice = InputDati.leggiUpperChar("Sei sicuro di voler inviare le seguenti informazioni al consiglio intergalattico? (S, N): ", "SN");
        if(choice == 'S') {
            System.out.println("Le tue informazioni sono state inviate al consiglio intergalattico.");
            exit();
        } else {
            InputDati.leggiStringa(MSG_STOP);
        }
    }

}
