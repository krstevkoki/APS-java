package mk.ukim.finki.lab9;

public class BTree<T> {
    private BNode<T> head;

    public BTree() {
        head = new BNode<>(null);
        // po definicija ako nema koren, t.e. ako stebloto e prazno
        head.left = head;
        head.ltag = '-';
        // kaj vodacot sekogas desnata vrska pokazuva kon samiot sebe
        head.right = head;
        head.rtag = '+';
    }

    public BNode<T> makeRoot(T elem) {
        BNode<T> tmp = new BNode<>(elem);
        head.left = tmp;
        head.ltag = '+';

        tmp.left = head;
        tmp.ltag = '-';
        tmp.right = head;
        tmp.rtag = '-';

        return tmp;
    }

    public BNode<T> addChild(BNode<T> node, int where, T elem) {
        BNode<T> tmp = new BNode<>(elem);

        if (where == BNode.LEFT) {

            if (node.ltag == '+')   // veke postoi element
                return null;

            tmp.left = node.left;
            tmp.ltag = '-';
            tmp.right = node;
            tmp.rtag = '-';
            node.left = tmp;
            node.ltag = '+';
        } else {
            if (node.rtag == '+')   // veke postoi element
                return null;

            tmp.right = node.right;
            tmp.rtag = '-';
            tmp.left = node;
            tmp.ltag = '-';
            node.right = tmp;
            node.rtag = '+';
        }
        return tmp;
    }

    public BNode<T> successorInorder(BNode<T> node) {
        if (node.rtag == '-')
            return node.right;
        BNode<T> p = node.right;
        while (p.ltag == '+')
            p = p.left;
        return p;
    }

    public String inorderNonRecursive() {
        if (head.ltag == '-')      // drvoto e prazno
            return "";
        StringBuilder sb = new StringBuilder();
        BNode<T> p = head.left;
        while (p.ltag == '+')
            p = p.left;
        while (p != head) {
            //System.out.print(p.info.toString() + " ");
            sb.append(String.format("%s ", p));
            p = successorInorder(p);
        }
        return sb.toString();
    }
}
