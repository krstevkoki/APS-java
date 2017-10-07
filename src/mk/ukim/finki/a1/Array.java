package mk.ukim.finki.a1;

public class Array<E> {
    private E[] data;
    private int size;

    public Array(final int size) {
        data = (E[]) new Object[size];
        this.size = size;
    }

    public void set(final int position, final E object) {
        if (position >= 0 && position < size)
            data[position] = object;
        else
            System.out.println("Invalid index");
    }

    public E get(final int position) {
        if (position >= 0 && position < size)
            return data[position];
        System.out.println("Invalid index");
        return null;
    }

    public int getLength() {
        return size;
    }

    public int find(final E object) {
        for (int i = 0; i < size; ++i) {
            if (object.equals(data[i]))
                return i;
        }
        return -1;
    }

    public void insert(final int position, final E object) {
        if (position >= 0 && position <= size) {
            E[] temp = (E[]) new Object[size + 1];  // make a new Array with (size + 1) length
            for (int i = 0; i < size; ++i) {
                temp[i] = data[i];  // copy the elements in the new Array
            }
            if (size > position) {
                for (int i = position; i < size; ++i) {
                    temp[i + 1] = data[i];  // make space for the new element (shift right all elements after index: position)
                }
            }
            temp[position] = object;  // finally add the new element (object) in the Array
            data = temp;
            ++size;
        } else
            System.out.println("Invalid index");
    }

    public void delete(final int position) {
        if (position >= 0 && position < size) {
            E[] temp = (E[]) new Object[size - 1];  // make a new Array with (size - 1) length
            for (int i = 0; i < position; ++i) {
                temp[i] = data[i];  // copy the elements from beginning till the position of the element for deletion.
            }
            for (int i = position + 1; i < size; ++i) {
                temp[i - 1] = data[i];  // shift left the remaining elements for one position
            }
            data = temp;
            --size;
        } else
            System.out.println("Invalid index");
    }

    public void resize(final int newSize) {
        E[] temp = (E[]) new Object[newSize];  // make a new Array with (size - 1) length
        int copySize = size;  // make a new variable vor copy limit
        if (newSize < size)
            copySize = newSize;  // if the Array is going to be smaller than previous
        for (int i = 0; i < copySize; ++i) {
            temp[i] = data[i];
        }
        data = temp;
        size = newSize;
    }
}
