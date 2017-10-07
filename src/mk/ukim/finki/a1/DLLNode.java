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
    public String toString() {
        return element.toString();
    }
}
