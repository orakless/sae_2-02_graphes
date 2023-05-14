package graphe.implems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import graphe.core.*;


public class GrapheLArcs implements IGraphe {
    private List<Arc> arcs;

    private static final String NULL_STR = "";
    private static final int NO_VALUATION = -1;

    public GrapheLArcs() {
        this.arcs = new ArrayList<>();
    }

    public GrapheLArcs(String input) {
        this();
        peupler(input);
    }

    @Override
    public List<String> getSommets() {
        List<String> nodes = new ArrayList<>();
        for (Arc arc : this.arcs) {
            if (NULL_STR.equals(arc.getDestination()) && arc.getValuation() == NO_VALUATION ) {
                nodes.add(arc.getSource());
            }
        }
        Collections.sort(nodes);
        return nodes;
    }

    @Override
    public List<String> getSucc(String sommet) {
        List<String> outgoingArcs = new ArrayList<>();
        for (Arc arc : this.arcs) {
            if (sommet.equals(arc.getSource()) && arc.getValuation() != NO_VALUATION) {
                outgoingArcs.add(arc.getDestination());
            }
        }
        return outgoingArcs;
    }

    @Override
    public int getValuation(String src, String dest) {
        for (Arc arc: arcs) {
            if (src.equals(arc.getSource()) && dest.equals(arc.getDestination()))
                return arc.getValuation();
        }
        return -1;
    }

    @Override
    public boolean contientSommet(String sommet) {
        for(Arc a : this.arcs){
            if (a.getSource().equals(sommet) || a.getDestination().equals(sommet)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contientArc(String src, String dest) {
        for(Arc a : this.arcs){
            if(a.getSource().equals(src) && a.getDestination().equals(dest)){
                return true;
            }
        }
        return false;
    }

    private boolean contains(String source, String destination) {
        for (Arc arc: arcs) {
            if (source.equals(arc.getSource()) && destination.equals(arc.getDestination()))
                return true;
        }
        return false;
    }

    @Override
    public void ajouterSommet(String noeud) {
        if (!contains(noeud, NULL_STR))
            this.arcs.add(new Arc(noeud, NULL_STR, NO_VALUATION));
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) throws IllegalArgumentException {
        if (valeur < 0) throw new IllegalArgumentException("Valuation négative.");

        ajouterSommet(source);
        ajouterSommet(destination);
        if (contains(source, destination)) throw new IllegalArgumentException("Arc déjà présent.");
        else this.arcs.add(new Arc(source, destination, valeur));
    }

    @Override
    public void oterSommet(String noeud) {
        arcs.removeIf(arc -> noeud.equals(arc.getSource()) || noeud.equals(arc.getDestination()));
    }

    @Override
    public void oterArc(String source, String destination) throws IllegalArgumentException {
        if (!contains(source, destination)) throw new IllegalArgumentException("Cet arc n'existe pas.");
        arcs.removeIf(arc -> source.equals(arc.getSource()) && destination.equals(arc.getDestination()));
    }

    @Override
    public String toString() {
        return this.toAString();
    }
}
