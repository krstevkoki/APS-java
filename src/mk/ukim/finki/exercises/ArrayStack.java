package mk.ukim.finki.exercises;

import java.util.NoSuchElementException;

class ArrayStack<E> implements Stack<E> {
    private E[] array;
    private int depth;

    @SuppressWarnings("unchecked")
    public ArrayStack(int maxDepth) {
        array = (E[]) new Object[maxDepth];
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
        return array[depth - 1];
    }

    @Override
    public void push(E element) {
        if (depth == array.length)
            throw new IndexOutOfBoundsException();
        array[depth++] = element;
    }

    @Override
    public E pop() {
        if (depth == 0)
            throw new NoSuchElementException();
        return array[--depth];
    }

    @Override
    public void clear() {
        depth = 0;
    }
}
