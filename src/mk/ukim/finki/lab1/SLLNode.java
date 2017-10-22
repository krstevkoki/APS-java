package mk.ukim.finki.lab1;

class SLLNode<E> {
    private E element;
    private SLLNode<E> successor;

    public SLLNode(E element, SLLNode<E> successor) {
        this.element = element;
        this.successor = successor;
    }

    public E getElement() {
        return element;
    }

    public SLLNode<E> getSuccessor() {
        return successor;
    }

    public void setSuccessor(SLLNode<E> successor) {
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
