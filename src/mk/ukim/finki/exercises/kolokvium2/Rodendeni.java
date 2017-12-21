package mk.ukim.finki.exercises.kolokvium2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Rodendeni {
    public static void main(String[] args) throws IOException {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(input.readLine());
            CloseBHT<String, String> hash = new CloseBHT<>(n + n / 2);
            for (int i = 0; i < n; ++i) {
                String[] parts = input.readLine().split("\\s+");
                String name = parts[0];
                String[] dateParts = parts[1].split("\\.");
                String month = dateParts[1];
                hash.insert(month + "_" + name, name);
            }
            String monthToSearch = input.readLine();
            System.out.println(hash.searchBucketsByKey(monthToSearch));
        }
    }
}

class CloseBHT<K extends Comparable<K>, E> {
    // An object of class CBHT is a closed-bucket hash table, containing
    // entries of class MapEntry.
    private SLLNode<MapEntry<K, E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CloseBHT(int m) {
        // Construct an empty CBHT with m buckets.
        buckets = (SLLNode<MapEntry<K, E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        return Math.abs(key.toString().split("_")[0].hashCode()) % buckets.length;
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
        insertLast(b, newEntry);
        //buckets[b] = new SLLNode<MapEntry<K, E>>(newEntry, buckets[b]);
    }

    private void insertLast(int b, MapEntry<K, E> newEntry) {
        SLLNode<MapEntry<K, E>> current = buckets[b];
        if (current != null) {
            while (current.succ != null)
                current = current.succ;
            current.succ = new SLLNode<>(newEntry, null);
        } else buckets[b] = new SLLNode<>(newEntry, null);  // insertFirst;
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

    public String searchBucketsByKey(K key) {
        StringBuilder sb = new StringBuilder();
        int b = hash(key);
        SLLNode<MapEntry<K, E>> current = buckets[b];
        while (current != null) {
            sb.append(current.element.value);
            sb.append(" ");
            current = current.succ;
        }
        if (sb.toString().isEmpty())
            return "Nema";
        return sb.toString();
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
