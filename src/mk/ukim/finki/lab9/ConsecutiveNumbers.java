package mk.ukim.finki.lab9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsecutiveNumbers {
    private static boolean check(String inorderString) {
        String[] parts = inorderString.split("\\s+");
        int n = parts.length;
        int[] array = new int[n];
        for (int i = 0; i < n; ++i)
            array[i] = Integer.parseInt(parts[i]);
        for (int i = 0; i < n - 1; ++i)
            if ((array[i + 1] - array[i]) != 1)
                return false;
        return true;
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            BTree<Integer> bTree = new BTree<>();
            int n = Integer.parseInt(input.readLine());
            BNode<Integer>[] nodes = (BNode<Integer>[]) new BNode[n];

            for (int i = 0; i < n; ++i) {
                String[] lineParts = input.readLine().split("\\s+");
                int index = Integer.parseInt(lineParts[0]);
                int data = Integer.parseInt(lineParts[1]);
                String where = lineParts[2];
                if (where.equals("ROOT")) {
                    nodes[index] = bTree.makeRoot(data);
                } else {
                    int nodePosition = Integer.parseInt(lineParts[3]);
                    int whereInt = 1;
                    if (where.equals("RIGHT"))
                        whereInt = 2;
                    nodes[index] = bTree.addChild(nodes[nodePosition], whereInt, data);
                }
            }
            String inorderString = bTree.inorderNonRecursive();
            System.out.println(check(inorderString.substring(0, inorderString.length() - 1)));
        }
    }
}
