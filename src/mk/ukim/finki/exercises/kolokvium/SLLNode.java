package mk.ukim.finki.exercises.kolokvium;

public class SLLNode<E> {
    protected E element;
    protected SLLNode<E> next;

    public SLLNode(E element, SLLNode<E> next) {
        this.element = element;
        this.next = next;
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public SLLNode<E> getNext() {
        return next;
    }

    public void setNext(SLLNode<E> next) {
        this.next = next;
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
        return next != null ? next.equals(sllNode.next) : sllNode.next == null;
    }

    @Override
    public int hashCode() {
        int result = element != null ? element.hashCode() : 0;
        result = 31 * result + (next != null ? next.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}
