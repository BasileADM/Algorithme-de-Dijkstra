package dijkstra;

import graph.Graph;
import graph.GrapheHHAdj;
import graph.ShortestPath;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.lang.String;

public class Dijkstra<T> implements ShortestPath<T> {

	@Override
	public Distances<T> compute(Graph<T> g, T src, Animator<T> animator) throws IllegalArgumentException {
		return compute(g, src);
	}

	public ShortestPath.Distances<T> compute(Graph<T> g, T from) {
		HashMap<T, Integer> distances = new HashMap<>();
		HashMap<T, T> predecessors = new HashMap<>();
		HashMap<T, Boolean> visited = new HashMap<>();

		distances.put(from, 0);
		predecessors.put(from, null);

		PriorityQueue<T> pq = new PriorityQueue<>(Comparator.comparingInt(distances::get));
		pq.add(from);

		while (!pq.isEmpty()) {
			T current = pq.poll();
			if (visited.containsKey(current) && visited.get(current)) {
				continue; // si le sommet a déjà été visité, on l'ignore
			}
			visited.put(current, true);

			for (GrapheHHAdj.Arc<T> arc : g.getSucc(current)) {
				T voisin = arc.dst();
				int poids = arc.val();

				// vérification du poids négatif
				if (poids < 0) {
					throw new IllegalArgumentException("Arc de poids négatif détecté : " + current + " -> " + voisin);
				}

				int newDistance = distances.get(current) + poids;

				if (newDistance < distances.getOrDefault(voisin, Integer.MAX_VALUE)) {
					distances.put(voisin, newDistance);
					predecessors.put(voisin, current);
					pq.add(voisin);
				}
			}
		}

		return new ShortestPath.Distances<>(distances, predecessors);
	}
}
