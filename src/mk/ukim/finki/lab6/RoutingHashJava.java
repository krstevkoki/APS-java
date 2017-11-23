package mk.ukim.finki.lab6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RoutingHashJava {
    public static void main(String[] args) throws IOException {
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(bf.readLine());
            CBHTRouters<String, String[]> hashTable = new CBHTRouters<>(2 * n + 1);
            for (int i = 0; i < n; ++i) {
                String interfaceIP = bf.readLine();
                String[] networks = bf.readLine().split(",");
                hashTable.insert(interfaceIP, networks);
            }
            n = Integer.parseInt(bf.readLine());
            for (int i = 0; i < n; ++i) {
                String interfaceIP = bf.readLine();
                String network = bf.readLine();
                if (hashTable.search(interfaceIP, network))
                    System.out.println("postoi");
                else
                    System.out.println("ne postoi");
            }
        }
    }
}
