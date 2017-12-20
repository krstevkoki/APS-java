package mk.ukim.finki.exercises.kolokvium2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.LinkedList;

// Implementacija na redica
interface Queue<E> {
    // Elementi na redicata se objekti od proizvolen tip.
    // Metodi za pristap:

    boolean isEmpty();
    // Vrakja true ako i samo ako redicata e prazena.

    int size();
    // Ja vrakja dolzinata na redicata.

    E peek();
    // Go vrakja elementot na vrvot t.e. pocetokot od redicata.

    // Metodi za transformacija:

    void clear();
    // Ja prazni redicata.

    void enqueue(E x);
    // Go dodava x na kraj od redicata.

    E dequeue();
    // Go otstranuva i vrakja pochetniot element na redicata.
}

class LinkedQueue<E> implements Queue<E> {
    // Redicata e pretstavena na sledniot nacin:
    // length go sodrzi brojot na elementi.
    // Elementite se zachuvuvaat vo jazli dod SLL
    // front i rear se linkovi do prviot i posledniot jazel soodvetno.
    SLLNode<E> front, rear;
    int length;

    // Konstruktor ...
    public LinkedQueue() {
        clear();
    }

    public boolean isEmpty() {
        // Vrakja true ako i samo ako redicata e prazena.
        return (length == 0);
    }

    public int size() {
        // Ja vrakja dolzinata na redicata.
        return length;
    }

    public E peek() {
        // Go vrakja elementot na vrvot t.e. pocetokot od redicata.
        if (front == null)
            throw new NoSuchElementException();
        return front.element;
    }

    public void clear() {
        // Ja prazni redicata.
        front = rear = null;
        length = 0;
    }

    public void enqueue(E x) {
        // Go dodava x na kraj od redicata.
        SLLNode<E> latest = new SLLNode<E>(x, null);
        if (rear != null) {
            rear.succ = latest;
            rear = latest;
        } else
            front = rear = latest;
        length++;
    }

    public E dequeue() {
        // Go otstranuva i vrakja pochetniot element na redicata.
        if (front != null) {
            E frontmost = front.element;
            front = front.succ;
            if (front == null) rear = null;
            length--;
            return frontmost;
        } else
            throw new NoSuchElementException();
    }
}

class GraphNode<E> {
    int index;//index (reden broj) na temeto vo grafot
    private E ime;
    private E prezime;
    LinkedList<GraphNode<E>> neighbors;

    public GraphNode(int index, E ime, E prezime) {
        this.index = index;
        this.ime = ime;
        this.prezime = prezime;
        neighbors = new LinkedList<GraphNode<E>>();
    }

    public GraphNode(int index) {
        this.index = index;
        neighbors = new LinkedList<GraphNode<E>>();
    }

    public boolean containsNeighbor(GraphNode<E> o) {
        return neighbors.contains(o);
    }

    public void addNeighbor(GraphNode<E> o) {
        neighbors.add(o);
    }

    public void removeNeighbor(GraphNode<E> o) {
        if (neighbors.contains(o))
            neighbors.remove(o);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


    public LinkedList<GraphNode<E>> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(LinkedList<GraphNode<E>> neighbors) {
        this.neighbors = neighbors;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("INFO: %s SOSEDI: ", index));
        for (GraphNode<E> neighbor : neighbors)
            sb.append(String.format("%s; ", neighbor.index));
        return sb.toString();
    }
}

class Graph<E> {
    int num_nodes;
    GraphNode<E> adjList[];

    @SuppressWarnings("unchecked")
    public Graph(int num_nodes) {
        this.num_nodes = num_nodes;
        adjList = (GraphNode<E>[]) new GraphNode[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            adjList[i] = new GraphNode<E>(i);
    }

    public int adjacent(int x, int y) {
        // proveruva dali ima vrska od jazelot so
        // indeks x do jazelot so indeks y
        return (adjList[x].containsNeighbor(adjList[y])) ? 1 : 0;
    }

    public void addEdge(int x, int y) {
        // dodava vrska od jazelot so indeks x do jazelot so indeks y
        if (!adjList[x].containsNeighbor(adjList[y]))
            adjList[x].addNeighbor(adjList[y]);
    }

    public void deleteEdge(int x, int y) {
        adjList[x].removeNeighbor(adjList[y]);
    }

    public int[] najdikomponenti(int start) {
        // vasiot kod tuka
        return BFSSearch(start);
    }

    private int[] BFSSearch(int startingNode) {
        boolean[] visited = new boolean[num_nodes];
        for (int i = 0; i < visited.length; ++i)
            visited[i] = false;
        return BFSSearchNonrecursive(startingNode, visited);
    }

    private int[] BFSSearchNonrecursive(int startingNode, boolean[] visited) {
        visited[startingNode] = true;
        Queue<Integer> q = new LinkedQueue<>();
        LinkedList<Integer> komponenti = new LinkedList<>();
        q.enqueue(startingNode);
        komponenti.add(q.peek());
        while (!q.isEmpty()) {
            GraphNode<E> pom = adjList[q.dequeue()];
            GraphNode<E> tmp = null;
            for (int i = 0; i < pom.neighbors.size(); i++) {
                tmp = pom.neighbors.get(i);
                if (!visited[tmp.index]) {
                    visited[tmp.index] = true;
                    q.enqueue(tmp.index);
                    komponenti.add(tmp.index);
                }
            }
        }
        komponenti.sort(Integer::compare);
        Integer[] tmp = new Integer[komponenti.size()];
        tmp = komponenti.toArray(tmp);
        int[] result = new int[tmp.length];
        for (int i = 0; i < tmp.length; ++i)
            result[i] = tmp[i];
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.num_nodes; i++)
            sb.append(String.format("%s\n", adjList[i]));
        return sb.toString();
    }
}


public class KomponentiSvrzanost {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Graph<String> g = new Graph<String>(N);
        int sosedIndex;
        int i_num_nodes;
        String pom;

        for (int i = 0; i < N; i++) {
            i_num_nodes = Integer.parseInt(br.readLine());
            for (int j = 0; j < i_num_nodes; j++) {
                pom = br.readLine();
                sosedIndex = Integer.parseInt(pom);
                g.addEdge(i, sosedIndex);
            }
        }

        int teme = Integer.parseInt(br.readLine());
        int[] komponenti = g.najdikomponenti(teme);
        // vasiot kod tuka
        for (int komponenta : komponenti)
            System.out.println(komponenta);
        br.close();
    }
}
