package mk.ukim.finki.exercises;

interface Stack<E> {
    boolean isEmpty();

    E peek();

    void push(E element);

    E pop();

    void clear();
}
