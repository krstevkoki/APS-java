package mk.ukim.finki.a7;

import java.util.Random;

public class HeapTest {
    public static void main(String[] args) {
        Random rand = new Random();
        Heap<Integer> heap = new Heap<>(10);
        for (int i = 0; i < 10; ++i)
            heap.setElement(i, rand.nextInt(30));

        heap.heapSort();

        for (int i = 1; i < 10; ++i)
            if (heap.getAt(i - 1).compareTo(heap.getAt(i)) > 0)
                System.out.println("ERROR");
    }
}
