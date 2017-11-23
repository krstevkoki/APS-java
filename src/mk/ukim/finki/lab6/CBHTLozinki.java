package mk.ukim.finki.lab6;

class CBHTLozinki<K extends Comparable<K>, V> {
    private SLLNode<MapEntry<K, V>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHTLozinki(int m) {
        this.buckets = (SLLNode<MapEntry<K, V>>[]) new SLLNode[m];
    }

    public boolean search(K targetKey, V targetValue) {
        int hashCode = hash(targetKey);
        for (SLLNode<MapEntry<K, V>> current = buckets[hashCode]; current != null; current = current.next)
            if (current.element.key.equals(targetKey) && current.element.value.equals(targetValue))
                return true;
        return false;
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

    public void delete(K key) {
        int hashCode = hash(key);
        for (SLLNode<MapEntry<K, V>> predecessor = null, current = buckets[hashCode]; current != null;
             predecessor = current, current = current.next) {
            if (key.equals(current.element.key)) {
                if (predecessor == null)
                    buckets[hashCode] = current.next;
                else
                    predecessor.next = current.next;
                return;
            }
        }
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

