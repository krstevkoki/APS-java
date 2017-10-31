package mk.ukim.finki.exercises;

import java.util.Scanner;

public class ListaOdListi {
    private static long findMagicNumber(DLL<DLL<Integer>> list) {
        // Vashiot kod tuka...
        long product = 1;
        DLLNode<DLL<Integer>> tempFirst = list.getFirst();
        while (tempFirst != null) {
            long sum = 0;
            DLLNode<Integer> temp = tempFirst.element.getFirst();
            while (temp != null) {
                sum += temp.element;
                temp = temp.succ;
            }
            product *= sum;
            tempFirst = tempFirst.succ;
        }
        return product;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        DLL<DLL<Integer>> list = new DLL<>();
        for (int i = 0; i < n; i++) {
            DLL<Integer> tmp = new DLL<>();
            for (int j = 0; j < m; j++)
                tmp.insertLast(in.nextInt());
            list.insertLast(tmp);
        }
        in.close();
        System.out.println(findMagicNumber(list));
    }
}
