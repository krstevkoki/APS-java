package mk.ukim.finki.lab2;

import java.util.Iterator;

class DLL<E> {
    private DLLNode<E> first;
    private DLLNode<E> last;

    public DLL() {
        first = null;
        last = null;
    }

    public DLLNode<E> getFirst() {
        return first;
    }

    public DLLNode<E> getLast() {
        return last;
    }

    public int size() {
        int length = 0;
        Iterator<E> it = lrIterator();
        while (it.hasNext()) {
            ++length;
            it.next();
        }
        return length;
    }

    public void deleteList() {
        this.first = null;
        this.last = null;
    }

    public DLLNode<E> find(E element) {
        if (first != null) {
            DLLNode<E> temp = first;
            while (temp.successor != null && !(temp.element.equals(element)))
                temp = temp.successor;
            if (temp.element.equals(element))
                return temp;
            else
                System.out.println("Node is not found");
        } else
            System.out.println("Empty array!");
        return null;
    }

    // prints the Nodes in Left-to-Right order
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<E> it = lrIterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext())
                sb.append("\n");
        }
        return sb.toString();
    }

    // prints the Nodes in Right-to-Left order
    public String toStringRL() {
        StringBuilder sb = new StringBuilder();
        Iterator<E> it = rlIterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext())
                sb.append("\n");
        }
        return sb.toString();
    }

    public void insertFirst(DLLNode<E> first) {
        this.first = first;
    }

    public void insertFirst(E element) {
        DLLNode<E> insert = new DLLNode<>(element, null, first);
        if (first == null)
            last = insert;
        else
            first.predecessor = insert;
        first = insert;
    }

    public void insertLast(E element) {
        if (last == null)
            insertFirst(element);
        else {
            DLLNode<E> insert = new DLLNode<>(element, last, null);
            last.successor = insert;
            last = insert;
        }
    }

    public void insertAfter(E element, DLLNode<E> node) {
        if (node.equals(last))
            insertLast(element);
        else {
            DLLNode<E> insert = new DLLNode<>(element, node, node.successor);
            node.successor.predecessor = insert;
            node.successor = insert;
        }
    }

    public void insertBefore(E element, DLLNode<E> node) {
        if (node.equals(first))
            insertFirst(element);
        else {
            DLLNode<E> insert = new DLLNode<>(element, node.predecessor, node);
            node.predecessor.successor = insert;
            node.predecessor = insert;
        }
    }

    public void deleteFirst() {
        if (first != null) {
            first = first.successor;
            if (first != null)
                first.predecessor = null;
            else
                last = null;
        } else
            System.out.println("Empty Array");
    }

    public void deleteLast() {
        if (last != null) {
            if (last.equals(first))
                deleteFirst();
            else {
                last = last.predecessor;
                last.successor = null;
            }
        } else
            System.out.println("Empty Array");
    }

    public void delete(DLLNode<E> node) {
        if (node.equals(first))
            deleteFirst();
        else if (node.equals(last))
            deleteLast();
        else {
            node.predecessor.successor = node.successor;
            node.successor.predecessor = node.predecessor;
        }
    }

    public void deleteDuplicates() {
        if (first != null) {
            DLLNode<E> temp = first;
            while (temp.successor != null) {
                if (temp.element.equals(temp.successor.element))
                    delete(temp);
                temp = temp.successor;
            }
        } else
            System.out.println("Empty Array !!!");
    }

    public Iterator<E> lrIterator() {
        return new LRIterator();
    }

    public Iterator<E> rlIterator() {
        return new RLIterator();
    }

    // Left-to-Right iterator
    private class LRIterator implements Iterator<E> {
        private DLLNode<E> place;

        public LRIterator() {
            place = first;
        }

        @Override
        public boolean hasNext() {
            return place != null;
        }

        @Override
        public E next() {
            E element = place.element;
            place = place.successor;
            return element;
        }
    }

    // Right-to-Left iterator
    private class RLIterator implements Iterator<E> {
        private DLLNode<E> place;

        public RLIterator() {
            place = last;
        }

        @Override
        public boolean hasNext() {
            return place != null;
        }

        @Override
        public E next() {
            E element = place.element;
            place = place.predecessor;
            return element;
        }
    }
}
