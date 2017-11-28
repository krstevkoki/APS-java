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

class BNode<E> {
    static int LEFT = 1;
    static int RIGHT = 2;
    protected E info;
    protected BNode<E> left;
    protected BNode<E> right;

    public BNode(E info) {
        this.info = info;
        left = null;
        right = null;
    }

    public BNode() {
        this.info = null;
        left = null;
        right = null;
    }

    public BNode(E info, BNode<E> left, BNode<E> right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return String.format("%s ", info);
    }
}

class BTree<E> {
    protected BNode<E> root;
    protected int size;

    public BTree() {
        root = null;
    }

    public BTree(E info) {
        root = new BNode<E>(info);
    }

    public void makeRoot(E elem) {
        root = new BNode<>(elem);
        ++size;
    }

    public void makeRootNode(BNode<E> node) {
        root = node;
    }

    public BNode<E> addChild(BNode<E> node, int where, E elem) {
        BNode<E> tmp = new BNode<>(elem);
        if (where == BNode.LEFT) {
            if (node.left != null)  // veke postoi element
                return null;
            node.left = tmp;
        } else {
            if (node.right != null) // veke postoi element
                return null;
            node.right = tmp;
        }
        ++size;
        return tmp;
    }

    public BNode<E> addChildNode(BNode<E> node, int where, BNode<E> tmp) {
        if (where == BNode.LEFT) {
            if (node.left != null)  // veke postoi element
                return null;
            node.left = tmp;
        } else {
            if (node.right != null) // veke postoi element
                return null;
            node.right = tmp;
        }
        ++size;
        return tmp;
    }

    public void PreorderNonRecursive() {
        // vasiot kod ovde
        ArrayStack<BNode<E>> stack = new ArrayStack<>(size);
        BNode<E> root = this.root;
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            System.out.print(root);
            if (root.right != null)
                stack.push(root.right);
            if (root.left != null)
                stack.push(root.left);
        }
    }
}

class ArrayStack<E> implements Stack<E> {
    protected int depth;
    private E[] elems;

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

public class PreorderNonRecursive {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            BTree<String> tree = new BTree<>();
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
            tree.PreorderNonRecursive();
        }
    }
}
