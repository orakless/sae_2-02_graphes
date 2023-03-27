package graphe;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GrapheMAdjTest {

    private final String g31 =
            "A-C(2), A-D(1), "
                    + "B-G(3), "
                    + "C-H(2), "
                    + "D-B(3), D-C(5), D-E(3), "
                    + "E-C(1), E-G(3), E-H(7), "
                    + "F:, "
                    + "G-B(2), G-F(1), "
                    + "H-F(4), H-G(2), "
                    + "I-H(10), "
                    + "J:";

    @Test
    void testToString() {
        GrapheMAdj g = new GrapheMAdj(g31);
        assertEquals(g.toString(), g31);
    }

    @Test
    void testSuccesseurs() {
        GrapheMAdj g = new GrapheMAdj(g31);
        List<String> successeurs = new ArrayList<>();
        assertEquals(g.getSucc("F"), successeurs);
        successeurs.add("B");
        successeurs.add("C");
        successeurs.add("E");
        assertEquals(g.getSucc("D"), successeurs);
    }

    @Test
    void testSommets() {
        GrapheMAdj g = new GrapheMAdj(g31);
        List<String> sommets = new ArrayList<>(List.of(new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"}));
        assertEquals(sommets, g.getSommets());
    }

}