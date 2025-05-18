package adaptator;

import graph.Graph;
import maze.Maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GraphMaze<T> implements Graph<T> {

    private Maze<T> maze;
    public GraphMaze(Maze<T> maze) {
        this.maze = maze;
    }

    @Override
    public List<Arc<T>> getSucc(T s) {
        Set<T> voisins = maze.openedNeighbours(s);
        List<Arc<T>> succs = new ArrayList<>();
        for (T v : voisins) {
            succs.add(new Arc<>(1, v));//chaque arc est par default à un poids de 1
        }
        return succs;
    }
}
