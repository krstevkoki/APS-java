package mk.ukim.finki.lab1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class SpecialSLLJoin {
    private static SLL<Integer> specialJoin(SLL<Integer> lista1, SLL<Integer> lista2) {
        SLL<Integer> newList = new SLL<>();
        SLLNode<Integer> nodeList1 = lista1.getFirst();
        SLLNode<Integer> nodeList2 = lista2.getFirst();

        while (nodeList1 != null && nodeList2 != null) {
            if (nodeList1.getSuccessor() != null) {
                newList.insertLast(nodeList1.getElement());
                nodeList1 = nodeList1.getSuccessor();
                newList.insertLast(nodeList1.getElement());
                nodeList1 = nodeList1.getSuccessor();
            } else
                break;
            if (nodeList2.getSuccessor() != null) {
                newList.insertLast(nodeList2.getElement());
                nodeList2 = nodeList2.getSuccessor();
                newList.insertLast(nodeList2.getElement());
                nodeList2 = nodeList2.getSuccessor();
            } else
                break;
        }

        while (nodeList1 != null) {
            newList.insertLast(nodeList1.getElement());
            nodeList1 = nodeList1.getSuccessor();
        }

        while (nodeList2 != null) {
            newList.insertLast(nodeList2.getElement());
            nodeList2 = nodeList2.getSuccessor();
        }
        return newList;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);
        s = stdin.readLine();
        String[] pomniza = s.split(" ");
        SLL<Integer> lista1 = new SLL<>();
        for (int i = 0; i < N; i++)
            lista1.insertLast(Integer.parseInt(pomniza[i]));

        s = stdin.readLine();
        N = Integer.parseInt(s);
        s = stdin.readLine();
        pomniza = s.split(" ");
        SLL<Integer> lista2 = new SLL<>();
        for (int i = 0; i < N; i++)
            lista2.insertLast(Integer.parseInt(pomniza[i]));

        SLL<Integer> spoeni = specialJoin(lista1, lista2);
        System.out.println(spoeni.toString());
    }
}
