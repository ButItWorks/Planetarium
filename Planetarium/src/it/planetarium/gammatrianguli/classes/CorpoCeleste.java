package it.planetarium.gammatrianguli.classes;

public class CorpoCeleste {
    private String codiceId;

    private double massa;

    private Posizione posizione;

    public CorpoCeleste(String codiceId, double massa, Posizione posizione) {
        this.codiceId = codiceId;
        this.massa = massa;
        this.posizione = posizione;
    }

    public String getCodiceId() {
        return codiceId;
    }

    public double getMassa() {
        return massa;
    }

    public Posizione getPosizione() {
        return posizione;
    }

    public String getPosizioneFormat() {
        return String.format("(%.1f; %.1f)", posizione.getX(), posizione.getY());
    }
}
