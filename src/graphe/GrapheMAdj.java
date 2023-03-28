package graphe;

import java.util.*;

public class GrapheMAdj implements IGraphe {
    private static final int step = 3;
    private int[][] matrice;
    private final Map<String, Integer> indices;

    public GrapheMAdj(String graph) {
        matrice = new int[0][0];
        this.indices = new TreeMap<>();
        this.peupler(graph);
    }

    @Override
    public void ajouterSommet(String noeud) {
        if (!indices.containsKey(noeud)) {
            if (matrice.length <= indices.size() + 1) {
                int[][] newMatrice = new int[indices.size() + step][indices.size() + step];
                for (int i = 0; i < matrice.length; i++) {
                    System.arraycopy(matrice[i], 0, newMatrice[i], 0, matrice[i].length);
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
        if (!indices.containsKey(destination)) ajouterSommet(source);
        if (matrice[indices.get(source)][indices.get(destination)] > 0) throw new IllegalArgumentException("Arc déjà " +
                "présent");
        matrice[indices.get(source)][indices.get(destination)] = valeur;
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
        return indices.keySet().stream().toList();
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
        boolean first = true;
        final StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, Integer> source : indices.entrySet()) {
            String srcNom = source.getKey();
            int srcId = source.getValue();
            boolean hasArc = false;

            for (Map.Entry<String, Integer> destination : indices.entrySet()) {
                String destNom = destination.getKey();
                int destId = destination.getValue();

                if (matrice[srcId][destId] > 0) {
                    if (!first) sb.append(", ");
                    else first = false;

                    hasArc = true;
                    sb.append(srcNom).append("-").append(destNom)
                            .append("(").append(matrice[srcId][destId]).append(")");
                }
            }
            if (!hasArc) {
                if (!first) sb.append(", ");
                else first = false;
                sb.append(srcNom).append(":");
            }
        }
        return sb.toString();
    }
}
