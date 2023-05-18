
# SAE 2.02 : Graphes Orientés Valués

Manipulation de graphes. Projet de 4 élèves du groupe 103.  
Chargé de TP : [Camille KURTZ](https://github.com/ckurtz)  
Le SDK utilisé est OpenJDK 19. Notre projet est donc écrit pour fonctionner avec Java 19, et pourrait donc ne pas fonctionner sur d'autres versions de Java  

## Auteurs

- [Eva G.](https://github.com/orakless)
- [Rémi L.](https://github.com/remi-lem)
- [Ali K.](https://github.com/Tacoao)
- [Mya F.](https://github.com/G4iaa04)

## Synthèse du travail

### Partie 1 : Représentation des graphes orientés valués
Nous nous sommes conjointement répartis les différents types de graphe à implémenter.
- **Ali** se charge des graphes représentés par une table de hachage ([GrapheHHAdj.java](src/graphe/implems/GrapheHHAdj.java)).
- **Rémi** se charge des graphes représentés par une liste d'adjacence ([GrapheLAdj.java](src/graphe/implems/GrapheLAdj.java)).
- **Eva** se charge des graphes représentés par une matrice d'adjacence ([GrapheMAdj.java](src/graphe/implems/GrapheMAdj.java)).
- **Mya** se charge des graphes représentés par une liste d'arcs ([GrapheLArcs.java](src/graphe/implems/GrapheLArcs.java)).

Les 4 classes ont été codées et fonctionnent.

### Partie 2 : Algorithme du plus court chemin de Dijkstra
Notre implémentation de l'algorithme de Dijkstra passe les tests jusqu'à différents niveaux en fonction des types de graphes :
- GrapheHHAdj : Tous les graphes passent les tests.
- GrapheLAdj : jusqu'à XX.
- GrapheLArcs : jusqu'à XX.
- GrapheMAdj : jusqu'à XX.

## Tests unitaires
Nous avons programmé différents tests unitaires, ils sont disponibles dans le paquetage [testspersos](src/testspersos).

## Tests de performance
Les résulats des tests peuvent etre trouvés sur le fichier [perfs.md](perfs.md).

## Crédits
- **Keith Schwartz** pour l'[implémentation du tas de Fibonacci](https://keithschwarz.com/interesting/code/?dir=fibonacci-heap). Son code a été modifié dans le projet (remplacement des `double` par des `int`)
