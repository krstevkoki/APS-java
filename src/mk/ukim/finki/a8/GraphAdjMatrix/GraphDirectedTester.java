package mk.ukim.finki.a8.GraphAdjMatrix;

import java.util.Random;

public class GraphDirectedTester {
    public static void main(String[] args) {
        //Postavuvanje na bukvite od A do J vo niza
        Character niza[] = new Character[10];
        for (int i = 0; i < 10; i++)
            niza[i] = (char) ((int) 'A' + i);

        //Kreiranje na graf koj kako teminja gi sodrzi bukvite od nizata
        GraphDirected<Character> g = new GraphDirected<>(10, niza);

        //Kreiranje na 15 slucajni rebra megju teminjata
        Random r = new Random();
        for (int i = 0; i < 15; i++) {
            int x = r.nextInt(10);
            int y = r.nextInt(10);
            if (x != y) {
                if (g.adjacent(x, y) != 1) {
                    g.addEdge(x, y);
                    System.out.println(i + ": Dodavam nasoceno rebro od " + niza[x] + " do " + niza[y]);
                } else
                    i--;
            } else
                i--;
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
