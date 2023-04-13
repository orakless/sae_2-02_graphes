package graphe;

import java.util.*;

public class GrapheMAdj implements IGraphe {
    private static final int step = 3;
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
            if (matrice.length <= indices.size() + 1) {
                int[][] newMatrice = new int[indices.size() + step][indices.size() + step];
                for (int i = 0; i < newMatrice.length; i++) {
                    if (i < matrice.length)
                        System.arraycopy(matrice[i], 0, newMatrice[i], 0, matrice[i].length);
                    Arrays.fill(newMatrice[i], matrice.length, newMatrice.length, NO_VALUATION);
                }
                matrice = newMatrice;
            }
            indices.put(noeud, indices.size());
        }
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur)
            throws IllegalArgumentException {
        if (valeur < 0) throw new IllegalArgumentException("Valeur négative.");
        if (!indices.containsKey(source)) ajouterSommet(source);
        if (!indices.containsKey(destination)) ajouterSommet(destination);
        if (matrice[indices.get(source)][indices.get(destination)] > 0) throw new IllegalArgumentException("Arc déjà " +
                "présent");
        else matrice[indices.get(source)][indices.get(destination)] = valeur;
    }

    @Override
    public void oterSommet(String noeud) {
        if (indices.containsKey(noeud)) {
            int removeAfter = indices.get(noeud);
            indices.remove(noeud);

            indices.forEach((sommet, valeur) -> {
                if (valeur > removeAfter) valeur--;
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
            matrice[src][dest] = 0;
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
        final int srcId = indices.get(sommet);

        for (Map.Entry<String, Integer> dest : indices.entrySet()) {
            if (matrice[srcId][dest.getValue()] > 0)
                listeDesSucc.add(dest.getKey());
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
        return (matrice[indices.get(src)][indices.get(dest)] > 0);
    }

    @Override
    public String toString() {
        return this.toAString();
    }
}
