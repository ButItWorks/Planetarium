package it.planetarium.gammatrianguli.classes;

public class Satellite extends CorpoCeleste {

    private Pianeta pianetaRiferimento;

    public Satellite(String codiceId, double massa, Posizione posizione, Pianeta pianetaRiferimento) {
        super(codiceId, massa, posizione);
        this.pianetaRiferimento = pianetaRiferimento;
    }

    public Pianeta getPianetaRiferimento() {
        return pianetaRiferimento;
    }

    public String getPercorso() {
        String percorso = pianetaRiferimento.getStellaRiferimento().getCodiceId();
        percorso += " < " + pianetaRiferimento.getCodiceId();
        percorso += " < " + this.getCodiceId();
        return percorso;
    }
}
