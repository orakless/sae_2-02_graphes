package testspersos;

import graphe.core.IGraphe;
import graphe.implems.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphesTest {

    private final String graphe =
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


    void testToString(IGraphe g) {
        assertEquals(graphe, g.toString());
    }

    void testSuccesseurs(IGraphe g) {
        List<String> successeurs = new ArrayList<>();

        assertEquals(successeurs, g.getSucc("F"));
        assertNotEquals(successeurs, g.getSucc("A"));
        successeurs.add("B");
        successeurs.add("C");
        successeurs.add("E");
        assertEquals(successeurs, g.getSucc("D"));
    }

    void testContientArc(IGraphe g) {
        assertTrue(g.contientArc("A", "D"));
        assertFalse(g.contientArc("F", "D"));
    }

    void testSuppressionSommet(IGraphe g) {
       assertTrue(g.contientSommet("I"));
       g.oterSommet("I");
       assertFalse(g.contientSommet("I"));
       g.oterSommet("I");
    }

    void testSuppressionArc(IGraphe g) {
        assertThrows(IllegalArgumentException.class,
                () -> g.oterArc("X", "Y"));
        assertTrue(g.contientArc("A", "D"));
        g.oterArc("A", "D");
        assertFalse(g.contientArc("A", "D"));
    }

    void testAjoutSommet(IGraphe g) {
        assertFalse(g.contientSommet("Nouveau sommet"));
        g.ajouterSommet("Nouveau sommet");
        assertTrue(g.contientSommet("Nouveau sommet"));
        g.ajouterSommet("Nouveau sommet");
    }

    void testAjouterArc(IGraphe g) {
        assertThrows(IllegalArgumentException.class,
                () -> g.ajouterArc("G", "B", 2));
        assertFalse(g.contientArc("J", "F"));
        assertThrows(IllegalArgumentException.class,
                () -> g.ajouterArc("A", "B", -1));
        g.ajouterArc("J", "F", 3);
        assertTrue(g.contientArc("J", "F"));
    }

    void testValuation(IGraphe g) {
        assertEquals(2, g.getValuation("A", "C"));
        assertEquals(-1, g.getValuation("D", "A"));
    }

    @Test
    public void testerHHAdj() {
        IGraphe g = new GrapheHHAdj(graphe);
        testToString(g);
        testSuccesseurs(g);
        testValuation(g);
        testContientArc(g);
        testSuppressionSommet(g);
        testSuppressionArc(g);
        testAjoutSommet(g);
        testAjouterArc(g);
    }

    @Test
    public void testerMAdj() {
        IGraphe g = new GrapheMAdj(graphe);
        testToString(g);
        testSuccesseurs(g);
        testValuation(g);
        testContientArc(g);
        testSuppressionSommet(g);
        testSuppressionArc(g);
        testAjoutSommet(g);
        testAjouterArc(g);
    }

    @Test
    public void testerLAdj() {
        IGraphe g = new GrapheLAdj(graphe);
        testToString(g);
        testSuccesseurs(g);
        testValuation(g);
        testContientArc(g);
        testSuppressionSommet(g);
        testSuppressionArc(g);
        testAjoutSommet(g);
        testAjouterArc(g);
    }

    @Test
    public void testerLArcs() {
        IGraphe g = new GrapheLArcs(graphe);
        testToString(g);
        testSuccesseurs(g);
        testValuation(g);
        testContientArc(g);
        testSuppressionSommet(g);
        testSuppressionArc(g);
        testAjoutSommet(g);
        testAjouterArc(g);
    }
}
