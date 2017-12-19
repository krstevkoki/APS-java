package mk.ukim.finki.lab10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.LinkedList;

public class IzborPredmet {
    public static void main(String[] args) throws IOException {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(input.readLine());
            String[] list = new String[n];
            Hashtable<String, Integer> hashtable = new Hashtable<>();
            for (int i = 0; i < n; ++i) {
                list[i] = input.readLine();
                hashtable.put(list[i], i);
            }
            GraphDirected<String> graphDirected = new GraphDirected<>(n, list);
            n = Integer.parseInt(input.readLine());
            for (int i = 0; i < n; ++i) {
                String[] parts = input.readLine().split("\\s+");
                graphDirected.addEdge(hashtable.get(parts[parts.length - 1]), hashtable.get(parts[0]));
            }
            String lastPassed = input.readLine();
            LinkedList<GraphDirected<String>.GraphNode<String>> neighbours = graphDirected.
                    getNeighbours(hashtable.get(lastPassed));
            System.out.println(neighbours.getFirst().getData());
        }
    }
}
