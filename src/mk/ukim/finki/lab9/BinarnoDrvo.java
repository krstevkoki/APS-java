package mk.ukim.finki.lab9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BinarnoDrvo {
    public static void main(String[] args) throws IOException {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            BSearchTree<Integer> tree = new BSearchTree<>();
            int n = Integer.parseInt(input.readLine());
            for (int i = 0; i < n; ++i)
                tree.insert(Integer.parseInt(input.readLine()));
            int nodeValue = Integer.parseInt(input.readLine());
            int V = tree.height(tree.find(nodeValue));
            int count = tree.nodesOnDepth(V);
            System.out.println(V);
            System.out.println(count);
        }
    }
}
