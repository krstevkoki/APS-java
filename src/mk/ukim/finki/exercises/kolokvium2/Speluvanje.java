package mk.ukim.finki.exercises.kolokvium2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Speluvanje {
    private static void printIncorrectWords(String text, OBHT<Zbor, String> tabela) {
        String[] parts = text.toLowerCase().split("\\s+");
        boolean haveIncorrect = false;
        for (int i = 0; i < parts.length; ++i) {
            String word = parts[i];
            if (Character.isAlphabetic(word.charAt(word.length() - 1))) {
                int index = tabela.search(new Zbor(word));  // bucket index
                if (index >= 0) {
                    if (!word.equals(tabela.getBucket(index).value)) {
                        haveIncorrect = true;
                        System.out.println(word);
                    }
                } else {
                    haveIncorrect = true;
                    System.out.println(word);
                }
            } else {  // ako e interpunkciski znak posledniot znak
                word = word.substring(0, word.length() - 1);
                if (word.isEmpty())
                    break;
                int index = tabela.search(new Zbor(word));
                if (index >= 0) {
                    if (!word.equals(tabela.getBucket(index).value)) {
                        haveIncorrect = true;
                        System.out.println(word);
                    }
                } else {
                    haveIncorrect = true;
                    System.out.println(word);
                }
            }
        }
        if (!haveIncorrect)
            System.out.println("Bravo");
    }

    public static void main(String[] args) throws IOException {
        OBHT<Zbor, String> tabela;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            //---Vie odluchete za goleminata na hesh tabelata----
            tabela = new OBHT<Zbor, String>(N + N / 2);
            /*
             *
             * Vashiot kod tuka....
             *
             */
            for (int i = 0; i < N; ++i) {
                Zbor word = new Zbor(br.readLine());
                tabela.insert(word, word.zbor);
            }
            String text = br.readLine();
            printIncorrectWords(text, tabela);
        }
    }
}

class OBHT<K extends Comparable<K>, E> {
    private MapEntry<K, E>[] buckets;
    static final int NONE = -1; // ... distinct from any bucket index.
    @SuppressWarnings({"rawtypes", "unchecked"})
    private static final MapEntry former = new MapEntry(null, null);
    private int occupancy = 0;

    @SuppressWarnings("unchecked")
    public OBHT(int m) {
        buckets = (MapEntry<K, E>[]) new MapEntry[m];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public MapEntry<K, E> getBucket(int i) {
        return buckets[i];
    }

    public int search(K targetKey) {
        int b = hash(targetKey);
        int n_search = 0;
        for (; ; ) {
            MapEntry<K, E> oldEntry = buckets[b];
            if (oldEntry == null)
                return NONE;
            else if (targetKey.equals(oldEntry.key))
                return b;
            else {
                b = (b + 1) % buckets.length;
                n_search++;
                if (n_search == buckets.length)
                    return NONE;
            }
        }
    }

    public void insert(K key, E val) {
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        int n_search = 0;

        for (; ; ) {
            MapEntry<K, E> oldEntry = buckets[b];
            if (oldEntry == null) {
                if (++occupancy == buckets.length) {
                    System.out.println("Hash tabelata e polna!!!");
                }
                buckets[b] = newEntry;
                return;
            } else if (oldEntry == former
                    || key.equals(oldEntry.key)) {
                buckets[b] = newEntry;
                return;
            } else {
                b = (b + 1) % buckets.length;
                n_search++;
                if (n_search == buckets.length)
                    return;

            }
        }
    }

    @SuppressWarnings("unchecked")
    public void delete(K key) {
        int b = hash(key);
        int n_search = 0;
        for (; ; ) {
            MapEntry<K, E> oldEntry = buckets[b];

            if (oldEntry == null)
                return;
            else if (key.equals(oldEntry.key)) {
                buckets[b] = former;
                return;
            } else {
                b = (b + 1) % buckets.length;
                n_search++;
                if (n_search == buckets.length)
                    return;

            }
        }
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            if (buckets[i] == null)
                temp += "\n";
            else if (buckets[i] == former)
                temp += "former\n";
            else
                temp += buckets[i] + "\n";
        }
        return temp;
    }
}


class Zbor implements Comparable<Zbor> {
    String zbor;

    public Zbor(String zbor) {
        this.zbor = zbor;
    }

    @Override
    public boolean equals(Object obj) {
        Zbor pom = (Zbor) obj;
        return this.zbor.equals(pom.zbor);
    }

    @Override
    public int hashCode() {
        /*
         *
         * Vie ja kreirate hesh funkcijata
         *
         */
        return zbor.hashCode();
    }

    @Override
    public String toString() {
        return zbor;
    }

    @Override
    public int compareTo(Zbor arg0) {
        return zbor.compareTo(arg0.zbor);
    }
}

