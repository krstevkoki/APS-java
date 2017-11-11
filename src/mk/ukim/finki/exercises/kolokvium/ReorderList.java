package mk.ukim.finki.exercises.kolokvium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReorderList {
    private static SLLNode<Integer> findLast(SLL<Integer> lista) {
        SLLNode<Integer> tempFirst = lista.getFirst();
        while (tempFirst.next != null)
            tempFirst = tempFirst.next;
        return tempFirst;
    }

    private static SLL<Integer> reorderList(SLL<Integer> lista) {
        SLLNode<Integer> tempFirst = lista.getFirst();
        if (tempFirst.next != null) {
            while (tempFirst.next.next != null) {
                SLLNode<Integer> last = findLast(lista);
                lista.insertAfter(last.element, tempFirst);
                lista.delete(last);
                tempFirst = tempFirst.next.next;
                if (tempFirst.equals(findLast(lista)))
                    break;
            }
        }
        return lista;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(bf.readLine());
            String[] parts = bf.readLine().split(" ");
            SLL<Integer> lista = new SLL<>();
            for (int i = 0; i < n; ++i)
                lista.insertLast(Integer.parseInt(parts[i]));
            System.out.println(reorderList(lista).toString());
        }
    }
}
