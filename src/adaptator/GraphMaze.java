package adaptator;

import graph.Graph;
import maze.regular.RegularMaze;

import java.util.Collection;
import java.util.List;

public class GraphMaze implements Graph<Integer> {

    RegularMaze maze;

    public GraphMaze(RegularMaze maze) {
        this.maze = maze;
    }

    @Override
    public Collection<Integer> getNodes() {
        return List.of();
    }

    @Override
    public List<Arc<Integer>> getSucc(Integer s) {
        return List.of();
    }
}
