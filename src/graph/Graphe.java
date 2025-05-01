package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graphe implements VarGraph {

    private final HashMap<String, HashMap<String, Integer>> graph = new HashMap<>(); // final pour eviter de la reafecter a un autre objet (Idea by InteliJ)
    //une hashMap dans une autre : "String" correspond a la clé (donc un sommet) et "HashMap<String, Integer>" correspond a la list des ses voisins "String"

    @Override
    public void ajouterSommet(String noeud) {
        graph.putIfAbsent(noeud, new HashMap<>());
                                                    // putIfAbsent fais en sorte de ne pas ecraser une valeur c'est a dire que si j'ajoute 2 fois "A", le deuxieme
                                                    // ne va pas etre ajouté
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        if (!graph.containsKey(source) || !graph.containsKey(destination)) { // si la source et la destination n'existe pas, EXCEPTION
            throw new IllegalArgumentException("Sommet absent");
        }
        if (graph.get(source).containsKey(destination)) { // si la source, a deja cette destination, EXCEPTION pour eviter d'écraser
            throw new IllegalArgumentException("Arc déjà existant");
        }
        graph.get(source).put(destination, valeur); // on ajoute a la valeur de source (hasmMap des voisins) sa destination et son poids
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


    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String source : graph.keySet()) { // parcours toute les clé de notre graph
            HashMap<String, Integer> voisins = graph.get(source); // mets dans voisin, la liste des successeurs
            if (voisins.isEmpty()) { // si "source" n'a pas de succésseur, on affiche rien apres -->
                sb.append(source).append(": ");
            } else {
                for (String dest : voisins.keySet()) { // parcours les clé de la hashmap qui correspond au voisins de "source"
                    int poids = voisins.get(dest); // on stock pour chaque succ, son poids,
                    sb.append(source).append("-").append(dest) // syntaxe perso
                            .append("(").append(poids).append("), ");
                }
            }
        }
        return sb.toString();
    }

}
