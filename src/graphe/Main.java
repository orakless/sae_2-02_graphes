package graphe;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import Dijkstra.Dijkstra;


public class Main {
	private static final IGraphe[] implems = { 
     		new GrapheLArcs(), new GrapheLAdj(),
	//		new GrapheMAdj(),
            new GrapheHHAdj()
	};


	public static void main(String[] args) {
		for (IGraphe implem : implems) {
			System.out.print(implem.getClass().getSimpleName() + " : ");
			long duree = mesurer(implem, "graphes", "reponses");
			System.out.println(duree/1000000 + " millisecondes" );
		}
	}


	
	public static long mesurer(IGraphe g, String graphesRep,  String reponsesRep) {
		long dureeTotale = 0, debut, fin;
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

                try {
					g = g.getClass().getDeclaredConstructor().newInstance();
				} catch (Exception e) {
					System.out.println("Impossible de reset le graphe");
				} 
                Arc arc = GraphImporter.importer(file1.toFile(), g);

                List<Integer> listeEntiers = new ArrayList<>();
                int distance = GraphImporter.importerReponse(file2.toString(), listeEntiers);

//                System.out.println("\ngraphe: " + file1.getFileName());
//                System.out.println("chemin a trouver " + arc.getSource()+ " "+ arc.getDestination());
//                System.out.println("Graphe de  " + g.getSommets().size() + " sommets");

//                System.out.println("reponse: " + file2.getFileName());
//                if (distance >=0) {
//                	System.out.println("distance attendue : " + distance);
//                	System.out.println("chemin possible : " + listeEntiers);
//                } else System.out.println("Aucun chemin attendu");
                
                Map<String, Integer> dist = new HashMap<>();
                Map<String, String> prev = new HashMap<>();
                debut = System.nanoTime();
                Dijkstra.dijkstra(g, arc.getSource(), dist, prev);
                fin = System.nanoTime();
                dureeTotale += (fin - debut);
//                System.out.println("dijkstra a dure " + (fin - debut)/1000000 + " millisecondes");
                
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

//                if (dist.containsKey(arc.getDestination())) {
//                	System.out.println("chemin trouve : <" + String.join(", ", path)+">");
//                	System.out.println("Distance: " + dist.get(arc.getDestination()));
//                } else System.out.println("Aucun chemin trouve");
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de l'acces aux dossiers: " + e.getMessage());
        }
        return dureeTotale;
    }
	

}
