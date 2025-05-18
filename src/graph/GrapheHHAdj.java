package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GrapheHHAdj implements VarGraph {

    private final HashMap<String, List<Arc<String>>> graph = new HashMap<>();

    @Override
    public List<Arc<String>> getSucc(String s) {
        if (!graph.containsKey(s)) {
            throw new IllegalArgumentException("Sommet inexistant");
        }
        return graph.get(s);
    }

    @Override
    public void ajouterSommet(String noeud) {
        graph.putIfAbsent(noeud, new ArrayList<>());
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        ajouterSommet(source);
        ajouterSommet(destination);

        List<Arc<String>> list = graph.get(source);
        for (Arc<String> arc : list) {
            if (arc.dst().equals(destination)) {
                throw new IllegalArgumentException("arc déjà existant");
            }
        }
        list.add(new Arc<>(valeur, destination));
    }

    /**
     * @brief Retourne tous les sommets présents dans le graphe
     * @return Une liste contenant les identifiants de tous les sommets
     */
    public List<String> getAllSommets() {
        return new ArrayList<>(graph.keySet());
    }

    /**
     * @brief Vérifie si le graphe est vide
     * @return true si le graphe ne contient aucun sommet, false sinon
     */
    public boolean isGraphEmpty() {
        return graph.isEmpty();
    }

    /**
     * @brief Vérifie si un arc existe entre deux sommets
     * @param source Le sommet de départ
     * @param destination Le sommet d'arrivée
     * @return true si un arc existe entre source et destination, false sinon
     * @throws IllegalArgumentException si l'un des sommets n'existe pas dans le graphe
     */
    public boolean isConnected(String source, String destination) {
        if (!graph.containsKey(source)) {
            throw new IllegalArgumentException("Sommet inexistant");
        }
        if (!graph.containsKey(destination)) {
            throw new IllegalArgumentException("Sommet destination inexistant");
        }
        for (Arc<String> arc : graph.get(source)) {
            if (arc.dst().equals(destination)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @brief Récupère le poids (valeur) de l'arc entre deux sommets
     * @param source Le sommet source
     * @param destination Le sommet destination
     * @return La valeur (poids) de l'arc entre source et destination
     * @throws IllegalArgumentException si l'arc n'existe pas ou si le sommet source est absent
     */
    public int getPoids(String source, String destination) {
        if (!graph.containsKey(source)) {
            throw new IllegalArgumentException("Sommet source inexistant" + source);
        }
        for (Arc<String> arc : graph.get(source)) {
            if (arc.dst().equals(destination)) {
                return arc.val();
            }
        }
        throw new IllegalArgumentException("aucun arc entre " + source + " et " + destination);
    }

    /**
     * @brief Récupère la liste des voisins (successeurs) d'un sommet donné
     * @param source Le sommet source
     * @return Une liste d'arcs représentant les voisins de ce sommet
     * @throws IllegalArgumentException si le sommet n'existe pas
     */
    public List<Arc<String>> getVoisins(String source) {
        if (!graph.containsKey(source)) {
            throw new IllegalArgumentException("Sommet source inexistant" + source);
        }
        return graph.get(source);
    }

    /**
     * @brief Fournit une représentation textuelle du graphe.
     * @return Une chaîne de caractères représentant tous les arcs du graphe.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String source : graph.keySet()) {
            List<Arc<String>> voisins = graph.get(source);
            if (voisins.isEmpty()) {
                sb.append(source).append(":, ");
            } else {
                for (Arc<String> arc : voisins) {
                    sb.append(source).append("-").append(arc.dst()).append("(").append(arc.val()).append("), ");
                }
            }
        }
        return sb.toString();
    }
}
