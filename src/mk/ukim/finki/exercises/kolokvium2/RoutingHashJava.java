package mk.ukim.finki.exercises.kolokvium2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RoutingHashJava {
    public static void main(String[] args) throws IOException {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(input.readLine());
            CBHT<String, String> hashtable = new CBHT<>(n + n / 2);
            for (int i = 0; i < n; ++i) {
                String routerInterfaceIP = input.readLine();
                String networkIP = input.readLine();
                hashtable.insert(routerInterfaceIP, networkIP);
            }
            n = Integer.parseInt(input.readLine());
            for (int i = 0; i < n; ++i) {
                String routerInterfaceIP = input.readLine();
                String hostIP = input.readLine();
                hostIP = hostIP.substring(0, hostIP.lastIndexOf('.'));
                SLLNode<MapEntry<String, String>> result = hashtable.search(routerInterfaceIP);
                if (result != null) {
                    String networkIP = result.element.value.substring(0, result.element.value.lastIndexOf('.'));
                    if (networkIP.equals(hostIP))
                        System.out.println("postoi");
                } else System.out.println("ne postoi");
            }
        }
    }
}
