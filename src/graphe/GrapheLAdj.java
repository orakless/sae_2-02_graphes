/**
 * @author Rémi L
 * @file GrapheLAdj.java
 * @date 26 mars 2023
 */

package graphe;

import java.util.*;

public class GrapheLAdj implements IGraphe {
    private Map<String, List<Arc>> ladj;

    public static final int ERROR_INDEX = -1;

    public GrapheLAdj(String grapheStr) {
        this.ladj = new HashMap<String, List<Arc>>();//TODO : remi : HashMp ?
        this.peupler(grapheStr);
    }

    @Override
    public void ajouterSommet(String noeud) {
        if (this.ladj.containsValue(noeud)){
            throw new IllegalArgumentException("Ce sommet est deja present dans le graphe");
        }
        this.ladj.put(noeud, new ArrayList<Arc>());
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        ladj.get(source).add(new Arc(source, destination, valeur));
    }

    @Override
    public void oterSommet(String noeud) {
        this.ladj.remove(noeud);
    }

    @Override
    public void oterArc(String source, String destination) {
        if(!this.ladj.containsKey(source)){
            throw new IllegalArgumentException("L'arc que vous essayez de supprimer n'existe pas");
        }
        for (int i = 0; i < ladj.get(source).size(); i++) {
            if (ladj.get(source).get(i).getDestination().equals(destination)) {
                ladj.get(source).remove(i);
                break;
            }
        }
    }

    @Override
    public List<String> getSommets() {
        return new ArrayList<>(this.ladj.keySet());
    }

    @Override
    public List<String> getSucc(String sommet) {
        List<String> listeDesSucc = new ArrayList<>();
        for (Arc arc : ladj.get(sommet)) {
            listeDesSucc.add(arc.getDestination());
        }
        return listeDesSucc;
    }

    @Override
    public int getValuation(String src, String dest) {
        if(!this.ladj.containsKey(src)){
            throw new IllegalArgumentException("L'arc demande n'existe pas");
        }
        for (Arc a : this.ladj.get(src)) {
            if (a.getDestination().equals(dest)) {
                return a.getValuation();
            }
        }
        return ERROR_INDEX;
    }

    @Override
    public boolean contientSommet(String sommet) {
        return this.ladj.containsKey(sommet);
    }

    @Override
    public boolean contientArc(String src, String dest) {
        boolean result = false;
        for (Arc a : this.ladj.get(src)) {
            if (a.getDestination().equals(dest)){
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        boolean first = true;
        final StringBuilder sb = new StringBuilder();
        for (String sommet : this.getSommets()) {
            if(!first){
                sb.append(", ");
            }
            else {
                first = false;
            }
            List<Arc> arcs = new ArrayList<Arc>(this.ladj.get(sommet));
            arcs.sort(Comparator.comparing(Arc::getDestination));
            for (Arc arc : arcs) {
                sb.append(sommet).append("-").append(arc.getDestination());
                sb.append("(").append(arc.getValuation()).append(")");
            }

        }
        return sb.toString();
    }
}
