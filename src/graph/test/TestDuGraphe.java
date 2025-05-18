package graph.test;

import graph.GrapheHHAdj;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestDuGraphe {

    /**
     * @brief Test complet du graphe avec ajout de sommets, d’arcs, 
     * vérification des connexions, du poids, des exceptions et de la représentation textuelle.
     */
    @Test
    void test() {
        GrapheHHAdj graphe = new GrapheHHAdj();

        assertTrue(graphe.isGraphEmpty());

        graphe.ajouterSommet("A");
        graphe.ajouterSommet("B");
        graphe.ajouterSommet("C");
        graphe.ajouterSommet("D");

        assertFalse(graphe.isGraphEmpty());
        assertTrue(graphe.getVoisins("A").isEmpty());
        assertTrue(graphe.getVoisins("B").isEmpty());
        assertTrue(graphe.getVoisins("C").isEmpty());

        graphe.ajouterArc("A", "B", 2);
        graphe.ajouterArc("A", "C", 5);

        assertTrue(graphe.isConnected("A", "B"));
        assertTrue(graphe.isConnected("A", "C"));
        assertFalse(graphe.isConnected("B", "A"));
        assertEquals(2, graphe.getPoids("A", "B"));
        assertEquals(5, graphe.getPoids("A", "C"));

        System.out.print("Graphe : ");
        System.out.println(graphe);
        System.out.print("Liste sommets: " + graphe.getAllSommets());

        // exceptions pour sommets ou arcs inexistants
        assertThrows(IllegalArgumentException.class, () -> graphe.getVoisins("Z"));
        assertThrows(IllegalArgumentException.class, () -> graphe.getPoids("A", "Z"));
        assertThrows(IllegalArgumentException.class, () -> graphe.isConnected("A", "Z"));
        assertThrows(IllegalArgumentException.class, () -> graphe.getSucc("Z"));

        String representation = graphe.toString();
        assertTrue(representation.contains("A-B(2)"));
        assertTrue(representation.contains("A-C(5)"));
    }

    /**
     * @brief Test de l’ajout automatique de sommets via ajouterArc 
     * et détection de doublon d’arc avec exception.
     */
    @Test
    void test2() {
        GrapheHHAdj g2 = new GrapheHHAdj();
        g2.ajouterArc("X", "Y", 10); // X et Y pas encore ajoutés automatiquement

        assertTrue(g2.isConnected("X", "Y"));
        assertEquals(10, g2.getPoids("X", "Y"));

        // double ajout d'arc = exception
        assertThrows(IllegalArgumentException.class, () -> g2.ajouterArc("X", "Y", 10));

        String representation2 = g2.toString();
        assertTrue(representation2.contains("X-Y(10)"));
        assertFalse(representation2.contains("A-B(12)"));
    }

}
