package graphe;


import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import Dijkstra.Dijkstra;
import org.junit.jupiter.api.Test;

class IGrapheTest {
	private final IGraphe[] graphes = { 
			new GrapheLArcs(), new GrapheLAdj(),
			new GrapheMAdj(), new GrapheHHAdj()
	};
	
	// graphe de l'exercice 3.1 du poly de maths
	// avec en plus un noeud isole : J
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
	
	private final String g31a = ""       // arcs non tries
			+ "D-C(5), D-E(3), D-B(3), "
			+ "E-G(3), E-C(1), E-H(7), "
			+ "I-H(10), "
			+ "J:,"
			+ "G-B(2), G-F(1), "
			+ "F:, "
			+ "H-G(2), H-F(4), "
			+ "A-C(2), A-D(1), "
			+ "B-G(3), "
			+ "C-H(2) ";
	
	@Test
	void exo3_1Maths() {
		for (IGraphe g : graphes) {
			g.peupler(g31a); 
			tester3_1(g);			
		}
	}
	
	void tester3_1(IGraphe g) {
		List<String> sommets_exp = List.of("A","B","C","D","E","F","G","H","I","J");
		List<String> sommets = new ArrayList<String>(g.getSommets()); // pas forcement triee
		Collections.sort(sommets);
		assertEquals(sommets_exp, sommets);
		assertTrue(g.contientSommet("C"));
		assertFalse(g.contientSommet("c"));
		assertTrue(g.contientArc("C","H"));
		assertFalse(g.contientArc("H","C"));
		assertEquals(7,g.getValuation("E", "H"));
		List<String> successeurs = new ArrayList<String>(g.getSucc("D")); // pas forcement triee
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
	
	void petiteImporation(IGraphe g) {
		Arc a = GraphImporter.importer("graphes/g-10-1.txt", g);
		assertEquals("1-3(5), "
				+ "10-3(3), 2-1(5), 2-3(5), 2-5(4), "
				+ "3-4(4), 3-5(4), 4-10(1), 4-2(1), 4-7(3), "
				+ "5-9(4), 6-2(3), 6-3(4), 7-3(2),"
				+ " 8-2(4), 8-6(1), 9-2(4)",
				g.toString());
		assertEquals("5", a.getSource());
		assertEquals("7", a.getDestination());		
	}
	
	//@Test
	void petitTestImportation() {
		for (IGraphe g : graphes)
			petiteImporation(g);	
	}
	
	@Test
	void importer() throws NumberFormatException, FileNotFoundException {
		String graphesRep = "graphes"; 
        String reponsesRep = "reponses"; 
        try {
            List<Path> files1 = Files.list(Paths.get(graphesRep))
                                     .filter(Files::isRegularFile)
                                     .collect(Collectors.toList());
            List<Path> files2 = Files.list(Paths.get(reponsesRep))
                                     .filter(Files::isRegularFile)
                                     .collect(Collectors.toList());

            Iterator<Path> iterator1 = files1.iterator();
            Iterator<Path> iterator2 = files2.iterator();

            while (iterator1.hasNext() && iterator2.hasNext()) {
                Path file1 = iterator1.next();
                Path file2 = iterator2.next();

                IGraphe g = new GrapheLAdj();
                Arc arc = GraphImporter.importer(file1.toFile(), g);

                List<Integer> listeEntiers = new ArrayList<>();
                int distance_attendue = GraphImporter.importerReponse(file2.toString(), listeEntiers);

                System.out.println("\ngraphe: " + file1.getFileName());
                System.out.println("chemin a trouver " + arc.getSource()+ " "+ arc.getDestination());
                System.out.println("Graphe de  " + g.getSommets().size() + " sommets");

                System.out.println("reponse: " + file2.getFileName());
                if (distance_attendue >=0) {
                	System.out.println("distance attendue : " + distance_attendue);
                	System.out.println("chemin possible : " + listeEntiers);
                } else System.out.println("Aucun chemin attendu");
                
                Map<String, Integer> dist = new HashMap<>();
                Map<String, String> prev = new HashMap<>();
                long debut = System.nanoTime();
                Dijkstra.dijkstra(g, arc.getSource(), dist, prev);
                long fin = System.nanoTime();
                System.out.println("dijkstra a dure " + (fin - debut)/1000000 + " millisecondes");
                
                // reconstruction d'un plus court chemin
                List<String> path = new LinkedList<>();
                String currentNode = arc.getDestination();
                while (currentNode != null && !currentNode.equals(arc.getSource())) {
                    path.add(0, currentNode);
                    currentNode = prev.get(currentNode);
                }

                // ajouter la source
                if (currentNode != null && currentNode.equals(arc.getSource())) {
                    path.add(0, arc.getSource());
                }

                if (dist.containsKey(arc.getDestination())) {
                    int distance_trouvee = dist.get(arc.getDestination());
                	System.out.println("chemin trouve : <" + String.join(", ", path)+">");
                	System.out.println("Distance trouvee : " + distance_trouvee);
                	assertEquals(distance_trouvee, distance_attendue);
                } else {
                	System.out.println("Aucun chemin trouve");
                	assertEquals(-1, distance_attendue);
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de l'acces aux dossiers: " + e.getMessage());
        }
    }

}
