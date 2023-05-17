package testspersos;

import graphe.core.IGraphe;
import graphe.implems.*;
import org.junit.jupiter.api.Test;

class GraphesTest {
    @Test
    public void testCreaTemp() {
        IGraphe gMA = new GrapheMAdj();
        IGraphe gHH = new GrapheHHAdj();
        IGraphe gLAD = new GrapheLAdj();
        IGraphe gLAR = new GrapheLArcs();
    }
}
