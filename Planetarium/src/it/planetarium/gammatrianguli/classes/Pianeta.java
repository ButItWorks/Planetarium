package it.planetarium.gammatrianguli.classes;
import java.util.ArrayList;

public class Pianeta extends CorpoCeleste {

    private Stella stellaRiferimento;

    private ArrayList<Satellite> satellitiOrbitanti = new ArrayList<Satellite>();

    public Pianeta(String codiceId, double massa, Posizione posizione, Stella stellaRiferimento) {
        super(codiceId, massa, posizione);
        this.stellaRiferimento = stellaRiferimento;
    }

    public Stella getStellaRiferimento() {
        return stellaRiferimento;
    }

    public ArrayList<Satellite> getSatellitiOrbitanti() {
        return satellitiOrbitanti;
    }

    public int aggiungiSatellite(Satellite satellite) {
        satellitiOrbitanti.add(satellite);
        return satellitiOrbitanti.size();
    }

    public int rimuoviSatellite(Satellite satellite) {
        satellitiOrbitanti.remove(satellite);
        return satellitiOrbitanti.size();
    }

    public Satellite cercaSatellite(String codiceSatellite) {
        for (Satellite s : satellitiOrbitanti) {
            if (s.getCodiceId().equals(codiceSatellite)) {
                return s;
            }
        }
        return null;
    }

    public String getPercorso() {
        String percorso = stellaRiferimento.getCodiceId();
        percorso += " < " + this.getCodiceId();
        return percorso;
    }

}
