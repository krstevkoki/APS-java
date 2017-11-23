package mk.ukim.finki.lab6;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Lozinki {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            CBHTLozinki<String, String> hashTable = new CBHTLozinki<>(N * 2);
            for (int i = 1; i <= N; ++i) {
                String imelozinka = br.readLine();
                String[] pom = imelozinka.split("\\s+");
                hashTable.insert(pom[1], pom[0]);
            }
            String line;
            while (!((line = br.readLine()).equals("KRAJ"))) {
                String[] parts = line.split("\\s+");
                if (hashTable.search(parts[1], parts[0])) {
                    System.out.println("Najaven");
                    return;
                }
                System.out.println("Nenajaven");
            }
        }
    }
}
