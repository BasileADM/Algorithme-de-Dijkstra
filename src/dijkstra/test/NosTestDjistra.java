package dijkstra.test;

import dijkstra.Dijkstra;
import graph.Graph;
import graph.GrapheHHAdj;
import graph.ShortestPath;
import graph.VarGraph;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NosTestDjistra {

    private static final String GRAPH1 = "S-A(2), S-B(5), A-C(4), B-C(1), C-T(3)";
    private static final String GRAPH_NEG = "S-A(2), S-B(-3), A-C(4), B-C(1), C-T(3)";
    private static final String FROM = "S";
    private static final String TO = "T";
    private static final int EXPECTED_DIST = 9;
    private static final List<String> EXPECTED_PATH = List.of("T", "C", "A", "S");
    private static final Dijkstra<String> dijkstra = new Dijkstra<>();

    @Test
    void test() {
        VarGraph g = new GrapheHHAdj();
        g.peupler(GRAPH1);
        tester(g);
    }

    void tester(Graph<String> g) {
        ShortestPath.Distances<String> dst = dijkstra.compute(g, FROM);
        assertEquals(EXPECTED_DIST, dst.dist().get(TO));
        String c = EXPECTED_PATH.getFirst();
        for (String s : EXPECTED_PATH) {
            assertEquals(s, c);
            c = dst.pred().get(c);
        }
        assertNull(c);
    }

    @Test
    void pasDeValuationNegative() {
        VarGraph g = new GrapheHHAdj();
        g.peupler(GRAPH_NEG);
        assertThrows(IllegalArgumentException.class,
                ()->  dijkstra.compute(g, FROM));
    }

    @Test
    void utilisationDuResultat() {
        VarGraph g = new GrapheHHAdj();
        g.peupler(GRAPH1);
        ShortestPath.Distances<String> dst = dijkstra.compute(g, FROM);
        System.out.println("Graphe : " + g);
        System.out.println("Distances de A : " + dst.dist());
        System.out.println("Predecesseurs : " + dst.pred());
        System.out.println("Distance de " + FROM + " à " + TO + " : " + dst.dist().get(TO));
        System.out.print("Chemin de " + FROM + " à " + TO + " : ");
        String sommet = TO;
        Deque<String> pile = new ArrayDeque<>();
        while (sommet != null) {
            pile.push(sommet);
            sommet = dst.pred().get(sommet);
        }
        while(!pile.isEmpty()) {
            System.out.print(pile.pop() + " ");
        }
        System.out.println();
        System.out.println();
    }

    // ========================
    // NOUVEAU GRAPHE : GRAPH2
    // ========================
    private static final String GRAPH2 = "X-Y(2), Y-Z(2), Z-W(1), X-W(10)";
    private static final String GRAPH2_NEG = "X-Y(2), Y-Z(-2), Z-W(1), X-W(10)";
    private static final String FROM2 = "X";
    private static final String TO2 = "W";
    private static final int EXPECTED_DIST2 = 5;
    private static final List<String> EXPECTED_PATH2 = List.of("W", "Z", "Y", "X");

    @Test
    void testAutreGraphe() {
        VarGraph g2 = new GrapheHHAdj();
        g2.peupler(GRAPH2);
        tester2(g2);
    }

    void tester2(Graph<String> g) {
        ShortestPath.Distances<String> dst = dijkstra.compute(g, FROM2);
        assertEquals(EXPECTED_DIST2, dst.dist().get(TO2));
        String c = EXPECTED_PATH2.getFirst();
        for (String s : EXPECTED_PATH2) {
            assertEquals(s, c);
            c = dst.pred().get(c);
        }
        assertNull(c);
    }

    @Test
    void pasDeValuationNegativeAutreGraphe() {
        VarGraph g = new GrapheHHAdj();
        g.peupler(GRAPH2_NEG);
        assertThrows(IllegalArgumentException.class,
                ()-> dijkstra.compute(g, FROM2));
    }

    @Test
    void utilisationDuResultat2() {
        VarGraph g2 = new GrapheHHAdj();
        g2.peupler(GRAPH2);
        ShortestPath.Distances<String> dst = dijkstra.compute(g2, FROM2);
        System.out.println("Graphe : " + g2);
        System.out.println("Distances de X : " + dst.dist());
        System.out.println("Predecesseurs : " + dst.pred());
        System.out.println("Distance de " + FROM2 + " à " + TO2 + " : " + dst.dist().get(TO2));
        System.out.print("Chemin de " + FROM2 + " à " + TO2 + " : ");
        String sommet = TO2;
        Deque<String> pile = new ArrayDeque<>();
        while (sommet != null) {
            pile.push(sommet);
            sommet = dst.pred().get(sommet);
        }
        while(!pile.isEmpty()) {
            System.out.print(pile.pop() + " ");
        }
        System.out.println();
    }
}
