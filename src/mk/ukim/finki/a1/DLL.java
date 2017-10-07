package mk.ukim.finki.a1;

import java.util.Iterator;

public class DLL<E> {
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
                it.next();
                ++length;
            }
        }
        return length;
    }

    public DLLNode<E> find(E element) {
        if (first != null) {
            DLLNode<E> temp = first;
            while (temp.succesor != null && !(temp.element.equals(element)))
                temp = temp.succesor;
            if (temp.element.equals(element))
                return temp;
            else
                System.out.println("Node is not found in the Array!");
        } else
            System.out.println("Empty Array !!!");
        return null;
    }

    @Override
    public String toString() {
        String str = new String();
        if (first != null) {
            Iterator<E> it = iterator();
            while (it.hasNext())
                str += it.next() + " -> ";
        } else
            str += "Empty Array !!!";
        return str;
    }

    public String toStringR() {
        String str = new String();
        if (last != null) {
            Iterator<E> it = iteratorR();
            while (it.hasNext())
                str += it.next() + " -> ";
        } else
            str += "Empty Array !!!";
        return str;
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
            last.succesor = insert;
            last = insert;
        }
    }

    public void insertAfter(E element, DLLNode<E> node) {
        if (node.equals(last))
            insertLast(element);
        else {
            DLLNode<E> insert = new DLLNode<>(element, node, node.succesor);
            node.succesor.predecessor = insert;
            node.succesor = insert;
        }
    }

    public void insertBefore(E element, DLLNode<E> node) {
        if (node.equals(first))
            insertFirst(element);
        else {
            DLLNode<E> insert = new DLLNode<>(element, node.predecessor, node);
            node.predecessor.succesor = insert;
            node.predecessor = insert;
        }
    }

    public void deleteFirst() {
        if (first != null) {
            first = first.succesor;
            if (first != null)
                first.predecessor = null;
            if (first == null)
                last = null;
        } else
            System.out.println("Empty Array !!!");
    }

    public void deleteLast() {
        if (last != null) {
            if (first.succesor == null)
                deleteFirst();
            else {
                last = last.predecessor;
                last.succesor = null;
            }
        } else
            System.out.println("Empty Array !!!");
    }

    public void delete(DLLNode<E> node) {
        if (node.equals(first))
            deleteFirst();
        else if (node.equals(last))
            deleteLast();
        else {
            node.predecessor.succesor = node.succesor;
            node.succesor.predecessor = node.predecessor;
        }
    }

    public void izvadiDupliIPrebroj() {
        if (first != null) {
            DLLNode<E> temp = first;
            int counter = 0;
            while (temp.succesor != null) {
                if (temp.element.equals(temp.succesor.element)) {
                    delete(temp);
                    ++counter;
                }
                temp = temp.succesor;
            }
            System.out.println(counter + " duplicates were removed");
        } else
            System.out.println("Empty Array !!!");
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
            place = place.succesor;
            return nextElement;
        }

        @Override
        public void remove() {
            // Not implemented
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
        }
    }
}
