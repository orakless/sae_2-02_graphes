package graphe.algos;

import graphe.IHM.CheminATrouver;
import graphe.IHM.GraphDirectoryImporter;
import graphe.IHM.GraphImporter;
import graphe.IHM.Main;
import graphe.core.Arc;
import graphe.core.IGraphe;
import graphe.implems.GrapheHHAdj;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


// Placer les graphes a tester dans le repertoire GRAPHES_REP
// et les fichier de reponses attendues dans REPONSES_REP
// les sous-repertoires ne sont pas pris en commpte
// ce qui permet de piocher dedans ce qu'on veut tester
class DijkstraTest {


    @Test
    void testerTousLesGraphes() {
        GraphDirectoryImporter importer = new GraphDirectoryImporter(Main.GRAPHES_REP,
                Main.REPONSES_REP, true, new GrapheHHAdj());
        for (CheminATrouver cat : importer) {
            checkAndTime(cat.getGraph(), cat.getSD_arc().getSource(),
                    cat.getSD_arc().getDestination(), cat.getDistance_attendue());
        }
    }

    void checkAndTime(IGraphe g, String source, String dest, int distanceAttendue) {
        Map<String, Integer> dist = new HashMap<>();
        Map<String, String> prev = new HashMap<>();
        long duree = DijkstraTools.time(g, source, dist, prev);
        System.out.println("dijkstra a dure " + duree + " millisecondes");

        List<String> cheminTrouve = DijkstraTools.getPath(source, dest, prev);
        int distanceTrouvee = dist.getOrDefault(dest, -1);
        if (distanceTrouvee >=0) {
            System.out.println("Chemin trouve : <" + String.join(", ", cheminTrouve)+">");
            System.out.println("Distance trouvee : " + distanceTrouvee);
        } else
            System.out.println("Aucun chemin trouve !");
        assertEquals(distanceAttendue, distanceTrouvee);
    }
}