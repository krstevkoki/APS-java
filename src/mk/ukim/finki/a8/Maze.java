package mk.ukim.finki.a8;

import mk.ukim.finki.a8.GraphAdjMatrix.GraphUndirected;

import java.util.*;

public class Maze {
    private Hashtable<String, Integer> hash;
    private GraphUndirected<String> graph;
    private int startNodeIndex, endNodeIndex;

    public Maze() {
        graph = null;
        startNodeIndex = endNodeIndex = -1;
        hash = new Hashtable<>();
    }

    public void generateGraph(int rows, int columns, String[] mazeInput) {
        int numNodes = 0;
        for (int i = 1; i < rows; ++i) {
            for (int j = 1; j < columns; ++j) {
                if (mazeInput[i].charAt(j) != '#') {
                    String key = "(" + i + ", " + j + ")";
                    hash.put(key, numNodes);
                    if (mazeInput[i].charAt(j) == 'S')
                        startNodeIndex = numNodes;
                    if (mazeInput[i].charAt(j) == 'E')
                        endNodeIndex = numNodes;
                    ++numNodes;
                }
            }
        }
        graph = new GraphUndirected<>(numNodes);
        for (int i = 1; i < rows; ++i) {
            for (int j = 1; j < columns; ++j) {
                int x, y;
                if (mazeInput[i].charAt(j) != '#') {
                    // dali ima teme levo od tekovnoto
                    if (mazeInput[i].charAt(j - 1) != '#') {
                        x = hash.get("(" + i + ", " + j + ")");
                        y = hash.get("(" + i + ", " + (j - 1) + ")");
                        graph.addEdge(x, y);
                    }
                    // dali ima teme desno od tekovnoto
                    if (mazeInput[i].charAt(j + 1) != '#') {
                        x = hash.get("(" + i + ", " + j + ")");
                        y = hash.get("(" + i + ", " + (j + 1) + ")");
                        graph.addEdge(x, y);
                    }
                    // dali ima teme nad tekovnoto
                    if (mazeInput[i - 1].charAt(j) != '#') {
                        x = hash.get("(" + i + ", " + j + ")");
                        y = hash.get("(" + (i - 1) + ", " + j + ")");
                        graph.addEdge(x, y);
                    }
                    // dali ima teme pod tekovnoto
                    if (mazeInput[i + 1].charAt(j) != '#') {
                        x = hash.get("(" + i + ", " + j + ")");
                        y = hash.get("(" + (i + 1) + ", " + j + ")");
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
            int temp = stack.peek();
            int tmp = temp;
            for (int i = 0; i < graph.getNumNodes(); ++i) {
                if (graph.adjacent(temp, i) == 1) {
                    tmp = i;
                    if (!visited[tmp])
                        break;
                }
            }
            if (!visited[tmp]) {
                visited[tmp] = true;
                stack.push(tmp);
            } else stack.pop();
        }
        if (stack.isEmpty())
            System.out.println("Nema Reshenie");
        else {
            Collections.reverse(stack);
            while (!stack.isEmpty())
                System.out.println(stack.pop());
        }
    }

    public static void main(String[] args) {
        int rows = 6;
        int columns = 6;
        String[] mazeInput = new String[rows];
        mazeInput[0] = "######";
        mazeInput[1] = "# # ##";
        mazeInput[2] = "# # S#";
        mazeInput[3] = "# # ##";
        mazeInput[4] = "# E  #";
        mazeInput[5] = "######";

        Maze maze = new Maze();
        maze.generateGraph(rows, columns, mazeInput);
        System.out.println("Pateka:");
        maze.findPath();
    }
}
