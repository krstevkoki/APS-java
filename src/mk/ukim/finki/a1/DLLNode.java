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
        return succesor != null ? succesor.equals(dllNode.succesor) : dllNode.succesor == null;
    }

    @Override
    public int hashCode() {
        int result = element != null ? element.hashCode() : 0;
        result = 31 * result + (predecessor != null ? predecessor.hashCode() : 0);
        result = 31 * result + (succesor != null ? succesor.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}
