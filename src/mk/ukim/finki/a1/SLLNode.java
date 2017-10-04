package mk.ukim.finki.a1;

public class SLLNode<E> {
    protected E element;  // info na momentalniot jazol (node)
    protected SLLNode<E> successor;  // sledbenik na jazolot (node)

    public SLLNode(final E element, final SLLNode<E> successor) {
        this.element = element;
        this.successor = successor;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}
