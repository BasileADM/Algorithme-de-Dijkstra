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
		HashMap<T, Integer> distances = new HashMap<>();
		HashMap<T, T> predecesseurs = new HashMap<>();
		HashMap<T, Boolean> visites = new HashMap<>();

		distances.put(source, 0);
		predecesseurs.put(source, null);

		PriorityQueue<T> filePriorite = new PriorityQueue<>(Comparator.comparingInt(distances::get));
		filePriorite.add(source);

		while (!filePriorite.isEmpty()) {
			T courant = filePriorite.poll();
			if (visites.getOrDefault(courant, false)) {
				continue;
			}
			visites.put(courant, true);

			// Animation : une distance est définitivement connue
			animateur.accept(courant, distances.get(courant));

			for (GrapheHHAdj.Arc<T> arc : graphe.getSucc(courant)) {
				T voisin = arc.dst();
				int poids = arc.val();

				if (poids < 0) {
					throw new IllegalArgumentException("Arc de poids négatif détecté : " + courant + " -> " + voisin);
				}

				int nouvelleDistance = distances.get(courant) + poids;

				if (nouvelleDistance < distances.getOrDefault(voisin, Integer.MAX_VALUE)) {
					distances.put(voisin, nouvelleDistance);
					predecesseurs.put(voisin, courant);
					filePriorite.add(voisin);
				}
			}
		}

		return new ShortestPath.Distances<>(distances, predecesseurs);
	}
}
