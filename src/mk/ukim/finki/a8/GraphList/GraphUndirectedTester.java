package mk.ukim.finki.a8.GraphList;

public class GraphUndirectedTester {
    public static void main(String[] args) {
        // Postavuvanje na bukvite od A do J vo niza
        Character niza[] = new Character[10];
        for (int i = 0; i < 10; i++)
            niza[i] = (char) ('A' + i);

        GraphDirected<Character> g = new GraphDirected<>(10, niza);
        g.addEdge(0, 1);  // dodavam rebro od A do B
        g.addEdge(1, 2);  // dodavam rebro od B do C
        g.addEdge(2, 3);  // dodavam rebro od C do D
        g.addEdge(3, 6);  // dodavam rebro od D do G
        g.addEdge(6, 9);  // dodavam rebro od G do J
        g.addEdge(2, 5);  // dodavam rebro od B do F
        g.addEdge(5, 8);  // dodavam rebro od F do I
        g.addEdge(0, 4);  // dodavam rebro od A do E

        System.out.print(g);
        System.out.println("Depth First Search Recursive:");
        g.DFSSearch(0);
        System.out.println("Depth First Search Nonrecursive:");
        g.DFSSearchNonrecursive(0);
        System.out.println("Breadth First Search:");
        g.BFSSearch(0);
    }
}
