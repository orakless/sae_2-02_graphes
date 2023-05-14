package graphe.algos;

import graphe.core.IGrapheConst;
import fibonacciHeap.FibonacciHeap;
import java.util.*;

public class Dijkstra {
    public static void dijkstra(IGrapheConst graphe, String source, Map<String, Integer> dist, Map<String, String> pred) {
        FibonacciHeap<String> sommetsRestants = new FibonacciHeap<>();
        Map<String, FibonacciHeap.Entry<String>> entries = new HashMap<>();

        for (String sommet: graphe.getSommets()) {
            entries.put(sommet, sommetsRestants.enqueue(sommet, Integer.MAX_VALUE));
        }
        sommetsRestants.decreaseKey(entries.get(source), 0);

        while (!sommetsRestants.isEmpty()) {
            FibonacciHeap.Entry<String> currentSommet = sommetsRestants.dequeueMin();

            // Si il n'y a pas de chemin (donc, que c'est égal à Integer.MAX_VALUE), mettre la valeur dans dist à -1
            // et passer
            if (currentSommet.getPriority() == Integer.MAX_VALUE) {
                continue;
            }

            dist.put(currentSommet.getValue(), currentSommet.getPriority());

            for (String succ: graphe.getSucc(currentSommet.getValue())) {
                if (dist.containsKey(succ)) continue;

                int alt;
                if (currentSommet.getPriority() == Integer.MAX_VALUE)
                    alt = Integer.MAX_VALUE;
                else alt = currentSommet.getPriority()+graphe.getValuation(currentSommet.getValue(), succ);

                if (alt < entries.get(succ).getPriority()) {
                    pred.put(succ, currentSommet.getValue());
                    sommetsRestants.decreaseKey(entries.get(succ), alt);
                }
            }
        }
    }


}
