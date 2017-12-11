package mk.ukim.finki.lab8;

import mk.ukim.finki.a8.GraphAdjMatrix.GraphUndirected;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GraphCreate {
    public static void main(String[] args) throws IOException {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            int numCommands = Integer.parseInt(input.readLine());
            GraphUndirected<Character> characterGraph = null;
            for (int i = 1; i <= numCommands; ++i) {
                String[] commandParts = input.readLine().split("\\s+");
                if (commandParts[0].equals("CREATE")) {
                    Integer numNodes = Integer.parseInt(commandParts[1]);
                    Character[] chars = new Character[26];
                    for (int j = 0; j < 26; ++j)
                        chars[j] = (char) ('A' + j);
                    characterGraph = new GraphUndirected<>(numNodes, chars);
                } else if (commandParts[0].equals("ADDEDGE")) {
                    int x = Integer.parseInt(commandParts[1]);
                    int y = Integer.parseInt(commandParts[2]);
                    characterGraph.addEdge(x, y);
                } else if (commandParts[0].equals("PRINTMATRIX")) {
                    System.out.println(characterGraph);
                } else if (commandParts[0].equals("PRINTNODE")) {
                    int node = Integer.parseInt(commandParts[1]);
                    System.out.println(characterGraph.getNodeValue(node));
                } else if (commandParts[0].equals("ADJACENT")) {
                    int x = Integer.parseInt(commandParts[1]);
                    int y = Integer.parseInt(commandParts[2]);
                    System.out.println(characterGraph.adjacent(x, y));
                } else if (commandParts[0].equals("DELETEEDGE")) {
                    int x = Integer.parseInt(commandParts[1]);
                    int y = Integer.parseInt(commandParts[2]);
                    characterGraph.deleteEdge(x, y);
                }
            }
        }
    }
}
