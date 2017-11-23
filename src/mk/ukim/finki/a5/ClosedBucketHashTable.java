package mk.ukim.finki.a5;

import java.util.NoSuchElementException;

public class ClosedBucketHashTable<K extends Comparable<K>, V> {
    private SLLNode<MapEntry<K, V>>[] buckets;

    @SuppressWarnings("unchecked")
    public ClosedBucketHashTable(int m) {
        this.buckets = (SLLNode<MapEntry<K, V>>[]) new SLLNode[m];
    }

    public void insert(K key, V value) {
        MapEntry<K, V> newEntry = new MapEntry<>(key, value);
        int hashCode = hash(key);
        for (SLLNode<MapEntry<K, V>> current = buckets[hashCode]; current != null; current = current.next) {
            if (newEntry.key.equals(current.element.key)) {
                current.element = newEntry;
                return;
            }
        }
        buckets[hashCode] = new SLLNode<>(newEntry, buckets[hashCode]);
    }

    public SLLNode<MapEntry<K, V>> search(K targetKey) {
        int hashCode = hash(targetKey);
        for (SLLNode<MapEntry<K, V>> current = buckets[hashCode]; current != null; current = current.next)
            if (current.element.key.equals(targetKey))
                return current;
        throw new NoSuchElementException(targetKey.toString());
    }

    public void delete(K key) {
        int hashCode = hash(key);
        SLLNode<MapEntry<K, V>> target = search(key);
        SLLNode<MapEntry<K, V>> current = buckets[hashCode];
        while (!(current.next.equals(target)) && current.next != null)
            current = current.next;
        if (current.next.equals(target))
            current.next = target.next;
        else throw new NoSuchElementException(key.toString());
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < buckets.length; ++i) {
            sb.append(String.format("%d:", i));
            for (SLLNode<MapEntry<K, V>> current = buckets[i]; current != null; current = current.next)
                sb.append(String.format("%s ", current));
            sb.append("\n");
        }
        return sb.toString();
    }

    private class MapEntry<T extends Comparable<T>, E> implements Comparable<MapEntry<T, E>> {
        private T key;
        private E value;

        public MapEntry(T key, E value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(MapEntry<T, E> o) {
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
            if (key != null ? !key.equals(mapEntry.key) : mapEntry.key != null) return false;
            return value != null ? value.equals(mapEntry.value) : mapEntry.value == null;
        }
    }

    private class SLLNode<T> {
        private T element;
        private SLLNode<T> next;

        public SLLNode(T element, SLLNode<T> next) {
            this.element = element;
            this.next = next;
        }

        @Override
        public String toString() {
            return element.toString();
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SLLNode<?> sllNode = (SLLNode<?>) o;
            if (element != null ? !element.equals(sllNode.element) : sllNode.element != null) return false;
            return next != null ? next.equals(sllNode.next) : sllNode.next == null;
        }
    }
}
