package mk.ukim.finki.a1;

public class ArrayTester {
    public static void main(String[] args) {
        Array<Integer> niza = new Array<>(4);
        niza.set(0, new Integer(2));
        niza.set(1, new Integer(3));
        niza.set(2, new Integer(4));
        niza.set(3, new Integer(5));

        for (int i = 0; i < niza.getLength(); ++i) {
            System.out.println(niza.get(i).toString());
        }

        niza.insert(2, new Integer(3));
        System.out.println();
        for (int i = 0; i < niza.getLength(); ++i) {
            System.out.println(niza.get(i).toString());
        }

        niza.delete(2);
        System.out.println();
        for (int i = 0; i < niza.getLength(); ++i) {
            System.out.println(niza.get(i).toString());
        }

        niza.resize(5);
        niza.set(4, new Integer(4));
        System.out.println();
        for (int i = 0; i < niza.getLength(); ++i) {
            System.out.println(niza.get(i).toString());
        }

        niza.resize(2);
        System.out.println();
        for (int i = 0; i < niza.getLength(); ++i) {
            System.out.println(niza.get(i).toString());
        }

        System.out.println("\nThe element is at index: " + niza.find(new Integer(3)));
    }
}
