package Dijkstra;

import graphe.IGrapheConst;

import java.util.*;

public class Dijkstra {

    private static String getMinDist(String source, Map<String, Integer> dist, List<String> remaining) {
        int min = Integer.MAX_VALUE;
        String minSommet = source;

        for (String sommet: remaining) {
            if (min > dist.get(sommet) && dist.get(sommet) != -1) {
                min = dist.get(sommet);
                minSommet = sommet;
            }
        }

        return minSommet;
    }

    private static void plusCourtChemin(IGrapheConst graphe, String source, String dest, Map<String, Integer> dist, Map<String, String> pred) {
        int res = (dist.get(source)+graphe.getValuation(source, dest));
        if (res < dist.get(dest) || dist.get(dest) == -1) {
            dist.replace(dest, res);
            pred.replace(dest, source);
        }
    }
    public static void dijkstra(IGrapheConst graphe, String source, Map<String, Integer> dist, Map<String, String> pred) {
        List<String> sommetsRestants = new ArrayList<>();

        pred.put(source, ""); // Pas de prédécesseur pour le sommet d'origine
        dist.put(source, 0);
        sommetsRestants.add(source);

        for (String sommet: graphe.getSommets()) {
            if (!sommet.equals(source)) {
                dist.put(sommet, -1);
                pred.put(sommet, "");
                sommetsRestants.add(sommet);
            }
        }

        String currentSommet = source;

        while (!sommetsRestants.isEmpty()) {
            currentSommet = getMinDist(currentSommet, dist, sommetsRestants);
            sommetsRestants.remove(currentSommet);

            for (String succ: graphe.getSucc(currentSommet)) {
                plusCourtChemin(graphe, currentSommet, succ, dist, pred);
            }
        }
    }
}
