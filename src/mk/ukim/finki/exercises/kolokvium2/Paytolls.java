package mk.ukim.finki.exercises.kolokvium2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Paytolls {
    public static void main(String[] args) throws IOException {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(input.readLine());
            Integer[] list = new Integer[n];
            for (int i = 1; i <= n; ++i)
                list[i - 1] = i;
            GraphWeightedDirected<Integer> graph = new GraphWeightedDirected<>(n, list);
            String[] parts = input.readLine().split("\\s+");
            int fromCityIndex = graph.getIndex(Integer.parseInt(parts[0]));
            int toCityIndex = graph.getIndex(Integer.parseInt(parts[1]));
            int m = Integer.parseInt(input.readLine());
            for (int i = 0; i < m; ++i) {
                parts = input.readLine().split("\\s+");
                int indexCity1 = graph.getIndex(Integer.parseInt(parts[0]));
                int indexCity2 = graph.getIndex(Integer.parseInt(parts[1]));
                float paytolls = Float.parseFloat(parts[2]);
                graph.addEdge(indexCity1, indexCity2, paytolls);
                graph.addEdge(indexCity2, indexCity1, paytolls);
            }
            float[] distances = graph.dijkstra(fromCityIndex);
            System.out.println(String.format("%.0f", distances[toCityIndex]));
        }
    }
}

class GraphWeightedDirected<T> {
    private int numNodes;
    private GraphNode<T> adjList[];

    @SuppressWarnings("unchecked")
    public GraphWeightedDirected(int numNodes, T[] list) {
        this.numNodes = numNodes;
        adjList = (GraphNode<T>[]) new GraphNode[numNodes];
        for (int i = 0; i < numNodes; i++)
            adjList[i] = new GraphNode<>(i, list[i]);
    }

    @SuppressWarnings("unchecked")
    public GraphWeightedDirected(int numNodes) {
        this.numNodes = numNodes;
        adjList = (GraphNode<T>[]) new GraphNode[numNodes];
        for (int i = 0; i < numNodes; i++)
            adjList[i] = new GraphNode<>(i, null);
    }

    public int adjacent(int x, int y) {
        return adjList[x].containsNeighbor(adjList[y]) ? 1 : 0;
    }

    public void addEdge(int x, int y, float tezina) {
        // dodava vrska od jazelot so indeks x do jazelot so indeks y so tezina
        if (adjList[x].containsNeighbor(adjList[y]))
            adjList[x].updateNeighborWeight(adjList[y], tezina);
        else
            adjList[x].addNeighbor(adjList[y], tezina);
    }

    public void deleteEdge(int x, int y) {
        adjList[x].removeNeighbor(adjList[y]);
    }

    public void setInfo(int x, T info) {
        adjList[x].info = info;
    }

    public int getIndex(T info) {
        for (GraphNode<T> tGraphNode : adjList)
            if (tGraphNode.info.equals(info))
                return tGraphNode.index;
        return -1;
    }

    public int getNumNodes() {
        return numNodes;
    }

    public float[] dijkstra(int from) {
        /* Minimalna cena do sekoj od teminjata */
        float distance[] = new float[this.numNodes];
        /* dali za temeto e najdena konecnata (minimalna) cena */
        boolean finalno[] = new boolean[this.numNodes];
        for (int i = 0; i < this.numNodes; i++) {
            distance[i] = -1;
            finalno[i] = false;
        }

        finalno[from] = true;
        distance[from] = 0;
        /*
         * vo sekoj cekor za edno teme se dobiva konecna minimalna cena
         */
        for (int i = 1; i < this.numNodes; ++i) {
            /* za site sledbenici na from presmetaj ja cenata */
            for (GraphNodeNeighbor<T> pom : adjList[from].neighbors) {
                /* ako grankata kon sosedot nema konecna cena */
                if (!finalno[pom.node.index]) {
                    /* ako ne e presmetana cena za temeto */
                    if (distance[pom.node.index] <= 0)
                        distance[pom.node.index] = distance[from] + pom.weight;
                        /* inaku, ako e pronajdena poniska */
                    else if (distance[from] + pom.weight < distance[pom.node.index])
                        distance[pom.node.index] = distance[from] + pom.weight;
                }
            }
            /* najdi teme so min. cena koja ne e konecna */
            boolean minit = false; /* min. ne e inicijaliziran */
            int k = -1;
            float minc = -1;
            /* proveri gi site teminja */
            for (int j = 0; j < this.numNodes; ++j) {
                if (!finalno[j] && distance[j] != -1) { /*ako cenata ne e  konecna*/
                    if (!minit) { /* ako ne e inicijaliziran minimumot */
                        minc = distance[k = j]; /* proglasi go ova e minimum */
                        minit = true; /* oznaci deka min e inicijaliziran */
                    }
                    /* proveri dali e pronajdeno teme so pomala cena */
                    else if (minc > distance[j] && distance[j] > 0)
                        minc = distance[k = j];
                }
            }
            finalno[from = k] = true;
        }

        return distance;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.numNodes; i++)
            sb.append(String.format("%s\n", adjList[i]));
        return sb.toString();
    }

    /* =================== Inner Classes =================== */
    private class GraphNode<T> {
        private int index;
        private T info;
        private LinkedList<GraphNodeNeighbor<T>> neighbors;

        public GraphNode(int index, T info) {
            this.index = index;
            this.info = info;
            neighbors = new LinkedList<>();
        }

        public boolean containsNeighbor(GraphNode<T> o) {
            GraphNodeNeighbor<T> pom = new GraphNodeNeighbor<>(o, 0);
            return neighbors.contains(pom);
        }

        public void addNeighbor(GraphNode<T> o, float weight) {
            GraphNodeNeighbor<T> pom = new GraphNodeNeighbor<>(o, weight);
            neighbors.add(pom);
        }

        public void removeNeighbor(GraphNode<T> o) {
            GraphNodeNeighbor<T> pom = new GraphNodeNeighbor<>(o, 0);
            neighbors.remove(pom);
        }

        public void updateNeighborWeight(GraphNode<T> o, float weight) {
            for (GraphNodeNeighbor<T> pom : neighbors)
                if (pom.node.equals(o))
                    pom.weight = weight;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("INFO: %s SOSEDI: ", info));
            for (GraphNodeNeighbor<T> neighbor : neighbors)
                sb.append(String.format("(%s, %.2f); ", neighbor.node.info, neighbor.weight));
            return sb.toString();
        }
    }

    private class GraphNodeNeighbor<T> {
        private GraphNode<T> node;
        private float weight;

        public GraphNodeNeighbor(GraphNode<T> node, float weight) {
            this.node = node;
            this.weight = weight;
        }

        public GraphNodeNeighbor(GraphNode<T> node) {
            this.node = node;
            this.weight = 0;
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GraphNodeNeighbor<?> that = (GraphNodeNeighbor<?>) o;
            return node != null ? node.equals(that.node) : that.node == null;
        }

        @Override
        public int hashCode() {
            return node != null ? node.hashCode() : 0;
        }
    }
}

class Edge implements Comparable<Edge> {
    private int fromVertex, toVertex;
    private float weight;

    public Edge(int from, int to, float weight) {
        this.fromVertex = from;
        this.toVertex = to;
        this.weight = weight;
    }

    public int getFromVertex() {
        return fromVertex;
    }

    public int getToVertex() {
        return toVertex;
    }

    public float getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Float.compare(this.weight, o.weight);
    }

    @Override
    public String toString() {
        return (fromVertex + 1) + "-->" + (toVertex + 1) + " " + weight;
    }
}
