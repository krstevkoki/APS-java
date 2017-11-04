package mk.ukim.finki.lab4;

interface Stack<E> {
    boolean isEmpty();  // checks whether the Stack is empty

    E peek();  // returns the value of the top element without removing it

    void clear();  // clear the Stack

    void push(E x);  // adds element x to the top of the Stack

    E pop();  // removes the top element from the Stack and returns its value
}
