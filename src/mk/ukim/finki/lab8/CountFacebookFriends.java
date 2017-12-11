package mk.ukim.finki.lab8;

import mk.ukim.finki.a8.GraphList.GraphDirected;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class CountFacebookFriends {
    private static int findMinPath(int start, int end, int hops, GraphDirected<String> socialNetwork) {
        if (start == end)
            return hops;
        if (hops > socialNetwork.getNumNodes() - 1)
            return 1000;
        LinkedList<GraphDirected<String>.GraphNode<String>> neighbours = socialNetwork.getAdjList()[start].neighbours;
        if (hops > socialNetwork.getNumNodes())
            return Integer.MAX_VALUE;
        if (neighbours.contains(socialNetwork.getAdjList()[end]))
            return hops + 1;
        int min = Integer.MAX_VALUE;
        for (GraphDirected<String>.GraphNode<String> neighbour : neighbours) {
            int index = neighbour.index;
            if (index != start) {
                int br = findMinPath(index, end, hops + 1, socialNetwork);
                if (br < min)
                    min = br;
            }
        }
        return min;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            int numUsers = Integer.parseInt(input.readLine());
            String[] users = new String[numUsers];
            GraphDirected<String> socialNetwork = new GraphDirected<>(numUsers, users);
            for (int i = 0; i < numUsers; ++i) {
                int numFriends = Integer.parseInt(input.readLine());
                for (int j = 0; j < numFriends; ++j) {
                    String[] parts = input.readLine().split("\\s+");
                    socialNetwork.addEdge(i, Integer.parseInt(parts[0]));
                    socialNetwork.setNodeAt(i, parts[1] + " " + parts[2]);
                }
            }
            int start = Integer.parseInt(input.readLine());
            int end = Integer.parseInt(input.readLine());
            System.out.println(findMinPath(start, end, 0, socialNetwork));
        }
    }
}
