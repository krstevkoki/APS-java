package mk.ukim.finki.a3;

import java.util.NoSuchElementException;

class ArrayStack<E> implements Stack<E> {
    private E[] elements;
    private int depth;  // number of elements in the Stack

    @SuppressWarnings("unchecked")
    public ArrayStack(int maxDepth) {
        elements = (E[]) new Object[maxDepth];
        depth = 0;
    }

    @Override
    public boolean isEmpty() {
        return depth == 0;
    }

    @Override
    public E peek() {
        if (depth == 0)
            throw new NoSuchElementException();
        return elements[depth - 1];
    }

    @Override
    public void clear() {
        depth = 0;
    }

    @Override
    public void push(E x) {
        if (depth == elements.length)
            throw new MaximumSizeExceeded();
        elements[depth++] = x;
    }

    @Override
    public E pop() {
        if (depth == 0)
            throw new NoSuchElementException();
        return elements[--depth];
    }
}
