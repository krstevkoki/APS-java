package mk.ukim.finki.a3;

import java.util.NoSuchElementException;

class ArrayQueue<E> implements Queue<E> {
    private E[] elements;
    private int front, rear, length;

    @SuppressWarnings("unchecked")
    public ArrayQueue(int maxSize) {
        elements = (E[]) new Object[maxSize];
        clear();
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public E peek() {
        if (length != 0) {
            return elements[front];
        } else throw new NoSuchElementException();
    }

    @Override
    public void clear() {
        length = front = rear = 0;
    }

    @Override
    public void enqueue(E x) {
        if (length != elements.length) {
            if (rear == elements.length)
                rear = 0;
            elements[rear++] = x;
            ++length;
        } else throw new MaximumSizeExceeded();
    }

    @Override
    public E dequeue() {
        if (length != 0) {
            E element = elements[front++];
            if (front == elements.length)
                front = 0;
            --length;
            return element;
        } else throw new NoSuchElementException();
    }
}
