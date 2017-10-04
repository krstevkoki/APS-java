package mk.ukim.finki.a1;

public class SLL<E> {
    private SLLNode<E> first;

    SLL() {
        this.first = null;
    }

    public void deleteList() {
        this.first = null;
    }

    public SLLNode<E> getFirst() {
        return first;
    }

    public SLLNode<E> find(final E object) {
        if (first != null) {
            SLLNode<E> temp = first;
            while ((temp.successor != null) && (!temp.element.equals(object))) {
                temp = temp.successor;
            }
            if (temp.element.equals(object))
                return temp;
            else
                System.out.println("Node is not found in the Array!!!");
        } else
            System.out.println("Empty Array!!!");
        return first;
    }

    public int size() {
        int length = 0;
        if (first != null) {
            SLLNode<E> temp = first;
            length = 1;
            while (temp.successor != null) {
                temp = temp.successor;
                ++length;
            }
            return length;
        } else
            return length;
    }

    @Override
    public String toString() {
        String string = new String();
        if (first != null) {
            SLLNode<E> tmp = first;
            string += tmp + " -> ";
            while (tmp.successor != null) {
                tmp = tmp.successor;
                string += tmp + " -> ";
            }
        } else {
            string = "Empty Array!!!";
        }
        return string;
    }

    public void insertFirst(final E object) {
        // first = new SLLNode<>(object, first);
        SLLNode<E> insert = new SLLNode<>(object, first);
        first = insert;
    }

    public void insertAfter(final E object, final SLLNode<E> node) {
        if (node != null) {
            // node.successor = new SLLNode<>(object, node.successor);
            SLLNode<E> insert = new SLLNode<>(object, node.successor);
            node.successor = insert;
        } else {
            System.out.println("The Node is null!!!");
        }
    }

    public void insertBefore(final E object, final SLLNode<E> node) {
        SLLNode<E> temp = first;
        if (first != null) {
            if (first == node) {
                this.insertFirst(object);
                return;
            }
            while (temp.successor != node) {
                temp = temp.successor;
            }
            if (temp.successor == node)
                insertAfter(object, temp);
            else
                System.out.println("Node is not found in the Array!!!");

        } else {
            System.out.println("Empty Array!!!");
        }
    }

    public void insertLast(final E object) {
        if (first != null) {
            SLLNode<E> temp = first;
            while (temp.successor != null) {
                temp = temp.successor;
            }
            // temp.successor = new SLLNode<E>(object, null);
            SLLNode<E> ins = new SLLNode<>(object, null);
            temp.successor = ins;
        } else
            insertFirst(object);
    }

    public void deleteFirst() {
        if (first != null)
            first = first.successor;
        else
            System.out.println("Empty Array!!!");
    }

    public void delete(final SLLNode<E> node) {
        if (first != null) {
            if (first == node) {
                this.deleteFirst();
                return;
            }
            SLLNode<E> temp = first;
            while ((temp.successor != node) && (temp.successor.successor != null)) {
                temp = temp.successor;
            }
            if (temp.successor == node)
                temp.successor = node.successor;
            else
                System.out.println("Node is not found in the Array!!!");
        } else
            System.out.println("Empty Array!!!");
    }
}
