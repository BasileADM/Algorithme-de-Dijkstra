package graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestDuGraphe {
    @Test
    void test() {
        Graphe graphe = new Graphe();

        assertTrue(graphe.isEmpty());

        graphe.ajouterSommet("A");
        graphe.ajouterSommet("B");
        graphe.ajouterSommet("C");
        graphe.ajouterSommet("D");

        assertFalse(graphe.isEmpty());
        assertTrue(graphe.getVoisins("A").isEmpty());
        assertTrue(graphe.getVoisins("B").isEmpty());
        assertTrue(graphe.getVoisins("C").isEmpty());

        graphe.ajouterArc("A", "B", 2);
        graphe.ajouterArc("A", "C", 5);


        assertTrue(graphe.isConnected("A", "B"));
        assertTrue(graphe.isConnected("A", "C"));
        assertFalse(graphe.isConnected("B", "A"));
        assertEquals(2, graphe.poids("A", "B"));
        assertEquals(5, graphe.poids("A", "C"));
        //assertTrue(graphe.isConnected("A", "Z")); // exception destination absent

        System.out.print("Graphe : ");
        System.out.println(graphe);
    }
}
