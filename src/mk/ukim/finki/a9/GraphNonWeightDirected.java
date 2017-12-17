package mk.ukim.finki.a9;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

class GraphNonWeightDirected<T> {
    private int numNodes;
    private GraphNode<T> adjList[];

    @SuppressWarnings("unchecked")
    public GraphNonWeightDirected(int numNodes, T[] list) {
        this.numNodes = numNodes;
        adjList = (GraphNode<T>[]) new GraphNode[numNodes];
        for (int i = 0; i < numNodes; i++)
            adjList[i] = new GraphNode<>(i, list[i]);
    }

    @SuppressWarnings("unchecked")
    public GraphNonWeightDirected(int numNodes) {
        this.numNodes = numNodes;
        adjList = (GraphNode<T>[]) new GraphNode[numNodes];
        for (int i = 0; i < numNodes; i++)
            adjList[i] = new GraphNode<>(i, null);
    }

    public int adjacent(int x, int y) {
        return adjList[x].containsNeighbor(adjList[y]) ? 1 : 0;
    }

    public void addEdge(int x, int y) {
        if (!adjList[x].containsNeighbor(adjList[y]))
            adjList[x].addNeighbor(adjList[y]);
    }

    public void deleteEdge(int x, int y) {
        adjList[x].removeNeighbor(adjList[y]);
    }

    /* ***********************************TOPOLOGICAL SORT****************************************************** */
    private void dfsVisit(Stack<Integer> s, int i, boolean[] visited) {
        if (!visited[i]) {
            visited[i] = true;
            Iterator<GraphNode<T>> it = adjList[i].neighbors.iterator();
            System.out.println("dfsVisit: " + i + " Stack=" + s);
            while (it.hasNext())
                dfsVisit(s, it.next().index, visited);
            s.push(i);
            System.out.println("--dfsVisit: " + i + " Stack=" + s);
        }
    }

    public void topological_sort_dfs() {
        boolean visited[] = new boolean[numNodes];
        for (int i = 0; i < numNodes; i++)
            visited[i] = false;

        Stack<Integer> s = new Stack<>();

        for (int i = 0; i < numNodes; i++)
            dfsVisit(s, i, visited);

        System.out.println("----Stack=" + s);
        while (!s.isEmpty())
            System.out.println(adjList[s.pop()]);
    }

    /* ********************************************************************************************************* */

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.numNodes; i++)
            sb.append(String.format("%d: %s\n", i, adjList[i]));
        return sb.toString();
    }

    private class GraphNode<T> {
        private int index;
        private T info;
        private LinkedList<GraphNode<T>> neighbors;

        public GraphNode(int index, T info) {
            this.index = index;
            this.info = info;
            neighbors = new LinkedList<>();
        }

        public boolean containsNeighbor(GraphNode<T> o) {
            return neighbors.contains(o);
        }

        public void addNeighbor(GraphNode<T> o) {
            neighbors.add(o);
        }

        public void removeNeighbor(GraphNode<T> o) {
            neighbors.remove(o);
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GraphNode<?> graphNode = (GraphNode<?>) o;
            if (index != graphNode.index) return false;
            if (info != null ? !info.equals(graphNode.info) : graphNode.info != null) return false;
            return neighbors != null ? neighbors.equals(graphNode.neighbors) : graphNode.neighbors == null;
        }

        @Override
        public int hashCode() {
            int result = index;
            result = 31 * result + (info != null ? info.hashCode() : 0);
            result = 31 * result + (neighbors != null ? neighbors.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("INFO: %s SOSEDI: ", info));
            for (GraphNode<T> neighbor : neighbors)
                sb.append(String.format("%s ", neighbor.info));
            return sb.toString();
        }
    }
}
