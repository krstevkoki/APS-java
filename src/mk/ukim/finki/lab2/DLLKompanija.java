package mk.ukim.finki.lab2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DLLKompanija {
    public static DLLNode<Vraboten> getMiddle(DLLNode<Vraboten> first) {
        // Base case
        if (first == null)
            return null;
        DLLNode<Vraboten> fastSuccessor = first.successor;  // have to be after slowSuccessor for even number of elements
        DLLNode<Vraboten> slowSuccessor = first;

        // Move fastSuccessor by two and slowSuccessor by one
        // Finally slowSuccessor will point to middle node
        while (fastSuccessor != null) {
            fastSuccessor = fastSuccessor.successor;
            if (fastSuccessor != null) {
                slowSuccessor = slowSuccessor.successor;
                fastSuccessor = fastSuccessor.successor;
            }
        }
        return slowSuccessor;
    }

    public static DLLNode<Vraboten> sortedMerge(DLLNode<Vraboten> a, DLLNode<Vraboten> b) {
        DLLNode<Vraboten> result;
        if (a == null)
            return b;
        if (b == null)
            return a;

        if (a.element.getID() >= b.element.getID()) {
            result = a;
            result.successor = sortedMerge(a.successor, b);
        } else {
            result = b;
            result.successor = sortedMerge(a, b.successor);
        }
        return result;
    }

    public static DLLNode<Vraboten> mergeSort(DLLNode<Vraboten> first) {
        // Base case : if first is null
        if (first == null || first.successor == null) {
            return first;
        }

        DLLNode<Vraboten> middle = getMiddle(first);
        DLLNode<Vraboten> nextToMiddle = middle.successor;

        // divide the array in two half by removing the middle successor
        middle.successor = null;

        DLLNode<Vraboten> left = mergeSort(first);
        DLLNode<Vraboten> right = mergeSort(nextToMiddle);
        return sortedMerge(left, right);
    }

    public static DLL<Vraboten> removeWithLowerSalary(DLL<Vraboten> vraboteni, int salary) {
        DLL<Vraboten> removed = new DLL<>();
        DLLNode<Vraboten> temp = vraboteni.getFirst();
        while (temp != null) {
            if (temp.getElement().getSalary() >= salary)
                removed.insertLast(temp.getElement());
            temp = temp.successor;
        }
        removed.insertFirst(mergeSort(removed.getFirst()));
        return removed;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        DLL<Vraboten> vraboteni = new DLL<>();
        for (int i = 0; i < n; ++i) {
            int ID = Integer.parseInt(bf.readLine());
            int salary = Integer.parseInt(bf.readLine());
            Vraboten temp = new Vraboten(ID, salary);
            vraboteni.insertLast(temp);
        }
        int salary = Integer.parseInt(bf.readLine());
        DLL<Vraboten> removed = removeWithLowerSalary(vraboteni, salary);
        if (removed.getFirst() == null)
            System.out.println("nema");
        else
            System.out.println(removed.toString());
    }
}
