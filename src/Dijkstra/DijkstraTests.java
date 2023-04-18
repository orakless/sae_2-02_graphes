package Dijkstra;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import graphe.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DijkstraTests {

    private IGraphe[] graphes = {
            new GrapheLArcs(), new GrapheLAdj(),
            new GrapheMAdj(), new GrapheHHAdj()
    };

    private String grp =
        "A-E(4), "
        + "B-A(2), B-F(3), "
        + "C-B(9), C-F(6), C-D(2), "
        + "D-C(2), D-F(3), D-A(9), "
        + "E-D(4), "
        + "F-C(6), F-A(6), F-B(3)";

    @Test
    void testDijkstra() {
        for (IGraphe graphe: graphes) {
            graphe.peupler(grp);

            Map<String, Integer> dist = new HashMap<>();
            Map<String, String> pred = new HashMap<>();

            Dijkstra.dijkstra(graphe, "C", dist, pred);

            List<String> sommets = List.of("C", "B", "F", "D", "E", "A");
            List<Integer> distances = List.of(0, 8, 5, 2, 14, 10);
            List<String> predesc = List.of("", "F", "D", "C", "A", "B");

            for (int i = 0; i < sommets.size(); i++) {
                assertEquals(dist.get(sommets.get(i)), distances.get(i));
                assertEquals(pred.get(sommets.get(i)), predesc.get(i));
            }
        }
    }
}