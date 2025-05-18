package adaptator;

import graph.Graph;
import maze.Maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @brief Adaptateur qui transforme un labyrinthe (Maze) en graphe orienté pondéré utilisable avec les algorithmes de graphes.
 * 
 * Cette classe permet de représenter un labyrinthe comme un graphe en considérant chaque case comme un sommet
 * et chaque passage ouvert comme un arc de poids fixe (1).
 *
 * @param <T> Le type des sommets (souvent des coordonnées ou des identifiants de cellules).
 */
public class GraphMaze<T> implements Graph<T> {

    private Maze<T> maze;

    /**
     * @brief Constructeur de l'adaptateur à partir d'un labyrinthe.
     * @param maze Le labyrinthe à adapter sous forme de graphe.
     */
    public GraphMaze(Maze<T> maze) {
        this.maze = maze;
    }

    /**
     * @brief Renvoie la liste des successeurs accessibles depuis un sommet donné dans le labyrinthe.
     * Chaque passage ouvert correspond à un arc de poids 1.
     *
     * @param s Le sommet source.
     * @return Une liste d’arcs sortants depuis le sommet, chacun de poids 1.
     */
    @Override
    public List<Arc<T>> getSucc(T s) {
        Set<T> voisins = maze.openedNeighbours(s);
        List<Arc<T>> succs = new ArrayList<>();
        for (T v : voisins) {
            succs.add(new Arc<>(1, v)); // chaque arc a un poids de 1
        }
        return succs;
    }
}
