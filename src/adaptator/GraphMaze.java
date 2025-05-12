package adaptator;

import graph.Graph;
import maze.Maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GraphMaze<T> implements Graph<T> {

    private final Maze<T> maze;

    public GraphMaze(Maze<T> maze) {
        this.maze = maze;
    }


    @Override
    public List<Arc<T>> getSucc(T s) {
        Set<T> voisins = maze.openedNeighbours(s);
        List<Arc<T>> arcs = new ArrayList<>();
        for (T voisin : voisins)
            arcs.add(new Arc<>(1, voisin)); // Chaque arc a un poids de 1 (si pas spécifié)
        return arcs;
    }
}
