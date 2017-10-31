package mk.ukim.finki.a3;

import java.util.NoSuchElementException;

public class LinkedQueue<E> implements Queue<E> {
    private SLLNode<E> front, rear;
    private int length;

    public LinkedQueue() {
        clear();
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public E peek() {
        if (front != null)
            return front.element;
        throw new NoSuchElementException();
    }

    @Override
    public void clear() {
        front = rear = null;
        length = 0;
    }

    @Override
    public void enqueue(E x) {
        SLLNode<E> latest = new SLLNode<>(x, null);
        if (rear == null)
            front = rear = latest;
        else {
            rear.next = latest;
            rear = latest;
        }
        ++length;
    }

    @Override
    public E dequeue() {
        if (front != null) {
            E element = front.element;
            front = front.next;
            if (front == null)
                rear = null;
            --length;
            return element;
        }
        throw new NoSuchElementException();
    }
}
