package mk.ukim.finki.a1;

public class SLLTester {
    public static void main(String[] args) {
        SLL<Integer> niza = new SLL<>();
        System.out.println("The size of the Array is: " + niza.size());

        niza.insertLast(new Integer(10));
        System.out.println(niza.toString());
        System.out.println("The size of the Array is: " + niza.size());

        niza.insertFirst(new Integer(34));
        System.out.println(niza.toString());
        System.out.println("The size of the Array is: " + niza.size());

        niza.insertAfter(new Integer(23), niza.getFirst());
        System.out.println(niza.toString());
        System.out.println("The size of the Array is: " + niza.size());

        niza.insertBefore(new Integer(24), niza.getFirst());
        System.out.println(niza.toString());
        System.out.println("The size of the Array is: " + niza.size());

        niza.insertBefore(new Integer(18), niza.find(new Integer(34)));
        System.out.println(niza.toString());
        System.out.println("The size of the Array is: " + niza.size());

        niza.insertAfter(new Integer(75), niza.find(new Integer(10)));
        System.out.println(niza.toString());
        System.out.println("The size of the Array is: " + niza.size());

        niza.insertLast(new Integer(45));
        System.out.println(niza.toString());
        System.out.println("The size of the Array is: " + niza.size());

        niza.deleteFirst();
        System.out.println(niza.toString());
        System.out.println("The size of the Array is: " + niza.size());

        niza.delete(niza.find(new Integer(18)));
        System.out.println(niza.toString());
        System.out.println("The size of the Array is: " + niza.size());

        niza.delete(new SLLNode<>(0, null));
        System.out.println(niza.toString());
        System.out.println("The size of the Array is: " + niza.size());

        niza.mirror();
        System.out.println(niza.toString());
        System.out.println("The size of the Array is: " + niza.size());

        SLL<Integer> niza2 = new SLL<>();
        niza2.insertFirst(new Integer(60));
        niza2.insertBefore(new Integer(32), niza2.getFirst());
        niza2.insertLast(new Integer(69));
        niza2.insertAfter(new Integer(58), niza2.find(69));
        System.out.println(niza2.toString());
        System.out.println("The size of the Array is: " + niza2.size());

        niza.merge(niza2);
        System.out.println(niza.toString());
        System.out.println("The size of the Array is: " + niza.size());

        niza.deleteList();
        System.out.println(niza.toString());

        niza2.deleteList();
        System.out.println(niza2.toString());
    }
}
