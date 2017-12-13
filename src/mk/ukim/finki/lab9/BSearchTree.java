package mk.ukim.finki.lab9;

class BSearchTree<T extends Comparable<T>> {
    private BinaryTreeNode<T> root;

    public BSearchTree() {
        root = null;
    }

    public BinaryTreeNode<T> getRoot() {
        return root;
    }

    public void insert(T element) {
        root = insert(root, element);
    }

    private BinaryTreeNode<T> insert(BinaryTreeNode<T> subTreeRoot, T element) {
        if (subTreeRoot == null)
            return new BinaryTreeNode<>(element);
        else if (element.compareTo(subTreeRoot.data) < 0)
            subTreeRoot.left = insert(subTreeRoot.left, element);
        else if (element.compareTo(subTreeRoot.data) > 0)
            subTreeRoot.right = insert(subTreeRoot.right, element);
        return subTreeRoot;
    }

    public BinaryTreeNode<T> find(T element) {
        return binarySearch(root, element);
    }

    private BinaryTreeNode<T> binarySearch(BinaryTreeNode<T> subTreeRoot, T element) {
        if (subTreeRoot == null)
            return null;
        else if (element.compareTo(subTreeRoot.data) < 0)
            return binarySearch(subTreeRoot.left, element);
        else if (element.compareTo(subTreeRoot.data) > 0)
            return binarySearch(subTreeRoot.right, element);
        return subTreeRoot;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void makeEmpty() {
        root = null;
    }

    public int height(BinaryTreeNode<T> node) {
        if (node == null)
            return 0;
        if (node.left == null && node.right == null)
            return 1;  // special requirement, leaves have height 1
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public int nodesOnDepth(int depth) {
        return nodesOnDepth(root, depth, 0);
    }

    private int nodesOnDepth(BinaryTreeNode<T> node, int targetDepth, int currentDepth) {
        if (node == null)
            return 0;
        if (currentDepth == targetDepth)
            return 1;
        return nodesOnDepth(node.left, targetDepth, currentDepth + 1)
                + nodesOnDepth(node.right, targetDepth, currentDepth + 1);
    }

    public void printTree() {
        if (isEmpty())
            System.out.println("Empty Tree!");
        else
            printInorder(root);
    }

    private void printInorder(BinaryTreeNode<T> subTreeRoot) {
        if (subTreeRoot != null) {
            printInorder(subTreeRoot.left);
            System.out.print(subTreeRoot + " ");
            printInorder(subTreeRoot.right);
        }
    }

    private class BinaryTreeNode<T extends Comparable<T>> {
        private T data;
        private BinaryTreeNode<T> left;
        private BinaryTreeNode<T> right;

        public BinaryTreeNode(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BinaryTreeNode<?> that = (BinaryTreeNode<?>) o;
            if (data != null ? !data.equals(that.data) : that.data != null) return false;
            if (left != null ? !left.equals(that.left) : that.left != null) return false;
            return right != null ? right.equals(that.right) : that.right == null;
        }

        @Override
        public int hashCode() {
            int result = data != null ? data.hashCode() : 0;
            result = 31 * result + (left != null ? left.hashCode() : 0);
            result = 31 * result + (right != null ? right.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }
}
