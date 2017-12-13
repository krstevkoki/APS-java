package mk.ukim.finki.a7;

public class BinarySearchTree<T extends Comparable<T>> {
    private BinaryTreeNode<T> root;

    public BinarySearchTree() {
        root = null;
    }

    public void insert(T element) {
        root = insert(root, element);
    }

    public void remove(T element) {
        root = remove(root, element);
    }

    private BinaryTreeNode<T> remove(BinaryTreeNode<T> subTreeRoot, T element) {
        if (subTreeRoot == null) return null;
        if (element.compareTo(subTreeRoot.data) < 0)
            subTreeRoot.left = remove(subTreeRoot.left, element);
        else if (element.compareTo(subTreeRoot.data) > 0)
            subTreeRoot.right = remove(subTreeRoot.right, element);
        else if (subTreeRoot.left != null && subTreeRoot.right != null) {
            subTreeRoot.data = findMin(subTreeRoot.right);
            subTreeRoot.right = remove(subTreeRoot.right, subTreeRoot.data);
        } else {
            if (subTreeRoot.left != null) return subTreeRoot.left;
            else return subTreeRoot.right;
        }
        return subTreeRoot;
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

    public T findMin() {
        return findMin(root);
    }

    public T findMax() {
        return findMax(root);
    }

    private T findMin(BinaryTreeNode<T> subTreeRoot) {
        if (subTreeRoot == null) return null;
        if (subTreeRoot.left == null) return subTreeRoot.data;
        return findMin(subTreeRoot.left);
    }

    private T findMax(BinaryTreeNode<T> subTreeRoot) {
        if (subTreeRoot == null) return null;
        if (subTreeRoot.right == null) return subTreeRoot.data;
        return findMax(subTreeRoot.right);
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
