package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graphe implements VarGraph {

    private final HashMap<String, HashMap<String, Integer>> graph = new HashMap<>();

    @Override
    public void ajouterSommet(String noeud) {
        graph.putIfAbsent(noeud, new HashMap<>()); // ici, on ajoute le sommet "noeud" dans une liste "Hachable" avec un
                                                   // comme valeur une autre hashmap(qui va correspondre a la list des ses voisins)
                                                    // putIfAbsent fais en sorte de ne pas ecraser une valeur c'est a dire que si j'ajoute 2 fois "A", le deuxieme
                                                    // ne va pas etre ajouté
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        if (!graph.containsKey(source) || !graph.containsKey(destination)) {
            throw new IllegalArgumentException("Sommet absent");
        }
        if (graph.get(source).containsKey(destination)) {
            throw new IllegalArgumentException("Arc déjà existant");
        }
        graph.get(source).put(destination, valeur);
    }

    @Override
    public List<Arc<String>> getSucc(String s) {

        if (!graph.containsKey(s)){
            throw new IllegalArgumentException("Sommet absent");
        }

        // Récupérer les voisins du sommet 's'
        HashMap<String, Integer> voisins = graph.get(s);

        // Créer la liste des arcs (les voisins avec leur poids)
        List<Arc<String>> successeurs = new ArrayList<>();

        for (String successeur : voisins.keySet()) {
            Integer poids = voisins.get(successeur);
            successeurs.add(new Arc<>(poids, successeur));  // Créer un Arc avec le poids et la destination
        }
        return successeurs;
    }

    public boolean isEmpty() {
        return graph.isEmpty();
    }

    public HashMap<String, Integer> getVoisins(String s) {
        return graph.get(s);
    }


    public boolean isConnected(String source, String destination) {
        if (!graph.containsKey(source) || !graph.containsKey(destination)) {
            throw new IllegalArgumentException("Sommet absent");
        }
        return graph.get(source).containsKey(destination);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (String s : graph.keySet()) {
            sb.append(s).append("------> ").append(graph.get(s)).append("\n");
        }
        return sb.toString();
    }
}
