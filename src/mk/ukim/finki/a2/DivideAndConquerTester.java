package mk.ukim.finki.a2;

public class DivideAndConquerTester {
    public static void main(String[] args) {
        DivideAndConquerExamples dac = new DivideAndConquerExamples();

        int a[] = new int[]{9, 2, 4, 6, 0, 8, 7, 3, 1, 5};

        dac.mergeSort(a, 0, 9);

        for (int i = 0; i < 10; i++)
            System.out.print(a[i] + " ");

        System.out.println();

        System.out.printf("%d^%d = %d\n", 2, 9, dac.pow(2, 9));

        int niza[] = new int[]{9, 2, 4, 6, 0, 8, 7, 3, 1, 5};

        DvaNajmali rez = dac.find(niza, 0, 9);

        System.out.println("Dva najmali: " + rez.getA() + " " + rez.getB());
    }
}
