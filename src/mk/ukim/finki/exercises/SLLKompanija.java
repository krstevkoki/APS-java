package mk.ukim.finki.exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SLLKompanija {
    public static void main(String[] args) throws IOException {
        try (BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in))) {
            SLL lista1 = new SLL();
            String s = stdin.readLine();
            int N = Integer.parseInt(s);
            for (int i = 0; i < N; i++) {
                s = stdin.readLine();
                String s1 = stdin.readLine();
                lista1.insertLast(Integer.parseInt(s), Integer.parseInt(s1));
            }
            s = stdin.readLine();
            try {
                lista1 = lista1.brisi_pomali_od(Integer.parseInt(s));
                if (lista1 != null) {
                    lista1 = lista1.sortiraj_opagacki();
                    lista1.pecati(lista1);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
