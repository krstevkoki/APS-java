package mk.ukim.finki.lab7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

interface Stack<E> {
    // Elementi na stekot se objekti od proizvolen tip.
    // Metodi za pristap:
    boolean isEmpty();
    // Vrakja true ako i samo ako stekot e prazen.

    E peek();
    // Go vrakja elementot na vrvot od stekot.
    // Metodi za transformacija:

    void clear();
    // Go prazni stekot.

    void push(E x);
    // Go dodava x na vrvot na stekot.

    E pop();
    // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
}

public class PreorderNonRecursive {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        BTree<String> tree = new BTree<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            BNode<String> nodes[] = new BNode[N];
            for (int i = 0; i < N; i++)
                nodes[i] = new BNode<>();

            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                StringTokenizer st = new StringTokenizer(line);
                int index = Integer.parseInt(st.nextToken());
                nodes[index].info = st.nextToken();
                String action = st.nextToken();
                if (action.equals("LEFT"))
                    tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.LEFT, nodes[index]);
                else if (action.equals("RIGHT"))
                    tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.RIGHT, nodes[index]);
                else
                    tree.makeRootNode(nodes[index]);  // this node is the root
            }
        }
        tree.PreorderNonRecursive();
    }
}

class ArrayStack<E> implements Stack<E> {
    private E[] elems;
    private int depth;

    @SuppressWarnings("unchecked")
    public ArrayStack(int maxDepth) {
        // Konstrukcija na nov, prazen stek.
        elems = (E[]) new Object[maxDepth];
        depth = 0;
    }

    public boolean isEmpty() {
        // Vrakja true ako i samo ako stekot e prazen.
        return (depth == 0);
    }

    public E peek() {
        // Go vrakja elementot na vrvot od stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        return elems[depth - 1];
    }

    public void clear() {
        // Go prazni stekot.
        for (int i = 0; i < depth; i++) elems[i] = null;
        depth = 0;
    }

    public void push(E x) {
        // Go dodava x na vrvot na stekot.
        elems[depth++] = x;
    }

    public E pop() {
        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        E topmost = elems[--depth];
        elems[depth] = null;
        return topmost;
    }
}
