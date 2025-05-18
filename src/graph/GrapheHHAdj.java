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

    public List<String> getAllSommets() {
        return new ArrayList<>(graph.keySet());
    }

    public boolean isGraphEmpty() {
        return graph.isEmpty();
    }

    public boolean isConnected(String source, String destination) {
        if (!graph.containsKey(source)) {
            throw new IllegalArgumentException("Sommet inexistant");
        }
        if (!graph.containsKey(destination)) {
            throw new IllegalArgumentException("Sommet inexistant");
        }
        for (Arc<String> arc : graph.get(source)) {
            if (arc.dst().equals(destination)) {
                return true;
            }
        }
        return false; // Aucun arc trouvé entre source et destination
    }
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

    public List<Arc<String>> getVoisins(String source) {
        if (!graph.containsKey(source)) {
            throw new IllegalArgumentException("Sommet source inexistant" + source);
        }
        return graph.get(source);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String source : graph.keySet()) { // Parcours de toutes les clés (les sommets)
            List<Arc<String>> voisins = graph.get(source); // Liste des successeurs de 'source'
            if (voisins.isEmpty()) { // Si 'source' n'a pas de successeurs
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
