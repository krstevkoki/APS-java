package mk.ukim.finki.a9;

public class TopologicalSortTest {
    public static void main(String[] args) {
        int maximumVertices = 9;
        Integer[] list = new Integer[maximumVertices];
        for (int i = 0; i < maximumVertices; i++)
            list[i] = i;
        GraphNonWeightDirected<Integer> g = new GraphNonWeightDirected<>(maximumVertices, list);

        // adding edges
        g.addEdge(0, 1);
        g.addEdge(0, 7);
        g.addEdge(1, 7);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(2, 5);
        g.addEdge(2, 8);
        g.addEdge(3, 4);
        g.addEdge(3, 5);
        g.addEdge(4, 5);
        g.addEdge(5, 6);
        g.addEdge(6, 7);
        g.addEdge(6, 8);
        g.addEdge(7, 8);

        // topological sort
        g.topological_sort_dfs();

    }
}
