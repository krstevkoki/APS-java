package mk.ukim.finki.lab9;

class BNode<T> {
    protected static int LEFT = 1;
    protected static int RIGHT = 2;
    protected T info;
    protected BNode<T> left;
    protected BNode<T> right;
    protected char ltag;
    protected char rtag;

    public BNode(T info) {
        this.info = info;
        left = null;
        right = null;
        ltag = '-';
        rtag = '-';
    }

    public BNode() {
        this.info = null;
        left = null;
        right = null;
        ltag = '-';
        rtag = '-';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BNode<?> bNode = (BNode<?>) o;
        if (ltag != bNode.ltag) return false;
        if (rtag != bNode.rtag) return false;
        if (info != null ? !info.equals(bNode.info) : bNode.info != null) return false;
        if (left != null ? !left.equals(bNode.left) : bNode.left != null) return false;
        return right != null ? right.equals(bNode.right) : bNode.right == null;
    }

    @Override
    public int hashCode() {
        int result = info != null ? info.hashCode() : 0;
        result = 31 * result + (left != null ? left.hashCode() : 0);
        result = 31 * result + (right != null ? right.hashCode() : 0);
        result = 31 * result + (int) ltag;
        result = 31 * result + (int) rtag;
        return result;
    }

    @Override
    public String toString() {
        return info.toString();
    }
}
