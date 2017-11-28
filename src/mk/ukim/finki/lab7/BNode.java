package mk.ukim.finki.lab7;

class BNode<E> {
    protected static int LEFT = 1;
    protected static int RIGHT = 2;
    protected E info;
    protected BNode<E> left;
    protected BNode<E> right;

    public BNode(E info) {
        this.info = info;
        left = null;
        right = null;
    }

    public BNode() {
        this.info = null;
        left = null;
        right = null;
    }

    public BNode(E info, BNode<E> left, BNode<E> right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BNode<?> bNode = (BNode<?>) o;
        if (info != null ? !info.equals(bNode.info) : bNode.info != null) return false;
        if (left != null ? !left.equals(bNode.left) : bNode.left != null) return false;
        return right != null ? right.equals(bNode.right) : bNode.right == null;
    }

    @Override
    public int hashCode() {
        int result = info != null ? info.hashCode() : 0;
        result = 31 * result + (left != null ? left.hashCode() : 0);
        result = 31 * result + (right != null ? right.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s ", info);
    }
}
