package mk.ukim.finki.a1;

public class PolinomTester {
    public static void main(String[] args) {
        Array<Integer> n1 = new Array<>(10);
        Array<Integer> n2 = new Array<>(10);
        /* 2x^4 + 3 */
        n1.set(0, 2);
        n1.set(1, 4);
        n1.set(2, 2);
        n1.set(3, 0);
        n1.set(4, 3);
        /* ========== */
        n2.set(0, 3);
        n2.set(1, 3);
        n2.set(2, 1);
        n2.set(3, 2);
        n2.set(4, 2);
        n2.set(5, 0);
        n2.set(6, 8);
        /* x^3 + 2x^2 + 8 */
        Polinom a = new Polinom(n1);
        Polinom b = new Polinom(n2);
        Polinom c = a.soberi(b);
        System.out.print(c.toString());
        Polinom d = c.soberi(a);
        System.out.println("\n" + d.toString());
    }
}
