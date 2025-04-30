package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graphe implements VarGraph {

    private HashMap<String, HashMap<String, Integer>> graph = new HashMap<>();

    @Override
    public void ajouterSommet(String noeud) {
        graph.putIfAbsent(noeud, new HashMap<>()); // ici, on ajoute le sommet "noeud" dans une liste "Hachable" avec un
                                                   // comme valeur une autre hashmap(qui va correspondre a la list des ses voisins)
                                                    // putIfAbsent fais en sorte de ne pas ecraser une valeur c'est a dire que si j'ajoute 2 fois "A", le deuxieme
                                                    // ne va pas etre ajouté
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        assert !graph.isEmpty();
        assert graph.containsKey(source);
        assert graph.containsKey(destination);
        graph.get(source).put(destination, valeur);
    }

    @Override
    public List<Arc<String>> getSucc(String s) {
        assert !graph.isEmpty();
        assert graph.containsKey(s);

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

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (String s : graph.keySet()) {
            sb.append(s + "------> " + graph.get(s) + "\n");
        }
        return sb.toString();
    }
}
