package dijkstra;

import graph.Graph;
import graph.ShortestPath;

import java.util.*;

public class Dijkstra<S> {

    public ShortestPath.Distances<S> compute(Graph<S> g, S from) {
        // Vérifie tous les arcs pour détecter les poids négatifs avant de démarrer
        for (S node : g.getNodes()) {
            for (Graph.Arc<S> arc : g.getSucc(node)) {
                if (arc.val() < 0) {
                    throw new IllegalArgumentException("Arc de poids négatif détecté : " + node + " -> " + arc.dst());
                }
            }
        }

        HashMap<S, Integer> distances = new HashMap<>(); // afin de stocker la distance la plus courte entre "from" et chaque sommets
        HashMap<S, S> predecessors = new HashMap<>(); // afin de stocker chaque sommet avec son predecesseur le plus court

        // Initialisation : toutes les distances à l'infini sauf le départ
        for (S node : g.getNodes()) {
            distances.put(node, Integer.MAX_VALUE);
            predecessors.put(node, null);
        }
        distances.put(from, 0); // le premier sommet n'a pas de predecceseur donc 0

        PriorityQueue<S> pq = new PriorityQueue<>(Comparator.comparingInt(distances::get)); // tres bonne fonctionnalité que l'ia m'a proposé elle est très efficasse
        pq.add(from); // ajoute une queu auquel il va faire dans l'ordre alpha ex : A(4), B(1), C(2) B commencera puis C puis A

        while (!pq.isEmpty()) { // tant que nous somme dans le graphe
            S current = pq.poll(); // prendre le sommet avec la plus courte distance
            for (Graph.Arc<S> arc : g.getSucc(current)) { // stock dans arc la list des successeur
                S voisin = arc.dst(); // le nom du voisin
                int poids = arc.val(); // avec son poids
                int newDistance = distances.get(current) + poids; // Calcule la distance depuis le courant

                if (newDistance < distances.get(voisin)) { // si la distance est inférieur a un autre chemin, c'est le nouveau plus petit chemin
                    distances.put(voisin, newDistance); // voisin + sa distance la plus courte
                    predecessors.put(voisin, current); // voisin + son predecesseur qui a la distance la plus courte
                    pq.add(voisin); // on ajoute a la queu
                }
            }
        }

        return new ShortestPath.Distances<>(distances, predecessors); // on retourne nos deux hashMap afin de pouvoir afficher chaque sommet et la distance la plus courte de A a ce sommet
        // et chaque sommet avec son predecesseur le plus court
    }
}
