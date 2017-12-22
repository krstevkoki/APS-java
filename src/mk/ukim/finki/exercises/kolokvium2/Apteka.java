package mk.ukim.finki.exercises.kolokvium2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Apteka {
    public static void main(String[] args) throws IOException {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(input.readLine());
            ClosedBHT<String, Lek> hashtable = new ClosedBHT<>(n + n / 2);
            for (int i = 0; i < n; ++i) {
                String[] lineParts = input.readLine().split("\\s+");
                Lek lek = new Lek(lineParts[0], Integer.parseInt(lineParts[1]), Integer.parseInt(lineParts[2]),
                        Integer.parseInt(lineParts[3]));
                hashtable.insert(lek.name, lek);
            }
            String line;
            while ((line = input.readLine()) != null && line.length() > 0 && !line.equals("KRAJ")) {
                SLLNode<MapEntry<String, Lek>> result = hashtable.search(line.toUpperCase());
                int quantity = Integer.parseInt(input.readLine());
                if (result != null) {
                    Lek lek = result.element.value;
                    System.out.println(lek);
                    if (lek.processOrder(quantity))
                        System.out.println("Napravena naracka");
                    else System.out.println("Nema dovolno lekovi");
                } else System.out.println("Nema takov lek");
            }
        }
    }
}

class Lek {
    String name;
    int onPositiveList;
    int price;
    int inStock;

    public Lek(String name, int onPositiveList, int price, int inStock) {
        this.name = name;
        this.onPositiveList = onPositiveList;
        this.price = price;
        this.inStock = inStock;
    }

    public boolean processOrder(int quantity) {
        if (quantity > this.inStock)
            return false;
        this.inStock -= quantity;
        return true;
    }

    @Override
    public String toString() {
        if (onPositiveList == 1)
            return String.format("%s\n%s\n%d\n%d", name, "POZ", price, inStock);
        return String.format("%s\n%s\n%d\n%d", name, "NEG", price, inStock);
    }
}

class ClosedBHT<K extends Comparable<K>, E> {
    // An object of class CBHT is a closed-bucket hash table, containing
    // entries of class MapEntry.
    private SLLNode<MapEntry<K, E>>[] buckets;

    @SuppressWarnings("unchecked")
    public ClosedBHT(int m) {
        // Construct an empty CBHT with m buckets.
        buckets = (SLLNode<MapEntry<K, E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        int c1 = key.toString().charAt(0);
        int c2 = key.toString().charAt(1);
        int c3 = key.toString().charAt(2);
        return Math.abs((29 * (29 * (29 * 0 + c1) + c2) % 102780 + c3)) % buckets.length;
    }

    public SLLNode<MapEntry<K, E>> search(K targetKey) {
        // Find which if any node of this CBHT contains an entry whose key is
        // equal
        // to targetKey. Return a link to that node (or null if there is none).
        int b = hash(targetKey);
        for (SLLNode<MapEntry<K, E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (targetKey.equals(((MapEntry<K, E>) curr.element).key))
                return curr;
        }
        return null;
    }

    public void insert(K key, E val) {        // Insert the entry <key, val> into this CBHT.
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        for (SLLNode<MapEntry<K, E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
                // Make newEntry replace the existing entry ...
                curr.element = newEntry;
                return;
            }
        }
        // Insert newEntry at the front of the 1WLL in bucket b ...
        buckets[b] = new SLLNode<MapEntry<K, E>>(newEntry, buckets[b]);
    }

    public void delete(K key) {
        // Delete the entry (if any) whose key is equal to key from this CBHT.
        int b = hash(key);
        for (SLLNode<MapEntry<K, E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
                if (pred == null)
                    buckets[b] = curr.succ;
                else
                    pred.succ = curr.succ;
                return;
            }
        }
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            for (SLLNode<MapEntry<K, E>> curr = buckets[i]; curr != null; curr = curr.succ) {
                temp += curr.element.toString() + " ";
            }
            temp += "\n";
        }
        return temp;
    }
}
