package mk.ukim.finki.lab8;

import mk.ukim.finki.a8.GraphList.GraphUndirected;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Lavirint {
    private Hashtable<String, Integer> hash;
    private GraphUndirected<String> graph;
    private int startNodeIndex, endNodeIndex;

    public Lavirint() {
        hash = new Hashtable<>();
        graph = null;
        startNodeIndex = endNodeIndex = -1;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            String[] parts = bf.readLine().split(",");
            int rows = Integer.parseInt(parts[0]);
            int columns = Integer.parseInt(parts[1]);
            String[] mazeInput = new String[rows];
            for (int i = 0; i < rows; ++i)
                mazeInput[i] = bf.readLine();
            Lavirint maze = new Lavirint();
            maze.generateGraph(rows, columns, mazeInput);
            maze.findPath();
        }
    }

    public void generateGraph(int rows, int columns, String[] mazeInput) {
        int numNodes = 0;
        ArrayList<String> tempNodes = new ArrayList<>();
        for (int i = 1; i < rows; ++i) {
            for (int j = 1; j < columns; ++j) {
                if (mazeInput[i].charAt(j) != '#') {
                    String key = i + "," + j;
                    tempNodes.add(key);
                    hash.put(key, numNodes);
                    if (mazeInput[i].charAt(j) == 'S')
                        startNodeIndex = numNodes;
                    if (mazeInput[i].charAt(j) == 'E')
                        endNodeIndex = numNodes;
                    ++numNodes;
                }
            }
        }
        String[] nodes = new String[numNodes];
        graph = new GraphUndirected<>(numNodes, tempNodes.toArray(nodes));
        for (int i = 1; i < rows; ++i) {
            for (int j = 1; j < columns; ++j) {
                if (mazeInput[i].charAt(j) != '#') {
                    int x, y;
                    // ako ima teme levo od tekovnoto
                    if (mazeInput[i].charAt(j - 1) != '#') {
                        x = hash.get(i + "," + j);
                        y = hash.get(i + "," + (j - 1));
                        graph.addEdge(x, y);
                    }
                    // ako ima teme desno do tekovnoto
                    if (mazeInput[i].charAt(j + 1) != '#') {
                        x = hash.get(i + "," + j);
                        y = hash.get(i + "," + (j + 1));
                        graph.addEdge(x, y);
                    }
                    // ako ima teme nad tekovnoto
                    if (mazeInput[i - 1].charAt(j) != '#') {
                        x = hash.get(i + "," + j);
                        y = hash.get((i - 1) + "," + j);
                        graph.addEdge(x, y);
                    }
                    // ako ima teme pod tekovnoto
                    if (mazeInput[i + 1].charAt(j) != '#') {
                        x = hash.get(i + "," + j);
                        y = hash.get((i + 1) + "," + j);
                        graph.addEdge(x, y);
                    }
                }
            }
        }
    }

    public void findPath() {
        boolean[] visited = new boolean[graph.getNumNodes()];
        for (int i = 0; i < visited.length; ++i)
            visited[i] = false;
        visited[startNodeIndex] = true;
        Stack<Integer> stack = new Stack<>();
        stack.push(startNodeIndex);
        while (!stack.isEmpty() && stack.peek() != endNodeIndex) {
            GraphUndirected<String>.GraphNode<String> pom = graph.getAdjList()[stack.peek()];
            GraphUndirected<String>.GraphNode<String> tmp = null;
            for (int i = 0; i < pom.getNeighbours().size(); ++i) {
                tmp = pom.getNeighbours().get(i);
                if (!visited[tmp.getIndex()]) break;
            }
            if (tmp != null && !visited[tmp.getIndex()]) {
                visited[tmp.getIndex()] = true;
                stack.push(tmp.getIndex());
            } else stack.pop();
        }
        if (stack.isEmpty())
            System.out.println("Nema reshenie");
        else {
            Collections.reverse(stack);
            while (!stack.isEmpty()) {
                for (Map.Entry<String, Integer> entry : hash.entrySet()) {
                    if (entry.getValue().equals(stack.peek())) {
                        System.out.println(entry.getKey());
                    }
                }
                stack.pop();
            }
        }
    }
}
