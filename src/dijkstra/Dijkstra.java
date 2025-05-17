package dijkstra;

import graph.Graph;
import graph.GrapheHHAdj;
import graph.ShortestPath;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Dijkstra<T> implements ShortestPath<T> {

    @Override
    public Distances<T> compute(Graph<T> graphe, T source, Animator<T> animateur) throws IllegalArgumentException {
        // Contient les distances minimales connues depuis la source jusqu'à chaque sommet
        HashMap<T, Integer> distances = new HashMap<>();
        
        // Contient le prédécesseur de chaque sommet dans le plus court chemin trouvé
        HashMap<T, T> predecesseurs = new HashMap<>();
        
        // Marque les sommets déjà traités
        HashMap<T, Boolean> visites = new HashMap<>();

        // La distance à la source elle-même est 0
        distances.put(source, 0);
        predecesseurs.put(source, null);

        // File de priorité pour sélectionner le sommet avec la plus petite distance connue
        PriorityQueue<T> filePriorite = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        filePriorite.add(source);

        // Traitement de tous les sommets accessibles depuis la source
        while (!filePriorite.isEmpty()) {
            T courant = filePriorite.poll();

            // Si le sommet a déjà été visité, on le saute
            if (visites.getOrDefault(courant, false)) {
                continue;
            }
            visites.put(courant, true);

            // Animation (visualisation) : on indique que la distance pour ce sommet est désormais définitive
            animateur.accept(courant, distances.get(courant));

            // On parcourt tous les arcs sortants du sommet courant
            for (GrapheHHAdj.Arc<T> arc : graphe.getSucc(courant)) {
                T voisin = arc.dst();       // Sommet voisin
                int poids = arc.val();      // Poids de l'arc courant -> voisin

                // Si l'algorithme rencontre un poids négatif, on lève une exception
                if (poids < 0) {
                    throw new IllegalArgumentException("Arc de poids négatif détecté : " + courant + " -> " + voisin);
                }

                // Calcul de la nouvelle distance potentielle
                int nouvelleDistance = distances.get(courant) + poids;

                // Si la nouvelle distance est meilleure, on met à jour
                if (nouvelleDistance < distances.getOrDefault(voisin, Integer.MAX_VALUE)) {
                    distances.put(voisin, nouvelleDistance);   // mise à jour de la distance
                    predecesseurs.put(voisin, courant);        // mise à jour du prédécesseur
                    filePriorite.add(voisin);                 // ajout dans la file pour traitement futur
                }
            }
        }

        // On retourne l’objet contenant les distances finales et les prédécesseurs pour reconstruire les chemins
        return new ShortestPath.Distances<>(distances, predecesseurs);
    }
}
