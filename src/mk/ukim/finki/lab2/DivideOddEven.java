package mk.ukim.finki.lab2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DivideOddEven {
    public static void divide(DLL<Integer> lista) {
        DLL<Integer> oddList = new DLL<>();  // neparna lista
        DLL<Integer> evenList = new DLL<>();  // parna lista

        DLLNode<Integer> temp = lista.getFirst();
        while (temp != null) {
            if (temp.getElement() % 2 == 0)
                evenList.insertLast(temp.getElement());
            else
                oddList.insertLast(temp.getElement());
            temp = temp.successor;
        }
        if (oddList.getFirst() != null)
            System.out.println(oddList.toString());
        if (evenList.getFirst() != null)
            System.out.println(evenList.toString());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);
        s = stdin.readLine();
        String[] pomniza = s.split(" ");
        DLL<Integer> lista = new DLL<>();
        for (int i = 0; i < N; ++i)
            lista.insertLast(Integer.parseInt(pomniza[i]));
        divide(lista);
    }
}
