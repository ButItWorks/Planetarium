package it.planetarium.gammatrianguli.classes;
import java.util.ArrayList;

public class Stella extends CorpoCeleste {
    private ArrayList<Pianeta> pianetiOrbitanti = new ArrayList<Pianeta>();

    public Stella(String codiceId, double massa, Posizione posizione) {
        super(codiceId, massa, posizione);
    }

    public ArrayList<Pianeta> getPianetiOrbitanti() {
        return pianetiOrbitanti;
    }

    public int aggiungiPianeta(Pianeta pianeta) {
        pianetiOrbitanti.add(pianeta);
        return pianetiOrbitanti.size();
    }

    public int rimuoviPianeta(Pianeta pianeta) {
        pianetiOrbitanti.remove(pianeta);
        return pianetiOrbitanti.size();
    }

    public Pianeta cercaPianeta(String codicePianeta) {
        for (Pianeta p : this.pianetiOrbitanti) {
            if(p.getCodiceId().equals(codicePianeta)) {
                return p;
            }
        }
        return null;
    }
}
