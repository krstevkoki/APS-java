package mk.ukim.finki.lab7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WindowsExplorer {
    private static void processCommand(String[] commands, SLLTree<String> tree) {
        SLLTree.Node<String> path = tree.root();
        for (String command : commands) {
            String[] parts = command.split("\\s+");
            if (parts[0].compareTo("CREATE") == 0)
                tree.addChild(path, parts[1]);
            else if (parts[0].compareTo("OPEN") == 0) {
                SLLTree<String>.SLLNode<String> tmp = ((SLLTree<String>.SLLNode<String>) path).getFirstChild();
                while (tmp != null) {
                    if (tmp.getElement().equals(parts[1])) {
                        path = tmp;
                        break;
                    }
                    tmp = tmp.getSibling();
                }
            } else if (parts[0].compareTo("BACK") == 0)
                path = tree.parent(path);
            else if (parts[0].compareTo("DELETE") == 0) {
                SLLTree<String>.SLLNode<String> tmp = ((SLLTree<String>.SLLNode<String>) path).getFirstChild();
                SLLTree<String>.SLLNode<String> delete = null;
                while (tmp != null) {
                    if (tmp.getElement().equals(parts[1])) {
                        delete = tmp;
                        break;
                    }
                    tmp = tmp.getSibling();
                }
                if (delete != null)
                    tree.remove(delete);
            } else if (parts[0].compareTo("PATH") == 0) {
                printPathRecursive(path, tree);
                System.out.println();
            } else if (parts[0].compareTo("PRINT") == 0)
                tree.printTree();
            else throw new UnsupportedOperationException();
        }
    }

    private static void printPathRecursive(SLLTree.Node<String> start, SLLTree<String> tree) {
        SLLTree<String>.SLLNode<String> tmp = (SLLTree<String>.SLLNode<String>) start;
        if (tmp != null) {
            printPathRecursive(tree.parent(tmp), tree);
            System.out.print(tmp + "\\");
        }
    }

    public static void main(String[] args) throws IOException {
        SLLTree<String> tree;
        String[] commands;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            commands = new String[N];
            for (int i = 0; i < N; i++)
                commands[i] = br.readLine();
        }
        tree = new SLLTree<>();
        tree.makeRoot("c:");
        // vasiot kod stoi ovde
        processCommand(commands, tree);
    }
}
