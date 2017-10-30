package mk.ukim.finki.a3;

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> next;

    public SLLNode(E element, SLLNode<E> next) {
        this.element = element;
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
