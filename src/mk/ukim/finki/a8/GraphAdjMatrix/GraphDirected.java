package mk.ukim.finki.a8.GraphAdjMatrix;

import java.util.NoSuchElementException;
import java.util.Stack;

public class GraphDirected<T> {
    private int numNodes;
    private T nodes[];
    private int adjMatrix[][];

    @SuppressWarnings("unchecked")
    public GraphDirected(int numNodes) {
        this.numNodes = numNodes;
        this.nodes = (T[]) new Object[numNodes];
        this.adjMatrix = new int[numNodes][numNodes];
        for (int i = 0; i < numNodes; ++i)
            for (int j = 0; j < numNodes; ++j)
                adjMatrix[i][j] = 0;
    }

    public GraphDirected(int numNodes, T[] nodes) {
        this.numNodes = numNodes;
        this.nodes = nodes;
        this.adjMatrix = new int[numNodes][numNodes];
        for (int i = 0; i < numNodes; ++i)
            for (int j = 0; j < numNodes; ++j)
                adjMatrix[i][j] = 0;
    }

    public int getNumNodes() {
        return numNodes;
    }

    public int adjacent(int x, int y) {
        return adjMatrix[x][y];
    }

    public void addEdge(int x, int y) {
        adjMatrix[x][y] = 1;
    }

    public void deleteEdge(int x, int y) {
        adjMatrix[x][y] = 0;
    }

    public T getNodeValue(int x) {
        return nodes[x];
    }

    public void setNodeValue(int x, T value) {
        nodes[x] = value;
    }

    public void printNodes() {
        for (int i = 0; i < numNodes; ++i)
            System.out.print(nodes[i] + " ");
    }

    public void DFSSearch(int startingNode) {
        boolean[] visited = new boolean[numNodes];
        for (int i = 0; i < visited.length; ++i)
            visited[i] = false;
        DFSSearchRecursive(startingNode, visited);
    }

    private void DFSSearchRecursive(int node, boolean[] visited) {
        visited[node] = true;
        System.out.println(node + ": " + nodes[node]);
        for (int i = 0; i < numNodes; ++i)
            if (adjacent(node, i) == 1)
                if (!visited[i])
                    DFSSearchRecursive(i, visited);
    }

    public void DFSSearchNonrecursive(int startingNode) {
        boolean visited[] = new boolean[numNodes];
        for (int i = 0; i < numNodes; i++)
            visited[i] = false;
        visited[startingNode] = true;
        System.out.println(startingNode + ": " + nodes[startingNode]);
        Stack<Integer> s = new Stack<>();
        s.push(startingNode);
        while (!s.isEmpty()) {
            int pom = s.peek();
            int pom1 = pom;
            for (int i = 0; i < numNodes; i++) {
                if (adjacent(pom, i) == 1) {
                    pom1 = i;
                    if (!visited[i])
                        break;
                }
            }
            if (!visited[pom1]) {
                visited[pom1] = true;
                System.out.println(pom1 + ": " + nodes[pom1]);
                s.push(pom1);
            } else
                s.pop();
        }
    }

    public void BFSSearch(int startingNode) {
        boolean[] visited = new boolean[numNodes];
        for (int i = 0; i < visited.length; ++i)
            visited[i] = false;
        BFSSearchNonrecursive(startingNode, visited);
    }

    private void BFSSearchNonrecursive(int startingNode, boolean[] visited) {
        visited[startingNode] = true;
        System.out.println(startingNode + ": " + nodes[startingNode]);
        Queue<Integer> q = new LinkedQueue<>();
        q.enqueue(startingNode);
        while (!q.isEmpty()) {
            int pom = q.dequeue();
            for (int i = 0; i < numNodes; i++) {
                if (adjacent(pom, i) == 1) {
                    if (!visited[i]) {
                        visited[i] = true;
                        System.out.println(i + ": " + nodes[i]);
                        q.enqueue(i);
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numNodes; ++i) {
            for (int j = 0; j < numNodes; ++j)
                sb.append(String.format("%d ", adjMatrix[i][j]));
            if (i != numNodes - 1)
                sb.append("\n");
        }
        return sb.toString();
    }

    private interface Queue<T> {
        /* ===== Metodi za pristap: ===== */
        // Vrakja true ako i samo ako redicata e prazena.
        boolean isEmpty();

        // Ja vrakja dolzinata na redicata.
        int size();

        // Go vrakja elementot na vrvot t.e. pocetokot od redicata.
        T peek();

        /* ===== Metodi za transformacija: ===== */
        // Ja prazni redicata.
        void clear();

        // Go dodava x na kraj od redicata.
        void enqueue(T x);

        // Go otstranuva i vrakja pochetniot element na redicata.
        T dequeue();
    }


    private class LinkedQueue<T> implements Queue<T> {
        int length;
        SLLNode<T> front, rear;

        public LinkedQueue() {
            clear();
        }

        public boolean isEmpty() {
            return length == 0;
        }

        public int size() {
            return length;
        }

        public T peek() {
            if (front == null)
                throw new NoSuchElementException();
            return front.data;
        }

        public void clear() {
            front = rear = null;
            length = 0;
        }

        public void enqueue(T x) {
            SLLNode<T> latest = new SLLNode<>(x, null);
            if (rear != null) {
                rear.next = latest;
                rear = latest;
            } else
                front = rear = latest;
            length++;
        }

        public T dequeue() {
            if (front != null) {
                T element = front.data;
                front = front.next;
                if (front == null)
                    rear = null;
                length--;
                return element;
            } else
                throw new NoSuchElementException();
        }

    }

    private class SLLNode<T> {
        private T data;
        private SLLNode<T> next;

        public SLLNode(T data, SLLNode<T> next) {
            this.data = data;
            this.next = next;
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SLLNode<?> sllNode = (SLLNode<?>) o;
            if (data != null ? !data.equals(sllNode.data) : sllNode.data != null) return false;
            return next != null ? next.equals(sllNode.next) : sllNode.next == null;
        }

        @Override
        public int hashCode() {
            int result = data != null ? data.hashCode() : 0;
            result = 31 * result + (next != null ? next.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }
}
