package mk.ukim.finki.a9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class GraphWeightedDirected<T> {
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

    /* ************************** KRUSKAL ********************************************************************** */
    // Metoda koja generira niza od site rebra vo grafot
    private Edge[] getAllEdges() {
        int totalEdges = 0;
        for (int i = 0; i < this.numNodes; i++)
            // za sekoe teme go dodavame brojot na sosedi koi gi ima
            totalEdges += this.adjList[i].neighbors.size();

        Edge[] edges = new Edge[totalEdges];
        for (int i = 0, index = 0; i < this.numNodes; ++i) {
            for (int j = 0; j < this.adjList[i].neighbors.size(); ++j) {
                int index1 = this.adjList[i].index;
                // se zemaat indeksot i tezinata na j-ot sosed na temeto i
                int index2 = this.adjList[i].neighbors.get(j).node.index;
                float weight = this.adjList[i].neighbors.get(j).weight;
                edges[index++] = new Edge(index1, index2, weight);
            }
        }

        return edges;
    }

    // Metoda koja gi sortira site rebra
    private Edge[] sortEdges(Edge[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = i + 1; j < edges.length; j++) {
                if (edges[i].getWeight() > edges[j].getWeight()) {
                    Edge tmp = edges[i];
                    edges[i] = edges[j];
                    edges[j] = tmp;
                }
            }
        }
        return edges;
    }

    // Metoda koja pravi unija na dve drva
    private int[] union(int indexFromVertex, int indexToVertex, int[] vrski) {
        int findWhat, replaceWith;

        if (indexFromVertex < indexToVertex) {
            findWhat = vrski[indexToVertex];
            replaceWith = vrski[indexFromVertex];
        } else {
            findWhat = vrski[indexFromVertex];
            replaceWith = vrski[indexToVertex];
        }
        // za dvete poddrva stava ist index
        // t.e. gi spojuva
        for (int i = 0; i < vrski.length; ++i)
            if (vrski[i] == findWhat)
                vrski[i] = replaceWith;

        return vrski;
    }

    public List<Edge> kruskal() {
        /*
         * Pomoshna niza za sledenje na kreiranite drva. Ako dve teminja se del
         * od isto poddrvo togash imaat ista vrednost vo nizata
         */
        int vrski[] = new int[this.numNodes];

        // niza koja gi sodrzi site rebra
        Edge[] edges = getAllEdges();
        // se sortiraat rebrata spored tezinite vo neopagjacki redosled
        // edges = this.sortEdges(edges);
        Arrays.sort(edges);

        // inicijalizacija na pomosnata niza za evidencija,
        // sekoj jazel si e posebno drvo
        for (int i = 0; i < this.numNodes; i++)
            vrski[i] = i;

        // lista koja kje gi sodrzi MST rebra
        List<Edge> mstEdges = new ArrayList<>();

        for (Edge e : edges) {
            // za sekoe rebro vo sortiran redosled
            if (vrski[e.getFromVertex()] != vrski[e.getToVertex()]) {
                // ako teminjata na ova rebro ne se vo isto poddrvo
                // ova rebro e MST rebro
                mstEdges.add(e);
                // sega dvete teminja treba da se vo isto poddrvo
                // t.e se pravi merge (unija) taka sto vo nizata vrski
                // za site elementi od dvete poddrva
                // go setira istiot (najmal) element od dvete poddrva
                vrski = union(e.getFromVertex(), e.getToVertex(), vrski);
            }
            // ako sme dodale numNodes - 1 {|V| - 1} rebra moze da prestaneme
            if (mstEdges.size() == (this.numNodes - 1))
                break;
        }

        return mstEdges;
    }

    /* ***************************************************************************************************** */

    /* *********************** PRIM ************************************************************************* */
    // Metoda koja go naogja najmaloto rebro do teme na neposeten sosed
    private Edge getMinimalEdge(boolean[] included) {
        int index1 = Integer.MAX_VALUE, index2 = Integer.MAX_VALUE;
        float minWeight = Float.MAX_VALUE;

        for (int i = 0; i < this.numNodes; ++i) {
            if (included[i]) {
                // ako e vkluceno temeto i
                // izmini gi negovite nevkluceni sosedi
                for (GraphNodeNeighbor<T> pom : adjList[i].neighbors) {
                    // ako sosedot ne e poseten i ima do sega najmala tezina
                    if (!included[pom.node.index] && pom.weight < minWeight) {
                        index1 = i;  // indexFromVertex
                        index2 = pom.node.index;  // indexToVertex
                        minWeight = pom.weight;
                    }
                }
            }
        }

        if (minWeight < Float.MAX_VALUE)
            return new Edge(index1, index2, minWeight);
        return null;
    }

    public List<Edge> prim(int start_index) {
        // lista koja kje gi sodrzi MST rebra
        List<Edge> mstEdges = new ArrayList<>();

        boolean included[] = new boolean[this.numNodes];
        for (int i = 0; i < this.numNodes; i++)
            included[i] = false;

        included[start_index] = true;

        for (int i = 0; i < this.numNodes - 1; i++) {  // |V| - 1
            Edge e = this.getMinimalEdge(included);
            if (e == null) {
                System.out.println("Ne mozat da se povrzat site jazli");
                break;
            }
            included[e.getFromVertex()] = included[e.getToVertex()] = true;
            mstEdges.add(e);
        }

        return mstEdges;
    }

    /* ***************************************************************************************************** */

    /* **************** DIJKSTRA ***************************************************************************** */
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
    /* ***************************************************************************************************** */

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.numNodes; i++)
            sb.append(String.format("%s\n", adjList[i]));
        return sb.toString();
    }

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

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("INFO: %s SOSEDI: ", info));
            for (GraphNodeNeighbor<T> neighbor : neighbors)
                sb.append(String.format("(%s, %.2f); ", neighbor.node.info, neighbor.weight));
            return sb.toString();
        }

        public void updateNeighborWeight(GraphNode<T> o, float weight) {
            for (GraphNodeNeighbor<T> pom : neighbors)
                if (pom.node.equals(o))
                    pom.weight = weight;
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
