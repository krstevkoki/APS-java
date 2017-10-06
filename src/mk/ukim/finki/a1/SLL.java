package mk.ukim.finki.a1;

public class SLL<E> {
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
        int size = 0;
        if (first != null) {
            size = 1;
            SLLNode<E> temp = first;
            while (temp.successor != null) {
                temp = temp.successor;
                ++size;
            }
            return size;
        }
        return size;
    }

    @Override
    public String toString() {
        String str = new String();
        if (first != null) {
            SLLNode<E> temp = first;
            str += temp.element + " -> ";
            while (temp.successor != null) {
                temp = temp.successor;
                str += temp.element + " -> ";
            }
        } else
            str = "Empty Array !!!";
        return str;
    }

    public void insertFirst(E element) {
        first = new SLLNode<>(element, first);
    }

    public void insertAfter(E element, SLLNode<E> node) {
        if (node != null) {
            node.successor = new SLLNode<>(element, node.successor);
        } else
            System.out.println("The node is null!");
    }

    public void insertBefore(E element, SLLNode<E> node) {
        if (first != null) {
            if (first.equals(node)) {
                insertFirst(element);
            } else {
                SLLNode<E> temp = first;
                while (temp.successor != null && !(temp.successor.equals(node))) {
                    temp = temp.successor;
                }
                if (temp.successor.equals(node))
                    // insertAfter(element, temp);
                    temp.successor = new SLLNode<>(element, temp.successor);
                else
                    System.out.println("Node is not found in the Array!");
            }
        } else
            System.out.println("Empty Array !!!");
    }

    public void insertLast(E element) {
        if (first != null) {
            SLLNode<E> temp = first;
            while (temp.successor != null) {
                temp = temp.successor;
            }
            temp.successor = new SLLNode<>(element, null);
        } else
            insertFirst(element);
    }

    public void deleteFirst() {
        if (first != null) {
            first = first.successor;
        } else {
            System.out.println("Empty Array !!!");
        }
    }

    public void delete(SLLNode<E> node) {
        if (first != null) {
            if (first.equals(node))
                deleteFirst();
            else {
                SLLNode<E> temp = first;
                while (temp.successor.successor != null && !(temp.successor.equals(node))) {
                    temp = temp.successor;
                }
                if (temp.successor.equals(node))
                    temp.successor = node.successor;
                else
                    System.out.println("Node is not found in the Array!");
            }
        } else
            System.out.println("Empty Array !!!");
    }

    public SLLNode<E> find(E element) {
        if (first != null) {
            SLLNode<E> temp = first;
            while (temp.successor != null && !(temp.element.equals(element))) {
                temp = temp.successor;
            }
            if (temp.element.equals(element))
                return temp;
            else
                System.out.println("Node is not found in the Array!");
        } else
            System.out.println("Empty Array !!!");
        return null;
    }
}
