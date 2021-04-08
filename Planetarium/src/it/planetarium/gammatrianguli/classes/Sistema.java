package it.planetarium.gammatrianguli.classes;
import java.util.ArrayList;

public class Sistema {
    
    private String nomeSistema;

    private Stella stella;

    public Sistema(String nomeSistema, Stella stella) {
        this.nomeSistema = nomeSistema;
        this.stella = stella;
    }

    public String getNomeSistema() {
        return nomeSistema;
    }

    public Stella getStella() {
        return stella;
    }

    public boolean isPosizioneOccupata(Posizione posizione) {
        for (Pianeta p : stella.getPianetiOrbitanti()) {
            if(p.getPosizione().equals(posizione)) {
                return true;
            } else {
                for (Satellite s : p.getSatellitiOrbitanti()) {
                    if (s.getPosizione().equals(posizione)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Pianeta cercaPianeta(String codice) {
        return stella.cercaPianeta(codice);
    }

    public Satellite cercaSatellite(String codice) {
        for (Pianeta p : stella.getPianetiOrbitanti()) {
            Satellite s = p.cercaSatellite(codice);
            if(s != null) {
                return s;
            }
        }
        return null;
    }

    public ArrayList<Satellite> cercaSatellitiPianeta(Pianeta pianeta) {
        for (Pianeta p : stella.getPianetiOrbitanti()) {
            if(p.getCodiceId() == pianeta.getCodiceId()) {
                return p.getSatellitiOrbitanti();
            }
        }
        return null;
    }

    public int aggiungiPianeta(Pianeta pianeta) {      
        return stella.aggiungiPianeta(pianeta);
    }

    public int rimuoviPianeta(Pianeta pianeta) {
        return stella.rimuoviPianeta(pianeta);
    }

    public int aggiungiSatellite(Satellite satellite) {
        Pianeta pianetaRif = satellite.getPianetaRiferimento();
        for (Pianeta p : stella.getPianetiOrbitanti()) {
            if(p.getCodiceId() == pianetaRif.getCodiceId()) {
                return p.aggiungiSatellite(satellite);
            }
        }
        return -1;
    }

    public int rimuoviSatellite(Satellite satellite) {
        Pianeta pianetaRif = satellite.getPianetaRiferimento();
        for (Pianeta p : stella.getPianetiOrbitanti()) {
            if (p.getCodiceId() == pianetaRif.getCodiceId()) {
                return p.rimuoviSatellite(satellite);
            }
        }
        return -1;
    }

    public Posizione calcolaCentroMassa() {
        double mediaPesataX = stella.getMassa() * stella.getPosizione().getX();
        double mediaPesataY = stella.getMassa() * stella.getPosizione().getY();
        double massaTotale = stella.getMassa();
        double cdmX = 0.0;
        double cdmY = 0.0;
        for (Pianeta p : stella.getPianetiOrbitanti()) {
            mediaPesataX += p.getPosizione().getX() * p.getMassa();
            mediaPesataY += p.getPosizione().getY() * p.getMassa();
            massaTotale += p.getMassa();
            for (Satellite s : p.getSatellitiOrbitanti()) {
                mediaPesataX += s.getPosizione().getX() * s.getMassa();
                mediaPesataY += s.getPosizione().getY() * s.getMassa();
                massaTotale += s.getMassa();
            }
        }
        cdmX = mediaPesataX / massaTotale;
        cdmY = mediaPesataY / massaTotale;
        return new Posizione(cdmX, cdmY);
    }

}
