package mk.ukim.finki.a1;

public class DLLNode<E> {
    protected E element;
    protected DLLNode<E> succesor;
    protected DLLNode<E> predecessor;

    public DLLNode(E element, DLLNode<E> predecessor, DLLNode<E> succesor) {
        this.element = element;
        this.succesor = succesor;
        this.predecessor = predecessor;
    }

    public DLLNode<E> getSuccesor() {
        return succesor;
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
