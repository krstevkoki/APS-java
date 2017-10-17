package mk.ukim.finki.lab1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Array<E> {
    private E[] array;
    private int size;

    public Array(int size) {
        this.size = size;
        this.array = (E[]) new Object[size];
    }

    public int length() {
        return size;
    }

    public void set(int position, E element) {
        if (position >= 0 && position < size)
            array[position] = element;
        else
            System.out.println("Invalid index");
    }

    public E get(int position) {
        if (position >= 0 && position < size)
            return array[position];
        System.out.println("Invalid index");
        return null;
    }

    public int find(E element) {
        for (int i = 0; i < size; ++i) {
            if (element.equals(array[i]))
                return i;
        }
        return -1;
    }

    public void insert(int position, E element) {
        if (position >= 0 && position <= size) {
            E[] temp = (E[]) new Object[size + 1];
            for (int i = 0; i < size; ++i) {
                temp[i] = array[i];
            }
            if (position < size) {
                for (int i = position; i < size; ++i) {
                    temp[i + 1] = array[i];
                }
            }
            temp[position] = element;
            array = temp;
            ++size;
        } else
            System.out.println("Invalid index");
    }

    public void delete(int position) {
        if (position >= 0 && position < size) {
            E[] temp = (E[]) new Object[size - 1];
            for (int i = 0; i < position; ++i) {
                temp[i] = array[i];
            }
            for (int i = position + 1; i < size; ++i) {
                temp[i - 1] = array[i];
            }
            array = temp;
            --size;
        } else
            System.out.println("Invalid index");
    }

    public void resize(int newSize) {
        E[] temp = (E[]) new Object[newSize];
        int copySize = size;
        if (newSize < size)
            copySize = newSize;
        for (int i = 0; i < copySize; ++i) {
            temp[i] = array[i];
        }
        array = temp;
        size = newSize;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (E e : array) {
            sb.append(e);
            sb.append(" ");
        }
        return sb.toString();
    }

    public static int brojDoProsek(Array<Integer> niza) {
        double average = 0;
        for (int i = 0; i < niza.length(); ++i)
            average += niza.get(i);
        average /= niza.length();
        double currentMinDistance = Math.abs(niza.get(0) - average);
        double lastMinDistance = currentMinDistance;
        int minIndex = 0;
        for (int i = 1; i < niza.length(); ++i) {
            if (currentMinDistance >= Math.abs(niza.get(i) - average)) {
                lastMinDistance = currentMinDistance;
                currentMinDistance = Math.abs(niza.get(i) - average);
                if (lastMinDistance == currentMinDistance) {
                    if (niza.get(minIndex) > niza.get(i))
                        minIndex = i;
                } else
                    minIndex = i;
            }
        }
        // Vashiot kod tuka...
        return niza.get(minIndex);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);

        // Vashiot kod tuka...
        Array<Integer> intNiza = new Array<>(N);
        for (int i = 0; i < N; ++i)
            intNiza.set(i, Integer.parseInt(stdin.readLine()));

        System.out.println(brojDoProsek(intNiza));
    }
}
