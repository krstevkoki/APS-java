package mk.ukim.finki.lab10;

import mk.ukim.finki.a9.Edge;
import mk.ukim.finki.a9.GraphWeightedDirected;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Hierarchy {
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
                graphWeightedDirected.addEdge(y, x, weight);
            }
            String president = input.readLine();
            int president_index = graphWeightedDirected.getIndex(president);
            if (president_index != -1) {
                float sum = 0f;
                List<Edge> mstEdges = graphWeightedDirected.prim(president_index);
                for (Edge mstEdge : mstEdges)
                    sum += mstEdge.getWeight();
                System.out.println(String.format("%.0f", sum));
            } else
                System.err.println("Clenot ne postoi");
        }
    }
}
