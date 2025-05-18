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
        HashMap<T, Boolean> visited = new HashMap<>();

        distances.put(source, 0);
        predecesseurs.put(source, null);

        PriorityQueue<T> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        queue.add(source);
        while (!queue.isEmpty()) {
            T current = queue.poll();
            if (visited.getOrDefault(current, false))
                continue;
            visited.put(current, true);

            animateur.accept(current, distances.get(current));

            for (GrapheHHAdj.Arc<T> arc : graphe.getSucc(current)) {
                T voisin = arc.dst();
                int poid = arc.val();

                if (poid < 0)
                    throw new IllegalArgumentException("Arc de poids négatif détecté : " + current + " -> " + voisin);

                int nouvelleDistance = distances.get(current) + poid;
                if (nouvelleDistance < distances.getOrDefault(voisin, Integer.MAX_VALUE)) {
                    distances.put(voisin, nouvelleDistance);
                    predecesseurs.put(voisin, current);
                    queue.add(voisin);
                }
            }
        }
        return new ShortestPath.Distances<>(distances, predecesseurs);
    }
}
