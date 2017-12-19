package mk.ukim.finki.exercises.kolokvium2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KumanovskiDijalekt {
    private static String capitalize(char[] charArray) {
        char c = charArray[0];
        charArray[0] = Character.toUpperCase(c);
        return new String(charArray);
    }

    private static void printTranslated(String text, CBHT<String, String> hashtable) {
        String[] parts = text.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parts.length; ++i) {
            if (Character.isAlphabetic(parts[i].charAt(parts[i].length() - 1))) {
                SLLNode<MapEntry<String, String>> string = hashtable.search(parts[i].toLowerCase());
                if (string != null) {
                    if (sb.toString().length() - 2 < 0)
                        sb.append(capitalize(string.element.value.toCharArray()));
                    else if (sb.toString().charAt(sb.toString().length() - 2) == '.')
                            sb.append(capitalize(string.element.value.toCharArray()));
                    else
                        sb.append(string.element.value);
                    sb.append(" ");
                } else {
                    sb.append(parts[i]);
                    sb.append(" ");
                }
            } else {
                char removedChar = parts[i].charAt(parts[i].length() - 1);
                parts[i] = parts[i].substring(0, parts[i].length() - 1);
                SLLNode<MapEntry<String, String>> string = hashtable.search(parts[i].toLowerCase());
                if (string != null) {
                    sb.append(string.element.value);
                    sb.append(removedChar);
                    sb.append(" ");
                } else {
                    sb.append(parts[i]);
                    sb.append(removedChar);
                    sb.append(" ");
                }
            }
        }
        text = sb.toString();
        System.out.println(text);
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            String[] rechnik = new String[N];
            for (int i = 0; i < N; i++)
                rechnik[i] = br.readLine();
            String tekst = br.readLine();
            // Vasiot kod tuka
            if (N != 0) {
                CBHT<String, String> hashtable = new CBHT<>(N + N / 2);
                for (int i = 0; i < N; ++i) {
                    String[] parts = rechnik[i].split("\\s+");
                    hashtable.insert(parts[0], parts[1]);
                }
                printTranslated(tekst, hashtable);
            } else System.out.println(tekst);
        }
    }
}

class MapEntry<K extends Comparable<K>, E> implements Comparable<K> {
    // Each MapEntry object is a pair consisting of a key (a Comparable
    // object) and a value (an arbitrary object).
    K key;
    E value;

    public MapEntry(K key, E val) {
        this.key = key;
        this.value = val;
    }

    public int compareTo(K that) {
        // Compare this map entry to that map entry.
        @SuppressWarnings("unchecked")
        MapEntry<K, E> other = (MapEntry<K, E>) that;
        return this.key.compareTo(other.key);
    }

    public String toString() {
        return "<" + key + "," + value + ">";
    }
}

class SLLNode<E> {
    E element;
    SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class CBHT<K extends Comparable<K>, E> {
    // An object of class CBHT is a closed-bucket hash table, containing
    // entries of class MapEntry.
    private SLLNode<MapEntry<K, E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        // Construct an empty CBHT with m buckets.
        buckets = (SLLNode<MapEntry<K, E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
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
