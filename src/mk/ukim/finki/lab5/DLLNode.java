package mk.ukim.finki.lab5;

class DLLNode<E extends Comparable<E>> {
    protected E element;
    protected DLLNode<E> successor;
    protected DLLNode<E> predecessor;

    public DLLNode(E element, DLLNode<E> predecessor, DLLNode<E> successor) {
        this.element = element;
        this.successor = successor;
        this.predecessor = predecessor;
    }

    public DLLNode<E> getSuccessor() {
        return successor;
    }

    public DLLNode<E> getPredecessor() {
        return predecessor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        DLLNode<?> dllNode = (DLLNode<?>) o;
        return element != null ? element.equals(dllNode.element) : dllNode.element == null;
    }

    @Override
    public int hashCode() {
        return element != null ? element.hashCode() : 0;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}
