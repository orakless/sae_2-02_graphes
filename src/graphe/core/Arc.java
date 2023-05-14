package graphe.core;

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

    @Override
    public boolean equals(Object obj) {
        Arc arc2 = (Arc) obj;
        return ((this.destination.equals(arc2.destination)) && (this.source.equals(arc2.source)));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(source);
        sb.append("-");
        sb.append(destination);
        sb.append("(");
        sb.append(valuation);
        sb.append(")");
        return sb.toString();
    }
}
