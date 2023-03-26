package graphe;

public class Arc {
    private String noeudSource;

    public String getNoeudDest() {
        return noeudDest;
    }

    private String noeudDest;

    public int getValuation() {
        return valuation;
    }

    private int valuation;

    public Arc(String noeudSource, String noeudDest, int valuation) {
        this.noeudSource = noeudSource;
        this.noeudDest = noeudDest;
        this.valuation = valuation;
    }
}
