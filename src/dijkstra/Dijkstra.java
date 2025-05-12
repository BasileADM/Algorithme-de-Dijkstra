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

	public ShortestPath.Distances<T> compute(Graph<T> g, T from){
		for (String node : g.getAllSommets()){
			for (GrapheHHAdj.Arc<T> arc : g.getSucc((T) node)){
				if (arc.val() < 0){
					throw new IllegalArgumentException("Arc de poids négatif détecté : " + node + " -> " + arc.dst());
				}
			}
		}
		HashMap<T, Integer> distances = new HashMap<>(); // afin de stocker la distance la plus courte entre "from" et chaque sommets
		HashMap<T, T> predecessors = new HashMap<>(); // afin de stocker chaque sommet avec son predecesseur le plus court

		for (String node : g.getAllSommets()){
			distances.put((T) node, Integer.MAX_VALUE);
			predecessors.put((T) node, null);
		}
		distances.put(from, 0);
		predecessors.put(from, null);

		PriorityQueue<T> pq = new PriorityQueue<>(Comparator.comparingInt(distances::get));

		pq.add(from);
		while (!pq.isEmpty()){
			T current = (T) pq.poll();
			for (GrapheHHAdj.Arc<T> arc : g.getSucc(current)){
				T voisin = (T) arc.dst();
				int poids = arc.val();
				int newDistance = distances.get(current) + poids;
				if (newDistance < distances.get(voisin)){
					distances.put(voisin, newDistance); // voisin + sa distance la plus courte
					predecessors.put(voisin, current); // voisin + son predecesseur qui a la distance la plus courte
					pq.add(voisin);
				}

			}
		}
		return new ShortestPath.Distances<>(distances, predecessors);
	}
}
