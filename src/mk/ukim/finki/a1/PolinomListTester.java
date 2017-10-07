package mk.ukim.finki.a1;

class PolinomListTester {
    public static void main(String[] args) {
        SLL<Monom> k1 = new SLL<>();
        k1.insertLast(new Monom(2, 4));
        k1.insertLast(new Monom(3, 0));
        System.out.println("Polinom1: " + k1.toString());
        PolinomList p1 = new PolinomList(k1);

        SLL<Monom> k2 = new SLL<>();
        k2.insertLast(new Monom(1, 3));
        k2.insertLast(new Monom(2, 2));
        k2.insertLast(new Monom(8, 0));
        System.out.println("Polinom2: " + k2.toString());
        PolinomList p2 = new PolinomList(k2);

        System.out.println("Rezultat od zbir: " + p1.soberi(p2));
    }
}
