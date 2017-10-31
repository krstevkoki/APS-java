package mk.ukim.finki.a3;

interface Queue<E> {
    boolean isEmpty();

    int size();

    E peek();

    void clear();

    void enqueue(E x);

    E dequeue();
}
