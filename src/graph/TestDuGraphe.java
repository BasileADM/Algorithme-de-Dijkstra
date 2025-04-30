package graph;

public class TestDuGraphe {
    public static void main(String[] args) {
        Graphe graphe = new Graphe();

        graphe.ajouterSommet("A");
        graphe.ajouterSommet("B");
        graphe.ajouterSommet("C");

        graphe.ajouterArc("A", "B", 2);
        graphe.ajouterArc("A", "C", 5);
        graphe.ajouterArc("B", "C", 1);

        System.out.println("Contenu du graphe :");
        System.out.println(graphe);
    }
}
