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

        niza.insertLast(new Integer(45));
        System.out.println(niza.toString());
        System.out.println("The size of the Array is: " + niza.size());

        niza.deleteFirst();
        System.out.println(niza.toString());
        System.out.println("The size of the Array is: " + niza.size());

        niza.delete(niza.find(new Integer(10)));
        System.out.println(niza.toString());
        System.out.println("The size of the Array is: " + niza.size());

        niza.deleteList();
        System.out.println(niza.toString());
    }
}
