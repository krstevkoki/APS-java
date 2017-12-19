package mk.ukim.finki.lab10;

import mk.ukim.finki.a9.GraphWeightedDirected;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cities {
    public static void main(String[] args) throws IOException {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(input.readLine());
            GraphWeightedDirected<String> graphWeightedDirected = new GraphWeightedDirected<>(n);
            n = Integer.parseInt(input.readLine());
            for (int i = 0; i < n; ++i) {
                String[] parts = input.readLine().split("\\s+");
                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[2]);
                float weight = Float.parseFloat(parts[4]);
                graphWeightedDirected.setInfo(x, parts[1]);
                graphWeightedDirected.setInfo(y, parts[3]);
                graphWeightedDirected.addEdge(x, y, weight);
            }
            int from = graphWeightedDirected.getIndex(input.readLine());
            int to = graphWeightedDirected.getIndex(input.readLine());
            int[] pateka = new int[graphWeightedDirected.getNumNodes()];
            System.out.printf("%.1f", graphWeightedDirected.minPateka(from, to, pateka));
        }
    }
}
