package mk.ukim.finki.a9;

import java.util.List;

public class KruskalGraphTest {
    public static void main(String[] args) {
        int maximumVertices = 9;
        Integer[] list = new Integer[maximumVertices];
        for (int i = 0; i < maximumVertices; i++)
            list[i] = i;

        GraphWeightedDirected<Integer> g = new GraphWeightedDirected<>(maximumVertices, list);
        // adding edges
        g.addEdge(0, 1, 4);
        g.addEdge(0, 7, 8);
        g.addEdge(1, 7, 11);
        g.addEdge(1, 2, 8);
        g.addEdge(2, 3, 7);
        g.addEdge(2, 5, 4);
        g.addEdge(2, 8, 2);
        g.addEdge(3, 4, 9);
        g.addEdge(3, 5, 14);
        g.addEdge(4, 5, 10);
        g.addEdge(5, 6, 2);
        g.addEdge(6, 7, 1);
        g.addEdge(6, 8, 6);
        g.addEdge(7, 8, 7);

        List<Edge> mst = g.kruskal();
        System.out.println("Minimum Spanning Tree rebra dobieni so Kruskal se:");
        for (Edge e : mst)
            System.out.println("Teme: " + e.getFromVertex() + " --> teme: " + e.getToVertex());
    }
}
