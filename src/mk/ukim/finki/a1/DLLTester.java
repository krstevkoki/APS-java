package mk.ukim.finki.a1;

public class DLLTester {
    public static void main(String[] args) {
        DLL<Integer> niza = new DLL<>();
        System.out.println("Left-to-Rigt: " + niza.toString());
        System.out.println("Right-to-Left: " + niza.toStringR());
        System.out.println("The size of the Array is: " + niza.size() + "\n");

        /* ================================================================== */

        niza.insertFirst(new Integer(77));
        System.out.println("Left-to-Rigt:\n" + niza.toString());
        System.out.println("Right-to-Left:\n" + niza.toStringR());
        System.out.println("The size of the Array is: " + niza.size() + "\n");

        niza.deleteLast();
        System.out.println("Left-to-Rigt:\n" + niza.toString());
        System.out.println("Right-to-Left:\n" + niza.toStringR());
        System.out.println("The size of the Array is: " + niza.size() + "\n");

        niza.insertLast(new Integer(29));
        System.out.println("Left-to-Rigt:\n" + niza.toString());
        System.out.println("Right-to-Left:\n" + niza.toStringR());
        System.out.println("The size of the Array is: " + niza.size() + "\n");

        niza.deleteFirst();
        System.out.println("Left-to-Rigt:\n" + niza.toString());
        System.out.println("Right-to-Left:\n" + niza.toStringR());
        System.out.println("The size of the Array is: " + niza.size() + "\n");

        niza.insertFirst(new Integer(5));
        System.out.println("Left-to-Rigt:\n" + niza.toString());
        System.out.println("Right-to-Left:\n" + niza.toStringR());
        System.out.println("The size of the Array is: " + niza.size() + "\n");

        niza.insertLast(new Integer(9));
        System.out.println("Left-to-Rigt:\n" + niza.toString());
        System.out.println("Right-to-Left:\n" + niza.toStringR());
        System.out.println("The size of the Array is: " + niza.size() + "\n");

        niza.insertAfter(new Integer(6), niza.getFirst());
        System.out.println("Left-to-Rigt:\n" + niza.toString());
        System.out.println("Right-to-Left:\n" + niza.toStringR());
        System.out.println("The size of the Array is: " + niza.size() + "\n");

        niza.insertBefore(new Integer(8), niza.getLast());
        System.out.println("Left-to-Rigt:\n" + niza.toString());
        System.out.println("Right-to-Left:\n" + niza.toStringR());
        System.out.println("The size of the Array is: " + niza.size() + "\n");

        niza.insertAfter(new Integer(7), niza.find(6));
        System.out.println("Left-to-Rigt:\n" + niza.toString());
        System.out.println("Right-to-Left:\n" + niza.toStringR());
        System.out.println("The size of the Array is: " + niza.size() + "\n");

        niza.insertAfter(new Integer(10), niza.find(9));
        System.out.println("Left-to-Rigt:\n" + niza.toString());
        System.out.println("Right-to-Left:\n" + niza.toStringR());
        System.out.println("The size of the Array is: " + niza.size() + "\n");

        niza.insertFirst(new Integer(4));
        System.out.println("Left-to-Rigt:\n" + niza.toString());
        System.out.println("Right-to-Left:\n" + niza.toStringR());
        System.out.println("The size of the Array is: " + niza.size() + "\n");

        niza.insertLast(new Integer(11));
        System.out.println("Left-to-Rigt:\n" + niza.toString());
        System.out.println("Right-to-Left:\n" + niza.toStringR());
        System.out.println("The size of the Array is: " + niza.size() + "\n");

        niza.insertAfter(new Integer(66), niza.find(new Integer(4)));
        System.out.println("Left-to-Rigt:\n" + niza.toString());
        System.out.println("Right-to-Left:\n" + niza.toStringR());
        System.out.println("The size of the Array is: " + niza.size() + "\n");

        /* ================================================================== */

        niza.delete(niza.find(new Integer(66)));
        System.out.println("Left-to-Rigt:\n" + niza.toString());
        System.out.println("Right-to-Left:\n" + niza.toStringR());
        System.out.println("The size of the Array is: " + niza.size() + "\n");

        niza.deleteFirst();
        System.out.println("Left-to-Rigt:\n" + niza.toString());
        System.out.println("Right-to-Left:\n" + niza.toStringR());
        System.out.println("The size of the Array is: " + niza.size() + "\n");

        niza.deleteLast();
        System.out.println("Left-to-Rigt:\n" + niza.toString());
        System.out.println("Right-to-Left:\n" + niza.toStringR());
        System.out.println("The size of the Array is: " + niza.size() + "\n");

        niza.delete(niza.getFirst());
        System.out.println("Left-to-Rigt:\n" + niza.toString());
        System.out.println("Right-to-Left:\n" + niza.toStringR());
        System.out.println("The size of the Array is: " + niza.size() + "\n");

        niza.delete(niza.getLast());
        System.out.println("Left-to-Rigt:\n" + niza.toString());
        System.out.println("Right-to-Left:\n" + niza.toStringR());
        System.out.println("The size of the Array is: " + niza.size() + "\n");

        niza.deleteList();
        System.out.println("Left-to-Rigt: " + niza.toString());
        System.out.println("Right-to-Left: " + niza.toStringR());
        System.out.println("The size of the Array is: " + niza.size());

        /* ================================================================== */

        System.out.println("\n==================================\n");

        /* ================================================================== */

        DLL<Integer> niza2 = new DLL<>();
        System.out.println("Left-to-Rigt:\n" + niza2.toString());
        System.out.println("Right-to-Left:\n" + niza2.toStringR());
        System.out.println("The size of the Array is: " + niza2.size() + "\n");

        /* ================================================================== */

        niza2.insertFirst(new Integer(5));
        System.out.println("Left-to-Rigt:\n" + niza2.toString());
        System.out.println("Right-to-Left:\n" + niza2.toStringR());
        System.out.println("The size of the Array is: " + niza2.size() + "\n");

        niza2.insertFirst(new Integer(5));
        System.out.println("Left-to-Rigt:\n" + niza2.toString());
        System.out.println("Right-to-Left:\n" + niza2.toStringR());
        System.out.println("The size of the Array is: " + niza2.size() + "\n");

        niza2.insertAfter(new Integer(8), niza2.find(new Integer(5)));
        System.out.println("Left-to-Rigt:\n" + niza2.toString());
        System.out.println("Right-to-Left:\n" + niza2.toStringR());
        System.out.println("The size of the Array is: " + niza2.size() + "\n");

        niza2.insertAfter(new Integer(5), niza2.find(new Integer(8)));
        System.out.println("Left-to-Rigt:\n" + niza2.toString());
        System.out.println("Right-to-Left:\n" + niza2.toStringR());
        System.out.println("The size of the Array is: " + niza2.size() + "\n");

        niza2.insertLast(new Integer(5));
        System.out.println("Left-to-Rigt:\n" + niza2.toString());
        System.out.println("Right-to-Left:\n" + niza2.toStringR());
        System.out.println("The size of the Array is: " + niza2.size() + "\n");

        niza2.insertLast(new Integer(5));
        System.out.println("Left-to-Rigt:\n" + niza2.toString());
        System.out.println("Right-to-Left:\n" + niza2.toStringR());
        System.out.println("The size of the Array is: " + niza2.size() + "\n");

        /* ================================================================== */

        niza2.izvadiDupliIPrebroj();
        System.out.println("Left-to-Rigt:\n" + niza2.toString());
        System.out.println("Right-to-Left:\n" + niza2.toStringR());
        System.out.println("The size of the Array is: " + niza2.size() + "\n");

        niza2.deleteList();
        System.out.println("Left-to-Rigt:\n" + niza2.toString());
        System.out.println("Right-to-Left:\n" + niza2.toStringR());
        System.out.println("The size of the Array is: " + niza2.size() + "\n");
    }
}
