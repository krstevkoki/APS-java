package mk.ukim.finki.exercises.kolokvium2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Lozinki {
    public static void main(String[] args) throws IOException {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(input.readLine());
            CBHT<String, String> hashtable = new CBHT<>(n + n / 2);
            for (int i = 0; i < n; ++i) {
                String[] lineParts = input.readLine().split("\\s+");
                hashtable.insert(lineParts[0], lineParts[1]);
            }
            String line;
            System.out.println(hashtable);
            while ((line = input.readLine()) != null && line.length() > 0 && !line.equals("KRAJ")) {
                String[] lineParts = line.split("\\s+");
                SLLNode<MapEntry<String, String>> result = hashtable.search(lineParts[0]);
                System.out.println(result);
                if (result != null && result.element.value.equals(lineParts[1]))
                    System.out.println("Najaven");
                else System.out.println("Nenajaven");
            }
        }
    }
}
