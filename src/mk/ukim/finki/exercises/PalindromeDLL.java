package mk.ukim.finki.exercises;

import mk.ukim.finki.a1.DLL;
import mk.ukim.finki.a1.DLLNode;

import java.util.Scanner;

public class PalindromeDLL {
    private static int isItPalindrome(DLL<Integer> list) {
        // Vashiot kod tuka...
        DLLNode<Integer> tempFirst = list.getFirst();
        DLLNode<Integer> tempLast = list.getLast();
        int n = list.size() / 2;
        for (int i = 0; i < n; ++i) {
            if (!(tempFirst.equals(tempLast)))
                return -1;
            tempFirst = tempFirst.getSuccesor();
            tempLast = tempLast.getPredecessor();
        }
        return 1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        DLL<Integer> list = new DLL<>();
        for (int i = 0; i < n; i++)
            list.insertLast(in.nextInt());
        in.close();
        System.out.println(isItPalindrome(list));
    }
}
