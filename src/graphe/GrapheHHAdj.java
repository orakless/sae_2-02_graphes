package graphe;

import java.util.*;

import static java.util.Map.*;

public class GrapheHHAdj implements IGraphe {
private Map<String, Map<String , Integer>> hhadj;



public GrapheHHAdj(String graph){
    this.hhadj = new HashMap<>();
    this.peupler(graph);
}

    @Override
    public void ajouterSommet(String noeud) {
    this.hhadj.putIfAbsent(noeud,new HashMap<>());
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        Map<String, Integer> Map = this.hhadj.getOrDefault(source,new HashMap<>());
        Map.put(destination,valeur);
        this.hhadj.put(source,Map);
    }

    @Override
    public void oterSommet(String noeud) {

    this.hhadj.remove(noeud);
    }

    @Override
    public void oterArc(String source, String destination) {
        oterSommet(source);
        oterSommet(destination);
    }

    @Override
    public List<String> getSommets() {
    List<String> l_sommet = new ArrayList<>();
        this.hhadj.forEach((key, value) -> l_sommet.add((String) key));
        return l_sommet;
    }

    @Override
    public List<String> getSucc(String sommet) {
        Map<String , Integer> interieur = this.hhadj.get(sommet);
        List<String> l_succeseur = new ArrayList<>(interieur.keySet());
        return l_succeseur;
    }

    @Override
    public int getValuation(String src, String dest) {
        int valuation = 0;
        valuation=  this.hhadj.get(src).get(dest);
        return valuation;
    }

    @Override
    public boolean contientSommet(String sommet) {
    if (this.hhadj.containsKey(sommet))
        return true;
    else if (this.hhadj.values().contains(sommet)) {
        return  true;
    }
        return false;
    }

    @Override
    public boolean contientArc(String src, String dest) {
    if (this.hhadj.containsKey(src)&& this.hhadj.get(src).containsKey(dest))
        return true;
    return false;
    }

    public String toString(){
    String s ="";
    int index =0;
        for (Entry<String, Map<String, Integer>> entry : this.hhadj.entrySet()) {
            Map<String,Integer> Inner =  entry.getValue();
            if (Inner.size()==0){
                s+=entry.getKey()+":";
                if (index!=this.hhadj.size()-1)
                    s+=", ";
            }
        for (Entry<String,Integer> entry2 : Inner.entrySet()) {
                s += entry.getKey() + "-" + entry2.getKey() + "(" + entry2.getValue() + ")";
            if (index!=this.hhadj.size()-1)
                s+=", ";
            }
            ++index;
        }
        return s ;
    }
}
