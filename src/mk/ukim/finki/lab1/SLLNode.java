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
    public String toString() {
        return element.toString();
    }
}
