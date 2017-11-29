package mk.ukim.finki.lab7;

import mk.ukim.finki.a6.Tree;

import java.util.Iterator;

class SLLTree<T extends Comparable<T>> implements Tree<T> {
    private SLLNode<T> root;

    public SLLTree() {
        this.root = null;
    }

    @Override
    public Node<T> root() {
        return root;
    }

    @Override
    public void makeRoot(T element) {
        this.root = new SLLNode<>(element);
    }

    @Override
    public Node<T> parent(Node<T> node) {
        return ((SLLNode<T>) node).parent;
    }

    @Override
    public int childCount(Node<T> node) {
        int counter = 0;
        if (node != null) {
            SLLNode<T> currentNode = ((SLLNode<T>) node).firstChild;
            while (currentNode != null) {
                ++counter;
                currentNode = currentNode.sibling;
            }
        }
        return counter;
    }

    private int countMaxChildrenRecursive(Node<T> node) {
        int max = childCount(node);
        SLLNode<T> tmp = ((SLLNode<T>) node).firstChild;
        while (tmp != null) {
            max = Math.max(max, countMaxChildrenRecursive(tmp));
            tmp = tmp.sibling;
        }
        return max;
    }

    public int countMaxChildren() {
        if (root == null)
            return 0;
        return countMaxChildrenRecursive(root);
    }

    @Override
    public Node<T> addChild(Node<T> node, T element) {
        SLLNode<T> insert = new SLLNode<>(element);
        SLLNode<T> currentNode = (SLLNode<T>) node;
        if (currentNode.firstChild != null) {
            if (currentNode.firstChild.element.compareTo(element) >= 0) {
                insert.sibling = currentNode.firstChild;
                currentNode.firstChild = insert;
            } else {
                SLLNode<T> tmp = currentNode.firstChild;
                while (tmp.sibling != null) {
                    if (tmp.sibling.element.compareTo(element) >= 0) {
                        insert.sibling = tmp.sibling;
                        tmp.sibling = insert;
                        break;
                    }
                    tmp = tmp.sibling;
                }
                if (tmp.sibling == null)
                    tmp.sibling = insert;
            }
        } else
            currentNode.firstChild = insert;
        insert.parent = currentNode;
        return insert;
    }

    @Override
    public void remove(Node<T> node) {
        SLLNode<T> currentNode = (SLLNode<T>) node;
        if (currentNode.parent != null) {
            if (currentNode.parent.firstChild.equals(currentNode))
                currentNode.parent.firstChild = currentNode.sibling;
            else {
                SLLNode<T> tmp = currentNode.parent.firstChild;
                while (!(tmp.sibling.equals(currentNode)))
                    tmp = tmp.sibling;
                tmp.sibling = currentNode.sibling;
            }
        } else root = null;
    }

    public void printTree() {
        if (root == null)
            System.out.println("EMPTY");
        printTreeRecursive(root, 0);
    }

    private void printTreeRecursive(Node<T> node, int level) {
        if (node != null) {
            for (int i = 0; i < level; ++i)
                System.out.print(" ");
            System.out.println(node);
            SLLNode<T> temp = ((SLLNode<T>) node).firstChild;
            while (temp != null) {
                printTreeRecursive(temp, level + 1);
                temp = temp.sibling;
            }
        }
    }

    @Override
    public Iterator<T> children(Node<T> node) {
        return new SLLTreeIterator<>(((SLLNode<T>) node).firstChild);
    }

    /* ============= Inner Classes ============= */
    private class SLLTreeIterator<T> implements Iterator<T> {
        private SLLNode<T> place;

        public SLLTreeIterator(SLLNode<T> place) {
            this.place = place;
        }

        @Override
        public boolean hasNext() {
            return place != null;
        }

        @Override
        public T next() {
            T element = place.element;
            place = place.sibling;
            return element;
        }
    }

    class SLLNode<T> implements Tree.Node<T> {
        private T element;
        private SLLNode<T> parent, firstChild, sibling;

        public SLLNode(T element) {
            this.element = element;
            parent = firstChild = sibling = null;
        }

        @Override
        public T getElement() {
            return element;
        }

        @Override
        public void setElement(T element) {
            this.element = element;
        }

        public SLLNode<T> getParent() {
            return parent;
        }

        public SLLNode<T> getFirstChild() {
            return firstChild;
        }

        public SLLNode<T> getSibling() {
            return sibling;
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SLLNode<?> sllNode = (SLLNode<?>) o;
            if (element != null ? !element.equals(sllNode.element) : sllNode.element != null) return false;
            if (parent != null ? !parent.equals(sllNode.parent) : sllNode.parent != null) return false;
            if (firstChild != null ? !firstChild.equals(sllNode.firstChild) : sllNode.firstChild != null) return false;
            return sibling != null ? sibling.equals(sllNode.sibling) : sllNode.sibling == null;
        }

        @Override
        public int hashCode() {
            int result = element != null ? element.hashCode() : 0;
            result = 31 * result + (parent != null ? parent.hashCode() : 0);
            result = 31 * result + (firstChild != null ? firstChild.hashCode() : 0);
            result = 31 * result + (sibling != null ? sibling.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }
}
