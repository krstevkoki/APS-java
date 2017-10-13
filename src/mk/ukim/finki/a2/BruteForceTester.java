package mk.ukim.finki.a2;

public class BruteForceTester {
    public static void main(String[] args) {
        BruteForceExamples bf = new BruteForceExamples();

        Point a[] = new Point[10];

        a[0] = new Point(4, 5.5);
        a[1] = new Point(1.2, 12.3);
        a[2] = new Point(7.9, 9.8);
        a[3] = new Point(4.3, 5.5);
        a[4] = new Point(-9.9, 8.2);
        a[5] = new Point(1.2, 3);
        a[6] = new Point(9, 2.4);
        a[7] = new Point(2, 9.2);
        a[8] = new Point(1, 5.6);
        a[9] = new Point(2, 7);

        System.out.println("Najmalo rastojanie pomegju tockite: " + String.format("%.2f", bf.minDistance(a)));
        System.out.println("Vkupen broj na nacini na napagjanje na dve kralici e: " + bf.differentWaysNumber());
        System.out.println("Minimalen broj na moneti: " + bf.minCoinsNumber(79));
    }
}
