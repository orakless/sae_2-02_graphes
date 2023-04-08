package graphe;

import java.util.*;

import static java.util.Map.*;

public class GrapheHHAdj implements IGraphe {
private Map<String, Map<String , Integer>> hhadj;



public GrapheHHAdj(String graph){
    this.hhadj = new TreeMap<>();
    this.peupler(graph);
}
    public GrapheHHAdj(){
        this.hhadj = new TreeMap<>();
    }

    @Override
    public void ajouterSommet(String noeud) {

    this.hhadj.putIfAbsent(noeud,new HashMap<>());
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) throws IllegalArgumentException {
        if (!this.hhadj.containsKey(source)){
            ajouterSommet(source);
        }
        if (!this.hhadj.containsKey(destination)){
            ajouterSommet(destination);
        }
        if (!this.hhadj.get(source).containsKey(destination)){
            if (valeur < 0){
                throw new IllegalArgumentException("Valeur négative.");
            }
            this.hhadj.get(source).put(destination,valeur);
        }
        else throw new IllegalArgumentException("Arc existe");
    }

    @Override
    public void oterSommet(String noeud) {
        if (this.hhadj.containsKey(noeud)) {
            this.hhadj.remove(noeud);
        }
    }

    @Override
    public void oterArc(String source, String destination)throws IllegalArgumentException {
        Map<String , Integer> interieur= this.hhadj.get(source);
        if (interieur == null || !interieur.containsKey(destination))
            throw new IllegalArgumentException("Arc existe pas");
        this.hhadj.get(source).remove(destination);
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
        return this.hhadj.get(src).get(dest);
    }

    @Override
    public boolean contientSommet(String sommet) {
        return this.hhadj.containsKey(sommet);
    }

    @Override
    public boolean contientArc(String src, String dest) {
        return this.hhadj.containsKey(src)&& this.hhadj.get(src).containsKey(dest);
    }

    public String toString(){
        String s ="";
        int index =0;
        for (Entry<String, Map<String, Integer>> entry : this.hhadj.entrySet()) {
            Map<String,Integer> Inner =  new TreeMap<>(entry.getValue());
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
