package graph.test;

import graph.Graphe;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestDuGraphe {
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
        System.out.print("Liste sommets: " + graphe.getNodes());


        // exceptions pour sommets ou arcs inexistants
        assertThrows(IllegalArgumentException.class, () -> graphe.getVoisins("Z"));
        assertThrows(IllegalArgumentException.class, () -> graphe.poids("A", "Z"));
        assertThrows(IllegalArgumentException.class, () -> graphe.isConnected("A", "Z"));
        assertThrows(IllegalArgumentException.class, () -> graphe.getSucc("Z"));
        String representation = graphe.toString();

        assertTrue(representation.contains("A-B(2)"));
        assertTrue(representation.contains("A-C(5)"));
    }

    @Test
    void test2() {
        // ajout automatique des sommets par ajouterArc avec un deuxieme graphe
        Graphe g2 = new Graphe();
        g2.ajouterArc("X", "Y", 10); // X et Y pas encore ajoutés
        assertTrue(g2.isConnected("X", "Y"));
        assertEquals(10, g2.poids("X", "Y"));

        // double ajout d'arc = exception
        assertThrows(IllegalArgumentException.class, () -> g2.ajouterArc("X", "Y", 10));
        String representation2 = g2.toString();
        assertTrue(representation2.contains("X-Y(10)"));
        assertFalse(representation2.contains("A-B(12)"));
    }

}
