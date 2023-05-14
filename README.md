
# SAE 2.02 : Graphes Orientés Valués

Manipulation de graphes. Projet de 4 élèves du groupe 103.  
Chargé de TP: [Camille KURTZ](https://github.com/ckurtz)  
Le SDK utilisé est OpenJDK 19. Notre projet est donc écrit pour fonctionner avec Java 19, et pourrait donc ne pas fonctionner sur d'autres versions de Java  

## Auteurs

- [Eva G.](https://github.com/orakless)
- [Rémi L.](https://github.com/remi-lem)
- [Ali K.](https://github.com/Tacoao)
- [Mya F.](https://github.com/G4iaa04)

## Synthèse du travail

### Partie 1
Nous nous sommes conjointement répartis les différents types de graphe à implémenter.
- **Ali** se charge des graphes représentés par une table de hachage ([GrapheHHAdj.java](srcOLD/graphe/GrapheHHAdj.java))
- **Rémi** se charge des graphes représentés par une liste d'adjacence ([GrapheLAdj.java](srcOLD/graphe/GrapheLAdj.java))
- **Eva** se charge des graphes représentés par une matrice d'adjacence ([GrapheMAdj.java](srcOLD/graphe/GrapheMAdj.java))
- **Mya** se charge des graphes représentés par une liste d'arcs ([GrapheLArc.java](srcOLD/graphe/GrapheLArcs.java))


## Crédits
- **Keith Schwartz** pour l'[implémentation du tas de Fibonacci](https://keithschwarz.com/interesting/code/?dir=fibonacci-heap). Son code a été modifié dans le projet (remplacement des `double` par des `int`)