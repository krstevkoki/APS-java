package mk.ukim.finki.a8.GraphAdjMatrix;

import java.util.Random;

public class GraphUndirectedTester {
    public static void main(String[] args) {
        // Postavuvanje na bukvite od A do J vo niza
        Character chars[] = new Character[10];
        for (int i = 0; i < 10; i++)
            chars[i] = (char) ('A' + i);

        // Kreiranje na graf koj kako teminja gi sodrzi bukvite od nizata
        GraphUndirected<Character> g = new GraphUndirected<>(10, chars);

        // Kreiranje na 15 slucajni rebra megju teminjata
        Random r = new Random();
        for (int i = 1; i <= 15; i++) {
            int x = r.nextInt(10);
            int y = r.nextInt(10);
            if (x != y) {
                if (g.adjacent(x, y) != 1) {
                    g.addEdge(x, y);
                    System.out.println("Dodavam rebro megju " + chars[x] + " i " + chars[y]);
                } else
                    --i;
            } else
                --i;
        }
        System.out.println(g);
        System.out.println("Depth First Search Recursive:");
        g.DFSSearch(0);
        System.out.println("Depth First Search Nonrecursive:");
        g.DFSSearchNonrecursive(0);
        System.out.println("Breadth First Search:");
        g.BFSSearch(0);
    }
}
