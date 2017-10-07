package mk.ukim.finki.a1;

public class JoinSortedListsTester {
    public static void main(String[] args) {
        SLL<String> lista1 = new SLL<>();
        lista1.insertLast("Ana");
        lista1.insertLast("Bojana");
        lista1.insertLast("Dejan");
        /* ========================== */
        SLL<String> lista2 = new SLL<>();
        lista2.insertLast("Andrijana");
        lista2.insertLast("Biljana");
        lista2.insertLast("Darko");

        JoinSortedLists<String> js = new JoinSortedLists<>();
        js.join(lista1, lista2);
        System.out.println(js.toString());

        js.getResult().mirror();
        System.out.println(js.toString());
    }
}
