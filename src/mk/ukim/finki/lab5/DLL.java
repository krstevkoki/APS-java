package mk.ukim.finki.lab5;

import java.util.Iterator;
import java.util.NoSuchElementException;

class DLL<E extends Comparable<E>> {
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

    public void deleteList() {
        first = null;
        last = null;
    }

    public int size() {
        int length = 0;
        if (first != null && last != null) {
            Iterator<E> it = iterator();
            while (it.hasNext()) {
                ++length;
                it.next();

            }
        }
        return length;
    }

    public DLLNode<E> find(E element) {
        if (first != null) {
            DLLNode<E> temp = first;
            while (temp.successor != null && !(temp.element.equals(element)))
                temp = temp.successor;
            if (temp.element.equals(element))
                return temp;
            else throw new NoSuchElementException();
        } else throw new NoSuchElementException();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (first != null) {
            Iterator<E> it = iterator();
            while (it.hasNext()) {
                sb.append(it.next());
                if (it.hasNext())
                    sb.append(" ");
            }
        } else
            sb.append("Empty Array !!!");
        return sb.toString();
    }

    public String toStringR() {
        StringBuilder sb = new StringBuilder();
        if (last != null) {
            Iterator<E> it = iteratorR();
            while (it.hasNext()) {
                sb.append(it.next());
                if (it.hasNext())
                    sb.append(" ");
            }
        } else
            sb.append("Empty Array !!!");
        return sb.toString();
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
            if (first == null)
                last = null;
        } else
            throw new NoSuchElementException();
    }

    public void deleteLast() {
        if (last != null) {
            if (first.successor == null)
                deleteFirst();
            else {
                last = last.predecessor;
                last.successor = null;
            }
        } else
            throw new NoSuchElementException();
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

    public Iterator<E> iterator() {
        return new LRIterator();
    }

    public Iterator<E> iteratorR() {
        return new RLIterator();
    }

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
            E nextElement = place.element;
            place = place.successor;
            return nextElement;
        }

        @Override
        public void remove() {
            // Not implemented
            throw new UnsupportedOperationException();
        }
    }

    private class RLIterator implements Iterator<E> {
        DLLNode<E> place;

        RLIterator() {
            place = last;
        }

        @Override
        public boolean hasNext() {
            return place != null;
        }

        @Override
        public E next() {
            E nextElement = place.element;
            place = place.predecessor;
            return nextElement;
        }

        @Override
        public void remove() {
            // Not implemented
            throw new UnsupportedOperationException();
        }
    }
}
