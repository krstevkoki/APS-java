package mk.ukim.finki.exercises.kolokvium2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Preveduvac {
    public static void main(String[] args) throws IOException {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(input.readLine());
            OBHT<String, String> hashtable = new OBHT<>(n + n / 2);
            for (int i = 0; i < n; ++i) {
                String[] lineParts = input.readLine().split("\\s+");
                hashtable.insert(lineParts[1], lineParts[0]);
            }
            String line;
            while ((line = input.readLine()) != null && line.length() > 0 && !line.equals("KRAJ")) {
                int bucketIndex = hashtable.search(line);
                if (bucketIndex != -1)
                    System.out.println(hashtable.getBucket(bucketIndex).value);
            }
        }
    }
}
