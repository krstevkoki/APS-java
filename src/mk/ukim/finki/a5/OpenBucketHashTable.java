package mk.ukim.finki.a5;

import java.util.NoSuchElementException;

public class OpenBucketHashTable<K extends Comparable<K>, V> implements Cloneable {
    private final MapEntry<K, V> former = new MapEntry<>(null, null);
    private MapEntry<K, V>[] buckets;

    @SuppressWarnings("unchecked")
    public OpenBucketHashTable(int m) {
        this.buckets = (MapEntry<K, V>[]) new MapEntry[m];
    }

    public void insert(K key, V value) {
        MapEntry<K, V> newEntry = new MapEntry<>(key, value);
        int hashCode = hash(newEntry.key);
        int count = 0;
        while (true) {
            MapEntry<K, V> oldEntry = buckets[hashCode];
            if (oldEntry == null) {
                buckets[hashCode] = newEntry;
                return;
            } else if (oldEntry.equals(former) || oldEntry.key.equals(key)) {
                buckets[hashCode] = newEntry;
                return;
            } else {
                hashCode = (hashCode + 1) % buckets.length;
                if (++count == buckets.length)
                    return;
            }
        }
    }

    public void delete(K key) {
        int hashCode = hash(key);
        int count = 0;
        while (true) {
            MapEntry<K, V> oldEntry = buckets[hashCode];
            if (oldEntry == null)
                return;
            else if (oldEntry.equals(former)) {
                hashCode = (hashCode + 1) % buckets.length;
                if (++count == buckets.length)
                    return;
            } else if (oldEntry.key.equals(key)) {
                buckets[hashCode] = former;
                return;
            } else {
                hashCode = (hashCode + 1) % buckets.length;
                if (++count == buckets.length)
                    return;
            }
        }
    }

    // returns the element, with key targetKey, in the buckets
    public MapEntry<K, V> search(K targetKey) {
        int hashCode = hash(targetKey);
        int count = 0;
        while (true) {
            MapEntry<K, V> oldEntry = buckets[hashCode];
            if (oldEntry == null)
                throw new NoSuchElementException(targetKey.toString());
            else if (oldEntry.key.equals(targetKey))
                return oldEntry;
            else {
                hashCode = (hashCode + 1) % buckets.length;
                if (++count == buckets.length)
                    throw new NoSuchElementException(targetKey.toString());
            }
        }
    }

    // returns the index of the targetKey in the buckets
    public int find(K targetKey) {
        int hashCode = hash(targetKey);
        int count = 0;
        while (true) {
            MapEntry<K, V> oldEntry = buckets[hashCode];
            if (oldEntry == null)
                return -1;
            else if (oldEntry.key.equals(targetKey))
                return hashCode;
            else {
                hashCode = (hashCode + 1) % buckets.length;
                if (++count == buckets.length)
                    return -1;
            }
        }
    }

    public OpenBucketHashTable<K, V> clone() {
        OpenBucketHashTable<K, V> copy = new OpenBucketHashTable<>(buckets.length);
        for (int i = 0; i < buckets.length; ++i) {
            MapEntry<K, V> cp = buckets[i];
            if (cp != null && !(cp.equals(former)))
                copy.buckets[i] = new MapEntry<>(cp.key, cp.value);
            else
                copy.buckets[i] = cp;
        }
        return copy;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < buckets.length; ++i) {
            sb.append(String.format("%d:", i));
            if (buckets[i] == null)
                sb.append("\n");
            else if (buckets[i].equals(former))
                sb.append("former\n");
            else
                sb.append(String.format("%s\n", buckets[i]));
        }
        return sb.toString();
    }

    /* ============ Private Inner Classes  ============ */
    private class MapEntry<K extends Comparable<K>, V> implements Comparable<MapEntry<K, V>> {
        private K key;
        private V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(MapEntry<K, V> o) {
            return this.key.compareTo(o.key);
        }

        @Override
        public String toString() {
            return String.format("<%s, %s>", key.toString(), value.toString());
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MapEntry<?, ?> mapEntry = (MapEntry<?, ?>) o;
            if (key != null && mapEntry.key == null) return false;
            if (key != null ? !key.equals(mapEntry.key) : mapEntry.key != null) return false;
            return value != null ? value.equals(mapEntry.value) : mapEntry.value == null;
        }
    }
}
