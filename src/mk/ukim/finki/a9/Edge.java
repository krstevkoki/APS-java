package mk.ukim.finki.a9;

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
}
