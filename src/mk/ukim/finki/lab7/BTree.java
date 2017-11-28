package mk.ukim.finki.lab7;

class BTree<E extends Comparable<E>> {
    private BNode<E> root;
    private int size;

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

    public BNode<E> getRoot() {
        return root;
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
}
