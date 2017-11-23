package mk.ukim.finki.lab6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Apteka {
    public static void main(String[] args) throws IOException {
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(bf.readLine());
            CBHTApteka<Lekovi, Integer> hashTable = new CBHTApteka<>(n * 2 + 1);
            for (int i = 1; i <= n; ++i) {
                String[] parts = bf.readLine().split("\\s+");
                hashTable.insert(new Lekovi(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]),
                        Integer.parseInt(parts[3])), i);
            }
            String line;
            while (!((line = bf.readLine()).equals("KRAJ"))) {
                Lekovi lek = hashTable.search(new Lekovi(line, -1, -1, -1));
                if (lek == null) {
                    System.out.println("Nema takov lek");
                    bf.readLine();
                }
                else {
                    int quantity = Integer.parseInt(bf.readLine());
                    System.out.println(lek);
                    if (quantity > lek.getInStock())
                        System.out.println("Nema dovolno lekovi");
                    else {
                        lek.setInStock(lek.getInStock() - quantity);
                        System.out.println("Napravena naracka");
                    }
                }
            }
        }
    }
}
