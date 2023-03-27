package graphe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrapheLAdjTest {
    private String g31 =
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
        GrapheLAdj g = new GrapheLAdj(g31);
        System.out.println(g.toString());
    }
}