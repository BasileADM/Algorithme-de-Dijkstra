package graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class GrapheHHAdj implements VarGraph {

    private final HashMap<String, HashMap<String, Integer>> graph = new HashMap<>();


    @Override
    public Collection<String> getNodes() {
        return graph.keySet();
    }

    @Override
    public List<Arc<String>> getSucc(String s) {
        if (!graph.containsKey(s)){ // si il n'existe pas, EXCEPTION
            throw new IllegalArgumentException("Sommet absent");
        }

        // Récupérer les voisins du sommet 's'
        HashMap<String, Integer> voisins = graph.get(s); // voisin = 'b'

        // Créer la liste des arcs (les voisins avec leur poids)
        List<Arc<String>> successeurs = new ArrayList<>();

        for (String suc : voisins.keySet()) { // parcours toute les clé donc les sommets
            Integer poids = voisins.get(suc); // on met le integer de la haspmap (le poids du coup) dans une variable afin de pouvoir la réutiliser
            successeurs.add(new Arc<>(poids, suc));  // Créer un Arc avec le poids et la destination
        }
        return successeurs;
    }

    @Override
    public void ajouterSommet(String noeud) {
        graph.putIfAbsent(noeud, new HashMap<>());
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        ajouterSommet(source);      // ajoute s'ils n'existent pas
        ajouterSommet(destination);

        if (graph.get(source).containsKey(destination)) {
            throw new IllegalArgumentException("Arc déjà existant");
        }
        graph.get(source).put(destination, valeur);
    }

}
