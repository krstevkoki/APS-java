package mk.ukim.finki.a6;

import java.util.Stack;

class BinaryTree<T> {
    private BinaryTreeNode<T> root;

    public BinaryTree() {
        this.root = null;
    }

    public BinaryTree(T data) {
        this.root = new BinaryTreeNode<T>(data);
    }

    public void makeRoot(T data) {
        root = new BinaryTreeNode<T>(data);
    }

    public BinaryTreeNode<T> getRoot() {
        return root;
    }

    public BinaryTreeNode<T> addChild(BinaryTreeNode<T> node, String where, T info) {
        BinaryTreeNode<T> tmp = new BinaryTreeNode<>(info);
        if (where.trim().toUpperCase().equals("LEFT")) {
            if (node.left != null)
                return null;
            node.left = tmp;
        } else if (where.trim().toUpperCase().equals("RIGHT")) {
            if (node.right != null)
                return null;
            node.right = tmp;
        } else throw new UnsupportedOperationException();
        return tmp;
    }

    public int countInsideNodes() {
        return countInsideNodes(root);
    }

    private int countInsideNodes(BinaryTreeNode<T> node) {
        if (node == null)
            return 0;
        else if (node.left == null && node.right == null)
            return 0;
        return 1 + countInsideNodes(node.left) + countInsideNodes(node.right);
    }

    public int countLeaves() {
        return countLeaves(root);
    }

    private int countLeaves(BinaryTreeNode<T> node) {
        if (node == null)
            return 0;
        else if (node.left == null && node.right == null)
            return 1;
        return countLeaves(node.left) + countLeaves(node.right);
    }

    public int depth() {
        return depth(root);
    }

    private int depth(BinaryTreeNode<T> node) {
        if (node == null)
            return 0;
        if (node.left == null && node.right == null)
            return 0;
        return 1 + Math.max(depth(node.left), depth(node.right));
    }

    public void mirror() {
        mirror(root);
    }

    private void mirror(BinaryTreeNode<T> node) {
        if (node != null) {
            mirror(node.left);
            mirror(node.right);
            BinaryTreeNode<T> tmp = node.left;
            node.left = node.right;
            node.right = tmp;
        }
    }

    public void printTree(String mode) {
        if (mode.trim().toLowerCase().equals("inorder")) {
            System.out.print("INORDER: ");
            printInOrder(root);
        } else if (mode.trim().toLowerCase().equals("preorder")) {
            System.out.print("PREORDER: ");
            printPreOrder(root);
        } else if (mode.trim().toLowerCase().equals("postorder")) {
            System.out.print("POSTORDER: ");
            printPostOrder(root);
        } else throw new UnsupportedOperationException();
        System.out.println();
    }

    private void printInOrder(BinaryTreeNode<T> node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node);
            printInOrder(node.right);
        }
    }

    private void printPreOrder(BinaryTreeNode<T> node) {
        if (node != null) {
            System.out.print(node);
            printPreOrder(node.left);
            printPreOrder(node.right);
        }
    }

    private void printPostOrder(BinaryTreeNode<T> node) {
        if (node != null) {
            printPostOrder(node.left);
            printPostOrder(node.right);
            System.out.print(node);
        }
    }

    public void printInOrderNonRecursive() {
        System.out.print("INORDER (nonrecursive): ");
        Stack<BinaryTreeNode<T>> stack = new Stack<>();
        BinaryTreeNode<T> root = getRoot();
        while (true) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            if (stack.isEmpty())
                break;
            root = stack.pop();
            System.out.print(root);
            root = root.right;
        }
        System.out.println();
    }

    public void printPreOrderNonRecursive() {
        System.out.print("PREORDER (nonrecursive): ");
        Stack<BinaryTreeNode<T>> stack = new Stack<>();
        BinaryTreeNode<T> root = getRoot();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            System.out.print(root);
            if (root.right != null)
                stack.push(root.right);
            if (root.left != null)
                stack.push(root.left);
        }
        System.out.println();
    }

    public void printPostOrderNonRecursive() {
        System.out.print("POSTORDER (nonrecursive): ");
        Stack<BinaryTreeNode<T>> stack1 = new Stack<>();
        Stack<BinaryTreeNode<T>> stack2 = new Stack<>();  // used for storing the proper order of the elements
        BinaryTreeNode<T> root = getRoot();
        if (root != null) {
            stack1.push(root);
            while (!stack1.isEmpty()) {
                root = stack1.pop();
                stack2.push(root);
                if (root.left != null)
                    stack1.push(root.left);
                if (root.right != null)
                    stack1.push(root.right);
            }
            while (!stack2.isEmpty())
                System.out.print(stack2.pop());
        }
        System.out.println();
    }

    /* ============ Inner Classes ============  */
    protected class BinaryTreeNode<T> {
        private T data;
        private BinaryTreeNode<T> left;
        private BinaryTreeNode<T> right;

        public BinaryTreeNode(T data, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public BinaryTreeNode(T data) {
            this.data = data;
            this.left = this.right = null;
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
            return String.format("%s ", data);
        }
    }
}
