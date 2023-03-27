package graphe;

public class Arc {
    public String getSource() {
        return source;
    }

    private String source;

    public String getDestination() {
        return destination;
    }

    private String destination;

    public int getValuation() {
        return valuation;
    }

    private int valuation;

    public Arc(String source, String destination, int valuation) {
        this.source = source;
        this.destination = destination;
        this.valuation = valuation;
    }
}
