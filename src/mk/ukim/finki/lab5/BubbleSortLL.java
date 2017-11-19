package mk.ukim.finki.lab5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BubbleSortLL {
    private static void bubbleSort(DLL<Integer> list) {
        DLLNode<Integer> tempFirst = list.getFirst();
        while (tempFirst.successor != null) {
            DLLNode<Integer> temp = list.getFirst();
            while (temp.successor != null) {
                if (temp.element.compareTo(temp.successor.element) > 0) {
                    Integer tmp = temp.element;
                    temp.element = temp.successor.element;
                    temp.successor.element = tmp;
                }
                temp = temp.successor;
            }
            tempFirst = tempFirst.successor;
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(stdin.readLine());
            String line = stdin.readLine();
            String[] parts = line.split(" ");
            DLL<Integer> list = new DLL<>();
            for (String part : parts)
                list.insertLast(Integer.parseInt(part));
            bubbleSort(list);
            System.out.println(list);
        }
    }
}
