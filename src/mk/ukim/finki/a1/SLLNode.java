package mk.ukim.finki.a1;

public class SLLNode<E> {
    protected E element;  // info na momentalniot jazol (node)
    protected SLLNode<E> successor;  // sledbenik na jazolot (node)

    public SLLNode(final E element, final SLLNode<E> successor) {
        this.element = element;
        this.successor = successor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        SLLNode<?> sllNode = (SLLNode<?>) o;
        if (element != null ? !element.equals(sllNode.element) : sllNode.element != null)
            return false;
        return successor != null ? successor.equals(sllNode.successor) : sllNode.successor == null;
    }

    @Override
    public int hashCode() {
        int result = element != null ? element.hashCode() : 0;
        result = 31 * result + (successor != null ? successor.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}
