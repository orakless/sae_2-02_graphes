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
        this();
        this.peupler(grapheStr);
        //clean();//TODO REMI : verifier ca
    }

    private void clean() {
        ArrayList<String> lesSommetsQuiOntPasDArcs = recupSommetsQuiOntPAsDArcs();
        for(String sommetPasDarcs : lesSommetsQuiOntPasDArcs) {
            if(isDestination(sommetPasDarcs)){
                oterSommet(sommetPasDarcs);
            }
        }
    }

    private ArrayList<String> recupSommetsQuiOntPAsDArcs() {
        ArrayList<String> listeRetour = new ArrayList<>();
        for(String sommet : this.ladj.keySet()){
            if(this.ladj.get(sommet).isEmpty()){
                listeRetour.add(sommet);
            }
        }
        return listeRetour;
    }

    private ArrayList<String> recupSommetsQuiOntDesArcs(){
        ArrayList<String> listeRetour = new ArrayList<>();
        for(String sommet : this.ladj.keySet()){
            if(!this.ladj.get(sommet).isEmpty()){
                listeRetour.add(sommet);
            }
        }
        return listeRetour;
    }

    private boolean isDestination(String sommet){
        ArrayList<String> lesSommetsQuiOntDesArcs = recupSommetsQuiOntDesArcs();
        for(String sommetDest : lesSommetsQuiOntDesArcs) {
            for(Arc arc : this.ladj.get(sommetDest)) {
                if(arc.getDestination().equals(sommet)){
                    return true;
                }
            }
        }
        return false;
    }

    public GrapheLAdj() {
        this.ladj = new HashMap<>();
    }

    @Override
    public void ajouterSommet(String noeud) {
        if (!this.ladj.containsKey(noeud)){
            this.ladj.put(noeud, new ArrayList<>());
        }
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        if(!this.ladj.containsKey(source)){
            ajouterSommet(source);
        }
        else if(this.ladj.get(source).contains(new Arc(source, destination, valeur))){
            throw new IllegalArgumentException("L'arc que vous essayez d'ajouter existe deja");
        }
        if(valeur < 0){
            throw new IllegalArgumentException("L'arc que vous essayez d'ajouter a une valuation négative");
        }
        ladj.get(source).add(new Arc(source, destination, valeur));
    }

    @Override
    public void oterSommet(String sommetASuppr) {
        if(this.ladj.containsKey(sommetASuppr)){
            this.ladj.remove(sommetASuppr);
        }
        for (String sommet : this.ladj.keySet()) {
            for (int i = 0; i < ladj.get(sommet).size(); i++) {
                if (ladj.get(sommet).get(i).getDestination().equals(sommetASuppr)) {
                    oterArc(sommet, sommetASuppr);
                }
            }
        }
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
        ArrayList<String> listeRetour = new ArrayList<>(this.ladj.keySet());
        for(String sommet : this.ladj.keySet()) {
            for(Arc arc : this.ladj.get(sommet)) {
                if(!listeRetour.contains(arc.getDestination())){
                    listeRetour.add(arc.getDestination());
                }
            }
        }
        Collections.sort(listeRetour);
        return listeRetour;
    }

    @Override
    public List<String> getSucc(String sommet) {
        List<String> listeDesSucc = new ArrayList<>();
        if(ladj.get(sommet) == null){
            return listeDesSucc;
        }
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
        SortedSet<String> sommets_tries = new TreeSet<>(this.ladj.keySet());
        for (String sommet : sommets_tries) {
            Comparator<Arc> comparator = Comparator.comparing(Arc::toString);
            ArrayList<Arc> arcsDuSommet = (ArrayList<Arc>) this.ladj.get(sommet);
            arcsDuSommet.sort(comparator);
            if(arcsDuSommet.isEmpty()){
                if(!first){
                    sb.append(", ");
                }
                else {
                    first = false;
                }
                sb.append(sommet).append(":");
            }
            for (Arc arc : arcsDuSommet) {
                if(!first){
                    sb.append(", ");
                }
                else {
                    first = false;
                }
                sb.append(arc.toString());
            }

        }
        return sb.toString();
    }
}
