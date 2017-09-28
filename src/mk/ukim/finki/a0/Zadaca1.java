package mk.ukim.finki.a0;

import java.util.Scanner;

public class Zadaca1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Izbor: 1-Krug   2-Kvadrat");
        short choice = input.nextShort();
        if (choice == 1) {
            Scanner numDouble = new Scanner(System.in);
            System.out.println("Vnesete radius na Krug:");
            System.out.print("R = ");
            double r = numDouble.nextDouble();
            double plostinaNaKrug = r * r * Math.PI;
            double perimetarNaKrug = 2 * r * Math.PI;
            System.out.println("Perimetarot na Krug so radius " + r + " e: " + perimetarNaKrug);
            System.out.println("Plostinata na Krug so radius " + r + " e: " + plostinaNaKrug);
        } else if (choice == 2) {
            Scanner numInt = new Scanner(System.in);
            System.out.println("Vnesete strana na Kvadrat:");
            System.out.print("a = ");
            int a = numInt.nextInt();
            int plostinaNaKvadrat = a * a;
            int perimetarNaKvadrat = 4 * a;
            System.out.println("Perimetarot na Kvadrat so strana " + a + " e: " + perimetarNaKvadrat);
            System.out.println("Plostinata na Kvadrat so strana " + a + " e: " + plostinaNaKvadrat);
        } else {
            System.out.println("Nevaliden izbor!");
        }
    }
}
