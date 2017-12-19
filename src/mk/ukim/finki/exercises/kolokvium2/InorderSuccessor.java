package mk.ukim.finki.exercises.kolokvium2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InorderSuccessor {
    public static void main(String[] args) throws IOException {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; ++i) {
                int num = Integer.parseInt(br.readLine());
                tree.insert(num);
            }
            int prev = tree.findMin();
            System.out.println(prev);

            for (int i = 1; i < N; ++i) {
                int tmp = tree.getSuccessor(prev);
                System.out.println(tmp);
                prev = tmp;
            }
        }
    }
}

class BSearchNode<E extends Comparable<E>> {
    public E info;
    public BSearchNode<E> left;
    public BSearchNode<E> right;

    public BSearchNode(E info) {
        this.info = info;
        left = null;
        right = null;
    }

    public BSearchNode(E info, BSearchNode<E> left, BSearchNode<E> right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }

}

class BinarySearchTree<E extends Comparable<E>> {

    /**
     * The tree root.
     */
    private BSearchNode<E> root;

    /**
     * Construct the tree.
     */
    public BinarySearchTree() {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     *
     * @param x the item to insert.
     */
    public void insert(E x) {
        root = insert(x, root);
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     *
     * @param x the item to remove.
     */
    public void remove(E x) {
        root = remove(x, root);
    }

    /**
     * Find the smallest item in the tree.
     *
     * @return smallest item or null if empty.
     */
    public E findMin() {
        return elementAt(findMin(root));
    }

    /**
     * Find the largest item in the tree.
     *
     * @return the largest item of null if empty.
     */
    public E findMax() {
        return elementAt(findMax(root));
    }

    /**
     * Find an item in the tree.
     *
     * @param x the item to search for.
     * @return the matching item or null if not found.
     */
    public BSearchNode<E> find(E x) {
        return find(x, root);
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty() {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     *
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree() {
        if (isEmpty()) {
            System.out.println("Empty tree");
        } else {
            printTree(root);
        }
    }

    /**
     * Internal method to get element field.
     *
     * @param t the node.
     * @return the element field or null if t is null.
     */
    private E elementAt(BSearchNode<E> t) {
        if (t == null)
            return null;
        return t.info;
    }

    /**
     * Internal method to insert into a subtree.
     *
     * @param x the item to insert.
     * @param t the node that roots the tree.
     * @return the new root.
     */
    private BSearchNode<E> insert(E x, BSearchNode<E> t) {
        if (t == null) {
            t = new BSearchNode<E>(x, null, null);
        } else if (x.compareTo(t.info) < 0) {
            t.left = insert(x, t.left);
        } else if (x.compareTo(t.info) > 0) {
            t.right = insert(x, t.right);
        } else ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     *
     * @param x the item to remove.
     * @param t the node that roots the tree.
     * @return the new root.
     */
    private BSearchNode<E> remove(Comparable x, BSearchNode<E> t) {
        if (t == null)
            return t;   // Item not found; do nothing

        if (x.compareTo(t.info) < 0) {
            t.left = remove(x, t.left);
        } else if (x.compareTo(t.info) > 0) {
            t.right = remove(x, t.right);
        } else if (t.left != null && t.right != null) { // Two children
            t.info = findMin(t.right).info;
            t.right = remove(t.info, t.right);
        } else {
            if (t.left != null)
                return t.left;
            else
                return t.right;
        }
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     *
     * @param t the node that roots the tree.
     * @return node containing the smallest item.
     */
    private BSearchNode<E> findMin(BSearchNode<E> t) {
        if (t == null) {
            return null;
        } else if (t.left == null) {
            return t;
        }
        return findMin(t.left);
    }

    /**
     * Internal method to find the largest item in a subtree.
     *
     * @param t the node that roots the tree.
     * @return node containing the largest item.
     */
    private BSearchNode<E> findMax(BSearchNode<E> t) {
        if (t == null) {
            return null;
        } else if (t.right == null) {
            return t;
        }
        return findMax(t.right);
    }

    /**
     * Internal method to find an item in a subtree.
     *
     * @param x is item to search for.
     * @param t the node that roots the tree.
     * @return node containing the matched item.
     */
    private BSearchNode<E> find(E x, BSearchNode<E> t) {
        if (t == null)
            return null;

        if (x.compareTo(t.info) < 0) {
            return find(x, t.left);
        } else if (x.compareTo(t.info) > 0) {
            return find(x, t.right);
        } else {
            return t;    // Match
        }
    }

    /**
     * Internal method to print a subtree in sorted order.
     *
     * @param t the node that roots the tree.
     */
    private void printTree(BSearchNode<E> t) {
        if (t != null) {
            printTree(t.left);
            System.out.println(t.info);
            printTree(t.right);
        }
    }

    public E getSuccessor(BSearchNode<E> t, E x) {
        // vasiot kod ovde
        E succ = null;
        if (t.info.equals(x) && t.right != null)
            return findMin(t.right).info;
        if (t.info.equals(x) && t.right == null)
            return succ;
        if (x.compareTo(t.info) < 0) {
            E tmp = t.info;
            succ = getSuccessor(t.left, x);
            if (succ == null)
                succ = tmp;
        }
        if (x.compareTo(t.info) > 0)
            succ = getSuccessor(t.right, x);
        return succ;
    }

    public E getSuccessor(E x) {
        return getSuccessor(root, x);
    }
}
