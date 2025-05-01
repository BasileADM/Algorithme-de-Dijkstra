package graph;

import static org.junit.jupiter.api.Assertions.*;

public class TestDuGraphe {
    public static void main(String[] args) {
        Graphe graphe = new Graphe();

        assertTrue(graphe.isEmpty());

        graphe.ajouterSommet("A");
        graphe.ajouterSommet("B");
        graphe.ajouterSommet("C");

        assertFalse(graphe.isEmpty());

        assertTrue(graphe.getVoisins("A").isEmpty());
        assertTrue(graphe.getVoisins("B").isEmpty());
        assertTrue(graphe.getVoisins("C").isEmpty());

        graphe.ajouterArc("A", "B", 2);
        graphe.ajouterArc("A", "C", 5);

        assertTrue(graphe.isConnected("A", "B"));
        assertFalse(graphe.isConnected("B", "A"));


        System.out.print("Graphe :");
        System.out.println(graphe);
    }
}
