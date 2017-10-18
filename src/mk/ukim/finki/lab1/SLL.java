package mk.ukim.finki.lab1;

import java.util.Iterator;
import java.util.NoSuchElementException;

class SLL<E extends Comparable<E>> {
    private SLLNode<E> first;

    public SLL() {
        first = null;
    }

    public SLLNode<E> getFirst() {
        return first;
    }

    public void deleteList() {
        first = null;
    }

    public int size() {
        int length = 0;
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            ++length;
            it.next();
        }
        return length;
    }

    public SLLNode<E> find(E element) {
        if (first != null) {
            SLLNode<E> temp = first;
            while (temp.getSuccessor() != null && !(temp.getElement().equals(element)))
                temp = temp.getSuccessor();
            if (temp.getElement().equals(element))
                return temp;
            else
                throw new NoSuchElementException();
        } else
            System.out.println("Empty Array!");
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext())
                sb.append(" ");
        }
        return sb.toString();
    }

    public void insertFirst(E element) {
        first = new SLLNode<>(element, first);
    }

    public void insertAfter(E element, SLLNode<E> node) {
        if (node != null) {
            node.setSuccessor(new SLLNode<E>(element, node.getSuccessor()));
        } else
            throw new NoSuchElementException();
    }

    public void insertBefore(E element, SLLNode<E> node) {
        if (first != null) {
            if (first.equals(node)) {
                insertFirst(element);
                return;
            }
            SLLNode<E> temp = first;
            while (temp.getSuccessor() != null && !(temp.getSuccessor().equals(node.getSuccessor())))
                temp = temp.getSuccessor();
            if (temp.getSuccessor().equals(node.getSuccessor()))
                insertAfter(element, temp);
            else
                throw new NoSuchElementException();
        } else
            System.out.println("Empty Array!");
    }

    public void insertLast(E element) {
        if (first != null) {
            SLLNode tmp = first;
            while (tmp.getSuccessor() != null)
                tmp = tmp.getSuccessor();
            tmp.setSuccessor(new SLLNode(element, null));
        } else
            insertFirst(element);
    }

    public void deleteFirst() {
        if (first != null)
            first = first.getSuccessor();
        else
            throw new NoSuchElementException();
    }

    public void delete(SLLNode<E> node) {
        if (node != null) {
            if (node == first) {
                deleteFirst();
                return;
            }
            SLLNode temp = first;
            while (temp.getSuccessor() != null && !(temp.getSuccessor().equals(node)))
                temp = temp.getSuccessor();
            if (temp.getSuccessor().equals(node))
                temp.setSuccessor(node.getSuccessor());
            else
                throw new NoSuchElementException();
        } else
            throw new NoSuchElementException();
    }

    public void deleteDuplicates() {
        if (first != null) {
            SLLNode<E> temp = first;
            while (temp.getSuccessor() != null) {
                if (temp.getElement().equals(temp.getSuccessor().getElement()))
                    delete(temp);
                temp = temp.getSuccessor();
            }
        } else
            throw new NoSuchElementException();
    }

    public SLL<E> joinLists(SLL<E> list2) {
        SLL<E> newList = new SLL<>();
        SLLNode<E> nodeList1 = this.getFirst();
        SLLNode<E> nodeList2 = list2.getFirst();

        while (nodeList1 != null && nodeList2 != null) {
            if (nodeList1.getElement().compareTo(nodeList2.getElement()) < 0) {
                newList.insertLast(nodeList1.getElement());
                nodeList1 = nodeList1.getSuccessor();
            } else {
                newList.insertLast(nodeList2.getElement());
                nodeList2 = nodeList2.getSuccessor();
            }
        }

        while (nodeList1 != null) {
            newList.insertLast(nodeList1.getElement());
            nodeList1 = nodeList1.getSuccessor();
        }

        while (nodeList2 != null) {
            newList.insertLast(nodeList2.getElement());
            nodeList2 = nodeList2.getSuccessor();
        }

        return newList;
    }

    public Iterator<E> iterator() {
        return new LRIterator();
    }

    private class LRIterator implements Iterator<E> {
        private SLLNode<E> place;

        public LRIterator() {
            place = first;
        }

        @Override
        public boolean hasNext() {
            return place != null;
        }

        @Override
        public E next() {
            E nextElement = place.getElement();
            place = place.getSuccessor();
            return nextElement;
        }
    }
}
