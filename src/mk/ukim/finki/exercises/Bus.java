package mk.ukim.finki.exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bus {
    private static void presmetajCena(int N, int M) {
        int cena;
        // ako ima 1 vozrasen mora da plate za site deca (min == max)
        if (N == 1) {
            cena = N * 100 + (M - 1) * 100;
            System.out.println(cena);
            System.out.println(cena);
        } else if (M == 1) {
            // ako ima 1 dete nema da plate (min == max)
            cena = N * 100 + (M - 1) * 100;
            System.out.println(cena);
            System.out.println(cena);
        } else if (M == 0) {
            // ako nema deca samo vozrasnite plakjaat (min == max)
            cena = N * 100;
            System.out.println(cena);
            System.out.println(cena);
        } else {
            int maxCena = N * 100 + (M - 1) * 100;  // sekogash maksimalna cena e koga eden vozrasen kje plate za site deca
            int minCena;
            // ako ima pomalku vozrasni od deca, minimalno kje se plate koga site,
            // osven posledniot, plakjaat za edno dete, dodeka posledniot plakja za ostanatite M - N deca
            if (N <= M) {
                M -= N;
                minCena = N * 100 + M * 100;
            } else  // ako ima povekje vozrasni od deca, minimalno kje platat koga sekoj vozrasen plakja za edno dete
                minCena = N * 100;
            System.out.println(minCena);
            System.out.println(maxCena);
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            int M = Integer.parseInt(br.readLine());
            presmetajCena(N, M);
        }
    }
}
