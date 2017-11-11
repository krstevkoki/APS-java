package mk.ukim.finki.exercises.kolokvium;

import java.util.Iterator;

public class SLL<E> {
    private SLLNode<E> first;

    public SLL() {
        first = null;
    }

    public void setFirst(SLLNode<E> first) {
        this.first = first;
    }

    public SLLNode<E> getFirst() {
        return first;
    }

    public void deleteList() {
        first = null;
    }

    public int size() {
        int size = 0;
        if (first != null) {
            Iterator<E> it = iterator();
            while (it.hasNext()) {
                ++size;
                it.next();
            }
            return size;
        }
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (first != null) {
            Iterator<E> it = iterator();
            while (it.hasNext()) {
                sb.append(it.next());
                if (it.hasNext())
                    sb.append("->");
            }
        } else
            sb.append("Empty Array !!!");
        return sb.toString();
    }

    public void insertFirst(E element) {
        first = new SLLNode<>(element, first);
    }

    public void insertAfter(E element, SLLNode<E> node) {
        if (node != null)
            node.next = new SLLNode<>(element, node.next);
        else
            System.out.println("The node is null!");
    }

    public void insertBefore(E element, SLLNode<E> node) {
        if (first != null) {
            if (first.equals(node)) {
                insertFirst(element);
            } else {
                SLLNode<E> temp = first;
                while (temp.next != null && !(temp.next.equals(node)))
                    temp = temp.next;
                if (temp.next.equals(node))
                    insertAfter(element, temp);
                else
                    System.out.println("Node is not found in the Array!");
            }
        } else
            System.out.println("Empty Array !!!");
    }

    public void insertLast(E element) {
        if (first != null) {
            SLLNode<E> temp = first;
            while (temp.next != null)
                temp = temp.next;
            temp.next = new SLLNode<>(element, null);
        } else
            insertFirst(element);
    }

    public void deleteFirst() {
        if (first != null)
            first = first.next;
        else
            System.out.println("Empty Array !!!");
    }

    public void delete(SLLNode<E> node) {
        if (first != null) {
            if (first.equals(node))
                deleteFirst();
            else {
                SLLNode<E> temp = first;
                while (temp.next.next != null && !(temp.next.equals(node)))
                    temp = temp.next;
                if (temp.next.equals(node))
                    temp.next = node.next;
                else
                    System.out.println("Node is not found in the Array!");
            }
        } else
            System.out.println("Empty Array !!!");
    }

    public SLLNode<E> find(E element) {
        if (first != null) {
            SLLNode<E> temp = first;
            while (temp.next != null && !(temp.element.equals(element)))
                temp = temp.next;
            if (temp.element.equals(element))
                return temp;
        } else
            System.out.println("Empty Array !!!");
        return null;
    }

    public Iterator<E> iterator() {
        return new LRIterator();
    }

    private class LRIterator implements Iterator<E> {
        private SLLNode<E> place;

        private LRIterator() {
            this.place = first;
        }

        @Override
        public boolean hasNext() {
            return place != null;
        }

        @Override
        public E next() {
            E nextElement = place.element;
            place = place.next;
            return nextElement;
        }

        @Override
        public void remove() {
            // Not implemented
        }
    }
}
