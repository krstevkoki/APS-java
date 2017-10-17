package mk.ukim.finki.lab1;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        int n;
        Scanner in = new Scanner(System.in);
        n = in.nextInt();

        Patuvanje nizaPatuvanje[] = new Patuvanje[n];

        for (int i = 0; i < n; i++) {
            int tip = in.nextInt();
            if (tip == 0) {
                String ime = in.next();
                int cena = in.nextInt();
                int vreme = in.nextInt();
                nizaPatuvanje[i] = new GodishenOdmor(ime, cena, vreme);
            } else {
                String ime = in.next();
                int cena = in.nextInt();
                int pocD = in.nextInt();
                int pocM = in.nextInt();
                int krajD = in.nextInt();
                int krajM = in.nextInt();
                nizaPatuvanje[i] = new PraznicnoPatuvanje(ime, cena, pocD, pocM, krajD, krajM);

            }
        }
        // решение на барање 1
        for (Patuvanje patuvanje : nizaPatuvanje) {
            if (patuvanje instanceof PraznicnoPatuvanje) {
                PraznicnoPatuvanje tempPatuvanje = (PraznicnoPatuvanje) patuvanje;
                if (tempPatuvanje.getStartMonth() == 6)
                    System.out.print(tempPatuvanje.toString() + " ");
            }
        }

        System.out.println();

        // решение на барање 2
        double averageTravelTime = 0;
        for (Patuvanje patuvanje : nizaPatuvanje)
            averageTravelTime += patuvanje.vratiVremeVoDenovi();
        System.out.println(averageTravelTime / nizaPatuvanje.length);

        // решение на барање 3
        String name = in.next();
        int price = in.nextInt();
        int duration = in.nextInt();
        Patuvanje odmor = new GodishenOdmor(name, price, duration);

        // решение на барање 4
        System.out.println(Patuvanje.vratiMinCena(nizaPatuvanje, nizaPatuvanje.length, odmor));
    }
}
