package mk.ukim.finki.lab10;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Stack;

class GraphDirected<T> {
    private int numNodes;
    private GraphNode<T>[] adjList;

    @SuppressWarnings("unchecked")
    public GraphDirected(int numNodes) {
        this.numNodes = numNodes;
        this.adjList = (GraphNode<T>[]) new GraphNode[numNodes];
    }

    @SuppressWarnings("unchecked")
    public GraphDirected(int numNodes, T[] list) {
        this.numNodes = numNodes;
        this.adjList = (GraphNode<T>[]) new GraphNode[numNodes];
        for (int i = 0; i < numNodes; ++i)
            this.adjList[i] = new GraphNode<>(i, list[i]);
    }

    public int getNumNodes() {
        return numNodes;
    }

    public GraphNode<T>[] getAdjList() {
        return adjList;
    }

    public void setNodeAt(int x, T node) {
        adjList[x].data = node;
    }

    public int adjacent(int x, int y) {
        return adjList[x].containsNeighbour(adjList[y]) ? 1 : 0;
    }

    public void addEdge(int x, int y) {
        adjList[x].addNeighbour(adjList[y]);
    }

    public void deleteEdge(int x, int y) {
        adjList[x].removeNeighbour(adjList[y]);
    }

    public void DFSSearch(int startingNode) {
        boolean[] visited = new boolean[numNodes];
        for (int i = 0; i < visited.length; ++i)
            visited[i] = false;
        DFSSearchRecursive(startingNode, visited);
    }

    private void DFSSearchRecursive(int node, boolean[] visited) {
        visited[node] = true;
        System.out.println(node + ": " + adjList[node].data);
        for (int i = 0; i < adjList[node].neighbours.size(); ++i) {
            GraphNode<T> temp = adjList[node].neighbours.get(i);
            if (!visited[temp.index])
                DFSSearchRecursive(temp.index, visited);
        }
    }

    public void DFSSearchNonrecursive(int startingNode) {
        boolean visited[] = new boolean[numNodes];
        for (int i = 0; i < numNodes; i++)
            visited[i] = false;
        visited[startingNode] = true;
        System.out.println(startingNode + ": " + adjList[startingNode].data);
        Stack<Integer> s = new Stack<>();
        s.push(startingNode);
        while (!s.isEmpty()) {
            GraphNode<T> pom = adjList[s.peek()];
            GraphNode<T> tmp = null;
            for (int i = 0; i < pom.neighbours.size(); ++i) {
                tmp = pom.neighbours.get(i);
                if (!visited[tmp.index])
                    break;
            }
            if (tmp != null && !visited[tmp.index]) {
                visited[tmp.index] = true;
                System.out.println(tmp.index + ": " + tmp.data);
                s.push(tmp.index);
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
        System.out.println(startingNode + ": " + adjList[startingNode].data);
        Queue<Integer> q = new LinkedQueue<>();
        q.enqueue(startingNode);
        while (!q.isEmpty()) {
            GraphNode<T> pom = adjList[q.dequeue()];
            GraphNode<T> tmp = null;
            for (int i = 0; i < pom.neighbours.size(); i++) {
                tmp = pom.neighbours.get(i);
                if (!visited[tmp.index]) {
                    visited[tmp.index] = true;
                    System.out.println(tmp.index + ": " + tmp.data);
                    q.enqueue(tmp.index);
                }
            }
        }
    }

    public LinkedList<GraphNode<T>> getNeighbours(int x) {
        return adjList[x].neighbours;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numNodes; ++i)
            sb.append(String.format("%d: %s\n", adjList[i].index, adjList[i].data));
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

    public class GraphNode<T> {
        private int index;  // reden broj na temeto vo grafot
        private T data;
        private LinkedList<GraphNode<T>> neighbours;

        public GraphNode(int index, T data) {
            this.index = index;
            this.data = data;
            this.neighbours = new LinkedList<>();
        }

        public boolean containsNeighbour(GraphNode<T> node) {
            return neighbours.contains(node);
        }

        public void addNeighbour(GraphNode<T> node) {
            if (!containsNeighbour(node))
                neighbours.add(node);
        }

        public void removeNeighbour(GraphNode<T> node) {
            neighbours.remove(node);
        }

        public T getData() {
            return data;
        }

        public int getIndex() {
            return index;
        }

        public LinkedList<GraphNode<T>> getNeighbours() {
            return neighbours;
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GraphNode<?> graphNode = (GraphNode<?>) o;
            if (index != graphNode.index) return false;
            if (data != null ? !data.equals(graphNode.data) : graphNode.data != null) return false;
            return neighbours != null ? neighbours.equals(graphNode.neighbours) : graphNode.neighbours == null;
        }

        @Override
        public int hashCode() {
            int result = index;
            result = 31 * result + (data != null ? data.hashCode() : 0);
            result = 31 * result + (neighbours != null ? neighbours.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%s", "DATA: " + data + " SOSEDI:"));
            for (GraphNode<T> neighbour : neighbours)
                sb.append(String.format("%s ", neighbour.data));
            return sb.toString();
        }
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
