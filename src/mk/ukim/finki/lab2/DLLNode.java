package mk.ukim.finki.lab2;

class DLLNode<E> {
    protected E element;
    protected DLLNode<E> predecessor;
    protected DLLNode<E> successor;

    public DLLNode(E element, DLLNode<E> predecessor, DLLNode<E> successor) {
        this.element = element;
        this.predecessor = predecessor;
        this.successor = successor;
    }

    public E getElement() {
        return element;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        DLLNode<?> dllNode = (DLLNode<?>) o;
        if (element != null ? !element.equals(dllNode.element) : dllNode.element != null)
            return false;
        if (predecessor != null ? !predecessor.equals(dllNode.predecessor) : dllNode.predecessor != null)
            return false;
        return successor != null ? successor.equals(dllNode.successor) : dllNode.successor == null;
    }

    @Override
    public int hashCode() {
        int result = element != null ? element.hashCode() : 0;
        result = 31 * result + (predecessor != null ? predecessor.hashCode() : 0);
        result = 31 * result + (successor != null ? successor.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}
