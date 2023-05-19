package graphe.implems;

import java.util.*;

import static java.lang.Math.ceil;

import graphe.core.*;


public class GrapheMAdj implements IGraphe {
    private int step = 1;
    private int realIndiceCount = 0;
    private int[][] matrice;
    private final Map<String, Integer> indices;

    private final int NO_VALUATION = -1;

    public GrapheMAdj() {
        matrice = new int[0][0];
        this.indices = new HashMap<>();
    }

    public GrapheMAdj(String graph) {
        this();
        this.peupler(graph);
    }

    @Override
    public void ajouterSommet(String noeud) {
        if (!indices.containsKey(noeud)) {
            indices.put(noeud, null);
        }
    }

    private int getMaxValue() {
        int max = -1;
        for (String val : indices.keySet()) {
            if (indices.get(val) != null && indices.get(val) > max) {
                max = indices.get(val);
            }
        }
        return max;
    }

    private void addToGraph(String node) {
        indices.replace(node, null, getMaxValue()+1);
        realIndiceCount++;
        if (matrice.length <= realIndiceCount) {
            int[][] newMatrice = new int[realIndiceCount + step][realIndiceCount + step];
            for (int i = 0; i < newMatrice.length; i++) {
                Arrays.fill(newMatrice[i], 0, newMatrice.length, NO_VALUATION);
                if (i < matrice.length)
                    System.arraycopy(matrice[i], 0, newMatrice[i], 0, matrice[i].length);
            }
            matrice = newMatrice;
            System.gc();
            step = (int) ceil(matrice.length / 10.0);
        }
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur)
            throws IllegalArgumentException {
        if (valeur < 0) throw new IllegalArgumentException("Valeur négative.");

        ajouterSommet(source);
        ajouterSommet(destination);

        if (indices.get(source) != null && indices.get(destination) != null) {
            if (matrice[indices.get(source)][indices.get(destination)] != NO_VALUATION)
                throw new IllegalArgumentException("Arc déjà présent");
        } else {
            if (indices.get(source) == null)
                addToGraph(source);
            if (indices.get(destination) == null)
                addToGraph(destination);
        }
        matrice[indices.get(source)][indices.get(destination)] = valeur;
    }

    @Override
    public void oterSommet(String noeud) {
        if (indices.containsKey(noeud)) {
            int removeAfter = indices.get(noeud);
            indices.remove(noeud);

            indices.forEach((sommet, valeur) -> {
                if (valeur != null && valeur > removeAfter) valeur--;
            });

            for (int[] line : matrice) {
                System.arraycopy(line, removeAfter, line, removeAfter, line.length - removeAfter);
            }
        }
    }

    @Override
    public void oterArc(String source, String destination)
            throws IllegalArgumentException {
        try {
            int src = indices.get(source), dest = indices.get(destination);
            matrice[src][dest] = NO_VALUATION;
        } catch (NullPointerException NPE) {
            throw new IllegalArgumentException("Sommets inexistants");
        }
    }

    @Override
    public List<String> getSommets() {
        return new ArrayList<>(indices.keySet());
    }


    @Override
    public List<String> getSucc(String sommet) {
        List<String> listeDesSucc = new ArrayList<>();
        if (indices.get(sommet) != null) {
            final int srcId = indices.get(sommet);
            for (Map.Entry<String, Integer> dest : indices.entrySet()) {
                if (dest.getValue() != null && matrice[srcId][dest.getValue()] != NO_VALUATION)
                    listeDesSucc.add(dest.getKey());
            }
        }

        return listeDesSucc;
    }

    @Override
    public int getValuation(String src, String dest) {
        return matrice[indices.get(src)][indices.get(dest)];
    }

    @Override
    public boolean contientSommet(String sommet) {
        return indices.containsKey(sommet);
    }

    @Override
    public boolean contientArc(String src, String dest) {
        if (indices.getOrDefault(src, null) == null || indices.getOrDefault(dest, null) == null) return false;
        return (matrice[indices.get(src)][indices.get(dest)] != NO_VALUATION);
    }

    @Override
    public String toString() {
        return this.toAString();
    }
}
