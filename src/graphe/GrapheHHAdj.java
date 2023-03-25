package graphe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Map.*;

public class GrapheHHadj implements IGraphe {
    private Map<String, Map<String , Integer>> hhadj;



    public GrapheHHadj(String graph){
        this.hhadj = new HashMap<>();
        this.peupler(graph);
    }

    @Override
    public void ajouterSommet(String noeud) {
        this.hhadj.putIfAbsent(noeud,new HashMap<>());
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        Map<String, Integer> Map;
        Map = new HashMap<>();
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
        List<String> s = new ArrayList<>();
        this.hhadj.forEach((key, value) -> s.add((String) key));
        return s;
    }

    @Override
    public List<String> getSucc(String sommet) {
        List<String> s = new ArrayList<>();
        this.hhadj.forEach((key, value) -> {
            if (key == sommet) {
                value.


            }
        }
    });
        return s;
}

    @Override
    public int getValuation(String src, String dest) {
        return this.hhadj.get(src,);
    }

    @Override
    public boolean contientSommet(String sommet) {

        return false;
    }

    @Override
    public boolean contientArc(String src, String dest) {

        return false;
    }
}
