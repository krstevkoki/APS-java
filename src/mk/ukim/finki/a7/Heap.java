package mk.ukim.finki.a7;

public class Heap<T extends Comparable<T>> {
    private T[] elements;

    @SuppressWarnings("unchecked")
    public Heap(int size) {
        this.elements = (T[]) new Comparable[size];
    }

    public T getAt(int i) {
        return elements[i];
    }

    public int getParent(int i) {
        return ((i + 1) / 2) - 1;
    }

    public int getLeft(int i) {
        return (2 * i) + 1;
    }

    public int getRight(int i) {
        return (2 * i) + 2;
    }

    public void setElement(int index, T element) {
        elements[index] = element;
    }

    private void swap(int i, int j) {
        T temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }

    public void adjust(int i, int n) {
        int left = getLeft(i);
        int right = getRight(i);
        int largest = i;
        if (left < n && elements[left].compareTo(elements[largest]) > 0)
            largest = left;
        if (right < n && elements[right].compareTo(elements[largest]) > 0)
            largest = right;
        if (largest != i) {
            swap(i, largest);
            adjust(largest, n);
        }
    }

    public void buildHeap() {
        int n = elements.length;
        for (int i = (n / 2) - 1; i >= 0; --i)
            adjust(i, n);
    }

    public void heapSort() {
        buildHeap();
        for (int i = elements.length - 1; i >= 0; --i) {
            swap(0, i);
            adjust(0, i);
        }
    }
}
