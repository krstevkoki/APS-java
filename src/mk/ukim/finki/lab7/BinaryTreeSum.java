package mk.ukim.finki.lab7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BinaryTreeSum {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            BTree<Integer> tree = new BTree<>();
            BNode<Integer> nodes[] = new BNode[N];
            for (int i = 0; i < N; i++)
                nodes[i] = new BNode<>();

            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                StringTokenizer st = new StringTokenizer(line);
                int index = Integer.parseInt(st.nextToken());
                nodes[index].info = Integer.parseInt(st.nextToken());
                String action = st.nextToken();
                if (action.equals("LEFT"))
                    tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.LEFT, nodes[index]);
                else if (action.equals("RIGHT"))
                    tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.RIGHT, nodes[index]);
                else  // this node is the root
                    tree.makeRootNode(nodes[index]);
            }
            int baranaVrednost = Integer.parseInt(br.readLine());
            // vasiot kod ovde
            BNode<Integer> root = tree.find(tree.getRoot(), baranaVrednost);
            System.out.println(BTree.sumInLeftSubTreeLowerThan(root.left, root) + " " +
                    BTree.sumInRightSubTreeGreaterThen(root.right, root));
        }
    }
}

/*
class BTree<E extends Comparable<E>> {
    static int LEFT = 1;
    protected BNode<E> root;
    protected int size;

    public BTree() {
        root = null;
    }

    public BTree(E info) {
        root = new BNode<>(info);
    }

    public static int sumInLeftSubTreeLowerThan(BNode<Integer> node, BNode<Integer> root) {
        if (node != null) {
            if (node.info.compareTo(root.info) < 0)
                return node.info + sumInLeftSubTreeLowerThan(node.left, root)
                        + sumInLeftSubTreeLowerThan(node.right, root);
            else
                return sumInLeftSubTreeLowerThan(node.left, root) + sumInLeftSubTreeLowerThan(node.right, root);
        }
        return 0;
    }

    public static int sumInRightSubTreeGreaterThen(BNode<Integer> node, BNode<Integer> root) {
        if (node != null) {
            if (node.info.compareTo(root.info) > 0)
                return node.info + sumInRightSubTreeGreaterThen(node.left, root)
                        + sumInRightSubTreeGreaterThen(node.right, root);
            else
                return sumInRightSubTreeGreaterThen(node.left, root) + sumInRightSubTreeGreaterThen(node.right, root);
        }
        return 0;
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
        if (where == LEFT) {
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
        if (where == LEFT) {
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

    public BNode<E> find(BNode<E> node, E nodeValue) {
        BNode<E> result = null;
        if (node == null)
            return null;
        if (node.info.compareTo(nodeValue) == 0)
            return node;
        if (node.left != null)
            result = find(node.left, nodeValue);
        if (result == null)
            result = find(node.right, nodeValue);
        return result;
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
}*/

/*class BNode<E> {
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
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BNode<?> bNode = (BNode<?>) o;
        if (info != null ? !info.equals(bNode.info) : bNode.info != null) return false;
        if (left != null ? !left.equals(bNode.left) : bNode.left != null) return false;
        return right != null ? right.equals(bNode.right) : bNode.right == null;
    }

    @Override
    public int hashCode() {
        int result = info != null ? info.hashCode() : 0;
        result = 31 * result + (left != null ? left.hashCode() : 0);
        result = 31 * result + (right != null ? right.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s ", info);
    }
}*/
