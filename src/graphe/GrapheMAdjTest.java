package graphe;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
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
        assertNotEquals(g.getSucc("A"), successeurs);
        successeurs.add("B");
        successeurs.add("C");
        successeurs.add("E");
        assertEquals(g.getSucc("D"), successeurs);
    }

    @Test
    void testSommets() {
        IGraphe g = new GrapheMAdj(g31);
        List<String> sommets = new ArrayList<>(List.of(new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"}));
        assertEquals(sommets, g.getSommets());
        sommets.remove(0);
        assertNotEquals(sommets, g.getSommets());
        IGraphe g2 = new GrapheMAdj("A:");
        g2.oterSommet("A");
        assertEquals(new ArrayList<String>(), g2.getSommets());
    }

    @Test
    void contientArc() {
        IGraphe g = new GrapheMAdj(g31);
        assertTrue(g.contientArc("A", "D"));
        assertFalse(g.contientArc("F", "D"));
    }

    @Test
    void testProf() {
        IGraphe g = new GrapheMAdj(g31);
        List<String> sommets_exp = List.of("A","B","C","D","E","F","G","H","I","J");
        List<String> sommets = new ArrayList<>(g.getSommets()); // pas forcement triee
        Collections.sort(sommets);
        assertEquals(sommets_exp, g.getSommets());
        assertTrue(g.contientSommet("C"));
        assertFalse(g.contientSommet("c"));
        assertTrue(g.contientArc("C","H"));
        assertFalse(g.contientArc("H","C"));
        assertEquals(7,g.getValuation("E", "H"));
        List<String> successeurs = new ArrayList<>(g.getSucc("D")); // pas forcement triee
        Collections.sort(successeurs);
        assertEquals(List.of("B","C", "E"), successeurs);
        assertEquals(g31, g.toString());

        g.ajouterSommet("A"); // ne fait rien car A est deja present
        assertEquals(g31, g.toString());
        assertThrows(IllegalArgumentException.class,
                () -> g.ajouterArc("G", "B", 1));		// deja present
        g.oterSommet("X"); // ne fait rien si le sommet n'est pas present
        assertEquals(g31, g.toString());
        assertThrows(IllegalArgumentException.class,
                () -> g.oterArc("X", "Y"));  // n'existe pas

        assertThrows(IllegalArgumentException.class,
                () -> g.ajouterArc("A", "B", -1)); // valuation negative
    }

}