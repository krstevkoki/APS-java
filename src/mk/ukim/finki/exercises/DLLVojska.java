package mk.ukim.finki.exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DLLVojska {
    private static DLL<Integer> vojska(DLL<Integer> lista, int a, int b, int c, int d) {
        // Vasiot kod tuka
        DLLNode<Integer> tempNode = lista.getFirst();
        DLLNode<Integer> tempNodeA = lista.getFirst();
        DLLNode<Integer> tempNodeB = lista.getFirst();
        DLLNode<Integer> tempNodeC = lista.getFirst();
        DLLNode<Integer> tempNodeD = lista.getFirst();
        while (tempNode != null) {
            if (tempNode.element.equals(a))
                tempNodeA = tempNode;
            if (tempNode.element.equals(b))
                tempNodeB = tempNode;
            if (tempNode.element.equals(c))
                tempNodeC = tempNode;
            if (tempNode.element.equals(d))
                tempNodeD = tempNode;
            tempNode = tempNode.succ;
        }
        DLL<Integer> result = new DLL<>();
        tempNode = lista.getFirst();
        while (!(tempNode.equals(tempNodeA))) {
            result.insertLast(tempNode.element);
            tempNode = tempNode.succ;
        }
        tempNode = tempNodeC;
        while (!(tempNode.equals(tempNodeD))) {
            result.insertLast(tempNode.element);
            tempNode = tempNode.succ;
        }
        result.insertLast(tempNode.element);
        tempNode = tempNodeB.succ;
        while (!(tempNode.equals(tempNodeC))) {
            result.insertLast(tempNode.element);
            tempNode = tempNode.succ;
        }
        tempNode = tempNodeA;
        while (!(tempNode.equals(tempNodeB.succ))) {
            result.insertLast(tempNode.element);
            tempNode = tempNode.succ;
        }
        tempNode = tempNodeD.succ;
        while (tempNode != null) {
            result.insertLast(tempNode.element);
            tempNode = tempNode.succ;
        }
        lista = result;
        return lista;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in))) {
            DLL<Integer> lista = new DLL<>();
            String s = stdin.readLine();
            int N = Integer.parseInt(s);
            s = stdin.readLine();
            String[] ids = s.split(" ");
            for (int i = 0; i < N; i++)
                lista.insertLast(Integer.parseInt(ids[i]));

            s = stdin.readLine();
            String[] interval = s.split(" ");
            int a = Integer.parseInt(interval[0]);
            int b = Integer.parseInt(interval[1]);

            s = stdin.readLine();
            interval = s.split(" ");
            int c = Integer.parseInt(interval[0]);
            int d = Integer.parseInt(interval[1]);

            DLL<Integer> result = vojska(lista, a, b, c, d);
            DLLNode<Integer> node = result.getFirst();
            System.out.print(node.element);
            node = node.succ;
            while (node != null) {
                System.out.print(" " + node.element);
                node = node.succ;
            }
        }
    }
}
