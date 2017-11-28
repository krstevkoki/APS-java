package mk.ukim.finki.a6;

import java.util.Iterator;

interface Tree<T> {
    Tree.Node<T> root();

    Tree.Node<T> parent(Tree.Node<T> node);

    int childCount(Tree.Node<T> node);

    void makeRoot(T element);

    Tree.Node<T> addChild(Tree.Node<T> node, T element);

    void remove(Tree.Node<T> node);

    Iterator<T> children(Tree.Node<T> node);

    /* ========== Inner Interace ========== */
    interface Node<T> {
        T getElement();

        void setElement(T element);
    }
}
